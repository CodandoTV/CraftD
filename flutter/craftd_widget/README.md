## 🧑‍💻 How to use?

You can use the existing components or create your own components, more details you can check in our [documentation web site](https://codandotv.github.io/CraftD).

### Existing Components

You can check the existing components CraftD already has [here](https://codandotv.github.io/CraftD/#components-that-already-exist-in-the-library).

### Create your own component

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

- Add your Component json object in Dymanic.json

```json
{
    "key": "CraftDBbutton",
    "value": {
         "text": "Knife",
     ... place your properties
     }
 }
```
