import SwiftUI
import CraftDSwiftUI

@main
struct CraftDSampleApp: App {
    var body: some Scene {
        WindowGroup {
            let craftBuilders = CraftDBuilderManager()
            CraftDynamic(craftBuilders: craftBuilders)
        }
    }
}
