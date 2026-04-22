---
name: compose-ui
description: Boas práticas para Composables no CraftD. Use ao escrever ou revisar componentes Compose.
---

# Criando um componente Compose no CraftD

## Checklist obrigatório

- [ ] `XxxProperties` definido em `craftd-core/commonMain`
- [ ] `CraftDComponentKey.XXX_COMPONENT` adicionado ao enum em `craftd-core`
- [ ] Composable `CraftDXxx` criado em `craftd-compose/commonMain`
- [ ] `CraftDXxxBuilder` criado e implementa `CraftDBuilder`
- [ ] Builder registrado no `CraftDBuilderManager`
- [ ] `onAction`/fallback coberto (mesmo que seja no-op)
- [ ] Teste unitário em `commonTest`
- [ ] Documentação em `docs/how-to-use/compose.md`

---

## 1. Composable — assinatura padrão

```kotlin
@Composable
fun CraftDXxx(
    properties: XxxProperties,
    onAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) { ... }
```

- `modifier` sempre como último parâmetro opcional, aplicado no elemento raiz
- `onAction` com default `{}` — nunca omitir o parâmetro
- Composable é **stateless**: estado vem do caller, nunca interno

### `onAction` condicional (quando actionProperties pode ser null)

```kotlin
val clickableModifier = if (properties.actionProperties != null) {
    modifier.clickable { onAction() }
} else modifier
```

---

## 2. Builder — estrutura padrão

```kotlin
class CraftDXxxBuilder(
    override val key: String = CraftDComponentKey.XXX_COMPONENT.key
) : CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val properties = model.value.convertToElement<XxxProperties>()
        properties?.let {
            CraftDXxx(
                properties = it,
                onAction = { it.actionProperties?.let { action -> listener.invoke(action) } },
            )
        }
    }
}
```

### Builder com dependência externa (ex: imageLoader)

Quando o componente precisa de lib de terceiro (Coil, etc.), recebe via construtor — nunca acopla diretamente:

```kotlin
class CraftDXxxBuilder(
    private val imageLoader: @Composable (url: String, contentDescription: String?, modifier: Modifier) -> Unit,
    override val key: String = CraftDComponentKey.XXX_COMPONENT.key,
) : CraftDBuilder { ... }
```

---

## 3. Registro no CraftDBuilderManager

```kotlin
// Registro interno (sem dependência externa)
CraftDComponentKey.XXX_COMPONENT.key to CraftDXxxBuilder()

// Builder com dependência externa — consumidor injeta via add()
craftDBuilderManager.add(
    CraftDXxxBuilder(imageLoader = { url, desc, mod -> /* Coil, etc. */ })
)
```

---

## 4. Theming

- Usar `MaterialTheme.colorScheme` e `MaterialTheme.typography`
- Nunca valores hardcoded de cor, tamanho de fonte ou espaçamento

---

## 5. Performance

- `remember` para computações caras entre recomposições
- `derivedStateOf` quando estado muda frequentemente mas UI atualiza em threshold
- Lambdas estáveis para evitar recomposição desnecessária de filhos

---

## 6. Preview

```kotlin
@Preview(showBackground = true)
@Composable
private fun CraftDXxxPreview() {
    CraftDXxx(properties = XxxProperties(...)) {}
}
```
