# Jetpack Compose

- Create your ComponentPropertyClass with properties that you need. In this example, I used checkbox component:

!!! warning "Immutable and Stable annotations"
    Here we have some points to consider To avoid unnecessary recompositions at your component. We recommend use the `@Immutable` and `@Stable` annotations in your properties.

```kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Stable
data class CheckBoxProperties(
    @JsonProperty("text") val text: String? = null,
    ... define your properties here
)
```

- Add your Component json object in `Dymanic.json`:

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
