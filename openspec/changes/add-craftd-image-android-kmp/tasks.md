## 1. craftd-core: Model and enum

- [ ] 1.1 Add `IMAGE_COMPONENT("CraftDImage")` to `CraftDComponentKey` enum
- [ ] 1.2 Create `CraftDContentScale` enum in `craftd-core/commonMain/domain/` with values: `CROP`, `FIT`, `FILL_BOUNDS`, `FILL_WIDTH`, `FILL_HEIGHT`, `INSIDE`, `NONE`
- [ ] 1.3 Create `ImageProperties` data class in `craftd-core/commonMain/data/model/image/ImageProperties.kt` with fields: `url`, `contentScale`, `contentDescription`, `actionProperties`

## 2. craftd-compose: Composable and builder

- [ ] 2.1 Create `toContentScale()` internal extension function in `craftd-compose/commonMain` mapping `CraftDContentScale` → `ContentScale`
- [ ] 2.2 Create `CraftDImage` composable in `craftd-compose/commonMain/ui/image/CraftDImage.kt` accepting `ImageProperties`, `imageLoader` lambda, `onAction` callback, and `modifier`
- [ ] 2.3 Create `CraftDImageBuilder` in `craftd-compose/commonMain/ui/image/CraftDImageBuilder.kt` with injectable `imageLoader` constructor parameter
- [ ] 2.4 Register `CraftDImageBuilder` in `CraftDBuilderManager` (craftd-compose)

## 3. craftd-xml: Component and render

- [ ] 3.1 Create `CraftDImageComponent` (custom View or standard `ImageView` wrapper) in `craftd-xml/src/main/kotlin/.../ui/image/`
- [ ] 3.2 Create `CraftDImageComponentRender` in `craftd-xml/src/main/kotlin/.../ui/image/CraftDImageComponentRender.kt` with injectable `imageLoader: (url: String, imageView: ImageView) -> Unit`
- [ ] 3.3 Register `CraftDImageComponentRender` in `craftd-xml`'s `CraftDBuilderManager.getBuilderRenders()`

## 4. Tests

- [ ] 4.1 Unit test for `ImageProperties` serialization/deserialization (craftd-core)
- [ ] 4.2 Unit test for `toContentScale()` covering all `CraftDContentScale` values
- [ ] 4.3 Unit test for `CraftDImageBuilder` — verify `imageLoader` is called with correct args and `actionProperties` triggers listener

## 5. Documentation

- [ ] 5.1 Update `docs/how-to-use/compose.md` with `CraftDImage` usage example (including imageLoader injection with Coil)
- [ ] 5.2 Update `docs/how-to-use/view-system.md` with `CraftDImageComponentRender` usage example

## 6. Cleanup

- [ ] 6.1 Delete `openspec/changes/add-craftd-image/notes.md` (context consumed)
