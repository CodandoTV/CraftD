package com.github.codandotv.craftd.compose.ui.image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.github.codandotv.craftd.androidcore.data.model.image.ImageProperties
import com.github.codandotv.craftd.compose.extensions.toArrangementCompose

@Composable
fun CraftDImage(
        imageProperties: ImageProperties,
        modifier: Modifier = Modifier,
        clickable: (() -> Unit)? = null,
) {
    val modifierCustom = clickable?.let { Modifier.clickable { clickable.invoke() } } ?: modifier

    Row(
            horizontalArrangement = imageProperties.align.toArrangementCompose(),
            modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
                model = imageProperties.url,
                contentDescription = imageProperties.contentDescription,
                modifier =
                        modifierCustom
                                .then(
                                        if (imageProperties.fillMaxSize == true) {
                                            Modifier.fillMaxSize()
                                        } else {
                                            Modifier
                                        }
                                )
                                .then(
                                        imageProperties.aspectRatio?.let {
                                            Modifier.aspectRatio(it)
                                        }
                                                ?: Modifier
                                ),
                contentScale = imageProperties.contentScale?.toContentScale() ?: ContentScale.Fit
        )
    }
}

private fun String.toContentScale(): ContentScale =
        when (this.lowercase()) {
            "crop" -> ContentScale.Crop
            "fit" -> ContentScale.Fit
            "fillbounds" -> ContentScale.FillBounds
            "fillwidth" -> ContentScale.FillWidth
            "fillheight" -> ContentScale.FillHeight
            "inside" -> ContentScale.Inside
            "none" -> ContentScale.None
            else -> ContentScale.Fit
        }
