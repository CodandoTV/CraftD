package com.github.rviannaoliveira.dynamic.xml.presentation.ui.text

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponent
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.ViewRenderer

class CraftDTextViewComponentRender(override var onClickListener: CraftDViewListener?) :
    ViewRenderer<CraftDTextViewComponentRender.TextViewHolder>(
        CraftDComponent.TEXT_VIEW_COMPONENT.key, CraftDComponent.TEXT_VIEW_COMPONENT.ordinal
    ) {

    inner class TextViewHolder(val textView: CraftDTextViewComponent) : RecyclerView.ViewHolder(textView)

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
        return TextViewHolder(CraftDTextViewComponent(parent.context))
    }
}