package com.github.codandotv.craftd.compose

import androidx.compose.ui.graphics.Color

expect fun String?.parseColorCompose(): Color
expect fun parseHtmlToString(html: String): String
