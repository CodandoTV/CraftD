# View System

- Add in a xml that you want to use

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

-  Add in your ViewModel the interface from the Dynamic

```kotlin
class YourViewModel(
    val craft: CraftDView,
    val repository: DynamicRepository 
) : ViewModel() {
 //Stuffs 
}
```

- In your `Activity`/`Fragment` connect the Dynamic to the adapter in xml

```kotlin
  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = vm.craft as CraftDViewAdapter
        //--- Stuffs
}
```

- Create object Properties of according with your components for example:

```kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
data class TextProperties(
    @JsonProperty("xxx") val myProperties1: String,
    @JsonProperty("xxx") val myProperties2: String,
)
```

!!! tip "View Renderds"

   Create object ViewRenders of according with your components for example:

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

- Configure your ViewModel to accept for example

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

- Enjoy and Have fun to create a json that you need

!!! tip "Your component must have three properties"
    Your json must to have at least two parameters key and value that are respective of your object for example:

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

So now enjoy your component!!!