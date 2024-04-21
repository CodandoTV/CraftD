package com.github.rviannaoliveira.dynamic.compose.presentation.extensions

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color

internal fun String?.parseColorCompose(): Color {
    return try {
        Color(parseColor(this))
    } catch (ex: Exception) {
        Color.Unspecified
    }
}
