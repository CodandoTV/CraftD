package com.github.codandotv.craftd.compose.ui.button

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponent
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDComposeBuilder

class CraftDButtonComposeBuilder : CraftDComposeBuilder(
    key = CraftDComponent.BUTTON_COMPONENT.key
) {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val buttonProperties = model.value.convertToVO<ButtonProperties>()
        DynamicButton(buttonProperties) {
            buttonProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}