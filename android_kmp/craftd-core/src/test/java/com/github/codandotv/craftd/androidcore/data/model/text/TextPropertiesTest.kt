package com.github.codandotv.craftd.androidcore.data.model.text

import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle

@RunWith(JUnit4::class)
class TextPropertiesTest {

    @Test
    fun `given all null parameters when creating TextProperties then properties are null`() {
        val textProperties = TextProperties()

        assertNull(textProperties.text)
        assertNull(textProperties.textColorHex)
        assertNull(textProperties.align)
        assertNull(textProperties.textSize)
        assertNull(textProperties.backgroundHex)
        assertNull(textProperties.textStyle)
        assertFalse(textProperties.textAllCaps ?: false)
        assertNull(textProperties.actionProperties)
        assertNull(textProperties.textHtml)
    }

    @Test
    fun `given all parameters when creating TextProperties then all properties are set correctly`() {
        val actionProperties = mockk<ActionProperties>()
        val textProperties = TextProperties(
            text = "Sample Text",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#000000",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            actionProperties = actionProperties,
            textHtml = "<b>Bold</b>"
        )

        assertEquals("Sample Text", textProperties.text)
        assertEquals("#FFFFFF", textProperties.textColorHex)
        assertEquals(CraftDAlign.CENTER, textProperties.align)
        assertEquals("16sp", textProperties.textSize)
        assertEquals("#000000", textProperties.backgroundHex)
        assertEquals(CraftDTextStyle.BOLD, textProperties.textStyle)
        assertTrue(textProperties.textAllCaps ?: false)
        assertEquals(actionProperties, textProperties.actionProperties)
        assertEquals("<b>Bold</b>", textProperties.textHtml)
    }

    @Test
    fun `given partial parameters when creating TextProperties then only provided properties are set`() {
        val textProperties = TextProperties(
            text = "Partial",
            textColorHex = "#FF0000",
            textSize = "14sp"
        )

        assertEquals("Partial", textProperties.text)
        assertEquals("#FF0000", textProperties.textColorHex)
        assertEquals("14sp", textProperties.textSize)
        assertNull(textProperties.align)
        assertNull(textProperties.backgroundHex)
        assertNull(textProperties.textStyle)
        assertFalse(textProperties.textAllCaps ?: false)
        assertNull(textProperties.actionProperties)
        assertNull(textProperties.textHtml)
    }

    @Test
    fun `given TextProperties when calling copy with new text then returns new instance with updated text`() {
        val original = TextProperties(text = "Original")
        val copied = original.copy(text = "Updated")

        assertEquals("Updated", copied.text)
        assertEquals("Original", original.text)
    }

    @Test
    fun `given TextProperties when calling copy with multiple fields then returns new instance with all updates`() {
        val original = TextProperties(
            text = "Original",
            textColorHex = "#FFFFFF",
            textSize = "12sp"
        )
        val copied = original.copy(
            text = "Modified",
            textColorHex = "#000000"
        )

        assertEquals("Modified", copied.text)
        assertEquals("#000000", copied.textColorHex)
        assertEquals("12sp", copied.textSize)
    }

    @Test
    fun `given two identical TextProperties when comparing with equals then returns true`() {
        val properties1 = TextProperties(
            text = "Test",
            textColorHex = "#FFFFFF",
            textSize = "16sp"
        )
        val properties2 = TextProperties(
            text = "Test",
            textColorHex = "#FFFFFF",
            textSize = "16sp"
        )

        assertEquals(properties1, properties2)
    }

