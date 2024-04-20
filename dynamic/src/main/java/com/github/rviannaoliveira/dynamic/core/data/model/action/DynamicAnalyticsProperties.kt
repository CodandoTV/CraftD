package com.github.rviannaoliveira.dynamic.core.data.model.action

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DynamicAnalyticsProperties(
    @JsonProperty("category")
    val category: String? = null,

    @JsonProperty("action")
    val action: String? = null,

    @JsonProperty("label")
    val label: String? = null,

    @JsonProperty("track")
    val track: String? = null,
)
