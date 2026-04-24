# CraftD — Claude Instructions

## O que é o projeto

CraftD é uma biblioteca de **Server Driven UI** multiplatforma. O servidor decide quais componentes renderizar e como; o cliente (app) apenas executa. Suporta:

- Android Compose (`android_kmp/craftd-compose`)
- Android View System / XML (`android_kmp/craftd-xml`)
- KMP Compose Multiplatform (`android_kmp/craftd-compose` via commonMain)
- iOS SwiftUI (`ios/craftd-swiftui`)
- Flutter (`flutter/craftd_widget`)

---

## Estrutura de módulos

```
android_kmp/
  craftd-core/          # modelos e abstrações compartilhadas (KMP)
    commonMain/         # código compartilhado entre plataformas
    androidMain/        # implementações Android-específicas
  craftd-compose/       # implementação Compose / KMP
  craftd-xml/           # implementação View System (XML)
  app-sample-android/   # app de exemplo Android
  app-sample-cmp/       # app de exemplo KMP Compose
  build-logic/          # configuração de build compartilhada

ios/craftd-swiftui/     # biblioteca iOS (Swift Package + CocoaPods)
flutter/craftd_widget/  # biblioteca Flutter (pub.dev)
docs/                   # documentação do site (MkDocs)
```

---

## Regras arquiteturais — nunca violar

1. **Módulos de plataforma não dependem uns dos outros.** `craftd-compose`, `craftd-xml`, `ios/` e `flutter/` dependem apenas do `craftd-core`. Jamais entre si.

2. **Todo novo componente implementa a abstração do platform.** No Android/KMP é `CraftDBuilder`. No iOS é o equivalente Swift. No Flutter é o equivalente Dart. Nunca criar componente avulso fora dessa abstração.

3. **`onAction` / fallback sempre coberto.** Toda implementação de componente deve tratar o callback de ação (`ActionProperties`), mesmo que seja um no-op.

4. **`commonMain` é sagrado.** Código em `commonMain` não pode ter dependências de plataforma. Se precisar de comportamento diferente por plataforma, usa `expect/actual`.

5. **Novos componentes seguem o padrão existente.** Consultar `CraftDButtonBuilder` e `CraftDTextBuilder` como referência de implementação antes de criar algo novo.

6. **Nomes de classes são consistentes entre todas as plataformas.** Ex: se um componente se chama `CraftDImage` no Android, deve se chamar `CraftDImage` em iOS e Flutter também.

7. **Todo novo componente deve ter teste unitário** (quando possível) e **documentação em `docs/` na seção da respectiva plataforma**.

8. **Toda mudança que afeta comportamento público da lib** (novo componente, novo parâmetro, breaking change) deve atualizar `docs/how-to-use/` da plataforma correspondente: `compose.md`, `view-system.md`, `swift-ui.md` ou `futter.md`.

9. **Todo novo builder deve ser registrado no `CraftDBuilderManager`** da respectiva plataforma. No Compose/KMP: `CraftDComponentKey.X.key to CraftDXBuilder()`. No XML: adicionar o render em `getBuilderRenders()`. Nunca criar um builder sem registrá-lo.

10. **Dependências de bibliotecas externas devem ser abstraídas.** Nunca acoplar diretamente uma lib de terceiro (ex: Coil, Picasso, Glide) dentro do builder. Expor uma interface/função como parâmetro do construtor para que o consumidor injete a implementação.

---

## Contexto por plataforma

Antes de iniciar qualquer task, identifique a plataforma e leia o arquivo correspondente em `.claude/instructions/`:

- Android/KMP → `.claude/instructions/android-patterns.md`
- iOS → `.claude/instructions/ios-patterns.md`
- Flutter → `.claude/instructions/flutter-patterns.md`

Ao gerar um `proposal.md` via `/propose`, detecte a plataforma na descrição do usuário e adicione frontmatter no início do arquivo:

```
---
platform: android   # mencionou android / compose / xml / kmp
platform: ios       # mencionou ios / swiftui / swift
platform: flutter   # mencionou flutter / dart
platform: all       # multiplatforma ou não ficou claro
---
```

Ao iniciar `/apply`, leia o campo `platform:` do `proposal.md` da change e carregue o arquivo de instructions correspondente antes de qualquer outra leitura.

---

## Convenções de código

- **Kotlin:** segue as convenções oficiais do Kotlin. Prefere `data class` para modelos.
- **Nomenclatura de componentes:** prefixo `CraftD` em tudo que é parte da lib (ex: `CraftDButton`, `CraftDButtonBuilder`).
- **Testes:** JUnit4 + MockK. Nomenclatura em backtick: `` `given X when Y then Z` ``. Path espelha o source: `src/test/java/...`
- **Commits:** mensagens em inglês, semânticas (`feat:`, `fix:`, `test:`, `chore:`, `docs:`).

---

## Implementação de tasks

Ao concluir cada task de um `tasks.md`:
1. Implemente o código da task
2. Rode `./gradlew build` no módulo afetado (`android_kmp/`)
3. Corrija erros de compilação se houver
4. Só então marque `[x]` no `tasks.md`

