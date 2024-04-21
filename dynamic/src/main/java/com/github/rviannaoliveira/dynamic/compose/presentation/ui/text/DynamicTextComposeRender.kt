package com.github.rviannaoliveira.dynamic.compose.presentation.ui.text

import androidx.compose.runtime.Composable
import com.github.rviannaoliveira.dynamic.compose.presentation.builder.DynamicComposeBuilder
import com.github.rviannaoliveira.dynamic.core.data.convertToVO
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import com.github.rviannaoliveira.dynamic.core.data.model.text.TextProperties
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicComponent
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicViewListener

class DynamicTextComposeRender : DynamicComposeBuilder(
    DynamicComponent.TEXT_VIEW_COMPONENT.key
) {
    @Composable
    override fun craft(model: SimpleProperties, listener: DynamicViewListener) {
        val textProperties = model.value.convertToVO<TextProperties>()
        DynamicText(textProperties = textProperties) {
            textProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}