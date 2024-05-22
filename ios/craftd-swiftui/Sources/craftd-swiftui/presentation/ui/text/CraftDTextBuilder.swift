import SwiftUI

class CraftDTextBuilder: CraftDBuilder {
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
