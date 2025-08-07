package com.github.codandotv.craftd.androidcore.data.model.action

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Stable
@Immutable
@Serializable
data class ActionProperties(
    @SerialName("deeplink")
    val deeplink: String? = null,

    @SerialName("actionData")
    val actionData: JsonElement? = null,  // Substitui Any? para ser compatível com JSON dinâmico

    @SerialName("analytics")
    val analytics: AnalyticsProperties? = null,
)
