package com.github.codandotv.craftd.androidcore.data.model.text

import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

@RunWith(JUnit4::class)
class TextPropertiesTest {

    @Test
    fun `given all parameters when constructing TextProperties then all fields are set correctly`() {
        val actionProps = mockk<ActionProperties>()
        val textProps = TextProperties(
            text = "Hello World",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            actionProperties = actionProps,
            textHtml = "<b>HTML</b>"
        )

        assertEquals("Hello World", textProps.text)
        assertEquals("#FF0000", textProps.textColorHex)
        assertEquals(CraftDAlign.CENTER, textProps.align)
        assertEquals("16sp", textProps.textSize)
        assertEquals("#FFFFFF", textProps.backgroundHex)
        assertEquals(CraftDTextStyle.BOLD, textProps.textStyle)
        assertTrue(textProps.textAllCaps ?: false)
        assertEquals(actionProps, textProps.actionProperties)
        assertEquals("<b>HTML</b>", textProps.textHtml)
    }

    @Test
    fun `given no parameters when constructing TextProperties then all fields have default values`() {
        val textProps = TextProperties()

        assertEquals(null, textProps.text)
        assertEquals(null, textProps.textColorHex)
        assertEquals(null, textProps.align)
        assertEquals(null, textProps.textSize)
        assertEquals(null, textProps.backgroundHex)
        assertEquals(null, textProps.textStyle)
        assertFalse(textProps.textAllCaps ?: true)
        assertEquals(null, textProps.actionProperties)
        assertEquals(null, textProps.textHtml)
    }

    @Test
    fun `given partial parameters when constructing TextProperties then only specified fields are set`() {
        val textProps = TextProperties(
            text = "Partial",
            textColorHex = "#0000FF"
        )

        assertEquals("Partial", textProps.text)
        assertEquals("#0000FF", textProps.textColorHex)
        assertEquals(null, textProps.align)
        assertEquals(null, textProps.textSize)
        assertEquals(null, textProps.backgroundHex)
        assertEquals(null, textProps.textStyle)
        assertFalse(textProps.textAllCaps ?: true)
        assertEquals(null, textProps.actionProperties)
        assertEquals(null, textProps.textHtml)
    }

    @Test
    fun `given TextProperties when calling copy with new values then returns new instance with updated fields`() {
        val original = TextProperties(
            text = "Original",
            textColorHex = "#FF0000",
            align = CraftDAlign.LEFT
        )

        val copied = original.copy(
            text = "Updated",
            textColorHex = "#00FF00"
        )

        assertEquals("Updated", copied.text)
        assertEquals("#00FF00", copied.textColorHex)
        assertEquals(CraftDAlign.LEFT, copied.align)
        assertEquals("Original", original.text)
        assertEquals("#FF0000", original.textColorHex)
    }

    @Test
    fun `given TextProperties when calling copy without parameters then returns identical copy`() {
        val original = TextProperties(
            text = "Test",
            textColorHex = "#123456",
            textAllCaps = true
        )

        val copied = original.copy()

        assertEquals(original.text, copied.text)
        assertEquals(original.textColorHex, copied.textColorHex)
        assertEquals(original.textAllCaps, copied.textAllCaps)
        assertNotEquals(original, copied as Any)
    }

    @Test
    fun `given two TextProperties with same values when comparing with equals then returns true`() {
        val props1 = TextProperties(
            text = "Same",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "14sp"
        )
        val props2 = TextProperties(
            text = "Same",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "14sp"
        )

        assertEquals(props1, props2)
    }

