# iOS / SwiftUI — Instruções de plataforma

> Leia este arquivo ao iniciar qualquer task iOS. Ignore `android_kmp/` e `flutter/`.

---

## Abstrações principais

| Classe | Papel |
|---|---|
| `CraftDBuilder` | Protocol base para criar componentes |
| `CraftDBuilderManager` | Registra e resolve builders pelo `key` |
| `CraftDynamic` | View principal que renderiza o SDUI |
| `SimpleProperties` | Modelo base de dados |
| `ActionProperties` | Dados de ação (deeplink + analytics) |
| `CraftDViewListener` | Callback de ações para o consumidor |

---

## Estrutura de pastas

```
ios/craftd-swiftui/
  Sources/CraftD/
    builder/        → CraftDBuilder.swift (protocol), CraftDBuilderManager.swift
    ui/
      [name]/
        CraftD[Name].swift         → SwiftUI View do componente
        CraftD[Name]Builder.swift  → implementa CraftDBuilder
    model/
      [Name]Properties.swift       → struct do modelo
```

## Padrão por novo componente (exemplo: CraftDFoo)

1. `Sources/CraftD/model/FooProperties.swift` — struct do modelo
2. `Sources/CraftD/ui/foo/CraftDFoo.swift` — SwiftUI View
3. `Sources/CraftD/ui/foo/CraftDFooBuilder.swift` — implementa CraftDBuilder
4. Registrar no `CraftDBuilderManager`
5. Adicionar ao sample app iOS

## Referência

Consultar `CraftDButton` / `CraftDButtonBuilder` como padrão antes de criar algo novo.