package com.github.codandotv.craftd.compose

import androidx.core.text.HtmlCompat

actual fun parseHtmlToPlainText(html: String): String {
    return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}