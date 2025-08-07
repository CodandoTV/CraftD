package com.github.codandotv.craftd.xml.ui.button

import android.graphics.Color.parseColor
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.xml.ui.CraftDViewRenderer

class ButtonComponentRender(override var onClickListener: CraftDViewListener?) :
    CraftDViewRenderer<ButtonComponentRender.ButtonHolder>(
        CraftDComponentKey.BUTTON_COMPONENT.key, CraftDComponentKey.BUTTON_COMPONENT.ordinal
    ) {

    inner class ButtonHolder(val button: CraftDButtonComponent) : RecyclerView.ViewHolder(button)

    override fun bindView(model: SimpleProperties, holder: ButtonHolder, position: Int) {
        val buttonProperties = model.value.convertToElement<ButtonProperties>()

        buttonProperties?.let { holder.button.setProperties(it) }
        buttonProperties?.actionProperties?.let { actionProperties ->
            holder.button.setOnClickListener {
                onClickListener?.invoke(actionProperties)
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): ButtonHolder {
        return ButtonHolder(CraftDButtonComponent(parent.context))
    }
}

internal fun String.parseColor(): Int {
    return try {
        parseColor(this)
    } catch (ex: Exception) {
        0
    }
}