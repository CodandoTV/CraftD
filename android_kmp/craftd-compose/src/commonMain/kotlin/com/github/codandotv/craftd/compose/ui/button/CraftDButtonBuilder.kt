package com.github.codandotv.craftd.compose.ui.button

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDBuilder

class CraftDButtonBuilder(
    override val key: String = CraftDComponentKey.BUTTON_COMPONENT.key
) : CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val buttonProperties = model.value.convertToElement<ButtonProperties>()
        buttonProperties?.let {
            CraftDButton(it) {
                buttonProperties.actionProperties?.let { listener.invoke(it) }
            }
        }
    }
}