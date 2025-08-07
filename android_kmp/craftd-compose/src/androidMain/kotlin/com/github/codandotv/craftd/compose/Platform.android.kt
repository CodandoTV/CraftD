package com.github.codandotv.craftd.compose

import androidx.compose.ui.graphics.Color
import androidx.core.text.HtmlCompat

actual fun String?.parseColorCompose(): Color {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (ex: Exception) {
        Color.Unspecified
    }
}

actual fun parseHtmlToString(html: String): String {
    return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}