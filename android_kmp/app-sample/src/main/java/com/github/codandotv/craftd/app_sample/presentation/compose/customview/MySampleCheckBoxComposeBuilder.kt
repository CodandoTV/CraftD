package com.github.codandotv.craftd.app_sample.presentation.compose.customview

import androidx.compose.runtime.Composable
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.checkbox.CheckBoxProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.compose.builder.CraftDComposeBuilder

class MySampleCheckBoxComposeBuilder : CraftDComposeBuilder(
    key = "MySampleCheckBox"
) {
    @Composable
    override fun craft(model: SimpleProperties, listener: CraftDViewListener) {
        val checkBoxProperties = model.value.convertToVO<CheckBoxProperties>()
        MySampleCheckBox(checkBoxProperties) {
            checkBoxProperties.actionProperties?.let { listener.invoke(it) }
        }
    }
}