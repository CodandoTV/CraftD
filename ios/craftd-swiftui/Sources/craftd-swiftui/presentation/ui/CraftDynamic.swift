import SwiftUI

public struct CraftDynamic : View {
    private var craftBuilders: CraftDBuilderManager
    private var listener: CraftDViewListener
    
    public init(craftBuilders: CraftDBuilderManager,
                listener: @escaping CraftDViewListener
    ) {
        self.craftBuilders = craftBuilders
        self.listener = listener
    }
    
    @State private var list: [SimpleProperties] = []
        
    public var body: some View {
        ScrollView {
            LazyVStack {
                ForEach(list) { item in
                    let builder = craftBuilders.getBuilder(
                        key: item.key
                    )
                    AnyView(erasing: builder.craft(
                        model: item,
                        listener: listener)
                    )
                }
            }
        }
        .padding()
        .onAppear(perform: {
            do {
                try loadJson()
            } catch {
                print(error.localizedDescription)
            }
        })
    }
    
    private func loadJson() throws {
        guard let jsonData = Bundle.main.path(forResource: "dynamic", ofType: "json") else {
            return
        }
        let fileURL = URL(fileURLWithPath: jsonData)
        let data = try Data(contentsOf: fileURL)
        let container = try SimplePropertiesContainer(from: data)
        list = container.items
    }
    
    
    
}