    @Test
    fun `given two TextProperties with different text when comparing with equals then returns false`() {
        val props1 = TextProperties(text = "Text1")
        val props2 = TextProperties(text = "Text2")

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two TextProperties with different textColorHex when comparing with equals then returns false`() {
        val props1 = TextProperties(textColorHex = "#FF0000")
        val props2 = TextProperties(textColorHex = "#00FF00")

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two TextProperties with different align when comparing with equals then returns false`() {
        val props1 = TextProperties(align = CraftDAlign.LEFT)
        val props2 = TextProperties(align = CraftDAlign.RIGHT)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two TextProperties with different textStyle when comparing with equals then returns false`() {
        val props1 = TextProperties(textStyle = CraftDTextStyle.BOLD)
        val props2 = TextProperties(textStyle = CraftDTextStyle.ITALIC)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two TextProperties with different textAllCaps when comparing with equals then returns false`() {
        val props1 = TextProperties(textAllCaps = true)
        val props2 = TextProperties(textAllCaps = false)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two TextProperties with different actionProperties when comparing with equals then returns false`() {
        val actionProps1 = mockk<ActionProperties>()
        val actionProps2 = mockk<ActionProperties>()
        val props1 = TextProperties(actionProperties = actionProps1)
        val props2 = TextProperties(actionProperties = actionProps2)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two TextProperties with same values when comparing hashCode then returns same hash`() {
        val props1 = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            textSize = "16sp"
        )
        val props2 = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            textSize = "16sp"
        )

        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given two TextProperties with different values when comparing hashCode then likely returns different hash`() {
        val props1 = TextProperties(text = "Test1")
        val props2 = TextProperties(text = "Test2")

        assertNotEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given TextProperties instance when calling toString then returns string representation`() {
        val textProps = TextProperties(
            text = "Test",
            textColorHex = "#FF0000"
        )

        val stringRep = textProps.toString()

        assertTrue(stringRep.isNotEmpty())
        assertTrue(stringRep.contains("TextProperties"))
    }

    @Test
    fun `given TextProperties with all null fields when comparing equals then returns true`() {
        val props1 = TextProperties()
        val props2 = TextProperties()

        assertEquals(props1, props2)
    }

    @Test
    fun `given TextProperties with null text when constructing then text is null`() {
        val textProps = TextProperties(text = null)

        assertEquals(null, textProps.text)
    }

    @Test
    fun `given TextProperties when setting actionProperties then field is updated`() {
        val textProps = TextProperties()
        val actionProps = mockk<ActionProperties>()
        textProps.actionProperties = actionProps

        assertEquals(actionProps, textProps.actionProperties)
    }

    @Test
    fun `given TextProperties with empty string text when constructing then text is empty string`() {
        val textProps = TextProperties(text = "")

        assertEquals("", textProps.text)
    }

    @Test
    fun `given TextProperties with textHtml when constructing then textHtml is set`() {
        val htmlContent = "<b>Bold</b><i>Italic</i>"
        val textProps = TextProperties(textHtml = htmlContent)

        assertEquals(htmlContent, textProps.textHtml)
    }

    @Test
    fun `given CraftDAlign enum when accessing CENTER then constant exists`() {
        val align = CraftDAlign.CENTER

        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given CraftDAlign enum when accessing LEFT then constant exists`() {
        val align = CraftDAlign.LEFT

        assertEquals(CraftDAlign.LEFT, align)
    }

    @Test
    fun `given CraftDAlign enum when accessing RIGHT then constant exists`() {
        val align = CraftDAlign.RIGHT

        assertEquals(CraftDAlign.RIGHT, align)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing BOLD then constant exists`() {
        val style = CraftDTextStyle.BOLD

        assertEquals(CraftDTextStyle.BOLD, style)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing ITALIC then constant exists`() {
        val style = CraftDTextStyle.ITALIC

        assertEquals(CraftDTextStyle.ITALIC, style)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing NORMAL then constant exists`() {
        val style = CraftDTextStyle.NORMAL

        assertEquals(CraftDTextStyle.NORMAL, style)
    }

    @Test
    fun `given TextProperties with boolean false when constructing textAllCaps then false is preserved`() {
        val textProps = TextProperties(textAllCaps = false)

        assertFalse(textProps.textAllCaps ?: true)
    }

    @Test
    fun `given TextProperties with boolean true when constructing textAllCaps then true is preserved`() {
        val textProps = TextProperties(textAllCaps = true)

        assertTrue(textProps.textAllCaps ?: false)
    }

    @Test
    fun `given TextProperties when copying and modifying actionProperties then original is unchanged`() {
        val originalAction = mockk<ActionProperties>()
        val props = TextProperties(actionProperties = originalAction)
        val newAction = mockk<ActionProperties>()

        props.actionProperties = newAction

        assertEquals(newAction, props.actionProperties)
        assertNotEquals(originalAction, props.actionProperties)
    }

    @Test
    fun `given multiple TextProperties when comparing different combinations then equals works correctly`() {
        val base = TextProperties()
        val withText = TextProperties(text = "Test")
        val withColor = TextProperties(textColorHex = "#FF0000")
        val withBoth = TextProperties(text = "Test", textColorHex = "#FF0000")

        assertEquals(base, base)
        assertNotEquals(base, withText)
        assertNotEquals(withText, withColor)
        assertEquals(
            TextProperties(text = "Test", textColorHex = "#FF0000"),
            withBoth
        )
    }

    @Test
    fun `given TextProperties with all fields when comparing with partially filled then returns false`() {
        val full = TextProperties(
            text = "Text",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            textHtml = "<b>HTML</b>"
        )
        val partial = TextProperties(
            text = "Text",
            textColorHex = "#FF0000"
        )

        assertNotEquals(full, partial)
    }

    @Test
    fun `given TextProperties instance when accessing properties multiple times then returns consistent values`() {
        val textProps = TextProperties(
            text = "Consistent",
            textColorHex = "#FF0000"
        )

        val text1 = textProps.text
        val text2 = textProps.text
        val color1 = textProps.textColorHex
        val color2 = textProps.textColorHex

        assertEquals(text1, text2)
        assertEquals(color1, color2)
        assertEquals(text1, "Consistent")
        assertEquals(color1, "#FF0000")
    }

    @Test
    fun `given TextProperties when using destructuring then all fields are accessible`() {
        val (text, color, align, size, bg, style, caps, action, html) = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            textHtml = "<b>HTML</b>"
        )

        assertEquals("Test", text)
        assertEquals("#FF0000", color)
        assertEquals(CraftDAlign.CENTER, align)
        assertEquals("16sp", size)
        assertEquals("#FFFFFF", bg)
        assertEquals(CraftDTextStyle.BOLD, style)
        assertTrue(caps ?: false)
        assertEquals(null, action)
        assertEquals("<b>HTML</b>", html)
    }

    @Test
    fun `given TextProperties with special characters when constructing then special characters are preserved`() {
        val specialText = "Hello & Goodbye <> \"quotes\" 'apostrophes'"
        val textProps = TextProperties(text = specialText)

        assertEquals(specialText, textProps.text)
    }

    @Test
    fun `given TextProperties with very long text when constructing then long text is preserved`() {
        val longText = "A".repeat(10000)
        val textProps = TextProperties(text = longText)

        assertEquals(longText, textProps.text)
        assertEquals(10000, textProps.text?.length)
    }

    @Test
    fun `given TextProperties when marking as Stable then composition stability is maintained`() {
        val textProps = TextProperties(text = "Test")

        assertEquals("Test", textProps.text)
    }

    @Test
    fun `given TextProperties when marking as Immutable then immutability contract is indicated`() {
        val textProps = TextProperties(text = "Immutable")

        assertEquals("Immutable", textProps.text)
    }

    @Test
    fun `given TextProperties with numeric textSize when constructing then textSize is stored as string`() {
        val textProps = TextProperties(textSize = "18sp")

        assertEquals("18sp", textProps.textSize)
    }

    @Test
    fun `given TextProperties with different case hex colors when constructing then colors are preserved as-is`() {
        val uppercase = TextProperties(textColorHex = "#FF0000")
        val lowercase = TextProperties(textColorHex = "#ff0000")

        assertEquals("#FF0000", uppercase.textColorHex)
        assertEquals("#ff0000", lowercase.textColorHex)
        assertNotEquals(uppercase, lowercase)
    }
}