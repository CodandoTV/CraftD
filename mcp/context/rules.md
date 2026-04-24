# CraftD — Architectural Rules

CraftD is a **Server Driven UI** multiplatform library. The server decides which components to render and how; the client (app) only executes. Supports Android Compose, Android XML, KMP Compose Multiplatform, iOS SwiftUI, and Flutter.

## Module Structure

```
android_kmp/
  craftd-core/          # shared models and abstractions (KMP)
    commonMain/         # code shared across platforms
    androidMain/        # Android-specific implementations
  craftd-compose/       # Compose / KMP implementation
  craftd-xml/           # View System (XML) implementation
  app-sample-android/   # Android sample app
  app-sample-cmp/       # KMP Compose sample app
  build-logic/          # shared build configuration

ios/craftd-swiftui/     # iOS library (Swift Package + CocoaPods)
flutter/craftd_widget/  # Flutter library (pub.dev)
docs/                   # site documentation (MkDocs)
```

## Architectural Rules — Never Violate

1. **Platform modules do not depend on each other.** `craftd-compose`, `craftd-xml`, `ios/`, and `flutter/` depend only on `craftd-core`. Never on each other.

2. **Every new component implements the platform abstraction.** On Android/KMP: `CraftDBuilder`. On iOS: the Swift equivalent. On Flutter: the Dart equivalent. Never create a standalone component outside this abstraction.

3. **`onAction` / fallback always covered.** Every component implementation must handle the action callback (`ActionProperties`), even as a no-op.

4. **`commonMain` is sacred.** Code in `commonMain` cannot have platform dependencies. If different behavior is needed per platform, use `expect/actual`.

5. **New components follow the existing pattern.** Consult `CraftDButtonBuilder` and `CraftDTextBuilder` as reference implementations before creating anything new.

6. **Class names are consistent across all platforms.** Example: if a component is called `CraftDImage` on Android, it must be called `CraftDImage` on iOS and Flutter too.

7. **Every new component must have a unit test** (when possible) and **documentation in `docs/` under the respective platform section**.

8. **Every change that affects public library behavior** (new component, new parameter, breaking change) must update `docs/how-to-use/` for the corresponding platform: `compose.md`, `view-system.md`, `swift-ui.md`, or `flutter.md`.

9. **Every new builder must be registered in `CraftDBuilderManager`** of the respective platform. In Compose/KMP: `CraftDComponentKey.X.key to CraftDXBuilder()`. In XML: add the render in `getBuilderRenders()`. Never create a builder without registering it.

10. **External library dependencies must be abstracted.** Never directly couple a third-party lib (e.g., Coil, Picasso, Glide) inside the builder. Expose an interface/function as a constructor parameter so the consumer injects the implementation.

## Code Conventions

- **Kotlin:** follows official Kotlin conventions. Prefer `data class` for models.
- **Component naming:** `CraftD` prefix on everything that is part of the lib (e.g., `CraftDButton`, `CraftDButtonBuilder`).
- **Tests:** JUnit4 + MockK. Backtick naming: `` `given X when Y then Z` ``. Path mirrors source: `src/test/java/...`
- **Commits:** messages in English, semantic (`feat:`, `fix:`, `test:`, `chore:`, `docs:`).

## What NOT to Do

- Do not create a component outside the `CraftDBuilder` abstraction (or platform equivalent)
- Do not add platform dependencies in `commonMain`
- Do not create dependencies between platform modules (`craftd-compose` → `craftd-xml`, for example)
- Do not commit `local.properties` or credential files
- Do not use `--no-verify` to bypass CI hooks
