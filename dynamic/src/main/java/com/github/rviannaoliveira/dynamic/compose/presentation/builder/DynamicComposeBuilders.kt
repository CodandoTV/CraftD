package com.github.rviannaoliveira.dynamic.compose.presentation.builder

import com.github.rviannaoliveira.dynamic.compose.presentation.ui.button.DynamicButtonComposeBuilder
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.empty.DynamicEmptyComposeRender
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.text.DynamicTextComposeRender

class DynamicComposeBuilders {

    private val listDynamicBuilder = listOf(
        DynamicButtonComposeBuilder(),
        DynamicTextComposeRender()
    )

    private val customBuilders = mutableListOf<DynamicComposeBuilder>()

    fun addBuilderRenders(
        customDynamicBuilderList: List<DynamicComposeBuilder>
    ): DynamicComposeBuilders {
        customBuilders.addAll(customDynamicBuilderList)
        return this
    }
   fun addBuilderRender(
        dynamicComposeBuilder: DynamicComposeBuilder
    ): DynamicComposeBuilders {
        customBuilders.add(dynamicComposeBuilder)
        return this
    }

    fun getBuilder(
        key: String
    ): DynamicComposeBuilder = (listDynamicBuilder + customBuilders)
        .distinctBy { it.key }
        .find {
            it.key == key
        } ?: DynamicEmptyComposeRender()
}