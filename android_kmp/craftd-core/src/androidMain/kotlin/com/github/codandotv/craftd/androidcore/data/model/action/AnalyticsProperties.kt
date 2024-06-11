package com.github.codandotv.craftd.androidcore.data.model.action

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Stable
@Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
data class AnalyticsProperties(
    @JsonProperty("category")
    val category: String? = null,

    @JsonProperty("action")
    val action: String? = null,

    @JsonProperty("label")
    val label: String? = null,

    @JsonProperty("track")
    val track: String? = null,
)
