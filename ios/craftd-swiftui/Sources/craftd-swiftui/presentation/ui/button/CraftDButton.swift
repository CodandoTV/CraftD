import SwiftUI

struct CraftDButton: View {
    let text: String
    let textSize: String
    let fillMaxSize: Bool
    let textAllCaps: Bool
    let textAlign: Alignment
    let actionProperties: ActionProperties?
    let textColorHex: String
    let backgroundHex: String
    let alignment: Alignment
    let listener: CraftDViewListener
    
    init(_ properties: ButtonProperties, listener: @escaping CraftDViewListener) {
        self.text = properties.text ?? ""
        self.textSize = properties.textSize ?? "20"
        self.fillMaxSize = properties.fillMaxSize ?? false
        self.textAllCaps = properties.textAllCaps ?? false
        self.textAlign = properties.textAlign?.alignment ?? .center
        self.actionProperties = properties.actionProperties
        self.textColorHex = properties.textColorHex ?? "#0000FF"
        self.backgroundHex = properties.backgroundHex ?? "#FFF"
        self.alignment = properties.align?.alignment ?? .center
        self.listener = listener
    }
    var body: some View {
        Button(action: {
            listener(actionProperties)
        }, label: {
            Text(textAllCaps ? text.uppercased() : text)
                .font(.system(size: CGFloat((textSize as NSString).floatValue)))
                .frame(maxWidth: fillMaxSize ? .infinity : nil, maxHeight: fillMaxSize ? .infinity : nil,
                       alignment: textAlign)
        })
        .foregroundStyle(Color(hex: textColorHex))
        .background(Color(hex: backgroundHex))
        .frame(alignment: alignment)
    }
}

#Preview {
    CraftDButton(ButtonProperties(text: "Título do botão",
                                  textColorHex: "#F3AEDE",
                                  align: .center,
                                  textAlign: .left,
                                  textSize: "50",
                                  textAllCaps: false,
                                  fillMaxSize: true,
                                  backgroundHex: "#351C75",
                                  actionProperties: .init(
                                    deeplink: "deeplink",
                                    analytics: nil)),
                 listener: { _ in })
}
