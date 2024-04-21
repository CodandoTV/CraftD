package com.github.rviannaoliveira.dynamic.compose.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle

internal fun CraftDTextStyle?.toTextStyle() = when (this) {
    CraftDTextStyle.BOLD -> TextStyle(fontWeight = FontWeight.Bold)
    CraftDTextStyle.ITALIC -> TextStyle(fontStyle = FontStyle.Italic)
    else -> TextStyle()
}

internal fun CraftDAlign?.toAlignCompose() = when (this) {
    CraftDAlign.CENTER -> TextAlign.Center
    CraftDAlign.LEFT -> TextAlign.Left
    CraftDAlign.RIGHT -> TextAlign.Right
    else -> null

}
internal fun CraftDAlign?.toArrangementCompose() : Arrangement.Horizontal = when (this) {
    CraftDAlign.CENTER -> Arrangement.Center
    CraftDAlign.RIGHT -> Arrangement.Start
    else -> Arrangement.End
}