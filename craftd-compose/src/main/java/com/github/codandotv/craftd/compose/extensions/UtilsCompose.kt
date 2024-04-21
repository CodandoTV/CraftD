package com.github.codandotv.craftd.compose.extensions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle

fun CraftDTextStyle?.toTextStyle() = when (this) {
    CraftDTextStyle.BOLD -> TextStyle(fontWeight = FontWeight.Bold)
    CraftDTextStyle.ITALIC -> TextStyle(fontStyle = FontStyle.Italic)
    else -> TextStyle()
}

fun CraftDAlign?.toAlignCompose() = when (this) {
    CraftDAlign.CENTER -> TextAlign.Center
    CraftDAlign.LEFT -> TextAlign.Left
    CraftDAlign.RIGHT -> TextAlign.Right
    else -> null

}
fun CraftDAlign?.toArrangementCompose() : Arrangement.Horizontal = when (this) {
    CraftDAlign.CENTER -> Arrangement.Center
    CraftDAlign.RIGHT -> Arrangement.Start
    else -> Arrangement.End
}

fun String?.parseColorCompose(): Color {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (ex: Exception) {
        Color.Unspecified
    }
}