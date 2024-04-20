package com.github.rviannaoliveira.dynamic.core.data

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimplePropertiesResponse

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
