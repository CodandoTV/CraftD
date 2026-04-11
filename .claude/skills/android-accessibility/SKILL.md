---
name: android-accessibility
description: Checklist de acessibilidade para componentes CraftD. Use ao criar ou revisar componentes Compose.
---

# Android Accessibility Checklist

## Para cada componente CraftD

- [ ] **Touch target mínimo de 48x48dp** para elementos interativos
- [ ] **`contentDescription`** em imagens e ícones (ou `null` se decorativo)
- [ ] **Contraste WCAG AA**: 4.5:1 para texto normal, 3.0:1 para texto grande/ícones
- [ ] **Semântica correta** em componentes customizados

## Padrões Compose

```kotlin
// Touch target
Box(Modifier.sizeIn(minWidth = 48.dp, minHeight = 48.dp)) { ... }

// Conteúdo decorativo
Icon(contentDescription = null)

// Merge semantics em componentes compostos
Modifier.semantics(mergeDescendants = true) { }

// Heading para navegação por screen reader
Modifier.semantics { heading() }
```

## CraftD-específico

- `CraftDButton` e componentes clicáveis devem garantir 48dp de touch target
- `onAction` deve ter `contentDescription` significativa quando possível
