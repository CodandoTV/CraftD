# Setup

## Run this command with Flutter:

```sh
 flutter pub add craftd_widget
```

## This will add a line like this to your package's pubspec.yaml (and run an implicit flutter pub get):

```yaml
dependencies:
  craftd_widget: $last_version
```

more detail [pub.dev](https://pub.dev/packages/craftd_widget/install)

# How to use

## 1. Create your ComponentPropertyClass with properties that you need

- In this example i used Button component

```dart
class ButtonProperties {
  const ButtonProperties({
      required this.text,
    ... place your construtor properties
  });

  final String text;
   ... place your properties
}

```

## 2. Add your Component json object in Dymanic.json

```json
{
    "key": "CraftDBbutton",
    "value": {
         "text": "Knife",
     ... place your properties
     }
 }

```

## 3. Create your Component

> :memo: **Note:** Your widget must have two properties.

- ButtonProperties: The mapped properties from json
- callback: This make reference to the component's behaviour, for example: onclick -> for buttons, onchange -> for checkbox etc...

```dart
class CraftDButton extends StatelessWidget {
    const CraftDButton(
      {super.key, required this.buttonProperties, required this.callback});

      ... place your code

}
```

## 4. Create your Component Builder

> :memo: **Note:** This Builder must extend CraftBuilder Class and override craft and fromJson methods.

```dart

class CraftDButtonBuilder extends CraftDBuilder<ButtonProperties> {
  CraftDButtonBuilder() : super(key: key);

  @override
  Widget craft(ButtonProperties model, CraftDViewListener listener) {
    ... place your code
  }

  @override
  ButtonProperties fromJson(properties) {
    return ButtonProperties(
        text: properties["text"],
        ... rest of tour code
    )
  }

  static String key = "CraftDButton";
}
```

## 5. In your Page, create your CraftDBuilder declaration and put it into CraftDynamic Widget

```dart
 // You can put it in your dependency injection
    final craftdBuilderManager = CraftDBuilderManager();

    return CraftDynamic(
         simplePropertiesList: simplePropertiesList,
         craftDBuilderManager : craftdBuilderManager,
         onAction: (actionProperties) {
        print(
                "categoria ${actionProperties.analyticsProperties?.category} "
                "label ${actionProperties.analyticsProperties?.label} - "
                "track ${actionProperties.analyticsProperties?.track}");
            });
          }

```

## So now just enjoy your component!!!

## Credits

The CraftD library was made inspired by these repositories:

https://github.com/vivchar/RendererRecyclerViewAdapter

https://github.com/GustavoHSSantorio/Dynamic-Adapter (We worked in the initial solution)

Thanks to these repositories that made I think in new ideas to generate a new library version
