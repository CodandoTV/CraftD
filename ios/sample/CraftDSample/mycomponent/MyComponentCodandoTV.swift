import CraftDSwiftUI
import SwiftUI

struct MyComponentCodandoTV: View {
    let text: String
    let listener: CraftDViewListener
    
    init(_ properties: MyComponentCodandoTVProperties,
         listener: @escaping CraftDViewListener) {
        self.text = properties.text ?? "empty"
        self.listener = listener
    }
    var body: some View {
        Text(text)
    }
}
