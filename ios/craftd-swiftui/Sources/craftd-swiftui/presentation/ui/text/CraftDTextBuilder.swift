import SwiftUI

public class CraftDTextBuilder: CraftDBuilder {
    public let key = "CraftDText"
    
    let decoder = JSONDecoder()
    
    public func craft(
        model: SimpleProperties,
        listener: CraftDViewListener
    ) -> any View {
        do {
            let properties = try model.decodeValue(TextProperties.self, using: decoder)
            let text = properties.text ?? ""
            let textSize = properties.textSize ?? "20"
            let textAllCaps = properties.textAllCaps ?? false
            return Text(textAllCaps ? text.uppercased() : text)
                .font(.system(size: CGFloat((textSize as NSString).floatValue)))
                .foregroundStyle(Color(hex: properties.textColorHex ?? "#0000FF"))
                .background(Color(hex: properties.backgroundHex ?? "#FFF"))
        } catch {
            return EmptyView()
        }
    }
}

#Preview {
    let builder = CraftDTextBuilder()
    return builder
        .craft(model:
                SimpleProperties(key: builder.key,
                                 value: [
                                    "text": "Texto",
                                    "textColorHex": "#f3aede",
                                    "textSize": "15",
                                    "textAllCaps": true,
                                    "backgroundHex": "#351C75"
                                 ])) { actionProperties in
                                     debugPrint(actionProperties)
                                 }
}
