import CraftDSwiftUI
import SwiftUI

class MyComponentCodandoTVBuilder: CraftDBuilder {
    
    public func craft(
    model: SimpleProperties,
    listener: @escaping CraftDViewListener) -> any View {
        do {
            let properties = try model.decodeValue(MyComponentCodandoTVProperties.self, using: decoder)
            return MyComponentCodandoTV(
                properties, listener: listener)
        } catch {
            return EmptyView()
        }
    }

}
