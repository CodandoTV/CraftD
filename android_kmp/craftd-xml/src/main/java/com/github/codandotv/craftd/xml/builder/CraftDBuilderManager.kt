package com.github.codandotv.craftd.xml.builder

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.xml.ui.CraftDViewRenderer
import com.github.codandotv.craftd.xml.ui.button.ButtonComponentRender
import com.github.codandotv.craftd.xml.ui.text.CraftDTextViewComponentRender

object CraftDBuilderManager {
    fun getBuilderRenders(
        simpleProperties: List<SimpleProperties>,
        customDynamicBuilderList: List<CraftDViewRenderer<*>> = emptyList(),
        onAction: CraftDViewListener
    ): List<CraftDViewRenderer<*>> {
        val allViewRenders = (customDynamicBuilderList + listOf(
            CraftDTextViewComponentRender(onAction),
            ButtonComponentRender(onAction)
        ))

        return simpleProperties.distinctBy { it.key }.mapNotNull { simpleProperties ->
                val key = simpleProperties.key
                allViewRenders.find {
                    it.key == key
                }
            }
    }
}