package com.github.codandotv.craftd.xml.builder

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.xml.ui.CraftDViewRenderer
import android.widget.ImageView
import com.github.codandotv.craftd.xml.ui.button.ButtonComponentRender
import com.github.codandotv.craftd.xml.ui.image.CraftDImageComponentRender
import com.github.codandotv.craftd.xml.ui.text.CraftDTextViewComponentRender

object CraftDBuilderManager {
    fun getBuilderRenders(
        simpleProperties: List<SimpleProperties>,
        customDynamicBuilderList: List<CraftDViewRenderer<*>> = emptyList(),
        onAction: CraftDViewListener,
        imageLoader: ((url: String, imageView: ImageView) -> Unit)? = null,
    ): List<CraftDViewRenderer<*>> {
        val allViewRenders = (customDynamicBuilderList + listOfNotNull(
            CraftDTextViewComponentRender(onAction),
            ButtonComponentRender(onAction),
            imageLoader?.let { CraftDImageComponentRender(it, onAction) }
        ))

        return simpleProperties.distinctBy { it.key }.mapNotNull { simpleProperties ->
                val key = simpleProperties.key
                allViewRenders.find {
                    it.key == key
                }
            }
    }
}