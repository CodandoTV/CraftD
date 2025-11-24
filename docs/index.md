# Welcome to CraftD

## Features

### 🔗 Compatibility

| Tech                       | Support         |
|----------------------------|-----------------|
| View System - Android      | ✅ Supported    |
| Jetpack Compose - Android  | ✅ Supported    |
| Widgets Flutter            | ✅ Supported    |
| SwiftUI - iOS              | ✅ Supported    |
| Compose Multiplatform      | ✅ Supported    |

### Components that already exist in the library

| Component         | Jetpack Compose / CMP | Android View | Flutter | SwiftUI |
|-------------------|-----------------------|--------------|---------|---------|
| Button            | X                     |       X      |    X    |   -     |
| Text              | X                     |       X      |    X    |   X     |
| Checkbox          | X                     |       -      |    -    |   -     |

## Backend structure expected by CraftD

To render components dynamically, CraftD expects your backend to return a JSON array where:

- **`key`** is the identifier of the component you want to render (ex: `CraftDTextView`, `CraftDButton`).
- **`value`** represents the structure and properties of that component.
- Each platform (Compose, View System, Flutter, SwiftUI) interprets these properties according to its own specification.

### Example of expected structure

```json
[
  {
    "key": "CraftDTextView",
    "value": {
      "text": "Knife",
      "backgroundHex" : "#9A71F6",
      "textSize": "30",
      "textColorHex": "#000000"
    }
  },
  {
    "key": "CraftDButton",
    "value": {
      "text": "Some Action :)",
      "align": "RIGHT",
      "textAlign": "CENTER",
      "textAllCaps": true,
      "textSize": "20",
      "textColorHex": "#FFFFFF",
      "backgroundHex": "#2fa003",
      "actionProperties": {
        "deeplink": "CraftDview://any",
        "analytics": {
          "category": "hello",
          "action": "world",
          "label": "everywhere"
        }
      }
    }
  }
]
```

### How to extend

Each component type has its own platform-specific specification for how it is built or rendered, but all of them use the same JSON structure.
To understand all available properties, check the documentation for:

- **Compose**
- **Android View System**
- **Flutter**
- **SwiftUI**


### Screen recordings

<div class="grid cards" markdown>
  <figure markdown="span">
    ![Compose Android](./assets/video/compose-android.gif){ width="300" }
    <figcaption>JetPack Compose / CMP</figcaption>
  </figure>

  <figure markdown="span">
    ![View System Android](./assets/video/viewsystem-android.gif){ width="300" }
    <figcaption>View System Android</figcaption>
  </figure>

  <figure markdown="span">
    ![Flutter](./assets/video/flutter.gif){ width="300" }
    <figcaption>Flutter</figcaption>
  </figure>

  <figure markdown="span">
    ![Swift UI](./assets/video/swift-ui.gif){ width="300" }
    <figcaption>Swift UI</figcaption>
  </figure>
</div>


!!! info "Credits"

    A Server Driven UI library for Android.
    Inspired by the [_DynamicView_](https://github.com/rviannaoliveira/DynamicView).
