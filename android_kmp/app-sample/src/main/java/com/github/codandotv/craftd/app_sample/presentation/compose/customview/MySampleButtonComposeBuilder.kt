package com.github.codandotv.craftd.app_sample.presentation.compose.customview

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.compose.builder.CraftDBuilder

class MySampleButtonComposeBuilder(override val key: String = "MySampleButton") : CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener) {
        val buttonProperties = model.value.convertToElement<ButtonProperties>()
        MySampleButton(buttonProperties) {
            buttonProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}