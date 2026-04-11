## 1. craftd-core: Model and enum

- [x] 1.1 Add `IMAGE_COMPONENT("CraftDImage")` to `CraftDComponentKey` enum
- [x] 1.2 Create `CraftDContentScale` enum in `craftd-core/commonMain/domain/` with values: `CROP`, `FIT`, `FILL_BOUNDS`, `FILL_WIDTH`, `FILL_HEIGHT`, `INSIDE`, `NONE`
- [x] 1.3 Create `ImageProperties` data class in `craftd-core/commonMain/data/model/image/ImageProperties.kt` with fields: `url`, `contentScale`, `contentDescription`, `actionProperties`

## 2. craftd-compose: Composable and builder

- [x] 2.1 Create `toContentScale()` internal extension function in `craftd-compose/commonMain` mapping `CraftDContentScale` → `ContentScale`
- [x] 2.2 Create `CraftDImage` composable in `craftd-compose/commonMain/ui/image/CraftDImage.kt` accepting `ImageProperties`, `imageLoader` lambda, `onAction` callback, and `modifier`
- [x] 2.3 Create `CraftDImageBuilder` in `craftd-compose/commonMain/ui/image/CraftDImageBuilder.kt` with injectable `imageLoader` constructor parameter
- [x] 2.4 `CraftDImageBuilder` not pre-registered in `CraftDBuilderManager` by design — requires `imageLoader` injection by the consumer via `builderManager.add(CraftDImageBuilder(imageLoader))`

## 3. craftd-xml: Component and render

- [x] 3.1 Create `CraftDImageComponent` (custom View or standard `ImageView` wrapper) in `craftd-xml/src/main/kotlin/.../ui/image/`
- [x] 3.2 Create `CraftDImageComponentRender` in `craftd-xml/src/main/kotlin/.../ui/image/CraftDImageComponentRender.kt` with injectable `imageLoader: (url: String, imageView: ImageView) -> Unit`
- [x] 3.3 Register `CraftDImageComponentRender` in `craftd-xml`'s `CraftDBuilderManager.getBuilderRenders()`

## 4. Tests

- [x] 4.1 Unit test for `ImageProperties` serialization/deserialization (craftd-core)
- [x] 4.2 Unit test for `toContentScale()` covering all `CraftDContentScale` values
- [x] 4.3 Unit test for `CraftDImageBuilder` — verify `imageLoader` is called with correct args and `actionProperties` triggers listener

## 5. Documentation

- [x] 5.1 Update `docs/how-to-use/compose.md` with `CraftDImage` usage example (including imageLoader injection with Coil)
- [x] 5.2 Update `docs/how-to-use/view-system.md` with `CraftDImageComponentRender` usage example

## 6. Sample app

- [x] 6.1 Register `CraftDImageBuilder` (with Coil imageLoader) in `app-sample-android` Compose setup
- [x] 6.2 Add image entry to the mock/sample JSON in `app-sample-android` so the component is visible na tela Compose
- [x] 6.3 Register `CraftDImageComponentRender` (with Picasso imageLoader) in `app-sample-android` XML setup
- [x] 6.4 Add image entry to the mock/sample JSON in `app-sample-android` so the component is visible na tela XML

## 7. Cleanup

- [x] 7.1 Delete `openspec/changes/add-craftd-image-android-kmp/notes.md` (context consumed)
