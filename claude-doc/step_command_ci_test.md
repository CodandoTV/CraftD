# Auto Test Generation with Claude API — Step by Step

Guia completo para replicar o fluxo de geração automática de testes unitários
usando Claude API + GitHub Actions em qualquer repositório Android/KMP.

---

## Visão geral do fluxo

```
PR aberto
  → CI passa (pr.yml)
    → Workflow dispara (generate-tests.yml)
      → Escaneia arquivos sem cobertura
        → Chama Claude API para cada arquivo
          → Gera arquivos *Test.kt
            → Cria branch + abre PR automático com os testes
```

---

## Fase 1 — Análise do módulo

Antes de configurar qualquer coisa, entenda o que existe no módulo alvo.

**O que fazer:**
- Mapear todos os arquivos `.kt` de produção (`commonMain`, `androidMain`)
- Verificar se já existem testes em `src/test/java/`
- Identificar quais arquivos têm ou não cobertura
- Anotar o padrão de pacote (ex: `com.github.codandotv.craftd.androidcore`)

**Comando útil para listar arquivos sem teste:**
```bash
while IFS= read -r SRC; do
  TEST=$(echo "$SRC" \
    | sed 's|src/commonMain/kotlin/|src/test/java/|' \
    | sed 's|src/androidMain/kotlin/|src/test/java/|' \
    | sed 's|\.kt$|Test.kt|')
  if [ ! -f "$TEST" ]; then
    echo "SEM TESTE: $SRC"
  fi
done < <(find <seu-modulo>/src \
  \( -path "*/commonMain/kotlin/*.kt" -o -path "*/androidMain/kotlin/*.kt" \) \
  | grep -v "Test\.kt" | sort)
```

---

## Fase 2 — Criar o script Python

Crie o arquivo `.github/scripts/generate_tests.py`.

**Responsabilidades do script:**
- Receber a lista de arquivos via `CHANGED_FILES` (env var) ou args
- Para cada arquivo, verificar se o teste já existe — se sim, pular
- Converter o path fonte → path de teste:
  - `src/commonMain/kotlin/Foo.kt` → `src/test/java/FooTest.kt`
  - `src/androidMain/kotlin/Bar.kt` → `src/test/java/BarTest.kt`
- Chamar a Claude API com o conteúdo do arquivo
- Escrever o arquivo `*Test.kt` gerado no path correto
- Exportar outputs para o GitHub Actions (`generated_count`, `covered_names`)

**Modelo recomendado:** `claude-haiku-4-5-20251001`
> Muito mais barato que Opus (~10x) e suficiente para gerar testes de data classes,
> extension functions, DiffUtil callbacks e enums.

**Custo estimado:** ~$0.09 para 17 arquivos com Haiku.

**Prompt que funcionou bem:**
```
Você é um expert em Kotlin/KMP. Gere testes JUnit4 + MockK para o arquivo abaixo.
- Pacote: {package_name}
- Classe de teste: {ClassName}Test
- Nomenclatura: backtick notation `given X when Y then Z`
- Data classes: construção, defaults, copy(), equals/hashCode
- Extension functions com JsonElement: null, JSON válido, JSON inválido
- DiffUtil: areItemsTheSame e areContentsTheSame (incluindo AbstractMap)
- Enums: verificar todos os valores via enumValueOf
- Retorne APENAS o arquivo .kt, sem markdown, sem explicação.
```

---

## Fase 3 — Criar o workflow

Crie o arquivo `.github/workflows/generate-tests.yml`.

**Triggers necessários:**
```yaml
on:
  workflow_dispatch:          # execução manual pelo Actions UI
    inputs:
      override_files:
        description: 'Opcional: arquivos .kt específicos. Vazio = escaneia tudo.'
        required: false
        default: ''

  workflow_run:               # automático após o CI passar
    workflows: ["Nome exato do seu workflow de CI"]
    types: [completed]
```

> ⚠️ `workflow_run` só funciona se o arquivo do workflow estiver na branch **default (main)**.
> Enquanto o PR não for mergeado, o trigger automático não dispara.

> ⚠️ **O `generate-tests.yml` só dispara se o `pr.yml` passar com sucesso.**
> PRs que tocam apenas docs, README ou outros módulos não-Android podem fazer o
> `pr.yml` falhar — nesse caso o `generate-tests.yml` nunca é acionado.
> Para ver o fluxo completo, abra um PR tocando código dentro do módulo Android/KMP
> e confirme que o CI passa antes de esperar o generate rodar.

