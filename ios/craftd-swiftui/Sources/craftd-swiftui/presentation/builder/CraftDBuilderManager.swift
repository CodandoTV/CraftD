import Foundation

public class CraftDBuilderManager {
    public init() {
        add(builders: [
            CraftDTextBuilder(),
            CraftDButtonBuilder(),
            CraftDCheckBoxBuilder(),
            CraftDProgressBarBuilder(),
        ])
    }
    
    private var listCraftDBuilder: [String: CraftDBuilder] = [:]
    
    public func add(builders: [CraftDBuilder]) -> CraftDBuilderManager {
        builders.forEach { builder in add(builder: builder) }
        return self
    }
    
    public func add(builder: CraftDBuilder) -> CraftDBuilderManager {
        listCraftDBuilder[builder.key] = builder
        return self
    }
    
    public func getBuilder(key: String) -> CraftDBuilder {
        guard let builder = listCraftDBuilder[key] else {
            return CraftDEmptyBuilder()
        }
        return builder
    }
}
