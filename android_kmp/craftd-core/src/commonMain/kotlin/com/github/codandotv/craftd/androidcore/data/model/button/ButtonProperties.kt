package com.github.codandotv.craftd.androidcore.data.model.button

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Immutable
@Stable
data class ButtonProperties(
    @SerialName("text") val text: String? = null,
    @SerialName("textColorHex") val textColorHex: String? = null,
    @SerialName("align") val align: CraftDAlign? = null,
    @SerialName("textAlign") val textAlign: CraftDAlign? = null,
    @SerialName("textSize") val textSize: String? = null,
    @SerialName("textAllCaps") val textAllCaps: Boolean? = false,
    @SerialName("fillMaxSize") val fillMaxSize: Boolean? = false,
    @SerialName("backgroundHex") val backgroundHex: String? = null,
    @SerialName("actionProperties") var actionProperties: ActionProperties? = null,
)
