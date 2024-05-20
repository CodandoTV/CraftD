package com.github.codandotv.craftd.androidcore.data.model.action

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Stable
@Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
data class ActionProperties (
    @JsonProperty("deeplink")
    val deeplink : String?,

    @JsonProperty("actionData")
    val actionData: Any?,

    @JsonProperty("analytics")
    val analytics: AnalyticsProperties?,
)