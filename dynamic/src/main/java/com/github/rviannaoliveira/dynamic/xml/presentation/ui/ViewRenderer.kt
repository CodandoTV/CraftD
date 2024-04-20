package com.github.rviannaoliveira.dynamic.xml.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicViewListener
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties

abstract class ViewRenderer<VH : RecyclerView.ViewHolder>(val key: String, val viewType: Int) {
    abstract var onClickListener: DynamicViewListener?
    abstract fun bindView(model: SimpleProperties, holder: VH, position: Int)
    abstract fun createViewHolder(parent: ViewGroup): VH
}

