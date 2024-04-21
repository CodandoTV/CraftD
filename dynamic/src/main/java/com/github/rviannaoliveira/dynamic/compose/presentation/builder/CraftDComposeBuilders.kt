package com.github.rviannaoliveira.dynamic.compose.presentation.builder

import com.github.rviannaoliveira.dynamic.compose.presentation.ui.button.CraftDButtonComposeBuilder
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.empty.CraftDEmptyComposeRender
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.text.CraftDTextComposeRender

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