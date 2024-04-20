package com.github.codandotv.craftd.compose.ui.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.compose.ui.text.DynamicText
import com.github.codandotv.craftd.compose.extensions.toArrangementCompose

@Composable
fun DynamicButton(
    buttonProperties: ButtonProperties,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = buttonProperties.align.toArrangementCompose()
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
        ) {
            DynamicText(
                textProperties = TextProperties(
                    text = buttonProperties.text,
                    align = buttonProperties.textAlign,
                    textAllCaps = buttonProperties.textAllCaps,
                    textColorHex = buttonProperties.textColorHex,
                    textSize = buttonProperties.textSize,
                )
            )
        }
    }
}