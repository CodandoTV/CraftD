package com.github.codandotv.craftd.compose.builder

import com.github.codandotv.craftd.compose.ui.button.CraftDButtonComposeBuilder
import com.github.codandotv.craftd.compose.ui.empty.CraftDEmptyComposeRender
import com.github.codandotv.craftd.compose.ui.text.CraftDTextComposeRender

class CraftDComposeBuilders {

    private val listDynamicBuilder = listOf(
        CraftDButtonComposeBuilder(),
        CraftDTextComposeRender()
    )

    private val customBuilders = mutableListOf<CraftDComposeBuilder>()

    fun addBuilderRenders(
        customDynamicBuilderList: List<CraftDComposeBuilder>
    ): CraftDComposeBuilders {
        customBuilders.addAll(customDynamicBuilderList)
        return this
    }
   fun addBuilderRender(
        dynamicComposeBuilder: CraftDComposeBuilder
    ): CraftDComposeBuilders {
        customBuilders.add(dynamicComposeBuilder)
        return this
    }

    fun getBuilder(
        key: String
    ): CraftDComposeBuilder = (listDynamicBuilder + customBuilders)
        .distinctBy { it.key }
        .find {
            it.key == key
        } ?: CraftDEmptyComposeRender()
}