# Flutter — Platform Instructions

> Read this file when starting any Flutter task. Ignore `android_kmp/` and `ios/`.

---

## Core Abstractions

| Class | Role |
|---|---|
| `CraftDynamic` | Main widget that renders the SDUI |
| `CraftDViewListener` | Action callback for the consumer |
| `SimpleProperties` | Base data model |
| `ActionProperties` | Action data (deeplink + analytics) |
| `CraftDAlign` | Component alignment |

---

## Folder Structure

```
flutter/craftd_widget/
  lib/
    src/
      builder/      → CraftDBuilder (abstract), CraftDBuilderManager
      ui/
        [name]/
          craftd_[name].dart         → component Widget
          craftd_[name]_builder.dart → implements CraftDBuilder
      model/
        [name]_properties.dart       → model class
```

## Pattern for a new component (example: CraftDFoo)

1. `lib/src/model/foo_properties.dart` — model class
2. `lib/src/ui/foo/craftd_foo.dart` — Widget
3. `lib/src/ui/foo/craftd_foo_builder.dart` — implements CraftDBuilder
4. Register in `CraftDBuilderManager`
5. Add to the Flutter sample app

## Conventions

- File names in `snake_case`
- Classes in `PascalCase` with `CraftD` prefix
- External dependencies (e.g., cached_network_image) injected via constructor, never coupled in the builder

## Reference

Consult `CraftDButton` / `CraftDButtonBuilder` as the pattern before creating anything new.
