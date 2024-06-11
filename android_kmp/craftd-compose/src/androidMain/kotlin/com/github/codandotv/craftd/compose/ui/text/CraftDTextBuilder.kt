package com.github.codandotv.craftd.compose.ui.text

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDBuilder

class CraftDTextBuilder(
    override val key: String =  CraftDComponentKey.TEXT_VIEW_COMPONENT.key
) : CraftDBuilder{
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val textProperties = model.value.convertToVO<TextProperties>()
        CraftDText(textProperties = textProperties) {
            textProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}