import SwiftUI

struct CraftDCheckBox: View {
    @State var isChecked: Bool = false
    let text: String
    let checkedColorHex: String
    let uncheckedColorHex: String
    let disabled: Bool
    let textAlign: Alignment
    let align: Alignment
    let hasItRightText: Bool
    let actionProperties: ActionProperties?
    
    init(_ properties: CheckBoxProperties) {
        self.text = properties.text ?? ""
        self.checkedColorHex = properties.style?.checkedColorHex ?? "#0000FF"
        self.uncheckedColorHex = properties.style?.uncheckedColorHex ?? "#0000FF"
        self.disabled = !(properties.enable ?? true)
        self.textAlign = properties.textAlign?.alignment ?? .center
        self.align = properties.align?.alignment ?? .center
        self.hasItRightText = properties.hasItRightText ?? true
        self.actionProperties = properties.actionProperties
    }
    
    
    var checkImageName: String {
        isChecked ? "square" : "checkmark.square.fill"
    }
    
    var color: Color {
        Color(hex: isChecked ? checkedColorHex : uncheckedColorHex)
    }
    
    var body: some View {
        let image = Image(systemName: checkImageName)
            .renderingMode(.template)
            .foregroundStyle(color)
            .frame(width:20, height:20, alignment: .center)
        let text = Text(text)
            .frame(maxWidth: .infinity,
                   alignment: textAlign)
        Button(action: {
            debugPrint(actionProperties)
            #warning("TODO: implementar actions")
            isChecked.toggle()
        }, label: {
            HStack(spacing: 10) {
                if hasItRightText {
                    image
                    text
                } else {
                    text
                    image
                }
            }
        })
        .frame(alignment: align)
        .padding()
        .disabled(disabled)
    }
}

#Preview {
    CraftDCheckBox(CheckBoxProperties(
        text: "Aceito um gatinho",
        align: .left,
        textAlign: .left,
        enable: true,
        hasItRightText: true,
        actionProperties: .init(
            deeplink: "deeplink",
            analytics: .init(
                category: "category",
                action: "action",
                label: "label",
                track: "track")),
        style: .init(
            checkedColorHex: "#351C75",
            uncheckedColorHex: "#351C75"
        )))
}
