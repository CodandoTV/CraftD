## Why

CraftD currently supports text and button components but has no image component, leaving apps unable to render image elements via Server Driven UI. Adding `CraftDImage` closes this gap and enables servers to deliver image-based layouts across all platforms.

## What Changes

- New `ImageProperties` data class in `craftd-core/commonMain` (url, contentScale, contentDescription)
- New `CraftDImage` composable in `craftd-compose/commonMain`
- New `CraftDImageBuilder` (Compose/KMP) registered in `CraftDBuilderManager`
- New `CraftDImageBuilder` (XML) with `ImageComponentRender` registered in the XML `CraftDBuilderManager`
- `CraftDComponentKey.IMAGE_COMPONENT` enum entry added
- `imageLoader` abstraction parameter on the builder — Coil (or any loader) injected by the consumer, not coupled inside the lib
- Unit tests for `ImageProperties` and `toContentScale()` extension

## Capabilities

### New Capabilities

- `craftd-image`: Renders image components via SDUI on Android Compose, KMP, and XML platforms, with an injectable image loader abstraction.

### Modified Capabilities

<!-- none -->

## Impact

- `craftd-core`: new model class `ImageProperties`, new enum entry in `CraftDComponentKey`
- `craftd-compose`: new composable + builder, registration in `CraftDBuilderManager`
- `craftd-xml`: new render + registration in XML `CraftDBuilderManager`
- External dependency: Coil 3 (multiplatform) is the reference loader but must be injected by the consumer — no new lib dep added to core/compose modules
