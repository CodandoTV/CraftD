package com.github.codandotv.craftd.androidcore.data.model.checkbox

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign

@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
@Stable
data class CheckBoxProperties(
    @JsonProperty("text") val text: String? = null,
    @JsonProperty("align") val align: CraftDAlign? = null,
    @JsonProperty("textAlign") val textAlign: CraftDAlign? = null,
    @JsonProperty("enable") val enable: Boolean? = false,
    @JsonProperty("hasItRightText") val hasItRightText: Boolean? = false,
    @JsonProperty("actionProperties") var actionProperties: ActionProperties? = null,
)