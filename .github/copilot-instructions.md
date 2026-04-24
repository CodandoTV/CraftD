# CraftD — GitHub Copilot Instructions

CraftD is a **Server Driven UI** multiplatform library (Android Compose, Android XML, iOS SwiftUI, Flutter).

## Critical Rules

1. **Platform modules never depend on each other.** `craftd-compose`, `craftd-xml`, `ios/`, and `flutter/` depend only on `craftd-core`. Never on each other.
2. **Every new component implements the platform abstraction.** On Android/KMP: `CraftDBuilder`. On iOS: Swift protocol equivalent. On Flutter: Dart abstract class equivalent.
3. **`onAction`/fallback always covered** — even as a no-op.
4. **`commonMain` has no platform dependencies** — use `expect/actual` for platform-specific behavior.
5. **New builders must be registered in `CraftDBuilderManager`** of the respective platform. Never create a builder without registering it.
6. **External libraries (Coil, Glide, etc.) must be injected via constructor** — never coupled directly inside the builder.
7. **Class names are consistent across platforms** — `CraftDImage` on Android = `CraftDImage` on iOS and Flutter.
8. **`CraftD` prefix** on all lib classes and files.
9. **No hardcoded colors or typography** — use `MaterialTheme.colorScheme` / `MaterialTheme.typography`.
10. **Tests:** JUnit4 + MockK, backtick naming `` `given X when Y then Z` ``.

## What NOT to Do

- Do not create a component outside `CraftDBuilder` abstraction
- Do not add platform imports in `commonMain`
- Do not create cross-platform module dependencies
- Do not commit `local.properties` or credential files

## Complete Context

For full architectural rules, platform patterns, module graph, and available skills, read:

```
mcp/context/rules.md          ← architectural rules and conventions
mcp/context/module-graph.md   ← module dependencies
mcp/context/android.md        ← Android/KMP patterns
mcp/context/ios.md            ← iOS/SwiftUI patterns
mcp/context/flutter.md        ← Flutter patterns
mcp/context/skills/           ← available skills (new-component, review-pr, run-build, ...)
```
