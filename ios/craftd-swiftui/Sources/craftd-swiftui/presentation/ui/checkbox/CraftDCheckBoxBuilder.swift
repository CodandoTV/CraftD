import SwiftUI

class CraftDCheckBoxBuilder: CraftDBuilder {
    let decoder = JSONDecoder()
    
    public func craft(model: SimpleProperties, listener: CraftDViewListener) -> any View {
        do {
            let properties = try model.decodeValue(CheckBoxProperties.self, using: decoder)
            return CraftDCheckBox(properties)
        } catch {
            return EmptyView()
        }
    }
}