**Permissões necessárias:**
```yaml
permissions:
  contents: write
  pull-requests: write
```

**Condição do job:**
```yaml
if: |
  github.event_name == 'workflow_dispatch' ||
  (
    github.event.workflow_run.conclusion == 'success' &&
    github.event.workflow_run.actor.login != 'github-actions[bot]'
  )
```
> O check do bot evita loop infinito quando o próprio workflow abre um PR.

**Steps do job (em ordem):**

| Step | O que faz |
|------|-----------|
| Resolve trigger context | Normaliza `head_sha` e `pr_number` para os dois gatilhos |
| Checkout | Usa `GH_PAT` (não `GITHUB_TOKEN`) para permitir push |
| Set up Python | Versão 3.11 |
| Install dependencies | `pip install anthropic` |
| Find uncovered files | `find` nos diretórios `commonMain` e `androidMain` |
| Generate tests | Chama `generate_tests.py` com `CHANGED_FILES` |
| Check generated files | Usa `find` (não `git status`) para contar `*Test.kt` |
| Commit tests | `git add --force` + push para nova branch |
| Open PR | Usa `GH_TOKEN: ${{ secrets.GH_PAT }}` |

---

## Fase 4 — Credenciais necessárias (leia com atenção)

Esta fase é crítica. Sem as duas credenciais abaixo o workflow vai rodar e falhar
silenciosamente ou com erro de autenticação. Configure **antes** de testar.

Configure em: **Settings → Secrets and variables → Actions → New repository secret**

---

### 🔑 `ANTHROPIC_API_KEY` — Chave da API do Claude

**O que é:** A chave que autentica as chamadas à Claude API. Sem ela o script
não consegue chamar o modelo e nenhum teste é gerado.

