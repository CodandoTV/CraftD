package com.github.rviannaoliveira.dynamic.core.data.model.text

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.rviannaoliveira.dynamic.core.data.model.action.DynamicActionProperties
import com.github.rviannaoliveira.dynamic.core.domain.Align
import com.github.rviannaoliveira.dynamic.core.domain.DynamicTextStyle

@JsonIgnoreProperties(ignoreUnknown = true)
data class TextProperties(
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("textColorHex") val textColorHex: String? = null,
    @JsonProperty("align") val align: Align? = null,
    @JsonProperty("textSize") val textSize: String? = null,
    @JsonProperty("backgroundHex") val backgroundHex: String? = null,
    @JsonProperty("textStyle") val textStyle: DynamicTextStyle? = null,
    @JsonProperty("textAllCaps") val textAllCaps: Boolean? = false,
    @JsonProperty("actionProperties") var actionProperties: DynamicActionProperties? = null,
    @JsonProperty("textHtml") val textHtml: String? = null,
)

