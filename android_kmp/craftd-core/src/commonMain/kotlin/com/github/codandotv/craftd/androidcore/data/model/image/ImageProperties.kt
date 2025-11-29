package com.github.codandotv.craftd.androidcore.data.model.image

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Immutable
@Stable
data class ImageProperties(
        @SerialName("url") val url: String? = null,
        @SerialName("contentDescription") val contentDescription: String? = null,
        @SerialName("align") val align: CraftDAlign? = null,
        @SerialName("fillMaxSize") val fillMaxSize: Boolean? = false,
        @SerialName("aspectRatio") val aspectRatio: Float? = null,
        @SerialName("contentScale") val contentScale: String? = null,
        @SerialName("actionProperties") var actionProperties: ActionProperties? = null,
)
