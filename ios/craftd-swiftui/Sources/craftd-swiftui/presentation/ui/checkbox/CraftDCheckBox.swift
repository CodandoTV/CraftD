import SwiftUI

struct CraftDCheckBox: View {
    @State var isChecked: Bool = false
    let text: String
    
    init(_ properties: CheckBoxProperties) {
        self.text = properties.text ?? ""
    }
    
    var checkImageName: String {
        isChecked ? "square" : "checkmark.square.fill"
    }
    
    var body: some View {
        
        Button(action: {
            isChecked.toggle()
        }, label: {
            HStack(alignment: .top, spacing: 10) {
                Image(systemName: checkImageName)
                    .frame(width:20, height:20, alignment: .center)
                Text(text)
            }
        })
    }
}

#Preview {
    CraftDCheckBox(CheckBoxProperties())
}
