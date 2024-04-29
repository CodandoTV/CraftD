package com.github.codandotv.craftd.androidcore.data.model.button

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign

@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Stable
data class ButtonProperties(
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("textColorHex") val textColorHex: String? = null,
    @JsonProperty("align") val align: CraftDAlign? = null,
    @JsonProperty("textAlign") val textAlign: CraftDAlign? = null,
    @JsonProperty("textSize") val textSize: String? = null,
    @JsonProperty("textAllCaps") val textAllCaps: Boolean? = false,
    @JsonProperty("fillMaxSize") val fillMaxSize: Boolean? = false,
    @JsonProperty("backgroundHex") val backgroundHex: String? = null,
    @JsonProperty("actionProperties") var actionProperties: ActionProperties? = null,
)
