package com.github.codandotv.craftd.xml.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener

abstract class CraftDViewRenderer<VH : RecyclerView.ViewHolder>(val key: String, val viewType: Int) {
    abstract var onClickListener: CraftDViewListener?
    abstract fun bindView(model: SimpleProperties, holder: VH, position: Int)
    abstract fun createViewHolder(parent: ViewGroup): VH
}

