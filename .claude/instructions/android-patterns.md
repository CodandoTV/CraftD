# Android / KMP — Instruções de plataforma

> Leia este arquivo ao iniciar qualquer task Android/KMP. Ignore `ios/` e `flutter/`.

---

## Abstrações principais

| Classe | Papel |
|---|---|
| `CraftDBuilder` | Interface base para criar componentes |
| `CraftDBuilderManager` | Registra e resolve builders pelo `key` |
| `CraftDynamic` | Composable principal que renderiza o SDUI |
| `SimpleProperties` | Modelo base de dados (`key` + `value` JSON) |
| `ActionProperties` | Dados de ação (deeplink + analytics) |
| `CraftDComponentKey` | Enum com as chaves de componentes built-in |
| `CraftDViewListener` | Callback de ações para o consumidor |

---

## Estrutura de pastas

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

### craftd-xml (implementação View System)
```
src/main/kotlin/.../
  ui/
    [name]/
      CraftD[Name]Component.kt       → custom View
      CraftD[Name]ComponentRender.kt → implementa CraftDViewRenderer
  builder/
    CraftDBuilderManager.kt          → getBuilderRenders()
```

### Padrão por novo componente (exemplo: CraftDFoo)

1. `craftd-core/commonMain/data/model/foo/FooProperties.kt` — data class do modelo
2. `craftd-compose/commonMain/ui/foo/CraftDFoo.kt` — composable
3. `craftd-compose/commonMain/ui/foo/CraftDFooBuilder.kt` — builder
4. `craftd-xml/src/main/kotlin/.../ui/foo/CraftDFooComponent.kt` — custom View
5. `craftd-xml/src/main/kotlin/.../ui/foo/CraftDFooComponentRender.kt` — render
6. Registrar no `CraftDBuilderManager` de cada módulo
7. Adicionar ao `app-sample-android` (Compose + XML) e ao `dynamic.json`

---

## Princípios Compose

- Composables **stateless** — estado vem do caller (state hoisting)
- Todo componente expõe `modifier: Modifier = Modifier`
- Sem valores hardcoded de cor ou tipografia — usar `MaterialTheme.colorScheme` e `MaterialTheme.typography`
- Todo componente interativo: touch target mínimo de 48x48dp

## Build

- Dependências sempre via `libs.versions.toml` — nunca versão hardcoded no `build.gradle.kts`
- Configuração compartilhada entre módulos vai em convention plugin no `build-logic/`
- Rodar `./gradlew build` em `android_kmp/` após cada task antes de marcar `[x]`

## Testes

- JUnit4 + MockK para testes Android
- `kotlin("test")` + `kotlinx.serialization` + `compose.runtime` para commonTest
- Nomenclatura em backtick: `` `given X when Y then Z` ``
- Path espelha o source: `src/commonTest/kotlin/...`
