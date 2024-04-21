package com.github.rviannaoliveira.dynamic.compose.presentation.ui.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.toAlignCompose
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.toTextStyle
import com.github.rviannaoliveira.dynamic.core.data.model.text.TextProperties
import com.github.rviannaoliveira.dynamic.core.extensions.empty
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.button.parseColorCompose

@Composable
fun DynamicText(
    textProperties: TextProperties,
    modifier: Modifier = Modifier,
    clickable: (() -> Unit)? = null,
) {
    val modifierCustom = clickable?.let {
        Modifier.clickable {
            clickable.invoke()
        }
    } ?: modifier

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = textProperties.backgroundHex.parseColorCompose()
    ) {
        Text(
            modifier = modifierCustom,
            style = textProperties.textStyle.toTextStyle().copy(
                fontSize = textProperties.textSize?.toFloat()?.sp ?: TextUnit.Unspecified,
            ),
            text = textProperties.textHtml?.let { html ->
                HtmlCompat.fromHtml(
                    html, HtmlCompat.FROM_HTML_MODE_COMPACT
                ).toString()
            } ?: textProperties.textWithRightCaps(),
            color = textProperties.textColorHex?.parseColorCompose() ?: Color.Unspecified,
            textAlign = textProperties.align.toAlignCompose(),
        )
    }
}

private fun TextProperties.textWithRightCaps(): String = when (textAllCaps) {
    true -> text?.uppercase()
    false -> text?.lowercase()
    else -> text
} ?: String.empty()
