import Foundation

public struct SimpleProperties: Codable, Identifiable {
    public let key: String
    public let value: Data
    public let id = UUID()
}
