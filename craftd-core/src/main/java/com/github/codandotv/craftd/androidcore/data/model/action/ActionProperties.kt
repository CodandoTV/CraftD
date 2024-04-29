package com.github.codandotv.craftd.androidcore.data.model.action

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ActionProperties (
    @JsonProperty("deeplink")
    val deeplink : String?,

    @JsonProperty("actionData")
    val actionData: Any?,

    @JsonProperty("analytics")
    val analytics: AnalyticsProperties?,
)