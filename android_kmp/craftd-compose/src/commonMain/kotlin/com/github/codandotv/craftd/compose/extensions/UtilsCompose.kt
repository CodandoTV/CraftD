package com.github.codandotv.craftd.compose.extensions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
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
    CraftDAlign.RIGHT -> Arrangement.End
    else -> Arrangement.Start
}

fun CraftDAlign?.toAlignmentCompose() : Alignment.Vertical = when (this) {
    CraftDAlign.CENTER -> Alignment.CenterVertically
    CraftDAlign.BOTTOM -> Alignment.Bottom
    else -> Alignment.Top
}

fun String?.parseColorCompose(): Color {
    return runCatching {
        val clean = this!!.removePrefix("#")

        val argb = when (clean.length) {
            6 -> "FF$clean"     // Add alpha if missing
            8 -> clean
            else -> throw IllegalArgumentException("Invalid hex: $this")
        }

        val a = argb.substring(0, 2).toInt(16)
        val r = argb.substring(2, 4).toInt(16)
        val g = argb.substring(4, 6).toInt(16)
        val b = argb.substring(6, 8).toInt(16)

        Color(
            alpha = a,
            red = r,
            green = g,
            blue = b
        )
    }.getOrDefault(Color.Unspecified)
}
