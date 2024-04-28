import SwiftUI

public protocol CraftDBuilder {
    associatedtype T: Codable
    var key: String { get }
    
    func craft(model: SimpleProperties, listener: CraftDViewListener) -> AnyView
}

extension CraftDBuilder {
    func dataDecoding(data: Data) -> T? {
        do {
            return try JSONDecoder().decode(T.self, from: data)
        } catch {
            print(error.localizedDescription)
            return nil
        }
    }
}
