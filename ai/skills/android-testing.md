---
name: android-testing
description: Testing strategies for Android/KMP in CraftD. Use when creating or reviewing tests.
trigger: when the user asks to write tests, review test files, or fix test compilation errors
---

# Android / KMP Testing in CraftD

## Test Levels

| Level | Focus | Tools |
|---|---|---|
| Unit | Isolated logic (ViewModels, Repositories, Builders) | JUnit4, MockK |
| Integration | Interaction between components | AndroidX Test |
| Screenshot | Visual UI verification | Roborazzi (runs on JVM, no emulator needed) |

## CraftD Standard

- Framework: **JUnit4 + MockK**
- Naming: backtick notation `` `given X when Y then Z` ``
- Path: `src/test/java/...` mirroring the original file's package
- Every new component must have a unit test covering: construction, defaults, `copy()`, `equals/hashCode`, and the builder's `craft()`

## AI-Generated Tests (CI / Claude API)

The CI workflow can auto-generate tests via Claude API. These tests **often fail to compile or run** and need review. Common issues:

### Compilation Errors

| Problem | Symptom | Fix |
|---|---|---|
| Markdown code fence | File starts with ` ```kotlin ` | Remove ` ```kotlin ` and ` ``` ` from start/end |
| MockK: loose `throws()` | `mockk<Foo> { throws(...) }` | Use `mockk<Foo>(); every { mock.prop } throws ...` |
| `apply { val = }` on data class | `copy().apply { valProp = ... }` | `val` is immutable — use `copy(prop = ...)` |
| Non-existent enum | `CraftDAlign.START`, `CraftDTextStyle.REGULAR` | Check the real enum and substitute |
| AbstractMap entries incompatible | Wrong type override for `entries` | Remove the anonymous object if it's dead code |
| `assertNotEquals` for references | `assertNotEquals(a as Any, b as Any)` when `a == b` | Use `assertTrue(a !== b)` |
| Type inference: `assertNotEquals` | Two different types without explicit parameter | `assertNotEquals<Any?>(a, b)` |

### Build Gradle Dependencies

For `androidUnitTest` sourceSet (path `src/test/java/`), ensure:
```kotlin
androidUnitTest.dependencies {
    implementation(libs.junit)
    implementation(libs.mockk)
    implementation(kotlin("test-junit"))
}
```

### Runtime Errors

| Problem | Symptom | Fix |
|---|---|---|
| `@Stable`/`@Immutable`/`@Serializable` annotations | `assertTrue(isStable)` fails at runtime | BINARY retention — not visible via reflection. Remove the test |
| Mock of extension function | `MockKException: Missing mocked calls` | Kotlin extension functions need `mockkStatic`. Alternative: use `ByteArrayInputStream` |
| Jackson `convertValue` with String | `ClassCastException` | `convertValue` doesn't parse JSON strings — remove the test or use `readValue` |
| Jackson `convertValue` with generic List | Returns `List<LinkedHashMap>` instead of `List<Foo>` | Generics are erased at runtime — remove the test |

## Screenshot Tests (Roborazzi)

```bash
./gradlew recordRoborazziDebug   # record baseline
./gradlew verifyRoborazziDebug   # verify regression
```
