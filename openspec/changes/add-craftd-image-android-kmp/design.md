## Context

CraftD has `CraftDButton`, `CraftDText`, and `CraftDCheckBox` as built-in components. There is no image component. PR #78 attempted this but was rejected because it coupled Coil directly inside the builder, violating architectural rule 10 (external dependencies must be abstracted). This design delivers `CraftDImage` following the correct pattern.

The component must work on three targets: Android Compose, KMP (commonMain), and Android View System (XML). iOS and Flutter are out of scope for this change.

## Goals / Non-Goals

**Goals:**
- Add `ImageProperties` model to `craftd-core/commonMain`
- Add `CraftDImage` composable and `CraftDImageBuilder` to `craftd-compose/commonMain`
- Add `CraftDImageComponentRender` to `craftd-xml`
- Register both builders in their respective `CraftDBuilderManager`
- Add `IMAGE_COMPONENT` entry to `CraftDComponentKey`
- Keep image loading completely decoupled from the library via an injectable `imageLoader` lambda

**Non-Goals:**
- iOS / Flutter implementations (separate change)
- Bundling Coil or any image lib as a transitive dependency of craftd-compose or craftd-xml
- Caching, placeholder, or error-image strategies inside the lib (delegated to the injected loader)

## Decisions

### 1. Injectable imageLoader over a built-in loader

`CraftDImageBuilder` receives an `imageLoader: @Composable (url: String, contentDescription: String?, modifier: Modifier) -> Unit` parameter in its constructor. The consumer provides the Coil (or any other) implementation.

**Why:** Rule 10 forbids coupling third-party libs inside builders. The consumer already manages dependencies; injecting via constructor is the established escape hatch.

**Alternative considered:** Ship a default Coil implementation inside craftd-compose as an optional artifact. Rejected because it would introduce a transitive dependency and complicate version management.

### 2. ContentScale represented as a domain enum `CraftDContentScale`

A new enum `CraftDContentScale` lives in `craftd-core/commonMain/domain/` mirroring `CraftDAlign`. The builder maps it to `androidx.compose.ui.layout.ContentScale` via an `internal` extension function `toContentScale()`.

**Why:** `ContentScale` is a Compose type — it cannot live in `commonMain` of `craftd-core` (which must stay platform-free). The enum in core is the platform-neutral representation; the mapping lives in the compose module where it belongs.

**Alternative considered:** Pass a raw string and parse it in the builder. Rejected — no compile-time safety, harder to test.

### 3. XML implementation mirrors Compose: injectable loader as a View lambda

`CraftDImageComponentRender` receives `imageLoader: (url: String, imageView: ImageView) -> Unit` in its constructor. The consumer binds Coil (or Glide/Picasso) at the call site.

**Why:** Same rationale as decision 1, adapted to the View System API.

### 4. `IMAGE_COMPONENT` key follows existing naming convention

Key string: `"CraftDImage"` (consistent with `CRAFT_D` constant + component name pattern in `CraftDComponentKey`).

## Risks / Trade-offs

- **Consumer boilerplate**: every app must wire the `imageLoader` lambda. Mitigated by providing a clear usage example in `docs/how-to-use/compose.md` and `view-system.md`.
- **ContentScale mapping coverage**: if a new `ContentScale` variant is added by Compose later, the enum needs updating. Low risk — the set is stable.
- **XML ImageView constraints**: the XML component uses a standard `ImageView`; advanced features (rounded corners, cross-fade) remain the loader's responsibility. Acceptable — it matches the abstraction boundary.
