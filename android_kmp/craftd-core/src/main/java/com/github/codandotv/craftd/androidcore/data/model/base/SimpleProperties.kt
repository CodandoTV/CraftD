package com.github.codandotv.craftd.androidcore.data.model.base

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Immutable
@Stable
@JsonIgnoreProperties(ignoreUnknown = true)
data class SimpleProperties(
    @JsonProperty("key") val key: String = "",
    @JsonProperty("value") val value: Any? = null
)
