import Foundation

public class CraftDBuilders {
    public init() {}
    
    private var listCraftDBuilder : [any CraftDBuilder] = [
        CraftDTextBuilder()
    ]
    
    private let customBuilders = [any CraftDBuilder]()
    
    public func addBuilderRenders(
        listBuilders: [any CraftDBuilder]
    ) -> CraftDBuilders {
        listCraftDBuilder.append(contentsOf: listBuilders)
        return self
    }
    
    public func addBuilderRender(
        builder: any CraftDBuilder
    ) -> CraftDBuilders {
        listCraftDBuilder.append(builder)
        return self
    }
    
    public func getBuilder(
        key: String
    ) -> any CraftDBuilder {
        var newList = listCraftDBuilder
        newList.append(contentsOf: customBuilders)
        
        return newList.first { item in
            item.key == key
        } ?? CraftDTextBuilder()
    }
}
