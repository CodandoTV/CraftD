package com.github.rviannaoliveira.dynamic.compose.presentation.builder

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener

abstract class CraftDComposeBuilder(val key: String) {
    @Composable
    abstract fun craft(model: SimpleProperties, listener: CraftDViewListener)
}