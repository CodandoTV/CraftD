# Setup

## Installation

Run the following command in your Flutter project:

```sh
flutter pub add craftd_widget
```

This will add the following entry to your `pubspec.yaml` and run `flutter pub get` automatically:

```yaml
dependencies:
  craftd_widget: $last_version
```

For more details, see the [pub.dev package page](https://pub.dev/packages/craftd_widget/install).

# How to Use

## 1. Create your component properties class

Define a class with the properties your component needs. In this example, we use a Button component:

```dart
class ButtonProperties {
  const ButtonProperties({
    required this.text,
    // add your other constructor properties here
  });

  final String text;
  // add your other properties here
}
```

## 2. Add your component JSON object to Dynamic.json

```json
{
  "key": "CraftDButton",
  "value": {
    "text": "Knife"
    // add your other properties here
  }
}
```

## 3. Create your component widget

> **Note:** Your widget must have two parameters: the mapped properties and a callback for user interactions.

- **properties** — the object mapped from JSON
- **callback** — handles the component's behaviour (e.g. `onClick` for buttons, `onChange` for checkboxes)

```dart
class CraftDButton extends StatelessWidget {
  const CraftDButton({
    super.key,
    required this.buttonProperties,
    required this.callback,
  });

  // add your code here
}
```

## 4. Create your component builder

> **Note:** Your builder must extend `CraftDBuilder` and override the `craft` and `fromJson` methods.

```dart
class CraftDButtonBuilder extends CraftDBuilder<ButtonProperties> {
  CraftDButtonBuilder() : super(key: key);

  @override
  Widget craft(ButtonProperties model, CraftDViewListener listener) {
    // add your code here
  }

  @override
  ButtonProperties fromJson(properties) {
    return ButtonProperties(
      text: properties["text"],
      // add your other mappings here
    );
  }

  static String key = "CraftDButton";
}
```

## 5. Register the builder and add CraftDynamic to your page

```dart
// You can register builders in your dependency injection layer
final craftdBuilderManager = CraftDBuilderManager();

return CraftDynamic(
  simplePropertiesList: simplePropertiesList,
  craftDBuilderManager: craftdBuilderManager,
  onAction: (actionProperties) {
    print(
      "category: ${actionProperties.analyticsProperties?.category} "
      "label: ${actionProperties.analyticsProperties?.label} "
      "track: ${actionProperties.analyticsProperties?.track}"
    );
  },
);
```

## That's it — enjoy your dynamic components!

## Credits

CraftD was inspired by the following open-source projects:

- https://github.com/vivchar/RendererRecyclerViewAdapter
- https://github.com/GustavoHSSantorio/Dynamic-Adapter *(we collaborated on the initial solution)*

Thanks to these projects for sparking the ideas that led to CraftD.
