import SwiftUI
import CraftDSwiftUI

@main
struct CraftDSampleApp: App {
    var body: some Scene {
        WindowGroup {
            let craftBuilders = CraftDBuilderManager()
                .add(builder: MyComponentCodandoTVBuilder()
                )
           
            CraftDynamic(
                craftBuilders: craftBuilders
            ){ action in
                print(action?.analytics?.category ?? "")
                print(action?.analytics?.label ?? "")
                print(action?.deeplink ?? "")
            }
        }
    }
}
