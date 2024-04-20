package com.github.rviannaoliveira.dynamic.compose.presentation.ui.button

import androidx.compose.runtime.Composable
import com.github.rviannaoliveira.dynamic.compose.presentation.builder.DynamicComposeBuilder
import com.github.rviannaoliveira.dynamic.core.data.convertToVO
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import com.github.rviannaoliveira.dynamic.core.data.model.button.ButtonProperties
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicComponent
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicViewListener

class DynamicButtonComposeBuilder : DynamicComposeBuilder(
    key = DynamicComponent.BUTTON_COMPONENT.key
) {
    @Composable
    override fun craft(model: SimpleProperties, listener: DynamicViewListener) {
        val buttonProperties = model.value.convertToVO<ButtonProperties>()
        DynamicButton(buttonProperties) {
            buttonProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}