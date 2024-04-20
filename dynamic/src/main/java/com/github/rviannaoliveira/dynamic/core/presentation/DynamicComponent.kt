package com.github.rviannaoliveira.dynamic.core.presentation


enum class DynamicComponent(val key: String) {
    TEXT_VIEW_COMPONENT("${DYNAMIC}TextView"),
    BUTTON_COMPONENT("${DYNAMIC}Button"),
}

internal const val DYNAMIC = "Dynamic"