    @Test
    fun `given two different TextProperties when comparing with equals then returns false`() {
        val properties1 = TextProperties(text = "Test1")
        val properties2 = TextProperties(text = "Test2")

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two identical TextProperties when comparing hashCode then returns same hash`() {
        val properties1 = TextProperties(
            text = "Test",
            textColorHex = "#FFFFFF"
        )
        val properties2 = TextProperties(
            text = "Test",
            textColorHex = "#FFFFFF"
        )

        assertEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given two different TextProperties when comparing hashCode then may return different hash`() {
        val properties1 = TextProperties(text = "Test1")
        val properties2 = TextProperties(text = "Test2")

        assertNotEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given TextProperties with null text when calling copy then returns new instance with null text`() {
        val original = TextProperties(text = null)
        val copied = original.copy()

        assertNull(copied.text)
    }

    @Test
    fun `given TextProperties with all fields when calling copy with null values then returns new instance with null values`() {
        val original = TextProperties(
            text = "Original",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textSize = "16sp"
        )
        val copied = original.copy(
            text = null,
            textColorHex = null
        )

        assertNull(copied.text)
        assertNull(copied.textColorHex)
        assertEquals(CraftDAlign.CENTER, copied.align)
        assertEquals("16sp", copied.textSize)
    }

    @Test
    fun `given TextProperties with actionProperties when comparing equals then considers actionProperties`() {
        val actionProperties1 = mockk<ActionProperties>()
        val actionProperties2 = mockk<ActionProperties>()
        val properties1 = TextProperties(text = "Test", actionProperties = actionProperties1)
        val properties2 = TextProperties(text = "Test", actionProperties = actionProperties1)
        val properties3 = TextProperties(text = "Test", actionProperties = actionProperties2)

        assertEquals(properties1, properties2)
        assertNotEquals(properties1, properties3)
    }

    @Test
    fun `given TextProperties with textAllCaps true when comparing equals then considers textAllCaps`() {
        val properties1 = TextProperties(text = "Test", textAllCaps = true)
        val properties2 = TextProperties(text = "Test", textAllCaps = true)
        val properties3 = TextProperties(text = "Test", textAllCaps = false)

        assertEquals(properties1, properties2)
        assertNotEquals(properties1, properties3)
    }

    @Test
    fun `given TextProperties when calling toString then contains all field values`() {
        val textProperties = TextProperties(
            text = "Test",
            textColorHex = "#FFFFFF"
        )
        val stringRepresentation = textProperties.toString()

        assertTrue(stringRepresentation.contains("Test"))
        assertTrue(stringRepresentation.contains("FFFFFF"))
    }

    @Test
    fun `given TextProperties with all enum values when properties are created then enums are set correctly`() {
        for (align in CraftDAlign.entries) {
            val properties = TextProperties(align = align)
            assertEquals(align, properties.align)
        }

        for (style in CraftDTextStyle.entries) {
            val properties = TextProperties(textStyle = style)
            assertEquals(style, properties.textStyle)
        }
    }

    @Test
    fun `given TextProperties with CraftDAlign CENTER when created then align is CENTER`() {
        val properties = TextProperties(align = CraftDAlign.CENTER)
        assertEquals(CraftDAlign.CENTER, properties.align)
    }

    @Test
    fun `given TextProperties with CraftDAlign START when created then align is START`() {
        val properties = TextProperties(align = CraftDAlign.START)
        assertEquals(CraftDAlign.START, properties.align)
    }

    @Test
    fun `given TextProperties with CraftDAlign END when created then align is END`() {
        val properties = TextProperties(align = CraftDAlign.END)
        assertEquals(CraftDAlign.END, properties.align)
    }

    @Test
    fun `given TextProperties with CraftDTextStyle BOLD when created then textStyle is BOLD`() {
        val properties = TextProperties(textStyle = CraftDTextStyle.BOLD)
        assertEquals(CraftDTextStyle.BOLD, properties.textStyle)
    }

    @Test
    fun `given TextProperties with CraftDTextStyle ITALIC when created then textStyle is ITALIC`() {
        val properties = TextProperties(textStyle = CraftDTextStyle.ITALIC)
        assertEquals(CraftDTextStyle.ITALIC, properties.textStyle)
    }

    @Test
    fun `given TextProperties with empty text when created then text is empty string`() {
        val properties = TextProperties(text = "")
        assertEquals("", properties.text)
    }

    @Test
    fun `given TextProperties with html content when created then textHtml is set correctly`() {
        val htmlContent = "<b>Bold</b><i>Italic</i>"
        val properties = TextProperties(textHtml = htmlContent)
        assertEquals(htmlContent, properties.textHtml)
    }

    @Test
    fun `given TextProperties with valid hex colors when created then colors are set correctly`() {
        val textProperties = TextProperties(
            textColorHex = "#FF5733",
            backgroundHex = "#33FF57"
        )
        assertEquals("#FF5733", textProperties.textColorHex)
        assertEquals("#33FF57", textProperties.backgroundHex)
    }

    @Test
    fun `given TextProperties with numeric textSize when created then textSize is set correctly`() {
        val textProperties = TextProperties(textSize = "24sp")
        assertEquals("24sp", textProperties.textSize)
    }

    @Test
    fun `given TextProperties with textAllCaps false when created then textAllCaps is false`() {
        val textProperties = TextProperties(textAllCaps = false)
        assertFalse(textProperties.textAllCaps ?: true)
    }

    @Test
    fun `given two TextProperties with null actionProperties when comparing equals then returns true`() {
        val properties1 = TextProperties(text = "Test", actionProperties = null)
        val properties2 = TextProperties(text = "Test", actionProperties = null)

        assertEquals(properties1, properties2)
    }

    @Test
    fun `given TextProperties when calling copy without parameters then returns identical instance`() {
        val original = TextProperties(
            text = "Test",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER
        )
        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given TextProperties with multiple fields when calling copy selectively then preserves unmodified fields`() {
        val actionProperties = mockk<ActionProperties>()
        val original = TextProperties(
            text = "Original",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#000000",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            actionProperties = actionProperties,
            textHtml = "<b>Bold</b>"
        )
        val copied = original.copy(text = "Updated")

        assertEquals("Updated", copied.text)
        assertEquals("#FFFFFF", copied.textColorHex)
        assertEquals(CraftDAlign.CENTER, copied.align)
        assertEquals("16sp", copied.textSize)
        assertEquals("#000000", copied.backgroundHex)
        assertEquals(CraftDTextStyle.BOLD, copied.textStyle)
        assertTrue(copied.textAllCaps ?: false)
        assertEquals(actionProperties, copied.actionProperties)
        assertEquals("<b>Bold</b>", copied.textHtml)
    }

    @Test
    fun `given TextProperties with nullable fields when accessing properties then handles null gracefully`() {
        val properties = TextProperties()

        val text = properties.text
        val color = properties.textColorHex
        val align = properties.align
        val size = properties.textSize
        val background = properties.backgroundHex
        val style = properties.textStyle
        val html = properties.textHtml

        assertNull(text)
        assertNull(color)
        assertNull(align)
        assertNull(size)
        assertNull(background)
        assertNull(style)
        assertNull(html)
    }

    @Test
    fun `given TextProperties with mutable actionProperties when modifying then reflects changes`() {
        val actionProperties = mockk<ActionProperties>()
        val properties = TextProperties(actionProperties = actionProperties)

        assertEquals(actionProperties, properties.actionProperties)

        val newActionProperties = mockk<ActionProperties>()
        properties.actionProperties = newActionProperties

        assertEquals(newActionProperties, properties.actionProperties)
    }

    @Test
    fun `given TextProperties instances when comparing different align values then not equal`() {
        val properties1 = TextProperties(text = "Test", align = CraftDAlign.CENTER)
        val properties2 = TextProperties(text = "Test", align = CraftDAlign.START)

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given TextProperties instances when comparing different textStyle values then not equal`() {
        val properties1 = TextProperties(text = "Test", textStyle = CraftDTextStyle.BOLD)
        val properties2 = TextProperties(text = "Test", textStyle = CraftDTextStyle.ITALIC)

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given TextProperties when calling copy with same values then equals original`() {
        val original = TextProperties(
            text = "Test",
            textColorHex = "#FFFFFF",
            textSize = "16sp"
        )
        val copied = original.copy(
            text = "Test",
            textColorHex = "#FFFFFF",
            textSize = "16sp"
        )

        assertEquals(original, copied)
    }

    @Test
    fun `given TextProperties with default textAllCaps when created then textAllCaps is false`() {
        val properties = TextProperties()
        assertFalse(properties.textAllCaps ?: true)
    }
}