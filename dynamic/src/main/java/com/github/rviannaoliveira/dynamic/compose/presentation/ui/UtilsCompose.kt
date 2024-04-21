package com.github.rviannaoliveira.dynamic.compose.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.github.rviannaoliveira.dynamic.core.domain.Align
import com.github.rviannaoliveira.dynamic.core.domain.DynamicTextStyle
import org.w3c.dom.Text

internal fun DynamicTextStyle?.toTextStyle() = when (this) {
    DynamicTextStyle.BOLD -> TextStyle(fontWeight = FontWeight.Bold)
    DynamicTextStyle.ITALIC -> TextStyle(fontStyle = FontStyle.Italic)
    else -> TextStyle()
}

internal fun Align?.toAlignCompose() = when (this) {
    Align.CENTER -> TextAlign.Center
    Align.LEFT -> TextAlign.Left
    Align.RIGHT -> TextAlign.Right
    else -> null

}
internal fun Align?.toArrangementCompose() : Arrangement.Horizontal = when (this) {
    Align.CENTER -> Arrangement.Center
    Align.RIGHT -> Arrangement.Start
    else -> Arrangement.End
}