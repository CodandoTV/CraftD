package com.github.codandotv.craftd.xml.ui.text;

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.github.codandotv.craftd.androidcore.data.model.text.TextProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle
import com.github.codandotv.craftd.androidcore.extensions.getAttrColorRes
import com.github.codandotv.craftd.xml.ui.button.parseColor


class CraftDTextViewComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setProperties(
        textProperties: TextProperties,
        onLinkClickedListener: ((url: String) -> Unit)? = null
    ) {
        textProperties.text?.let {
            text = it
        }

        textProperties.backgroundHex?.let { backgroundHex ->
            setBackgroundColor(backgroundHex.parseColor())
        }

        textProperties.textSize?.let { size ->
            setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
        }

        textProperties.textStyle?.let {
            setTypeface(null, it.toTextStyle())
        }

        textProperties.align.toGravity()

        textProperties.textHtml?.let { textHtml ->
            text = HtmlCompat.fromHtml(textHtml, HtmlCompat.FROM_HTML_MODE_COMPACT)

            onLinkClickedListener?.let { linkListener ->
                val linkListener = object : InternalLinkMovementMethod.OnLinkClickedListener {
                    override fun onLinkClicked(url: String?): Boolean {
                        url?.let { linkListener.invoke(it) }
                        return true
                    }
                }
                movementMethod = InternalLinkMovementMethod(linkListener)
            }
        }

        textProperties.textColorHex?.let { textColor ->
            setTextColor(textColor.parseColor())
        } ?: run {
            setTextColor(
                ContextCompat.getColor(
                    context,
                    context.getAttrColorRes(android.R.attr.textColorPrimary)
                )
            )
        }
    }

    private fun CraftDAlign?.toGravity() = when (this) {
        CraftDAlign.CENTER -> {
            gravity = Gravity.CENTER
        }

        CraftDAlign.RIGHT -> {
            gravity = Gravity.RIGHT
        }

        else -> gravity = Gravity.LEFT
    }


    private fun CraftDTextStyle.toTextStyle() = when (this) {
        CraftDTextStyle.BOLD -> Typeface.BOLD
        CraftDTextStyle.ITALIC -> Typeface.ITALIC
        CraftDTextStyle.NORMAL -> Typeface.NORMAL
    }

    class InternalLinkMovementMethod(
        private var onLinkClickedListener: OnLinkClickedListener
    ) : LinkMovementMethod() {

        override fun onTouchEvent(
            widget: TextView,
            buffer: Spannable,
            event: MotionEvent
        ): Boolean {
            val action = event.action

            if (action == MotionEvent.ACTION_UP) {
                var x = event.x.toInt()
                var y = event.y.toInt()
                x -= widget.totalPaddingLeft
                y -= widget.totalPaddingTop
                x += widget.scrollX
                y += widget.scrollY
                val layout = widget.layout
                val line: Int = layout.getLineForVertical(y)
                val off: Int = layout.getOffsetForHorizontal(line, x.toFloat())
                val link = buffer.getSpans(off, off, URLSpan::class.java)
                if (link.isNotEmpty()) {
                    val url = link[0].url
                    val handled = onLinkClickedListener.onLinkClicked(url)
                    return if (handled) true
                    else super.onTouchEvent(widget, buffer, event)
                }
            }
            return super.onTouchEvent(widget, buffer, event)
        }

        interface OnLinkClickedListener {
            fun onLinkClicked(url: String?): Boolean
        }
    }
}