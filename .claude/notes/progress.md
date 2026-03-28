# CraftD — Notas de Progresso (Claude)

> Pasta criada por Claude Code para registrar decisões, análises e o que foi feito em cada sessão.

---

## Sessão 1 — 2026-03-28

### Objetivo
Analisar o módulo `android_kmp/craftd-core`, mapear cobertura de testes e criar infraestrutura para geração automática de testes via GitHub Actions + Claude API.

---

### 1. Análise de cobertura — `android_kmp/craftd-core`

#### Arquivos em `commonMain`

| Arquivo | Pacote | Tem teste? |
|---------|--------|------------|
| `SimpleProperties.kt` | `data.model.base` | ❌ |
| `SimplePropertiesResponse.kt` | `data.model.base` | ❌ |
| `ActionProperties.kt` | `data.model.action` | ❌ |
| `AnalyticsProperties.kt` | `data.model.action` | ❌ |
| `ButtonProperties.kt` | `data.model.button` | ❌ |
| `TextProperties.kt` | `data.model.text` | ❌ |
| `CheckBoxProperties.kt` | `data.model.checkbox` | ❌ |
| `StyleProperties.kt` | `data.model.checkbox` | ❌ |
| `CraftDAlign.kt` | `domain` | ❌ |
| `CraftDTextStyle.kt` | `domain` | ❌ |
| `CraftDComponentKey.kt` | `presentation` | ❌ |
| `CraftDViewListener.kt` | `presentation` | ❌ |
| `ViewMapper.kt` | `data` | ❌ |

#### Arquivos em `androidMain`

| Arquivo | Pacote | Tem teste? |
|---------|--------|------------|
| `CraftDSimplePropertiesDiffCallback.kt` | (root) | ❌ |
| `ViewMapperVo.kt` | `data` | ❌ |
| `ContextExtesion.kt` | `extensions` | ❌ |

**Cobertura atual: 0% — nenhum teste existe.**

#### Prioridade de implementação
1. **`ViewMapper.kt`** — lógica real com `try/catch` silencioso em `convertToElement`
2. **`CraftDSimplePropertiesDiffCallback.kt`** — lógica de diff com `AbstractMap`
3. **`SimplePropertiesResponse.kt`** — mapper `toSimpleProperties` / `toListSimpleProperties`
4. **`CraftDComponentKey.kt`** — constante `CRAFT_D` + valores do enum
5. Data classes restantes — construção, defaults, `copy()`, `equals`

---

### 2. Decisões de arquitetura

#### Workflow separado vs step no `pr.yml`
- **Decisão:** workflow separado (`.github/workflows/generate-tests.yml`)
- **Motivo:** falha na API do Claude não bloqueia CI; responsabilidades separadas; pode ser desabilitado independentemente
- Dispara via `workflow_run` após o `pr.yml` passar com sucesso

#### Padrão de testes
- Sem referência existente no projeto → **JUnit4 + MockK**
- Nomenclatura: `NomeDoArquivoTest.kt`
- Método style: backtick notation `` `given X when Y then Z`() ``
- Path: `src/test/java/...` espelhando o pacote do arquivo original

---

### 3. Arquivos criados nesta sessão

| Arquivo | Descrição |
|---------|-----------|
| `.github/scripts/generate_tests.py` | Script Python que chama `claude-opus-4-6` para gerar testes |
| `.github/workflows/generate-tests.yml` | Workflow que detecta arquivos alterados e abre PR com testes |

#### Fluxo do workflow
```
PR aberto
  → pr.yml (build + test) passa
    → generate-tests.yml dispara
      → detecta .kt alterados em craftd-core (exceto *Test.kt)
        → para cada arquivo sem teste → chama Claude API
          → escreve arquivos em src/test/java/...
            → cria branch: chore/add-tests-craftd-core-pr-{N}
              → abre PR automático com os testes gerados
```

#### Branch e commit padrão
- Branch: `chore/add-tests-craftd-core-pr-{número do PR}`
- Commit: `test: add unit tests for craftd-core (auto-generated via Claude)`
- PR title: `[Auto] Add unit tests for craftd-core`

---

### 4. Pendências identificadas

- [ ] Adicionar dependências de teste no `android_kmp/craftd-core/build.gradle.kts`:
  ```kotlin
  commonTest.dependencies {
      implementation(kotlin("test"))
  }
  androidUnitTest.dependencies {
      implementation(libs.junit)
      implementation(libs.mockk)
  }
  ```
- [ ] Adicionar secret `ANTHROPIC_API_KEY` em _Settings → Secrets → Actions_ do repositório
- [ ] Gerar os testes manualmente para os 16 arquivos sem cobertura (aguardando confirmação do usuário)
- [ ] Validar que o workflow consegue resolver `github.event.workflow_run.pull_requests[0]` (pode precisar de ajuste dependendo do tipo de PR)

---

### 5. Dependências do projeto (`craftd-core`)

```kotlin
// commonMain
api(libs.kotlinx.serialization.json)
implementation(libs.kotlinx.coroutines.core)
implementation(compose.runtime)
implementation(compose.foundation)
implementation(compose.material3)

// androidMain
implementation(libs.androidx.core)
implementation(libs.androidx.appcompat)
implementation(libs.google.material)
implementation(libs.fasterxml.jackson)
implementation(libs.fasterxml.jackson.databind)
```

---

*Atualizado em: 2026-03-28*