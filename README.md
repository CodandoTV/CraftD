[![Android API](https://img.shields.io/badge/api-21%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=21)
[![kotlin](https://img.shields.io/github/languages/top/codandotv/craftd.svg?style=for-the-badge&color=blueviolet)](https://kotlinlang.org/)
[![License MIT](https://img.shields.io/github/license/codandotv/craftd.svg?style=for-the-badge&color=orange)](https://opensource.org/licenses/MIT)

![Logo do CodandoTV](readme-files/codandotv.png)

<h1 align="center">
    <img height="150" src="https://github.com/user-attachments/assets/6893bd0d-7866-44b0-bc34-5c3759c1bacf"/>
    <br>
    <a href="https://codandotv.github.io/CraftD">CraftD</a>: A framework to implement Server-Driven UI quickly and easily to Android / iOS / Flutter / Kmp
</h1>

### Documentation
See the [project website](https://codandotv.github.io/CraftD) for documentation.

Take a look at [CraftD: Server Driven UI for All Platforms](https://medium.com/codandotv/craftd-server-driven-ui-for-all-platforms-b2624d2c2a7b)

## Features
### 🔗 Compatibility

| Tech                            |      Support         |
|---------------------------------|:--------------------:|
| View System - Android           |  ✅ **Supported**    |
| Jetpack Compose - Android       |  ✅ **Supported**    |
| Widget - Flutter                |  ✅ **Supported**    |
| SwiftUi - iOS                   |  ✅ **Supported**    |
| Jetpack Compose - Multiplatform |  ⚒️ **In Progress**   |

### Components that already exist in the library

| Component | Compose | View System | Widget | SwiftUI |
|-----------|----------|----------|----------|----------|
| Button    |    X    |      X      |    X   |    X    |
| Text      |    X    |      X      |    X   |    X    |
| CheckBox  |    X    |      -      |    -   |    X    |

### Create your custom component( android compose example )

```kotlin
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Stable
data class CheckBoxProperties(
    @JsonProperty("text") val text: String? = null,
    ... rest of your properties
)

```

#### Add your Component json object in Dymanic.json
```json
{
  "key": "CraftDCheckBox",
  "value": {
    ... place your properties
  }
}

```

#### Create your Component
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

#### Create your Component Builder
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

More details check our [documentation](https://codandotv.gitbook.io/craftd)


### Samples
| [Android Compose](https://github.com/CodandoTV/CraftD/tree/main/android_kmp/app-sample/src/main/java/com/github/codandotv/craftd/app_sample/presentation/compose) | [Android View System](https://github.com/CodandoTV/CraftD/tree/main/android_kmp/app-sample/src/main/java/com/github/codandotv/craftd/app_sample/presentation/xml) | [iOS SwiftUI](https://github.com/CodandoTV/CraftD/tree/main/ios/sample/CraftDSample) | [Flutter](https://github.com/CodandoTV/CraftD/tree/main/flutter/sample/lib) |
|----------|----------|----------|----------|
| <img src="https://github.com/CodandoTV/CraftD/assets/7690931/aa31d0a2-a998-402c-b2c2-4de5088ee30f" width="200" height="400" /> | <img src="https://github.com/CodandoTV/CraftD/assets/7690931/aa31d0a2-a998-402c-b2c2-4de5088ee30f" width="200" height="400" /> | <img src="https://github.com/user-attachments/assets/c8c653b2-1289-4437-85f2-f940d5135f27" width="200" height="400" /> | <img src="https://github.com/CodandoTV/CraftD/assets/7690931/dfabfda7-6501-4763-b040-3ee9fbf2a2be" width="200" height="400" /> |


### Credits

> A Server Driven UI library for Android.

Inspired by the [DynamicView](https://github.com/rviannaoliveira/DynamicView/).


This project exists thanks to all the people who contribute.
<a href="https://github.com/CodandoTV/CraftD"><img src="https://opencollective.com/craftd/contributors.svg?width=890&button=false" /></a>
