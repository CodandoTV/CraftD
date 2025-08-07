package com.github.codandotv.craftd.androidcore.data.model.checkbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StyleProperties(
    @SerialName("checkedColorHex")
    val checkedColor: String? = null,

    @SerialName("uncheckedColorHex")
    val uncheckedColor: String? = null
)
