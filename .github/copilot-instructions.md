# CraftD — GitHub Copilot

> Read `AGENTS.md` at the start of every session for full project context.

CraftD is a Server Driven UI multiplatform library (Android Compose, Android XML, iOS SwiftUI, Flutter).

## Critical Rules

1. Platform modules never depend on each other — `craftd-compose`, `craftd-xml`, `ios/`, and `flutter/` depend only on `craftd-core`.
2. Every new component implements `CraftDBuilder` (Android/KMP) or equivalent protocol.
3. `onAction`/fallback always covered, even as a no-op.
4. `commonMain` must have zero platform dependencies — use `expect/actual`.
5. Every new builder must be registered in `CraftDBuilderManager`.
6. `CraftD` prefix on all lib classes and files.
7. No hardcoded colors or typography — use `MaterialTheme`.
8. Tests: JUnit4 + MockK, backtick naming `` `given X when Y then Z` ``.

## Context Files

```
ia/module-graph.md         ← module dependency graph
ia/instructions/android.md ← Android/KMP patterns
ia/instructions/ios.md     ← iOS/SwiftUI patterns
ia/instructions/flutter.md ← Flutter patterns
ia/skills/                 ← skills: architecture, compose-ui, android-testing, android-gradle-logic, ...
```
