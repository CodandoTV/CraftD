package com.github.codandotv.craftd.compose.ui.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.image.ImageProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDBuilder

class CraftDImageBuilder(
    private val imageLoader: @Composable (url: String, contentDescription: String?, modifier: Modifier) -> Unit,
    override val key: String = CraftDComponentKey.IMAGE_COMPONENT.key,
) : CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val imageProperties = model.value.convertToElement<ImageProperties>()
        imageProperties?.let {
            CraftDImage(
                properties = it,
                imageLoader = imageLoader,
                onAction = { it.actionProperties?.let { action -> listener.invoke(action) } },
            )
        }
    }
}
