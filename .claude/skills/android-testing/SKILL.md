---
name: android-testing
description: Testing strategies for Android/KMP. Use when creating or reviewing tests no CraftD.
---

# Android Testing Strategies

## Níveis de teste

| Nível | Foco | Ferramentas |
|---|---|---|
| Unit | Lógica isolada (ViewModels, Repositories, Builders) | JUnit4, MockK |
| Integration | Interação entre componentes | AndroidX Test, Hilt |
| Screenshot | Verificação visual de UI | Roborazzi (roda na JVM, sem emulador) |

## Padrão no CraftD

- Framework: **JUnit4 + MockK**
- Nomenclatura: backtick notation `` `given X when Y then Z` ``
- Path: `src/test/java/...` espelhando o pacote do arquivo original
- Todo novo componente deve ter teste unitário cobrindo: construção, defaults, `copy()`, `equals/hashCode`, e o `craft()` do builder

## Screenshot tests (Roborazzi)

```bash
./gradlew recordRoborazziDebug   # grava baseline
./gradlew verifyRoborazziDebug   # verifica regressão
```

## Hilt em testes

```kotlin
@HiltAndroidTest
class MyTest {
    @get:Rule val hiltRule = HiltAndroidRule(this)
}
```
