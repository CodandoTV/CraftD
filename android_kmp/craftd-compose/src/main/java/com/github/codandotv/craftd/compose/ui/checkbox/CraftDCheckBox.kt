package com.github.codandotv.craftd.compose.ui.checkbox

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.codandotv.craftd.androidcore.data.model.checkbox.CheckBoxProperties
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.compose.extensions.toAlignmentCompose
import com.github.codandotv.craftd.compose.extensions.toArrangementCompose
import com.github.codandotv.craftd.compose.ui.text.CraftDText

@Composable
fun CraftDCheckBox(
    checkboxProperties: CheckBoxProperties,
    modifier: Modifier = Modifier,
    onChecked: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = checkboxProperties.align.toArrangementCompose(),
        verticalAlignment = checkboxProperties.textAlign.toAlignmentCompose(),
        modifier = Modifier.fillMaxWidth()
    ) {

        if (checkboxProperties.hasItRightText == true)
            CraftDText(
                textProperties = TextProperties(
                    text = checkboxProperties.text
                )
            )

        Checkbox(
            checked = checkboxProperties.enable ?: false,
            onCheckedChange = onChecked,
            modifier = modifier,
        )
        if (checkboxProperties.hasItRightText == false)
            CraftDText(
                textProperties = TextProperties(
                    text = checkboxProperties.text
                )
            )
    }
}