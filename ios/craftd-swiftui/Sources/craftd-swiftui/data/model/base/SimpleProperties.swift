import Foundation

public struct SimpleProperties : Codable, Identifiable {
    public let key : String
    public let value : SimpleValue
    public let id = UUID()
}

public protocol SimpleValue : Codable{
    
}
