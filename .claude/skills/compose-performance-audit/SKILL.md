---
name: compose-performance-audit
description: Audit and improve Jetpack Compose runtime performance. Use when diagnosing slow rendering, janky scrolling, or excessive recompositions in CraftD components.
---

# Compose Performance Audit

## Workflow

1. **Code-first review** — analyze the provided Composable for:
   - Recomposition storms from unstable parameters
   - Unstable keys in lazy lists
   - Heavy work during composition (formatting, sorting, allocation)
   - Missing `remember` calls
   - Deep nesting causing layout thrash

2. **Profiling guidance** (if code review is inconclusive):
   - Layout Inspector + Recomposition Highlights
   - Perfetto traces
   - Macrobenchmark
   - Always profile on release builds with R8 enabled

3. **Remediate**:
   - Add stability annotations (`@Stable`, `@Immutable`)
   - Use stable keys in lazy lists
   - Defer state reads
   - Memoize with `remember` / `derivedStateOf`

## CraftD-specific concerns

- `CraftDynamic` renderiza listas de componentes — prestar atenção em recomposições desnecessárias quando `properties` muda
- Builders devem ser `remember`-ed no lado do consumidor
- `ActionProperties` como parâmetro de lambda deve ser estável
