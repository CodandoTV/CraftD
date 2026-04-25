---
name: compose-ui
description: Best practices for Compose components in CraftD. Use when writing or reviewing Compose UI.
trigger: when the user asks to create, review, or fix a Compose component in CraftD
---

# Creating a Compose Component in CraftD

## Mandatory Checklist

- [ ] `XxxProperties` defined in `craftd-core/commonMain`
- [ ] `CraftDComponentKey.XXX_COMPONENT` added to the enum in `craftd-core`
- [ ] `CraftDXxx` composable created in `craftd-compose/commonMain`
- [ ] `CraftDXxxBuilder` created and implements `CraftDBuilder`
- [ ] Builder registered in `CraftDBuilderManager`
- [ ] `onAction`/fallback covered (even as no-op)
- [ ] Unit test in `commonTest`
- [ ] Documentation in `docs/how-to-use/compose.md`

---

## 1. Composable — Standard Signature

```kotlin
@Composable
fun CraftDXxx(
    properties: XxxProperties,
    onAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) { ... }
```

- `modifier` always as the last optional parameter, applied to the root element
- `onAction` with default `{}` — never omit the parameter
- Composable is **stateless**: state comes from the caller, never internal

### Conditional `onAction` (when actionProperties can be null)

```kotlin
val clickableModifier = if (properties.actionProperties != null) {
    modifier.clickable { onAction() }
} else modifier
```

---

## 2. Builder — Standard Structure

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

### Builder with External Dependency (e.g., imageLoader)

When the component needs a third-party lib (Coil, etc.), receive it via constructor — never couple directly:

```kotlin
class CraftDXxxBuilder(
    private val imageLoader: @Composable (url: String, contentDescription: String?, modifier: Modifier) -> Unit,
    override val key: String = CraftDComponentKey.XXX_COMPONENT.key,
) : CraftDBuilder { ... }
```

---

## 3. Registration in CraftDBuilderManager

```kotlin
// Internal registration (no external dependency)
CraftDComponentKey.XXX_COMPONENT.key to CraftDXxxBuilder()

// Builder with external dependency — consumer injects via add()
craftDBuilderManager.add(
    CraftDXxxBuilder(imageLoader = { url, desc, mod -> /* Coil, etc. */ })
)
```

---

## 4. Theming

- Use `MaterialTheme.colorScheme` and `MaterialTheme.typography`
- Never hardcode color, font size, or spacing values

---

## 5. Performance

- `remember` for expensive computations between recompositions
- `derivedStateOf` when state changes frequently but UI updates at a threshold
- Stable lambdas to avoid unnecessary recomposition of children

---

## 6. Preview

```kotlin
@Preview(showBackground = true)
@Composable
private fun CraftDXxxPreview() {
    CraftDXxx(properties = XxxProperties(...)) {}
}
```
