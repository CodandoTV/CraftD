import craftd_swiftui

struct ContentView: View {
    var list = [SimpleProperties]()
    let decoder = JSONDecoder()
    let bundle = Bundle.main.path(forResource: "dynamic", ofType: "json")
    var craftBuilders = CraftDBuilders()
    
    init(){
        do {
            let fileURL = URL(fileURLWithPath: bundle!)
            let jsonData = try Data(contentsOf: fileURL)
            list = try decoder.decode([SimpleProperties].self, from: jsonData)
        } catch {
            print("Erro ao ler o arquivo JSON:", error.localizedDescription)
        }
    }

    var body: some View {
        LazyVStack {
            ForEach(list) { item in
                let builder = craftBuilders.getBuilder(key: item.key)
                builder.craft(model: item) { action in
                    print(">>>>\(action)")
                }
            }
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
