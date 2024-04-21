package com.github.rviannaoliveira.dynamic.compose.presentation.builder

import androidx.compose.runtime.Composable
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicViewListener
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties

abstract class DynamicComposeBuilder(val key: String) {
    @Composable
    abstract fun craft(model: SimpleProperties, listener: DynamicViewListener)
}