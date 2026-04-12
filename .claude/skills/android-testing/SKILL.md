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

## Testes gerados por IA (CI / Claude API)

O workflow de CI pode gerar testes automaticamente via Claude API. Esses testes **frequentemente falham ao compilar ou rodar** e precisam de revisão. Checklist de problemas recorrentes:

### Erros de compilação

| Problema | Sintoma | Fix |
|---|---|---|
| Code fence markdown | Arquivo começa com ` ```kotlin ` | Remover ` ```kotlin ` e ` ``` ` do início/fim |
| MockK: `throws()` solto | `mockk<Foo> { throws(...) }` | Usar `mockk<Foo>(); every { mock.prop } throws ...` |
| `apply { val = }` em data class | `copy().apply { valProp = ... }` | `val` não é mutável — usar `copy(prop = ...)` |
| Enum inexistente | `CraftDAlign.START`, `CraftDTextStyle.REGULAR` | Verificar o enum real e substituir |
| AbstractMap entries incompat. | Override de `entries` com tipo errado | Remover o objeto anônimo se for código morto |
| `assertNotEquals` para ref. | `assertNotEquals(a as Any, b as Any)` quando `a == b` | Usar `assertTrue(a !== b)` |
| Type inference: `assertNotEquals` | Dois tipos diferentes sem parâmetro explícito | `assertNotEquals<Any?>(a, b)` |

### Erros de dependências em `build.gradle.kts`

Para o sourceSet `androidUnitTest` (path `src/test/java/`), garantir:
```kotlin
androidUnitTest.dependencies {
    implementation(libs.junit)
    implementation(libs.mockk)
    implementation(kotlin("test-junit"))
}
```

### Erros de runtime

| Problema | Sintoma | Fix |
|---|---|---|
| Anotações `@Stable`/`@Immutable`/`@Serializable` | `assertTrue(isStable)` falha em runtime | Retenção BINARY — não visível via reflection. Remover o teste |
| Mock de extension function | `MockKException: Missing mocked calls` em `every { stream.bufferedReader() }` | Extension functions Kotlin precisam de `mockkStatic`. Alternativa: usar `ByteArrayInputStream` |
| Jackson `convertValue` com String | `ClassCastException` ao tentar `jsonString.convertToVO<Foo>()` | `convertValue` não parseia JSON string — remover o teste ou usar `readValue` |
| Jackson `convertValue` com List genérica | Retorna `List<LinkedHashMap>` em vez de `List<Foo>` | Genéricos apagados em runtime — remover o teste |

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
