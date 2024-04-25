import SwiftUI

public class CraftDTextBuilder : CraftDBuilder {
    public static func == (lhs: CraftDTextBuilder, rhs: CraftDTextBuilder) -> Bool {
        lhs.key == rhs.key
    }
    
    public func hash(into hasher: inout Hasher) {
        hasher.combine(ObjectIdentifier(self))
    }
    
    public func craft(model: SimpleProperties,
               listener: CraftDViewListener) -> any View {
//        let textProperties = model.value.convertToVO<TextProperties>()
       return Text("a")
    }
    
    public let key = "CraftDText"
}
