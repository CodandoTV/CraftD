package com.github.codandotv.craftd.androidcore.data.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SimplePropertiesResponse(
    @SerialName("key") val key: String = "",
    @SerialName("value") val value: JsonElement? = null
)

@Serializable
data class DataSimplePropertiesResponse(
    val data: List<SimplePropertiesResponse>
)
