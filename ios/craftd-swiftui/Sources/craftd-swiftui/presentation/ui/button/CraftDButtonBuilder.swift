import SwiftUI

class CraftDButtonBuilder: CraftDBuilder {
    public func craft(model: SimpleProperties, listener: CraftDViewListener) -> any View {
        do {
            let properties = try model.decodeValue(ButtonProperties.self, using: decoder)
            return CraftDButton(properties)
        } catch {
            return EmptyView()
        }
    }
}
