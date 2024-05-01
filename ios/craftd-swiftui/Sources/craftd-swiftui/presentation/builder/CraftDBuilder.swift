import SwiftUI
import Foundation

public protocol CraftDBuilder {
    var key: String { get }
    
    func craft(
        model: SimpleProperties,
        listener: CraftDViewListener
    ) -> any View
}
