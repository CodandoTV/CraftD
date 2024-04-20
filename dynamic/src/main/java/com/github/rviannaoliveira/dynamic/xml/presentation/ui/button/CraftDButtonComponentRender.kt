package com.github.rviannaoliveira.dynamic.xml.presentation.ui.button

import android.graphics.Color.parseColor
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.github.codandotv.craftd.androidcore.data.convertToVO
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.button.ButtonProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponent
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.ViewRenderer

class ButtonComponentRender(override var onClickListener: CraftDViewListener?) :
    ViewRenderer<ButtonComponentRender.ButtonHolder>(
        CraftDComponent.BUTTON_COMPONENT.key, CraftDComponent.BUTTON_COMPONENT.ordinal
    ) {

    inner class ButtonHolder(val button: CraftDButtonComponent) : RecyclerView.ViewHolder(button)

    override fun bindView(model: SimpleProperties, holder: ButtonHolder, position: Int) {
        val buttonProperties = model.value.convertToVO<ButtonProperties>()

        holder.button.setProperties(buttonProperties)
        buttonProperties.actionProperties?.let { actionProperties ->
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

internal fun String?.parseColorCompose(): Color {
    return try {
        Color(parseColor(this))
    } catch (ex: Exception) {
        Color.Unspecified
    }
}
