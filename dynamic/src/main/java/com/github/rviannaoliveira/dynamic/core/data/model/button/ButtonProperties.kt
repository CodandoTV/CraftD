package com.github.rviannaoliveira.dynamic.core.data.model.button

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.rviannaoliveira.dynamic.core.data.model.action.DynamicActionProperties
import com.github.rviannaoliveira.dynamic.core.domain.Align

@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Stable
data class ButtonProperties(
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("textColorHex") val textColorHex: String? = null,
    @JsonProperty("align") val align: Align? = null,
    @JsonProperty("textAlign") val textAlign: Align? = null,
    @JsonProperty("textSize") val textSize: String? = null,
    @JsonProperty("textAllCaps") val textAllCaps: Boolean? = false,
    @JsonProperty("fillMaxSize") val fillMaxSize: Boolean? = false,
    @JsonProperty("backgroundHex") val backgroundHex: String? = null,
    @JsonProperty("actionProperties") var actionProperties: DynamicActionProperties? = null,
)
