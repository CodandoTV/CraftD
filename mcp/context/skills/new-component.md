---
name: new-component
description: Create a new Server Driven UI component in CraftD following the CraftDBuilder pattern across all platforms
trigger: when the user asks to create a new component, builder, or SDUI element
---

# Creating a New CraftD Component

## Reference Implementation

Always consult `CraftDButtonBuilder` and `CraftDTextBuilder` before starting. They are the canonical examples.

## Step-by-Step (Android/KMP)

### 1. craftd-core — model (commonMain)

```
android_kmp/craftd-core/src/commonMain/kotlin/.../data/model/[name]/[Name]Properties.kt
```

```kotlin
@Serializable
data class [Name]Properties(
    val someField: String = "",
    val actionProperties: ActionProperties? = null,
)
```

### 2. craftd-core — register the key

Add to `CraftDComponentKey` enum:
```kotlin
[NAME]_COMPONENT("[name]_component")
```

### 3. craftd-compose — composable (commonMain)

```
android_kmp/craftd-compose/src/commonMain/kotlin/.../ui/[name]/CraftD[Name].kt
```

```kotlin
@Composable
fun CraftD[Name](
    properties: [Name]Properties,
    onAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    // implementation
}
```

### 4. craftd-compose — builder (commonMain)

```kotlin
class CraftD[Name]Builder(
    override val key: String = CraftDComponentKey.[NAME]_COMPONENT.key
) : CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val properties = model.value.convertToElement<[Name]Properties>()
        properties?.let {
            CraftD[Name](
                properties = it,
                onAction = { it.actionProperties?.let { action -> listener.invoke(action) } },
            )
        }
    }
}
```

### 5. Register in CraftDBuilderManager (Compose)

```kotlin
CraftDComponentKey.[NAME]_COMPONENT.key to CraftD[Name]Builder()
```

### 6. craftd-xml — custom View + render

Create `CraftD[Name]Component.kt` and `CraftD[Name]ComponentRender.kt`, then register in `getBuilderRenders()`.

### 7. Build validation

```bash
cd android_kmp && ./gradlew build
```

Fix any compilation errors before proceeding.

## Checklist

- [ ] `[Name]Properties` in `craftd-core/commonMain`
- [ ] `CraftDComponentKey.[NAME]_COMPONENT` added to enum
- [ ] `CraftD[Name]` composable created
- [ ] `CraftD[Name]Builder` created and implements `CraftDBuilder`
- [ ] Builder registered in `CraftDBuilderManager` (Compose)
- [ ] `onAction`/fallback covered (even as no-op)
- [ ] XML View + render created and registered
- [ ] Unit tests in `commonTest`
- [ ] Documentation in `docs/how-to-use/compose.md`
- [ ] Sample app updated

## iOS / Flutter

Follow the same pattern adapted to each platform. See `mcp/context/ios.md` and `mcp/context/flutter.md` for platform-specific folder structure and conventions.
