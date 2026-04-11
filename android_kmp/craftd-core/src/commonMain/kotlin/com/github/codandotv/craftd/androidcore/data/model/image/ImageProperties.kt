package com.github.codandotv.craftd.androidcore.data.model.image

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Immutable
@Stable
data class ImageProperties(
    @SerialName("url") val url: String? = null,
    @SerialName("contentScale") val contentScale: CraftDContentScale? = null,
    @SerialName("contentDescription") val contentDescription: String? = null,
    @SerialName("actionProperties") val actionProperties: ActionProperties? = null,
)
