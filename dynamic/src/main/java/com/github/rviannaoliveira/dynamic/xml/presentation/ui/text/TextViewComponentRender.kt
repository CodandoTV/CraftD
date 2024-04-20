package com.github.rviannaoliveira.dynamic.xml.presentation.ui.text

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rviannaoliveira.dynamic.core.data.convertToVO
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import com.github.rviannaoliveira.dynamic.core.data.model.text.TextProperties
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicComponent
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicViewListener
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.ViewRenderer

class TextViewComponentRender(override var onClickListener: DynamicViewListener?) :
    ViewRenderer<TextViewComponentRender.TextViewHolder>(
        DynamicComponent.TEXT_VIEW_COMPONENT.key, DynamicComponent.TEXT_VIEW_COMPONENT.ordinal
    ) {

    inner class TextViewHolder(val textView: TextViewComponent) : RecyclerView.ViewHolder(textView)

    override fun bindView(model: SimpleProperties, holder: TextViewHolder, position: Int) {
        val textProperties = model.value.convertToVO<TextProperties>()
        holder.textView.setProperties(textProperties)
        holder.textView.setOnClickListener {
            textProperties.actionProperties?.let { actionProperties ->
                holder.textView.setOnClickListener {
                    onClickListener?.invoke(actionProperties)
                }
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): TextViewHolder {
        return TextViewHolder(TextViewComponent(parent.context))
    }
}