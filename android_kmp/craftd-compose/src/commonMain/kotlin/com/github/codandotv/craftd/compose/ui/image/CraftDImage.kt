package com.github.codandotv.craftd.compose.ui.image

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.codandotv.craftd.androidcore.data.model.image.ImageProperties

@Composable
fun CraftDImage(
    properties: ImageProperties,
    imageLoader: @Composable (url: String, contentDescription: String?, modifier: Modifier) -> Unit,
    onAction: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val clickableModifier = if (properties.actionProperties != null) {
        modifier.clickable { onAction() }
    } else modifier

    imageLoader(
        properties.url.orEmpty(),
        properties.contentDescription,
        clickableModifier,
    )
}
