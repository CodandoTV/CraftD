import SwiftUI

public protocol CraftDBuilder  : Hashable{
    var key: String{get}
    
    func craft(model: SimpleProperties,
               listener: CraftDViewListener) -> any View
}

