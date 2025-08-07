package com.github.codandotv.craftd.androidcore.data

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

inline fun <reified T> JsonElement?.convertToElement(): T? {
        if (this == null) return null
        return try {
                Json { ignoreUnknownKeys = true }.decodeFromJsonElement<T>(this)
        } catch (e: Exception) {
                null
        }
}

fun List<SimplePropertiesResponse>.toListSimpleProperties() = this.map {
    it.toSimpleProperties()
}

fun SimplePropertiesResponse.toSimpleProperties() =
        SimpleProperties(
                key = this.key,
                value = this.value
        )
