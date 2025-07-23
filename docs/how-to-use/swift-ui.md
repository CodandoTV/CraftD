# Swift UI

- Create your ComponentPropertyClass with properties that you need

```swift
public struct TextProperties: Decodable {
    public let text: String?
    public let textColorHex: String?
    public let textSize: String?
    public let backgroundHex: String?
    public let textAllCaps: Bool?
    public let textHtml: String?
}
```

- Add your Component json object in `Dymanic.json``

```json
[
  {
    "key": "MyCraftDText",
    "value": {
      "text": "Knife",
      "backgroundHex": "#9A71F6",
      "textSize": "30",
      "textColorHex": "#000000"
    }
  }
]
```

- Create your Component

!!! tip "This Builder must extend `CraftBuilder` Class and override craft method."
    ```swift
    public class MyCraftDTextBuilder: CraftDBuilder {
        public let key = "MyCraftDText"

        let decoder = JSONDecoder()

        public func craft(
            model: SimpleProperties,
            listener: CraftDViewListener
        ) -> any View {
            do {
                let properties = try model.decodeValue(TextProperties.self, using: decoder)
                return Text(properties.text ?? "")
            } catch {
                return EmptyView()
            }
        }
    }
    ```

- In your add your builder inside of `CraftBuilderManager`

```swift
@main
struct CraftDSampleApp: App {
    var body: some Scene {
        WindowGroup {
            //Or another View
            let craftDBuilderManager = CraftDBuilderManager()
                .add(builder: <#T##any CraftDBuilder#> like MyCraftDTextBuilder)
            CraftDynamic(craftDBuilderManager: CraftDBuilderManager)
        }
    }
}
```