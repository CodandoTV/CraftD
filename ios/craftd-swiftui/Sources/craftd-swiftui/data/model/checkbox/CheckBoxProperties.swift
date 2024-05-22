struct CheckBoxProperties: Codable {
    var text: String?
    var align: CraftDAlign?
    var textAlign: CraftDAlign?
    var enable: Bool?
    var hasItRightText: Bool?
    var actionProperties: ActionProperties?
    var style: StyleProperties?
}

struct StyleProperties: Codable {
    var checkedColorHex: String?
    var uncheckedColorHex: String?
}
