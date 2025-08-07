package com.github.codandotv.craftd.compose

import androidx.compose.ui.graphics.Color

actual fun String?.parseColorCompose(): Color =
    Color.Unspecified

actual fun parseHtmlToString(html: String): String {
    return html
}
