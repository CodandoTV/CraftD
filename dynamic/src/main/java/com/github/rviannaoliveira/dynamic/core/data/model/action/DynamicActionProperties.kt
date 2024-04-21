package com.github.rviannaoliveira.dynamic.core.data.model.action

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DynamicActionProperties (
    @JsonProperty("deeplink")
    val deeplink : String?,

    @JsonProperty("actionData")
    val actionData: Any?,

    @JsonProperty("analytics")
    val analytics: DynamicAnalyticsProperties?,
)