package com.github.codandotv.craftd.app_sample.presentation.compose.customview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.compose.extensions.toArrangementCompose

@Composable
fun MySampleButton(
    buttonProperties: ButtonProperties,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = buttonProperties.align.toArrangementCompose(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta),
            modifier = modifier,
        ) {
            Text(buttonProperties.text ?: "empty")
        }
    }
}