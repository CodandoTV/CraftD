package com.github.codandotv.craftd.androidcore.data.model.text

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle

@Stable
@Immutable
@Serializable
data class TextProperties(
    @SerialName("text") val text: String? = null,
    @SerialName("textColorHex") val textColorHex: String? = null,
    @SerialName("align") val align: CraftDAlign? = null,
    @SerialName("textSize") val textSize: String? = null,
    @SerialName("backgroundHex") val backgroundHex: String? = null,
    @SerialName("textStyle") val textStyle: CraftDTextStyle? = null,
    @SerialName("textAllCaps") val textAllCaps: Boolean? = false,
    @SerialName("actionProperties") var actionProperties: ActionProperties? = null,
    @SerialName("textHtml") val textHtml: String? = null,
)
