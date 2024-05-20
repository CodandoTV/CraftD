package com.github.codandotv.craftd.compose.builder

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.compose.ui.button.CraftDButtonBuilder
import com.github.codandotv.craftd.compose.ui.checkbox.CraftDCheckBoxBuilder
import com.github.codandotv.craftd.compose.ui.empty.CraftDEmptyBuilder
import com.github.codandotv.craftd.compose.ui.text.CraftDTextBuilder

@Stable
@Immutable
class CraftDBuilderManager {
    private val mapBuilder = hashMapOf(
        CraftDComponentKey.BUTTON_COMPONENT.key to CraftDButtonBuilder(),
        CraftDComponentKey.TEXT_VIEW_COMPONENT.key to CraftDTextBuilder(),
        CraftDComponentKey.CHECK_BOX_COMPONENT.key to CraftDCheckBoxBuilder(),
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