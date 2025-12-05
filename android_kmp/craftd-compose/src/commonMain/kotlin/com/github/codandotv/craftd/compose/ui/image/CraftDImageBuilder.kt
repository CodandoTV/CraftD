package com.github.codandotv.craftd.compose.ui.image

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.image.ImageProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDBuilder

class CraftDImageBuilder(override val key: String = CraftDComponentKey.IMAGE_COMPONENT.key) :
        CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val imageProperties = model.value.convertToElement<ImageProperties>()
        imageProperties?.let {
            CraftDImage(it) { imageProperties.actionProperties?.let { listener.invoke(it) } }
        }
    }
}
