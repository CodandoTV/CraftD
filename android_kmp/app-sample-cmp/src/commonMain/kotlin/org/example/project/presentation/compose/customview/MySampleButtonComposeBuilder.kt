package org.example.project.presentation.compose.customview

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDBuilder

class MySampleButtonComposeBuilder(override val key: String = "MySampleButton") : CraftDBuilder {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val buttonProperties = model.value.convertToElement<ButtonProperties>()
        buttonProperties?.let {
            MySampleButton(it) {
                buttonProperties.actionProperties?.let { listener.invoke(it) }
            }
        }
    }
}