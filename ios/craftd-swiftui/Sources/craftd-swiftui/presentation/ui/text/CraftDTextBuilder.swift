import SwiftUI

public class CraftDTextBuilder : CraftDBuilder {
    public typealias T = TextProperties
    public let key = "CraftDText"
    
    public func craft(model: SimpleProperties, listener: CraftDViewListener) -> AnyView {
        guard let textProperties: TextProperties = dataDecoding(data: model.value ?? Data()) else {
            return AnyView(Text("data"))
        }
        return AnyView(Text(textProperties.text ?? "error"))
    }
}
