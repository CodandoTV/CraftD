import Foundation

enum SimplePropertiesError: Error {
    case decoding
}

public struct SimplePropertiesContainer {
    public let items: [SimpleProperties]
}

public extension SimplePropertiesContainer {
    init(from data: Data) throws {
        guard let items = try JSONSerialization.jsonObject(with: data) as? [[String: Any]] else {
            throw SimplePropertiesError.decoding
        }
        self.items = try items.map(SimpleProperties.init(from:))
    }
}

public struct SimpleProperties: Identifiable {
    public let key: String
    public let value: [String: Any]
    public let id = UUID()
}

public extension SimpleProperties {
    init(from data: Data) throws {
        guard let dictionary = try JSONSerialization.jsonObject(with: data) as? [String: Any] else {
            throw SimplePropertiesError.decoding
        }
        try self.init(from: dictionary)
    }
    
    init(from dictionary: [String: Any]) throws {
        guard
            let key = dictionary["key"] as? String,
            let value = dictionary["value"] as? [String: Any]
        else {
            throw SimplePropertiesError.decoding
        }
        self.key = key
        self.value = value
    }
}

public extension SimpleProperties {
    func decodeValue<T: Decodable>(_ valueType: T.Type, using decoder: JSONDecoder) throws -> T {
        let data = try JSONSerialization.data(withJSONObject: value)
        return try decodeValue(using: decoder, from: data)
    }
    
    private func decodeValue<T: Decodable>(using decoder: JSONDecoder, from data: Data) throws -> T {
        try decoder.decode(T.self, from: data)
    }
}
