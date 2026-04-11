# Flutter — Instruções de plataforma

> Leia este arquivo ao iniciar qualquer task Flutter. Ignore `android_kmp/` e `ios/`.

---

## Abstrações principais

| Classe | Papel |
|---|---|
| `CraftDynamic` | Widget principal que renderiza o SDUI |
| `CraftDViewListener` | Callback de ações para o consumidor |
| `SimpleProperties` | Modelo base de dados |
| `ActionProperties` | Dados de ação (deeplink + analytics) |
| `CraftDAlign` | Alinhamento de componentes |

---

## Estrutura de pastas

```
flutter/craftd_widget/
  lib/
    src/
      builder/      → CraftDBuilder (abstract), CraftDBuilderManager
      ui/
        [name]/
          craftd_[name].dart         → Widget do componente
          craftd_[name]_builder.dart → implementa CraftDBuilder
      model/
        [name]_properties.dart       → classe do modelo
```

## Padrão por novo componente (exemplo: CraftDFoo)

1. `lib/src/model/foo_properties.dart` — classe do modelo
2. `lib/src/ui/foo/craftd_foo.dart` — Widget
3. `lib/src/ui/foo/craftd_foo_builder.dart` — implementa CraftDBuilder
4. Registrar no `CraftDBuilderManager`
5. Adicionar ao sample app Flutter

## Convenções

- Nomes de arquivos em `snake_case`
- Classes em `PascalCase` com prefixo `CraftD`
- Dependências externas (ex: cached_network_image) injetadas via construtor, nunca acopladas no builder

## Referência

Consultar `CraftDButton` / `CraftDButtonBuilder` como padrão antes de criar algo novo.
