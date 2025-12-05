package com.github.codandotv.craftd.xml.ui.image

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.image.ImageProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.xml.ui.CraftDViewRenderer

class ImageComponentRender(override var onClickListener: CraftDViewListener?) :
        CraftDViewRenderer<ImageComponentRender.ImageHolder>(
                CraftDComponentKey.IMAGE_COMPONENT.key,
                CraftDComponentKey.IMAGE_COMPONENT.ordinal
        ) {

    inner class ImageHolder(val image: CraftDImageComponent) : RecyclerView.ViewHolder(image)

    override fun bindView(model: SimpleProperties, holder: ImageHolder, position: Int) {
        val imageProperties = model.value.convertToElement<ImageProperties>()

        imageProperties?.let { holder.image.setProperties(it) }
        imageProperties?.actionProperties?.let { actionProperties ->
            holder.image.setOnClickListener { onClickListener?.invoke(actionProperties) }
        }
    }

    override fun createViewHolder(parent: ViewGroup): ImageHolder {
        return ImageHolder(CraftDImageComponent(parent.context))
    }
}
