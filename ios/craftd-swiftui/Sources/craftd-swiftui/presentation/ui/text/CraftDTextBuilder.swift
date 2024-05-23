import SwiftUI

class CraftDTextBuilder: CraftDBuilder {
    public func craft(
        model: SimpleProperties,
        listener: CraftDViewListener
    ) -> any View {
        do {
            let properties = try model.decodeValue(TextProperties.self, using: decoder)
            return CraftDText(properties)
        } catch {
            return EmptyView()
        }
    }
}
