package com.github.codandotv.craftd.androidcore.data.model.base

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Immutable
@Stable
@Serializable
data class SimpleProperties(
    @SerialName("key") val key: String = "",
    @SerialName("value") val value: JsonElement? = null
)
