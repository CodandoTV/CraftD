package com.github.codandotv.craftd.androidcore.data.model.checkbox

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Stable
@Immutable
@Serializable
data class CheckBoxProperties(
    @SerialName("text")
    val text: String? = null,

    @SerialName("align")
    val align: CraftDAlign? = null,

    @SerialName("textAlign")
    val textAlign: CraftDAlign? = null,

    @SerialName("enable")
    val enable: Boolean? = false,

    @SerialName("hasItRightText")
    val hasItRightText: Boolean? = false,

    @SerialName("actionProperties")
    var actionProperties: ActionProperties? = null,

    @SerialName("style")
    var styleProperties: StyleProperties? = null,
)
