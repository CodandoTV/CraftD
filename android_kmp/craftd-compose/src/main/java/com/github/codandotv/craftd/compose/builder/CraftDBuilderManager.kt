package com.github.codandotv.craftd.compose.builder

import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.compose.ui.button.CraftDButtonBuilder
import com.github.codandotv.craftd.compose.ui.empty.CraftDEmptyBuilder
import com.github.codandotv.craftd.compose.ui.text.CraftDTextBuilder

class CraftDBuilderManager {
    private val mapBuilder = hashMapOf(
        CraftDComponentKey.BUTTON_COMPONENT.key to CraftDButtonBuilder(),
        CraftDComponentKey.TEXT_VIEW_COMPONENT.key to CraftDTextBuilder(),
    )
    fun add(vararg arrayCraftDBuilder: CraftDBuilder) : CraftDBuilderManager{
        arrayCraftDBuilder.forEach {
            mapBuilder[it.key] = it
        }
        return this
    }

    fun getBuilder(
        key: String,
        customMap: Map<String, CraftDBuilder>? = null
    ): CraftDBuilder {
        customMap?.let {
            mapBuilder.putAll(customMap)
        }
        return mapBuilder[key] ?: CraftDEmptyBuilder();
    }

}