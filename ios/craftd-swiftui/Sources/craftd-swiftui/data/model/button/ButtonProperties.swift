struct ButtonProperties: Codable {
    var text: String?
    var textColorHex: String?
    var align: CraftDAlign?
    var textAlign: CraftDAlign?
    let textSize: String?
    let textAllCaps: Bool?
    let fillMaxSize: Bool?
    let backgroundHex: String?
    let actionProperties: ActionProperties?
}
