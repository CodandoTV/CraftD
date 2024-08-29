import SwiftUI

class CraftDEmptyBuilder: CraftDBuilder {
    public func craft(model: SimpleProperties, listener: @escaping CraftDViewListener) -> any View {
        EmptyView()
    }
}