Nunca marcar `[x]` antes do build passar.

### Orquestração com agents para componentes Android/KMP

Ao aplicar uma change que adiciona um novo componente Android/KMP (detectável pela estrutura das tasks: core → compose/xml → docs/sample), usar agents paralelos com worktrees isoladas seguindo estas rodadas:

**Rodada 1** — sequencial (core é dependência das demais):
- Agent Core → tasks de `craftd-core` (model, enum, key)

**Rodada 2** — paralelo (após Rodada 1 mergeada):
- Agent Compose → tasks de `craftd-compose` (composable, builder, registro, testes)
- Agent XML → tasks de `craftd-xml` (render, registro)

**Rodada 3** — sequencial (após Rodada 2):
- Agent Docs/Sample → tasks de documentação e sample app

**Rodada 4** — revisor (após Rodada 3):
- Agent Revisor → revisa todo o código produzido seguindo as regras de review do CLAUDE.md. Não faz build — cada agent já validou o seu.

Cada agent roda em worktree isolada (`isolation: "worktree"`) e valida o build antes de marcar `[x]`.

### Custo de contexto — diretrizes para agents

**Escopo de plataforma — ignorar pastas irrelevantes:**
- Tasks Android/KMP → ignorar `ios/` e `flutter/`
- Tasks iOS → ignorar `android_kmp/` e `flutter/`
- Tasks Flutter → ignorar `android_kmp/` e `ios/`

Nunca ler, listar ou referenciar arquivos fora da plataforma da task em execução.

**Quando NÃO usar agent (fazer inline):**
- Rodada 3 (Docs/Sample) e Rodada 4 (Revisor) — edições simples, o overhead do agent supera o benefício
- Qualquer task com menos de 10 arquivos a editar e sem necessidade de build isolado

**Quando usar agent com worktree:**
- Rodadas 1 e 2 — compilação isolada necessária, risco de conflito entre módulos paralelos

**Como montar o prompt de um agent:**
- Passar os caminhos exatos dos arquivos relevantes
- Incluir o trecho de código de referência (ex: o builder existente que deve ser replicado)
- Nunca escrever "leia o projeto e implemente" — especificar o quê e onde

**Modelo por tipo de tarefa:**
- Edições mecânicas (JSON, doc, registro simples): usar `model: "haiku"`
- Lógica, compilação e decisões arquiteturais: Sonnet (default)

Após cada mudança de estado relevante (agent iniciado, concluído ou com erro), exibir tabela de progresso:

| Agent | Status | Tasks |
|---|---|---|
| Agent Core | ✓ Completo | 1.x |
| Agent Compose | ⟳ Rodando | 2.x |
| Agent XML | ⏳ Aguardando | 3.x |

Ícones: `⟳` rodando, `✓` completo, `⏳` aguardando, `✗` erro.

Durante a execução, reportar progresso no formato:

```
[Agent Core]     ✓ 1.1 IMAGE_COMPONENT adicionado
[Agent Core]     ✓ 1.2 CraftDContentScale criado
[Agent Compose]  ⟳ 2.2 Criando CraftDImage composable...
[Agent XML]      ⟳ 3.1 Criando CraftDImageComponent...
[Agent Compose]  ✓ 2.2 CraftDImage composable criado
```

Usar `⟳` para em progresso e `✓` para concluído. Reportar a cada task iniciada e concluída.

---

## CI / automação

- **`pr.yml`** — build e testes, dispara em todo PR
- **`generate-tests.yml`** — gera testes unitários automaticamente via Claude API para `.kt` modificados, abre PR separado. Só roda após `pr.yml` passar. Não roda em PRs de bots.

## Review de PRs

Quando um PR for publicado ou quando solicitado, revisar usando `gh pr review` verificando:

1. **Regras arquiteturais** — componente implementa `CraftDBuilder` (ou equivalente), sem dependência entre módulos de plataforma, `commonMain` sem deps de plataforma
2. **Completude** — builder registrado no `CraftDBuilderManager`, `onAction`/fallback coberto, `ImageProperties` (ou equivalente) no `craftd-core`
3. **Testes** — testes unitários presentes e com nomenclatura correta em backtick
4. **Docs** — `docs/how-to-use/` atualizado para a plataforma afetada
5. **Padrão de código** — prefixo `CraftD`, `Modifier` exposto, sem valores hardcoded de cor/tipografia

Usar `gh pr review <número> --comment -b "<comentário>"` para comentários gerais e `gh api` para comentários em linha específicos.

---

## O que não fazer

- Não criar componente fora da abstração `CraftDBuilder` (ou equivalente de plataforma)
- Não adicionar dependência de plataforma em `commonMain`
- Não criar dependência entre módulos de plataforma (`craftd-compose` → `craftd-xml`, por exemplo)
- Não commitar `local.properties` nem arquivos de credenciais
- Não usar `--no-verify` para burlar hooks de CI
