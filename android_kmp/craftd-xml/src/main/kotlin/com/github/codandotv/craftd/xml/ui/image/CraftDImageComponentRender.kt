package com.github.codandotv.craftd.xml.ui.image

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.codandotv.craftd.androidcore.data.convertToElement
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.image.ImageProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.xml.ui.CraftDViewRenderer

class CraftDImageComponentRender(
    private val imageLoader: (url: String, imageView: ImageView) -> Unit,
    override var onClickListener: CraftDViewListener?,
) : CraftDViewRenderer<CraftDImageComponentRender.ImageHolder>(
    CraftDComponentKey.IMAGE_COMPONENT.key,
    CraftDComponentKey.IMAGE_COMPONENT.ordinal
) {

    inner class ImageHolder(val imageView: CraftDImageComponent) : RecyclerView.ViewHolder(imageView)

    override fun bindView(model: SimpleProperties, holder: ImageHolder, position: Int) {
        val imageProperties = model.value.convertToElement<ImageProperties>()

        imageProperties?.url?.let { url ->
            imageLoader(url, holder.imageView)
        }

        imageProperties?.actionProperties?.let { actionProperties ->
            holder.imageView.setOnClickListener {
                onClickListener?.invoke(actionProperties)
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): ImageHolder {
        return ImageHolder(CraftDImageComponent(parent.context))
    }
}
