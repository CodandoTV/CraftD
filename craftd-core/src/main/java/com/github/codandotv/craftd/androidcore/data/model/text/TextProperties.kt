package com.github.codandotv.craftd.androidcore.data.model.text

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle

@JsonIgnoreProperties(ignoreUnknown = true)
data class TextProperties(
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("textColorHex") val textColorHex: String? = null,
    @JsonProperty("align") val align: CraftDAlign? = null,
    @JsonProperty("textSize") val textSize: String? = null,
    @JsonProperty("backgroundHex") val backgroundHex: String? = null,
    @JsonProperty("textStyle") val textStyle: CraftDTextStyle? = null,
    @JsonProperty("textAllCaps") val textAllCaps: Boolean? = false,
    @JsonProperty("actionProperties") var actionProperties: ActionProperties? = null,
    @JsonProperty("textHtml") val textHtml: String? = null,
)

