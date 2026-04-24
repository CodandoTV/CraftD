# CraftD — Module Dependency Graph

## Dependency Rules

All platform modules depend **only** on `craftd-core`. Platform modules **never** depend on each other.

```
                    ┌─────────────────┐
                    │   craftd-core   │  ← shared models, abstractions (KMP)
                    │   (commonMain)  │
                    └────────┬────────┘
                             │ depends on
           ┌─────────────────┼─────────────────┐
           │                 │                 │
           ▼                 ▼                 ▼
  ┌────────────────┐ ┌──────────────┐ ┌─────────────────────┐
  │ craftd-compose │ │  craftd-xml  │ │  ios/craftd-swiftui │
  │  (Android/KMP) │ │  (Android)   │ │      (Swift)        │
  └────────────────┘ └──────────────┘ └─────────────────────┘
                                               
                    ┌─────────────────┐
                    │flutter/craftd_  │
                    │    widget       │
                    │   (Dart)        │
                    └─────────────────┘

  ✗ craftd-compose → craftd-xml      FORBIDDEN
  ✗ craftd-xml → craftd-compose      FORBIDDEN
  ✗ ios/ → android_kmp/              FORBIDDEN
  ✗ flutter/ → android_kmp/          FORBIDDEN
```

## Module Responsibilities

| Module | Language | Role |
|---|---|---|
| `craftd-core` | Kotlin (KMP) | Models (`XxxProperties`), enums (`CraftDComponentKey`), interfaces (`CraftDBuilder`, `CraftDViewListener`) |
| `craftd-compose` | Kotlin (Compose/KMP) | Composables (`CraftDXxx`), builders (`CraftDXxxBuilder`), `CraftDBuilderManager`, `CraftDynamic` |
| `craftd-xml` | Kotlin (Android) | Custom Views (`CraftDXxxComponent`), renders (`CraftDXxxComponentRender`), XML `CraftDBuilderManager` |
| `ios/craftd-swiftui` | Swift | SwiftUI Views, `CraftDBuilder` protocol, `CraftDBuilderManager`, `CraftDynamic` |
| `flutter/craftd_widget` | Dart | Widgets, `CraftDBuilder` abstract, `CraftDBuilderManager`, `CraftDynamic` |

## Adding a New Component

Always follow this order:

1. **craftd-core** — create `XxxProperties.kt` in `commonMain/data/model/xxx/`
2. **craftd-compose** — create composable + builder, register in `CraftDBuilderManager`
3. **craftd-xml** — create View + render, register in `getBuilderRenders()`
4. **ios/** — create View + builder (Swift), register in `CraftDBuilderManager`
5. **flutter/** — create widget + builder (Dart), register in `CraftDBuilderManager`

Core must be implemented before any platform module — they all depend on it.
