# Context: add-craftd-image

Baseado no PR #78 (https://github.com/CodandoTV/CraftD/pull/78) que ficou incompleto.

## O que deve ser implementado

Componente `CraftDImage` para suporte a imagens locais e de rede nas plataformas Android Compose, KMP e XML.

## Requisitos derivados do review do PR #78

- **Abstração do loader**: não acoplar Coil diretamente no builder. Expor parâmetro `imageLoader` no construtor do `CraftDImageBuilder` para o consumidor injetar a implementação (regra 10 do CLAUDE.md)
- **Registro no CraftDBuilderManager Compose/KMP**: `CraftDComponentKey.IMAGE_COMPONENT.key to CraftDImageBuilder()`
- **Registro no CraftDBuilderManager XML**: adicionar `ImageComponentRender` em `getBuilderRenders()`
- **Testes unitários**: incluir testes para `toContentScale()` (torná-la `internal`)
- **Evitar duplicação commonMain/androidMain**: manter implementação apenas em `commonMain` salvo necessidade real de `expect/actual`
- Coil 3 com suporte multiplatforma é a lib de referência, mas deve ser injetada, não acoplada
