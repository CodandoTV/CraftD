package com.github.codandotv.craftd.compose.ui.empty

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDBuilder
class CraftDEmptyBuilder(override val key: String = "") : CraftDBuilder{

    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
    }
}