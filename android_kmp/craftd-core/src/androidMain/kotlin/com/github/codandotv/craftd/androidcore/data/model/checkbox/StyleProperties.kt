package com.github.codandotv.craftd.androidcore.data.model.checkbox

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class StyleProperties(
    @JsonProperty("checkedColorHex")
    val checkedColor: String?,
    @JsonProperty("uncheckedColorHex")
    val uncheckedColor: String?
)