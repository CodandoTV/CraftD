# Jetpack Compose / CMP

- Create your `ComponentPropertyClass` with all the properties your component needs.  
  In this example, we’re using a **checkbox component**.

!!! warning "Immutable and Stable annotations"
To avoid unnecessary recompositions in your component,  
we recommend using the `@Immutable` and `@Stable` annotations in your properties.

---

### 1. Enable Kotlin Serialization
In your **root build.gradle.kts**, add the Kotlin serialization plugin (version **2.2.21** or higher):

```gradle
id("org.jetbrains.kotlin.plugin.serialization") version "2.2.21"
```

In your **module  build.gradle.kts** add

```gradle
   implementation(libs.kotlinx.serialization.json)
```

2. Create your owner object that has the same field in your json like: (This class must match the structure of the JSON that will be parsed:)

```kotlin
@Stable
@Immutable
@Serializable
data class CheckBoxProperties(
    @SerialName("text") val text: String? = null,
    ... define your properties here
)
```

!!! tip "Note"

    You can use your component object in dynamic.json inside the sample project [app-sample-android](https://github.com/CodandoTV/CraftD/tree/issue-59/convert-to-kmp/android_kmp/app-sample-android) or [KMP-sample](https://github.com/CodandoTV/CraftD/tree/issue-59/convert-to-kmp/android_kmp/KMP-Sample):

```json
{
  "key": "CraftDCheckBox",
  "value": {
    ... define your properties here
  }
}
```

- Create your Component

!!! tip "Your component must have three properties"

    - componentProperties: The mapped properties from json
    - modifier: Default for composable componets
    - behaviour: This make reference to the component's behaviour, for example: onclick -> for buttons, onchange -> for checkbox etc...

```kotlin
class CraftDCheckBoxBuilder(
    override val key: String = CraftDComponentKey.CHECK_BOX_COMPONENT.key
) :
    CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val checkBoxProperties = model.value.convertToVO<CheckBoxProperties>()
        CraftDCheckBox(checkBoxProperties) {
            checkBoxProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}
```

- Create your Component Builder:

!!! tip "Note"

This Builder must extend CraftBuilder Class and override craft method.

```kotlin
class CraftDCheckBoxBuilder(
    override val key: String = CraftDComponentKey.CHECK_BOX_COMPONENT.key
) :
    CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val checkBoxProperties = model.value.convertToVO<CheckBoxProperties>()
        CraftDCheckBox(checkBoxProperties) {
            checkBoxProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}
```

- In your screen you can add the builder inside of `CraftBuilderManager`

```kotlin
@Composable
fun InitialScreen(
    vm: SampleCraftDComposeViewModel
) {
    val properties by vm.properties.collectAsStateWithLifecycle()
    val dynamicBuilder = remember {
        CraftDBuilderManager().add(
            CraftDCheckBoxBuilder()
        )
    }
    LaunchedEffect(Unit) {
        vm.loadProperties()
    }

    CraftDynamic(
        properties = properties,
        dynamicBuilder = dynamicBuilder
    ) {
        //Component click return to do something
    }
}
```

So now enjoy your component!

---

## CraftDImage — Built-in image component

`CraftDImage` is a built-in component for rendering remote images via Server Driven UI. It requires an injected `imageLoader` so the consuming app chooses the image library.

### JSON payload

```json
{
  "key": "CraftDImage",
  "value": {
    "url": "https://example.com/photo.jpg",
    "contentScale": "CROP",
    "contentDescription": "A description for accessibility",
    "actionProperties": {
      "deeplink": "myapp://detail/1",
      "analytics": {
        "category": "image",
        "action": "tap",
        "label": "banner"
      }
    }
  }
}
```

Supported `contentScale` values: `CROP`, `FIT`, `FILL_BOUNDS`, `FILL_WIDTH`, `FILL_HEIGHT`, `INSIDE`, `NONE`.

### Registering the builder (with Coil)

`CraftDImageBuilder` is **not** pre-registered in `CraftDBuilderManager` because it requires an `imageLoader` lambda injected by the consumer.

```kotlin
// build.gradle.kts
implementation("io.coil-kt:coil-compose:2.6.0")
```

```kotlin
@Composable
fun InitialScreen(vm: SampleViewModel) {
    val craftdBuilderManager = remember {
        CraftDBuilderManager().add(
            CraftDImageBuilder(
                imageLoader = { url, contentDescription, modifier ->
                    AsyncImage(
                        model = url,
                        contentDescription = contentDescription,
                        modifier = modifier,
                    )
                }
            )
        )
    }

    CraftDynamic(
        properties = properties,
        craftDBuilderManager = craftdBuilderManager,
    ) { action -> /* handle action */ }
}
```
