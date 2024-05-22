import SwiftUI
import Foundation

public protocol CraftDBuilder {
    var key: String { get }
    
    func craft(
        model: SimpleProperties,
        listener: CraftDViewListener
    ) -> any View
}

extension CraftDBuilder {
    var key: String {
        String("\(Self.self)".replacingOccurrences(of: "Builder", with: ""))
    }
    
    var decoder: JSONDecoder {
        JSONDecoder()
    }
}
