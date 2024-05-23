import SwiftUI

struct CraftDText: View {
    let text: String
    let textSize: String
    let textAllCaps: Bool
    let textColorHex: String
    let backgroundHex: String
    
    init(_ properties: TextProperties) {
        self.text = properties.text ?? ""
        self.textSize = properties.textSize ?? "20"
        self.textAllCaps = properties.textAllCaps ?? false
        self.textColorHex = properties.textColorHex ?? "#0000FF"
        self.backgroundHex = properties.backgroundHex ?? "#FFF"
    }
    var body: some View {
        Text(textAllCaps ? text.uppercased() : text)
            .font(.system(size: CGFloat((textSize as NSString).floatValue)))
            .foregroundStyle(Color(hex: textColorHex))
            .background(Color(hex: backgroundHex))
    }
}

#Preview {
    CraftDText(.init(text: "Texto",
                     textColorHex: "#F3AEDE",
                     textSize: "15",
                     backgroundHex: "#351C75",
                     textAllCaps: true,
                     textHtml: nil))
}
