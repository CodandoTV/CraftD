import SwiftUI

public class CraftDButtonBuilder: CraftDBuilder {
    public let key = "CraftDButton"
    
    let decoder = JSONDecoder()
    
    public func craft(model: SimpleProperties, listener: CraftDViewListener) -> any View {
        do {
            let properties = try model.decodeValue(ButtonProperties.self, using: decoder)
            return Button(action: {
                debugPrint(properties.actionProperties)
                #warning("TODO: implementar actions")
            }, label: {
                let text = properties.text ?? ""
                let textSize = properties.textSize ?? "20"
                let fillMaxSize = properties.fillMaxSize ?? false
                let textAllCaps = properties.textAllCaps ?? false
                Text(textAllCaps ? text.uppercased() : text)
                    .font(.system(size: CGFloat((textSize as NSString).floatValue)))
                    .frame(maxWidth: fillMaxSize ? .infinity : nil, maxHeight: fillMaxSize ? .infinity : nil,
                           alignment: properties.textAlign?.alignment ?? .center)
            })
            .foregroundStyle(Color(hex: properties.textColorHex ?? "#0000FF"))
            .background(Color(hex: properties.backgroundHex ?? "#FFF"))
            .frame(alignment: properties.align?.alignment ?? .center)
        } catch {
            return EmptyView()
        }
    }
}

#Preview {
    let builder = CraftDButtonBuilder()
    return builder
        .craft(model:
                SimpleProperties(key: builder.key,
                                 value: [
                                    "text": "Título do botão",
                                    "textColorHex": "#f3aede",
                                    "align": "CENTER",
                                    "textAlign": "LEFT",
                                    "textSize": "50",
                                    "textAllCaps": false,
                                    "fillMaxSize": true,
                                    "backgroundHex": "#351C75",
                                    "actionProperties": [
                                        "deeplink": "deeplink",
                                        "analytics": nil
                                    ]
                                 ])) { actionProperties in
                                     debugPrint(actionProperties)
                                 }
}
