package com.github.codandotv.craftd.xml.ui

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties


interface CraftDView {
    fun registerRenderer(renderer: CraftDViewRenderer<*>)
    fun registerRenderers(renderers: List<CraftDViewRenderer<*>>)
    fun setViewObjectDiff(properties: List<SimpleProperties>)
    fun updateViewAt(properties: SimpleProperties, index: Int)
    fun notifyPositionRemovedAt(position: Int)
    fun notifyItemChangeAt(position: Int, payload: Any? = null)
    fun clear()
}
