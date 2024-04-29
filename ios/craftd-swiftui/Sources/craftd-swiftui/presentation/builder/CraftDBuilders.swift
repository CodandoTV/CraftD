import Foundation

enum CraftDBuildersError: Error {
    case builderNotFound
}

public class CraftDBuilders {
    public init() {}
    
    private var listCraftDBuilder: [String: CraftDBuilder] = [:]
    
    public func add(builders: [CraftDBuilder]) -> CraftDBuilders {
        builders.forEach { builder in add(builder: builder) }
        return self
    }
    
    public func add(builder: CraftDBuilder) -> CraftDBuilders {
        listCraftDBuilder[builder.key] = builder
        return self
    }
    
    public func getBuilder(key: String) -> CraftDBuilder {
        guard let builder = listCraftDBuilder[key] else {
            return CraftDTextBuilder()
        }
        return builder
    }
}
