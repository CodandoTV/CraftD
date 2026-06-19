# iOS / SwiftUI — Platform Instructions

> Read this file when starting any iOS task. Ignore `android_kmp/` and `flutter/`.

---

## Core Abstractions

| Class | Role |
|---|---|
| `CraftDBuilder` | Base protocol for creating components |
| `CraftDBuilderManager` | Registers and resolves builders by `key` |
| `CraftDynamic` | Main View that renders the SDUI |
| `SimpleProperties` | Base data model |
| `ActionProperties` | Action data (deeplink + analytics) |
| `CraftDViewListener` | Action callback for the consumer |

---

## Folder Structure

```
ios/craftd-swiftui/
  Sources/CraftD/
    builder/        → CraftDBuilder.swift (protocol), CraftDBuilderManager.swift
    ui/
      [name]/
        CraftD[Name].swift         → SwiftUI View for the component
        CraftD[Name]Builder.swift  → implements CraftDBuilder
    model/
      [Name]Properties.swift       → model struct
```

## Pattern for a new component (example: CraftDFoo)

1. `Sources/CraftD/model/FooProperties.swift` — model struct
2. `Sources/CraftD/ui/foo/CraftDFoo.swift` — SwiftUI View
3. `Sources/CraftD/ui/foo/CraftDFooBuilder.swift` — implements CraftDBuilder
4. Register in `CraftDBuilderManager`
5. Add to the iOS sample app

## Reference

Consult `CraftDButton` / `CraftDButtonBuilder` as the pattern before creating anything new.
