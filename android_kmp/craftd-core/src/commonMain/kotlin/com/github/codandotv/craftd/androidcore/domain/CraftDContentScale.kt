package com.github.codandotv.craftd.androidcore.domain

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
@Immutable
enum class CraftDContentScale {
    CROP,
    FIT,
    FILL_BOUNDS,
    FILL_WIDTH,
    FILL_HEIGHT,
    INSIDE,
    NONE
}
