package com.github.codandotv.craftd.compose.ui.checkbox

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.codandotv.craftd.androidcore.data.model.checkbox.CheckBoxProperties
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.compose.extensions.toAlignmentCompose
import com.github.codandotv.craftd.compose.extensions.toArrangementCompose
import com.github.codandotv.craftd.compose.parseColorCompose
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
        modifier = Modifier.fillMaxWidth(0.4f)
    ) {
        if (checkboxProperties.hasItRightText == true)
            Box(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .fillMaxWidth(0.65f)
            ) {
                CraftDText(
                    textProperties = TextProperties(
                        text = checkboxProperties.text,
                    )
                )
            }

        Checkbox(
            checked = checkboxProperties.enable ?: false,
            onCheckedChange = onChecked,
            modifier = modifier,
            colors = CheckboxDefaults.colors(
                checkedColor = checkboxProperties.styleProperties?.checkedColor.parseColorCompose(),
                uncheckedColor = checkboxProperties.styleProperties?.uncheckedColor.parseColorCompose()
            )
        )

        if (checkboxProperties.hasItRightText == false)
            CraftDText(
                textProperties = TextProperties(
                    text = checkboxProperties.text,
                )
            )
    }
}