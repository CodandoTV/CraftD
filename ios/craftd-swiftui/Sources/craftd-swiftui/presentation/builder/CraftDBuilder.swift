import SwiftUI
import Foundation

public protocol CraftDBuilder {
    var key: String { get }
    
    func craft(
        model: SimpleProperties,
        listener: @escaping CraftDViewListener
    ) -> any View
}

public extension CraftDBuilder {
    var key: String {
        String("\(Self.self)".replacingOccurrences(of: "Builder", with: ""))
    }
    
    var decoder: JSONDecoder {
        JSONDecoder()
    }
}
