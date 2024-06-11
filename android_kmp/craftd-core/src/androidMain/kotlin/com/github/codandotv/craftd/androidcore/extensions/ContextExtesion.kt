package com.github.codandotv.craftd.androidcore.extensions

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes

fun Context.loadJSONFromAsset(fileName: String): String =
    assets.open("$fileName.json").bufferedReader().use {
        it.readText()
    }

fun Context.getAttrColorRes(@AttrRes attribute: Int): Int {
    val value = TypedValue()
    return if (this.theme.resolveAttribute(attribute, value, false)) {
        value.data
    } else {
        return 0
    }
}