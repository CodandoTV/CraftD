package com.github.codandotv.craftd.androidcore.data.model.action

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Stable
@Immutable
@Serializable
data class AnalyticsProperties(
    @SerialName("category")
    val category: String? = null,

    @SerialName("action")
    val action: String? = null,

    @SerialName("label")
    val label: String? = null,

    @SerialName("track")
    val track: String? = null,
)
