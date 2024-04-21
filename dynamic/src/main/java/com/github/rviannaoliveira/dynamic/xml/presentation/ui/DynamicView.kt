package com.github.rviannaoliveira.dynamic.xml.presentation.ui

import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties

interface DynamicView {
    fun registerRenderer(renderer: ViewRenderer<*>)
    fun registerRenderers(renderers: List<ViewRenderer<*>>)
    fun setViewObjectDiff(properties: List<SimpleProperties>)
    fun updateViewAt(properties: SimpleProperties, index: Int)
    fun notifyPositionRemovedAt(position: Int)
    fun notifyItemChangeAt(position: Int, payload: Any? = null)
    fun clear()
}
