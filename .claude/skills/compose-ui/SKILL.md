---
name: compose-ui
description: Boas práticas para Composables no CraftD. Use ao escrever ou revisar componentes Compose.
---

# Jetpack Compose Best Practices

## 1. State Hoisting

Composables do CraftD devem ser stateless — estado vem do caller:

```kotlin
@Composable
fun CraftDXxx(
    properties: XxxProperties,
    onAction: (ActionProperties) -> Unit,
    modifier: Modifier = Modifier  // sempre como último parâmetro opcional
)
```

## 2. Modifier

- Sempre expor `modifier: Modifier = Modifier` como parâmetro
- Aplicar no elemento raiz do componente
- Ordem importa: `padding().clickable()` ≠ `clickable().padding()`

## 3. Performance

- `remember` para computações caras entre recomposições
- `derivedStateOf` quando estado muda frequentemente mas UI atualiza em threshold
- Lambdas estáveis para evitar recomposição desnecessária de filhos

## 4. Theming

- Usar `MaterialTheme.colorScheme` e `MaterialTheme.typography`
- Nunca valores hardcoded de cor ou tipografia

## 5. Previews

```kotlin
@Preview(showBackground = true)
@Composable
private fun CraftDButtonPreview() {
    CraftDButton(properties = ButtonProperties(...)) {}
}
```
