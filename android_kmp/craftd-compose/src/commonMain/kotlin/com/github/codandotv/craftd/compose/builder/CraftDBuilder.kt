package com.github.codandotv.craftd.compose.builder

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener

interface CraftDBuilder {
    val key: String
    @Composable
    fun craft(model: SimpleProperties, listener: CraftDViewListener)
}