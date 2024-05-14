## Setup

Add in your settings.gradle `mavenCetral`
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        //Stuff
        mavenCentral()
    }

}
```

### There are tree versions for Android:
- [Compose](#compose-version)

```kotlin
implementation("io.github.codandotv:craftd-compose:${last_version}")
```

- [XML View System](#xml-version)
```kotlin
implementation("io.github.codandotv:craftd-xml:${last_version}")
```

- [Core](#core) - Is meant to be used for you to customize even the craftD mechanism
```kotlin
implementation("io.github.codandotv:craftd-core:${last_version}")
```

## How to use

### 🎯 Compose version

### 1. Create your ComponentPropertyClass with properties that you need
- In this example i used checkbox component
> :warning: **Warning:** Here we have some points to consider
> To avoid unnecessary recompositions at your component. We recommend use
> the @Immutable and @Stable annotations in your properties. More about it below

- **@immutable**: This guarantee the composition optimization based on the assumption that values read from the type will not change.

- **@stable**: this is used to communicate some guarantees to the compose compiler about how a certain type or function will behave and keep the compose compiler notified about changes

```kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Stable
data class CheckBoxProperties(
    @JsonProperty("text") val text: String? = null,
    ... define your properties here
)

```

### 2. Add your Component json object in Dymanic.json
```json
{
    "key": "CraftDCheckBox",
    "value": {
     ... define your properties here
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
   ... place your code
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

### 5. In your screen you can add the builder inside of CraftBuilderManager
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
## So now just enjoy your component!!!

------------------------------------------

### 🎯 XML version

### 1. Add in a xml that you want to use
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_height="match_parent"
    android:layout_width="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler"
        android:layout_height="match_parent"
        android:layout_width="match_parent"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager" />

</androidx.constraintlayout.widget.ConstraintLayout>

```

### 2. Add in your ViewModel the interface from the Dynamic

```kotlin
class YourViewModel(
    val craft: CraftDView,
    val repository: DynamicRepository 
) : ViewModel() {
 //Stuffs 
}
```

### 3. In your Activity/Fragment connect the Dynamic to the adapter in xml

```kotlin
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = vm.craft as CraftDViewAdapter
        //--- Stuffs
}
```


### 4. Create object `Properties` of according with your components for example:
```
@JsonIgnoreProperties(ignoreUnknown = true)
data class TextProperties(
    @JsonProperty("xxx") val myProperties1: String,
    @JsonProperty("xxx") val myProperties2: String,
)
```

### 5. Create object `ViewRenders` of according with your components for example:

```kotlin
class MyComponentRender(override var onClickListener: CraftDViewListener?)
    : ViewRenderer<MyComponentRender.MyHolder>("Your Key", "Your Identifier") {

    inner class MyHolder(val anyView: AnyView) : RecyclerView.ViewHolder(anyView)

    override fun bindView(model: SimpleProperties, holder: MyHolder, position: Int) {
        val anyProperties = model.value.convertToVO<AnyProperties>()

        holder.any.text = anyProperties.text
        anyProperties.textColorHex?.let { textColorHex ->
            //Stuff
        }
        anyProperties.actionProperties?.let { actionProperties ->
          //Stuff
        }
    }

    override fun createViewHolder(parent: ViewGroup): MyHolder {
        return MyHolder(AnyView(parent.context))
    }
}
```

### 6. Configure your ViewModel to accept for example:

```kotlin
class DynamicViewModel(
    val craft: CraftD,
    val repository: SampleCraftDRepository
) : ViewModel() {
  
    fun onResume() {
        viewModelScope.launch {
            repository.getDynamic()
                .catch {
                    it.printStackTrace()
                }
                .collect {
                    setupDynamicRender(it)
                    craft.setViewObjectDiff(it)
                }
        }
    }

    private fun setupDynamicRender(list: List<SimpleProperties>) {
        craft.registerRenderers(
            CraftDBuilderManager.getBuilderRenders(
                simpleProperties = list,
                customDynamicBuilderList = customListViewRender // Can you pass your custom list from ViewRender
            ) { action ->
                listener.invoke(action)
            })
    }

    private val listener = object : CraftDViewListener {
        override fun invoke(actionProperties: ActionProperties) {
            actionProperties.analytics?.let {
               //Stuff
            }
            actionProperties.deeplink?.let {
                //Stuff
            }
        }
    }
}
``` 

## 7. Enjoy and Have fun to create a json that you need

Your json must to have at least two parameters `key` and `value` that are respective of your object for example:
```json
{
  "data": [
    {
      "key": "MyDynamicView",
      "value": {
        "text": "Any",
        "textColor": "Any"
      }
    }
  ]
}
```
