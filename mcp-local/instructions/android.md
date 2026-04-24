# Android / KMP — Platform Instructions

> Read this file when starting any Android/KMP task. Ignore `ios/` and `flutter/`.

---

## Core Abstractions

| Class | Role |
|---|---|
| `CraftDBuilder` | Base interface for creating components |
| `CraftDBuilderManager` | Registers and resolves builders by `key` |
| `CraftDynamic` | Main composable that renders the SDUI |
| `SimpleProperties` | Base data model (`key` + `value` JSON) |
| `ActionProperties` | Action data (deeplink + analytics) |
| `CraftDComponentKey` | Enum with built-in component keys |
| `CraftDViewListener` | Action callback for the consumer |

---

## Folder Structure

### craftd-core (models and abstractions)
```
commonMain/
  data/
    model/
      base/       → SimpleProperties, SimplePropertiesResponse
      action/     → ActionProperties, AnalyticsProperties
      [name]/     → [Name]Properties.kt for each component
  domain/         → enums and sealed classes (CraftDAlign, CraftDTextStyle)
  presentation/   → CraftDViewListener, CraftDComponentKey
  extensions/     → extension functions
```

### craftd-compose (Compose/KMP implementation)
```
commonMain/
  builder/        → CraftDBuilder.kt (interface), CraftDBuilderManager.kt
  ui/
    [name]/
      CraftD[Name].kt         → the @Composable for the component
      CraftD[Name]Builder.kt  → implements CraftDBuilder
  extensions/     → Compose utility functions
```

### craftd-xml (View System implementation)
```
src/main/kotlin/.../
  ui/
    [name]/
      CraftD[Name]Component.kt       → custom View
      CraftD[Name]ComponentRender.kt → implements CraftDViewRenderer
  builder/
    CraftDBuilderManager.kt          → getBuilderRenders()
```

### Pattern for a new component (example: CraftDFoo)

1. `craftd-core/commonMain/data/model/foo/FooProperties.kt` — model data class
2. `craftd-compose/commonMain/ui/foo/CraftDFoo.kt` — composable
3. `craftd-compose/commonMain/ui/foo/CraftDFooBuilder.kt` — builder
4. `craftd-xml/src/main/kotlin/.../ui/foo/CraftDFooComponent.kt` — custom View
5. `craftd-xml/src/main/kotlin/.../ui/foo/CraftDFooComponentRender.kt` — render
6. Register in `CraftDBuilderManager` of each module
7. Add to `app-sample-android` (Compose + XML) and to `dynamic.json`

---

## Compose Principles

- Composables are **stateless** — state comes from the caller (state hoisting)
- Every component exposes `modifier: Modifier = Modifier`
- No hardcoded color or typography values — use `MaterialTheme.colorScheme` and `MaterialTheme.typography`
- Every interactive component: minimum touch target of 48x48dp

## Build

- Dependencies always via `libs.versions.toml` — never hardcoded version in `build.gradle.kts`
- Shared configuration across modules goes in a convention plugin in `build-logic/`
- Run `./gradlew build` in `android_kmp/` after each task before marking `[x]`

## Tests

- JUnit4 + MockK for Android tests
- `kotlin("test")` + `kotlinx.serialization` + `compose.runtime` for commonTest
- Backtick naming: `` `given X when Y then Z` ``
- Path mirrors source: `src/commonTest/kotlin/...`
