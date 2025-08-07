package com.github.codandotv.craftd.xml.ui.text

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.xml.ui.CraftDViewRenderer

class CraftDTextViewComponentRender(override var onClickListener: CraftDViewListener?) :
    CraftDViewRenderer<CraftDTextViewComponentRender.TextViewHolder>(
        CraftDComponentKey.TEXT_VIEW_COMPONENT.key, CraftDComponentKey.TEXT_VIEW_COMPONENT.ordinal
    ) {

    inner class TextViewHolder(val textView: CraftDTextViewComponent) : RecyclerView.ViewHolder(textView)

    override fun bindView(model: SimpleProperties, holder: TextViewHolder, position: Int) {
        val textProperties = model.value.convertToElement<TextProperties>()
        textProperties?.let { holder.textView.setProperties(it) }
        holder.textView.setOnClickListener {
            textProperties?.actionProperties?.let { actionProperties ->
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