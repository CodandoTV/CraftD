## ADDED Requirements

### Requirement: ImageProperties model exists in craftd-core
The system SHALL provide a `@Serializable` data class `ImageProperties` in `craftd-core/commonMain` containing at minimum: `url: String?`, `contentScale: CraftDContentScale?`, `contentDescription: String?`, and `actionProperties: ActionProperties?`.

#### Scenario: Server payload is deserialized into ImageProperties
- **WHEN** the SDUI JSON contains an image component value with `url`, `contentScale`, and `contentDescription` fields
- **THEN** `ImageProperties` is correctly deserialized with all fields populated

#### Scenario: Optional fields are absent from payload
- **WHEN** the SDUI JSON image value omits `contentScale` or `contentDescription`
- **THEN** the missing fields default to `null` and no exception is thrown

### Requirement: CraftDContentScale enum covers standard scale modes
The system SHALL provide a `CraftDContentScale` enum in `craftd-core/commonMain/domain/` with values: `CROP`, `FIT`, `FILL_BOUNDS`, `FILL_WIDTH`, `FILL_HEIGHT`, `INSIDE`, `NONE`.

#### Scenario: All enum values map to a ContentScale in Compose
- **WHEN** `toContentScale()` is called on each `CraftDContentScale` value
- **THEN** each value returns the corresponding `androidx.compose.ui.layout.ContentScale`

### Requirement: IMAGE_COMPONENT key registered in CraftDComponentKey
The system SHALL include `IMAGE_COMPONENT("CraftDImage")` as an entry in the `CraftDComponentKey` enum in `craftd-core`.

#### Scenario: Key is accessible from compose and xml modules
- **WHEN** `CraftDComponentKey.IMAGE_COMPONENT.key` is referenced from `craftd-compose` or `craftd-xml`
- **THEN** it returns the string `"CraftDImage"`

### Requirement: CraftDImageBuilder renders image via injected loader (Compose)
The system SHALL provide `CraftDImageBuilder` in `craftd-compose/commonMain` that accepts an `imageLoader` composable lambda and delegates image rendering to it. The builder SHALL handle a null `ImageProperties` gracefully (no-op / empty render).

#### Scenario: Builder delegates rendering to injected imageLoader
- **WHEN** `CraftDImageBuilder(imageLoader = myLoader).craft(model, listener)` is called with a valid `ImageProperties`
- **THEN** `myLoader` is invoked with the `url`, `contentDescription`, and a `Modifier`

#### Scenario: Null ImageProperties results in no-op
- **WHEN** the JSON value cannot be deserialized into `ImageProperties`
- **THEN** the builder renders nothing and does not throw

#### Scenario: ActionProperties triggers listener on click
- **WHEN** `ImageProperties.actionProperties` is non-null and the user taps the image
- **THEN** `CraftDViewListener.invoke(actionProperties)` is called

### Requirement: CraftDImageBuilder registered in Compose CraftDBuilderManager
The system SHALL register `CraftDComponentKey.IMAGE_COMPONENT.key to CraftDImageBuilder(imageLoader)` in `CraftDBuilderManager` (craftd-compose). The registration SHALL require the consumer to pass the `imageLoader` at setup time.

#### Scenario: CraftDBuilderManager resolves image builder by key
- **WHEN** `getBuilder("CraftDImage")` is called on a configured `CraftDBuilderManager`
- **THEN** it returns the registered `CraftDImageBuilder` instance

### Requirement: CraftDImageComponentRender renders image via injected loader (XML)
The system SHALL provide `CraftDImageComponentRender` in `craftd-xml` that accepts an `imageLoader: (url: String, imageView: ImageView) -> Unit` and delegates loading to it.

#### Scenario: Render delegates loading to injected loader
- **WHEN** `bindView` is called with a valid `ImageProperties`
- **THEN** the injected `imageLoader` is called with the `url` and the `ImageView`

#### Scenario: ActionProperties triggers listener on click in XML
- **WHEN** `ImageProperties.actionProperties` is non-null and the user taps the image view
- **THEN** `onClickListener.invoke(actionProperties)` is called

### Requirement: CraftDImageComponentRender registered in XML CraftDBuilderManager
The system SHALL add `CraftDImageComponentRender(imageLoader, onAction)` to the render list returned by `getBuilderRenders()` in `craftd-xml`'s `CraftDBuilderManager`.

#### Scenario: XML BuilderManager resolves image render by key
- **WHEN** `getBuilderRenders()` is called and the simpleProperties list contains a key `"CraftDImage"`
- **THEN** the returned list includes a `CraftDImageComponentRender` bound to that key
