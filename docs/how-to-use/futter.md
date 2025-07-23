# Flutter

- Create your ComponentPropertyClass with properties that you need

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

- Add your Component json object in `Dymanic.json`

```json
{
    "key": "CraftDBbutton",
    "value": {
         "text": "Knife",
     ... place your properties
     }
 }
```

- Create your Component

!!! tip "Your component must have three properties"
    - ButtonProperties: The mapped properties from json
    - callback: This make reference to the component's behaviour, for example: onclick -> for buttons, onchange -> for checkbox etc...

    ```dart
    class CraftDButton extends StatelessWidget {
       const CraftDButton(
          {super.key, required this.buttonProperties, required this.callback}
       );

      //... place your code
    }
    ```

- Create your Component Builder

!!! tip "This Builder must extend `CraftBuilder` Class and override craft and fromJson methods."
    ```dart
    class CraftDButtonBuilder extends CraftDBuilder<ButtonProperties> {
        CraftDButtonBuilder() : super(key: key);

        @override
        Widget craft(ButtonProperties model, CraftDViewListener listener) {
            //... place your code
        }

        @override
        ButtonProperties fromJson(properties) {
            return ButtonProperties(
                text: properties["text"],
                //... rest of tour code
            )
        }

        static String key = "CraftDButton";
    }
    ```

-  In your Page, create your `CraftDBuilder` declaration and put it into `CraftDynamic` Widget

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