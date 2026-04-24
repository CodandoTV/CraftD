---
name: run-build
description: Run the CraftD build and tests for the affected module
trigger: when the user asks to build, compile, run tests, or validate the project
---

# Running the CraftD Build

## Android / KMP

Always run from the `android_kmp/` directory:

```bash
cd android_kmp

# Full build (all modules)
./gradlew build

# Build a specific module
./gradlew :craftd-core:build
./gradlew :craftd-compose:build
./gradlew :craftd-xml:build

# Unit tests only
./gradlew testDebugUnitTest

# Tests for a specific module
./gradlew :craftd-core:testDebugUnitTest
```

## When to Run

Per CLAUDE.md rules: **run `./gradlew build` after every task before marking `[x]`**. Never mark a task complete without a passing build.

## Common Build Errors

| Error | Likely Cause | Fix |
|---|---|---|
| `Unresolved reference: XxxProperties` | Model not created in `craftd-core` | Create the `XxxProperties` data class first |
| `Class is not abstract and does not implement abstract member` | Builder missing `craft()` override | Implement `craft()` in the builder |
| `None of the following candidates is applicable` | Wrong import or type mismatch | Check `convertToElement<T>()` generic type |
| `Duplicate key` in `CraftDBuilderManager` | Component key registered twice | Check `CraftDComponentKey` for duplicate values |

## Screenshot Tests

```bash
# Record new baseline
./gradlew recordRoborazziDebug

# Verify no visual regression
./gradlew verifyRoborazziDebug
```

## iOS

```bash
cd ios/craftd-swiftui
swift build
swift test
```

## Flutter

```bash
cd flutter/craftd_widget
flutter pub get
flutter test
```
