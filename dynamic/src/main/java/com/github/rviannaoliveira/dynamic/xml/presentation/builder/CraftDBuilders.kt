package com.github.rviannaoliveira.dynamic.xml.presentation.builder

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.ViewRenderer
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.button.ButtonComponentRender
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.text.CraftDTextViewComponentRender

class CraftDBuilders {
    fun getBuilderRenders(
        simpleProperties: List<SimpleProperties>,
        customDynamicBuilderList: List<ViewRenderer<*>> = emptyList(),
        onAction: CraftDViewListener
    ): List<ViewRenderer<*>> {
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