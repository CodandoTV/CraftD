## How to use

### 1. Create your ComponentPropertyClass with properties that you need
- In this example i used checkbox component
```kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Stable
data class CheckBoxProperties(
    @JsonProperty("text") val text: String? = null,
    ... rest of your properties
)

```

### 2. Add your Component json object in Dymanic.json
```json
{
    "key": "CraftDCheckBox",
    "value": {
     ... rest of your properties
     }
 }
  
```

### 3. Create your Component
> :memo: **Note:** Your composable component must have three properties.
- componentProperties: The mapped properties from json
- modifier: Default for composable componets
- behaviour: This make reference to the component's behaviour, for example: onclick -> for buttons, onchange -> for checkbox etc...
```kotlin
@Composable
fun CraftDCheckBox(
    checkboxProperties: CheckBoxProperties,
    modifier: Modifier = Modifier,
    onChecked: (Boolean) -> Unit 
) {
   ... Rest of your code
}
```

### 4. Create your Component Builder
 > :memo: **Note:** This Builder must extend CraftBuilder Class and override craft method.

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

### 5. In your initial screen add yout builder inside of CraftBuilderManager
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
        println(
            ">>>> category ${it.analytics?.category} -" + " action ${it.analytics?.action} -" + " label  ${it.analytics?.label} -" + " deeplink ${it.deeplink}"
        )
    }
}
```
## So now just enjoy your component!!!

## Credits

The DynamicView library was made inspired by these repositories:

https://github.com/vivchar/RendererRecyclerViewAdapter

https://github.com/GustavoHSSantorio/Dynamic-Adapter (We worked in the initial solution)

Thanks to these repositories that made I think in new ideas to generate a new library version
