package com.github.codandotv.craftd.xml.ui.image

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import coil3.load
import com.github.codandotv.craftd.androidcore.data.model.image.ImageProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.xml.databinding.ImageBinding

class CraftDImageComponent
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
) : RelativeLayout(context, attrs, defStyleAttr) {
    private var binding: ImageBinding

    init {
        binding = ImageBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setProperties(imageProperties: ImageProperties) {
        imageProperties.url?.let { url -> binding.imageView.load(url) }

        imageProperties.contentDescription?.let { description ->
            binding.imageView.contentDescription = description
        }

        setupFillMaxSize(imageProperties)

        imageProperties.aspectRatio?.let { ratio ->
            binding.imageView.adjustViewBounds = true
            // AspectRatio can be handled via custom logic or ConstraintLayout
        }

        imageProperties.contentScale?.let { scale ->
            binding.imageView.scaleType = scale.toScaleType()
        }

        imageProperties.align?.let {
            binding.imageView.layoutParams =
                    LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            )
                            .apply { addRule(it.toRelativeLayoutParams()) }
        }
    }

    private fun setupFillMaxSize(imageProperties: ImageProperties) {
        binding.imageView.layoutParams =
                imageProperties.fillMaxSize?.let { isFillMaxSize ->
                    LayoutParams(
                            if (isFillMaxSize) {
                                ViewGroup.LayoutParams.MATCH_PARENT
                            } else {
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            },
                            ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
                        ?: LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        )
    }

    private fun CraftDAlign?.toRelativeLayoutParams(): Int =
            when (this) {
                CraftDAlign.CENTER -> CENTER_IN_PARENT
                CraftDAlign.RIGHT -> ALIGN_PARENT_END
                else -> ALIGN_PARENT_START
            }

    private fun String.toScaleType(): ImageView.ScaleType =
            when (this.lowercase()) {
                "crop" -> ImageView.ScaleType.CENTER_CROP
                "fit" -> ImageView.ScaleType.FIT_CENTER
                "fillbounds" -> ImageView.ScaleType.FIT_XY
                "fillwidth" -> ImageView.ScaleType.FIT_START
                "fillheight" -> ImageView.ScaleType.FIT_END
                "inside" -> ImageView.ScaleType.CENTER_INSIDE
                "none" -> ImageView.ScaleType.CENTER
                else -> ImageView.ScaleType.FIT_CENTER
            }
}
