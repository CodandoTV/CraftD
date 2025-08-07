package com.github.codandotv.craftd.androidcore.data

import com.fasterxml.jackson.databind.ObjectMapper


inline fun <reified T : Any> Any?.convertToVO(): T =
    ObjectMapper().convertValue(this, T::class.java) as T
