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

11. **Todo novo componente deve ser demonstrado no app de exemplo da respectiva plataforma.** Para Android Compose: `app-sample-android/` (registrar o builder no `CraftDBuilderManager` do sample e incluir o componente no JSON de mock/tela de exemplo). Para XML: idem na Activity XML do sample. Nunca criar um componente sem adicionar um exemplo funcional no sample correspondente.

---

## Abstrações principais por plataforma

As três plataformas espelham os mesmos conceitos com nomes equivalentes.

### Android / KMP (Kotlin)

| Classe | Papel |
|---|---|
| `CraftDBuilder` | Interface base para criar componentes |
| `CraftDBuilderManager` | Registra e resolve builders pelo `key` |
| `CraftDynamic` | Composable principal que renderiza o SDUI |
| `SimpleProperties` | Modelo base de dados (`key` + `value` JSON) |
| `ActionProperties` | Dados de ação (deeplink + analytics) |
| `CraftDComponentKey` | Enum com as chaves de componentes built-in |
| `CraftDViewListener` | Callback de ações para o consumidor |

### iOS / SwiftUI (Swift — `ios/craftd-swiftui/`)

| Classe | Papel |
|---|---|
| `CraftDBuilder` | Protocol base para criar componentes |
| `CraftDBuilderManager` | Registra e resolve builders pelo `key` |
| `CraftDynamic` | View principal que renderiza o SDUI |
| `SimpleProperties` | Modelo base de dados |
| `ActionProperties` | Dados de ação (deeplink + analytics) |
| `CraftDViewListener` | Callback de ações para o consumidor |

### Flutter (Dart — `flutter/craftd_widget/`)

| Classe | Papel |
|---|---|
| `CraftDynamic` | Widget principal que renderiza o SDUI |
| `CraftDViewListener` | Callback de ações para o consumidor |
| `SimpleProperties` | Modelo base de dados |
| `ActionProperties` | Dados de ação (deeplink + analytics) |
| `CraftDAlign` | Alinhamento de componentes |

> Ao adicionar um novo componente, ele deve ser implementado nas três plataformas seguindo a mesma abstração de cada uma. Consultar `CraftDButton` / `CraftDButtonBuilder` como referência em todas.

---

## Padrão de estrutura de pastas

### craftd-core (modelos e abstrações)
```
commonMain/
  data/
    model/
      base/       → SimpleProperties, SimplePropertiesResponse
      action/     → ActionProperties, AnalyticsProperties
      [name]/     → [Name]Properties.kt para cada componente
  domain/         → enums e sealed classes (CraftDAlign, CraftDTextStyle)
  presentation/   → CraftDViewListener, CraftDComponentKey
  extensions/     → funções de extensão
```

### craftd-compose (implementação Compose/KMP)
```
commonMain/
  builder/        → CraftDBuilder.kt (interface), CraftDBuilderManager.kt
  ui/
    [name]/
      CraftD[Name].kt         → o @Composable do componente
      CraftD[Name]Builder.kt  → implementa CraftDBuilder
  extensions/     → funções utilitárias Compose
```

### Padrão por novo componente (exemplo: CraftDImage)

1. `craftd-core/commonMain/data/model/image/ImageProperties.kt` — data class do modelo
2. `craftd-compose/commonMain/ui/image/CraftDImage.kt` — composable
3. `craftd-compose/commonMain/ui/image/CraftDImageBuilder.kt` — builder
4. Equivalentes em `ios/craftd-swiftui/` e `flutter/craftd_widget/` com a mesma estrutura

---

## Princípios de desenvolvimento

### Compose
- Composables devem ser **stateless** — estado vem sempre do caller (state hoisting)
- Todo componente expõe `modifier: Modifier = Modifier` como parâmetro
- Sem valores hardcoded de cor ou tipografia — usar `MaterialTheme.colorScheme` e `MaterialTheme.typography`
- Todo componente interativo deve ter **touch target mínimo de 48x48dp**

### Arquitetura
- A camada `domain` não pode ter dependências Android — apenas Kotlin puro
- Repositórios usam `suspend functions` main-safe

### Build
- Dependências sempre via `libs.versions.toml` — nunca versão hardcoded no `build.gradle.kts`
- Configuração compartilhada entre módulos vai em convention plugin no `build-logic/`

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