**Como obter:**
1. Acesse [console.anthropic.com](https://console.anthropic.com)
2. Vá em **API Keys → Create Key**
3. Copie o valor (começa com `sk-ant-...`) — ele só aparece uma vez

**Atenção ao saldo:**
- A API é **paga por uso** — não é gratuita
- Você precisa adicionar créditos em **Plans & Billing** antes de usar
- Créditos **não expiram** — ficam disponíveis até serem consumidos
- Com **$5** você tem de sobra para começar

**Custo estimado por execução:**

| Modelo | ~17 arquivos | ~50 arquivos |
|--------|-------------|-------------|
| `claude-haiku-4-5-20251001` | ~$0.09 | ~$0.25 |
| `claude-opus-4-6` | ~$1.50 | ~$4.50 |

> Use **Haiku** para geração de testes — é ~10x mais barato e suficiente para
> data classes, extension functions, DiffUtil e enums.

**Erro comum:** key correta mas conta sem saldo →
```
Your credit balance is too low to access the Anthropic API.
```
Solução: console.anthropic.com → Plans & Billing → Add credits.

---

### 🔑 `GH_PAT` — Personal Access Token do GitHub

**O que é:** Um token pessoal do GitHub que permite ao workflow abrir PRs
em nome de um usuário real. Substitui o `GITHUB_TOKEN` padrão do Actions.

**Por que não usar `GITHUB_TOKEN`:**
O `GITHUB_TOKEN` gerado automaticamente pelo Actions tem uma restrição de segurança
intencional do GitHub — ele **não pode abrir PRs** que disparariam outros workflows
(para evitar loops infinitos). O PAT pessoal não tem essa limitação.

**Como criar:**
1. Acesse seu perfil no GitHub → **Settings**
2. **Developer settings → Personal access tokens → Tokens (classic)**
3. **Generate new token (classic)**
4. Marque o escopo: `repo` (acesso completo)
5. Copie o token (começa com `ghp_...`)

**Boas práticas:**
- Dê um nome descritivo ao token (ex: `craftd-actions-bot`)
- Defina uma data de expiração (90 dias é um bom equilíbrio)
- Guarde o token em local seguro — o GitHub não mostra novamente

**Erro comum:** usar `GITHUB_TOKEN` no lugar do PAT →
```
refusing to allow a GitHub Actions Bot to create or approve pull requests
```
Solução: garantir que o step de `gh pr create` use `GH_TOKEN: ${{ secrets.GH_PAT }}`.

---

## Fase 5 — Armadilhas encontradas (e como evitar)

### ❌ `git status --short` não detecta os arquivos gerados
**Causa:** O diretório `src/test/java/` não existia no repo. O git lista diretórios
novos como um único item untracked, sem expandir os arquivos dentro.

**Solução:** Usar `find` para contar e listar os arquivos gerados:
```bash
COUNT=$(find seu-modulo/src/test -name "*Test.kt" 2>/dev/null | wc -l | tr -d ' ')
```

---

### ❌ `git add` não staged os arquivos de teste
**Causa:** Diretório novo não rastreado pelo git.

**Solução:** Usar `git add --force`:
```bash
git add --force seu-modulo/src/test/
```

---

### ❌ `workflow_run` usa a versão do workflow da `main`, não do PR
**Causa:** Por design do GitHub Actions — `workflow_run` sempre lê o arquivo
do workflow da branch default.

**Solução:** Mergear o PR do workflow para a `main` antes de testar o fluxo automático.
Para testar antes do merge, use `workflow_dispatch` manualmente.

---

### ❌ Actions não consegue criar PR (permission error)
**Causa:** `GITHUB_TOKEN` não tem permissão para abrir PRs que disparam workflows.

**Solução:** Criar um PAT pessoal com escopo `repo`, salvar como secret `GH_PAT`
e usar `GH_TOKEN: ${{ secrets.GH_PAT }}` no step de criação de PR.

---

### ❌ Saldo insuficiente na Anthropic API
**Causa:** A API key estava correta mas a conta não tinha créditos.

**Solução:** Adicionar créditos em console.anthropic.com → Plans & Billing.
Com Haiku, $5 cobrem ~50 execuções completas do módulo.

---

## Fase 6 — Como testar antes do merge

**Opção 1 — Rodar o script localmente:**
```bash
source ~/.bash_profile   # ou export ANTHROPIC_API_KEY=sk-ant-...

CHANGED_FILES=$(
  while IFS= read -r SRC; do
    TEST=$(echo "$SRC" \
      | sed 's|src/commonMain/kotlin/|src/test/java/|' \
      | sed 's|src/androidMain/kotlin/|src/test/java/|' \
      | sed 's|\.kt$|Test.kt|')
    [ ! -f "$TEST" ] && echo "$SRC"
  done < <(find <seu-modulo>/src \
    \( -path "*/commonMain/kotlin/*.kt" -o -path "*/androidMain/kotlin/*.kt" \) \
    | grep -v "Test\.kt" | sort)
) python3 .github/scripts/generate_tests.py
```

**Opção 2 — Após mergear, usar `workflow_dispatch`:**
```
GitHub → Actions → Auto Generate Cover+Test → Run workflow
```
Deixe o campo vazio para escanear tudo, ou informe arquivos específicos.

---

## Fase 7 — Verificar se funcionou

Após o workflow rodar, verifique:

1. **Step "Find uncovered Kotlin files"** — lista os arquivos detectados
2. **Step "Generate unit tests with Claude API"** — cada arquivo deve mostrar `[OK] Written: ...`
3. **Step "Check generated files"** — deve mostrar `Found N test file(s)`
4. **Step "Open Pull Request"** — URL do PR gerado aparece no log
5. **PR aberto automaticamente** com os testes em `src/test/java/...`

---

## Estrutura de arquivos criados

```
.github/
├── scripts/
│   └── generate_tests.py          # Script Python que chama a Claude API
└── workflows/
    ├── pr.yml                     # CI existente (build + test)
    └── generate-tests.yml         # Workflow de geração automática de testes

<seu-modulo>/
└── src/
    └── test/
        └── java/
            └── com/...            # Testes gerados espelhando o pacote do fonte
```

---

## Checklist rápido para um novo repo

- [ ] Identificar o módulo alvo e mapear arquivos sem cobertura
- [ ] Criar `.github/scripts/generate_tests.py`
- [ ] Criar `.github/workflows/generate-tests.yml` apontando para o nome correto do CI
- [ ] Adicionar secret `ANTHROPIC_API_KEY` (console.anthropic.com)
- [ ] Adicionar secret `GH_PAT` (PAT com escopo `repo`)
- [ ] Abrir PR com os arquivos do workflow e mergear para `main`
- [ ] Abrir qualquer PR tocando o módulo alvo e acompanhar o Actions
 
