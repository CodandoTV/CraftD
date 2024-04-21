package com.github.codandotv.craftd.androidcore.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse

inline fun <reified T : Any> Any?.convertToVO(): T =
        ObjectMapper().convertValue(this, T::class.java) as T

fun List<SimplePropertiesResponse>.toListSimpleProperties() = this.map {
    it.toSimpleProperties()
}

fun SimplePropertiesResponse.toSimpleProperties() =
        SimpleProperties(
                key = this.key,
                value = this.value
        )
