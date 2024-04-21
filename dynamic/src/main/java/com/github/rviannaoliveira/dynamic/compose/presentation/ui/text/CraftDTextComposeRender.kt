package com.github.rviannaoliveira.dynamic.compose.presentation.ui.text

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponent
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.rviannaoliveira.dynamic.compose.presentation.builder.CraftDComposeBuilder

class CraftDTextComposeRender : CraftDComposeBuilder(
    CraftDComponent.TEXT_VIEW_COMPONENT.key
) {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val textProperties = model.value.convertToVO<TextProperties>()
        CraftDText(textProperties = textProperties) {
            textProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}