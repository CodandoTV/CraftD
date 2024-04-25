import Foundation

public class CraftDBuilders {
    public init() {}
    
    private let listCraftDBuilder : [any CraftDBuilder] = [
        CraftDTextBuilder()
    ]
    
    private var customBuilders = [any CraftDBuilder]()
    
    public func addBuilderRenders(listBuilders: [any CraftDBuilder]) -> CraftDBuilders {
        customBuilders.append(contentsOf: listBuilders)
        return self
    }
    
    public func addBuilderRender(builder: any CraftDBuilder) -> CraftDBuilders {
        customBuilders.append(builder)
        return self
    }
    
    public func getBuilder(key: String) -> any CraftDBuilder {
        var newList = listCraftDBuilder
        newList.append(contentsOf: customBuilders)
        
        return newList.first { item in
            item.key == key
        } ?? CraftDTextBuilder()
    }
}
