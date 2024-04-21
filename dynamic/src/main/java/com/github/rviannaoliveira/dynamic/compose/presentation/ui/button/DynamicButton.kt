package com.github.rviannaoliveira.dynamic.compose.presentation.ui.button

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.text.DynamicText
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.toArrangementCompose
import com.github.rviannaoliveira.dynamic.core.data.model.button.ButtonProperties
import com.github.rviannaoliveira.dynamic.core.data.model.text.TextProperties

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