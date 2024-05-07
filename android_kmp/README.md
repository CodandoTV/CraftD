## How to use

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
class DynamicViewModel(
    val dynamic: Dynamic,
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
        
        binding.recycler.adapter = vm.dynamic as DynamicAdapter
        //--- Stuffs
}
```


### 4. Create the mapping to your visual components for example:

```kotlin
enum class DynamicComponent(val key: String) {
    TEXT_VIEW_COMPONENT("${DYNAMIC}TextView"),
}

fun List<SimpleProperties>.toRenders(listener : DynamicViewListener): List<ViewRenderer<*>> {
    return this
        .distinctBy { it.key }
        .mapNotNull { simpleProperties ->
            val key = simpleProperties.key
            when (DynamicComponent.values().find { it.key == key }) {
                DynamicComponent.TEXT_VIEW_COMPONENT -> TextViewComponentRender(listener)
                else -> null
            }
        }
}

internal const val DYNAMIC = "Dynamic"
```

### 5. Create object `Properties` of according with your components for example:
```
@JsonIgnoreProperties(ignoreUnknown = true)
data class TextProperties(
    @JsonProperty("xxx") val myProperties1: String,
    @JsonProperty("xxx") val myProperties2: String,
)
```

### 6. Create object `ViewRenders` of according with your components for example:


```kotlin
class MyComponentRender(override var onClickListener: DynamicViewListener?)
    : ViewRenderer<MyComponentRender.MyHolder>(DynamicComponent.MY_COMPONENT.key, DynamicComponent.MY_COMPONENT.ordinal) {

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

### 7. Configure your ViewModel to accept for example:

```kotlin
class DynamicViewModel(
    val dynamic: Dynamic,
    val repository: DynamicRepository
) : ViewModel() {
  
    fun onResume() {
        viewModelScope.launch {
            repository.getDynamic()
                .catch {
                    it.printStackTrace()
                }
                .collect {
                    setupDynamicRender(it)
                    dynamic.setViewObjectDiff(it)
                }
        }
    }

    private fun setupDynamicRender(list : List<SimpleProperties>) {
        dynamic.registerRenderers(list.toRenders {
            listener.invoke(it)
        })
    }

    private val listener = object : DynamicViewListener {
        override fun invoke(actionProperties: DynamicActionProperties) {
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


## Credits

The DynamicView library was made inspired by these repositories:

https://github.com/vivchar/RendererRecyclerViewAdapter

https://github.com/GustavoHSSantorio/Dynamic-Adapter (We worked in the initial solution)

Thanks to these repositories that made I think in new ideas to generate a new library version
