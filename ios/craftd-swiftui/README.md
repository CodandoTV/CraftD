## Setup

Add in your XXXXXXXXXX
```swift
```

## How to use

### 🎯 SwiftUI version

### 1. Create your ComponentPropertyClass with properties that you need
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

### 2. Add your Component json object in Dymanic.json
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

### 3. Create your Component
 
You can create any custom component that you need for example Text and add in the next step

### 4. Create your Component Builder
> :memo: **Note:** This Builder must extend CraftBuilder Class and override craft method.

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

### 5. In your add your builder inside of CraftBuilderManager
```swift
@main
struct CraftDSampleApp: App {
    var body: some Scene {
        WindowGroup {
            //Or another View
            CraftDBuilderManager()
                .add(builder: <#T##any CraftDBuilder#> like MyCraftDTextBuilder)
            CraftDynamic(craftBuilders: craftBuilders)
        }
    }
}
```
## So now just enjoy your component!!!
