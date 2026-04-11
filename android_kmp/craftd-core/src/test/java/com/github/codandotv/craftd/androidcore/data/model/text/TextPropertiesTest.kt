package com.github.codandotv.craftd.androidcore.data.model.text

import io.mockk.mockk
import io.mockk.verify
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle

@RunWith(JUnit4::class)
class TextPropertiesTest {

    @Test
    fun `given default constructor when creating TextProperties then all fields have default values`() {
        val textProperties = TextProperties()

        assertNull(textProperties.text)
        assertNull(textProperties.textColorHex)
        assertNull(textProperties.align)
        assertNull(textProperties.textSize)
        assertNull(textProperties.backgroundHex)
        assertNull(textProperties.textStyle)
        assertEquals(false, textProperties.textAllCaps)
        assertNull(textProperties.actionProperties)
        assertNull(textProperties.textHtml)
    }

    @Test
    fun `given all parameters when creating TextProperties then fields are set correctly`() {
        val actionProperties = mockk<ActionProperties>()
        val textProperties = TextProperties(
            text = "Sample Text",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            actionProperties = actionProperties,
            textHtml = "<b>Bold</b>"
        )

        assertEquals("Sample Text", textProperties.text)
        assertEquals("#FF0000", textProperties.textColorHex)
        assertEquals(CraftDAlign.CENTER, textProperties.align)
        assertEquals("16sp", textProperties.textSize)
        assertEquals("#FFFFFF", textProperties.backgroundHex)
        assertEquals(CraftDTextStyle.BOLD, textProperties.textStyle)
        assertTrue(textProperties.textAllCaps!!)
        assertEquals(actionProperties, textProperties.actionProperties)
        assertEquals("<b>Bold</b>", textProperties.textHtml)
    }

    @Test
    fun `given TextProperties with partial parameters when creating then unspecified fields are null`() {
        val textProperties = TextProperties(
            text = "Partial Text",
            textColorHex = "#FF0000"
        )

        assertEquals("Partial Text", textProperties.text)
        assertEquals("#FF0000", textProperties.textColorHex)
        assertNull(textProperties.align)
        assertNull(textProperties.textSize)
        assertNull(textProperties.backgroundHex)
        assertNull(textProperties.textStyle)
        assertEquals(false, textProperties.textAllCaps)
        assertNull(textProperties.actionProperties)
        assertNull(textProperties.textHtml)
    }

    @Test
    fun `given TextProperties when calling copy with new text then creates new instance with updated text`() {
        val original = TextProperties(text = "Original")
        val copied = original.copy(text = "Updated")

        assertEquals("Updated", copied.text)
        assertEquals("Original", original.text)
    }

    @Test
    fun `given TextProperties when calling copy with multiple parameters then creates new instance with all updates`() {
        val original = TextProperties(
            text = "Original",
            textColorHex = "#FF0000",
            align = CraftDAlign.LEFT
        )
        val copied = original.copy(
            text = "Updated",
            textColorHex = "#00FF00",
            textSize = "14sp"
        )

        assertEquals("Updated", copied.text)
        assertEquals("#00FF00", copied.textColorHex)
        assertEquals(CraftDAlign.LEFT, copied.align)
        assertEquals("14sp", copied.textSize)
    }

    @Test
    fun `given two identical TextProperties when comparing with equals then returns true`() {
        val first = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER
        )
        val second = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER
        )

        assertEquals(first, second)
        assertTrue(first == second)
    }

    @Test
    fun `given two different TextProperties when comparing with equals then returns false`() {
        val first = TextProperties(text = "Test1")
        val second = TextProperties(text = "Test2")

        assertNotEquals(first, second)
        assertFalse(first == second)
    }

    @Test
    fun `given TextProperties instances with different textAllCaps when comparing then returns false`() {
        val first = TextProperties(textAllCaps = true)
        val second = TextProperties(textAllCaps = false)

        assertNotEquals(first, second)
    }

    @Test
    fun `given two identical TextProperties when calling hashCode then returns same hash`() {
        val first = TextProperties(text = "Test", textColorHex = "#FF0000")
        val second = TextProperties(text = "Test", textColorHex = "#FF0000")

        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given two different TextProperties when calling hashCode then hash values may differ`() {
        val first = TextProperties(text = "Test1")
        val second = TextProperties(text = "Test2")

        assertNotEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given TextProperties when calling toString then returns string representation`() {
        val textProperties = TextProperties(text = "Test", textColorHex = "#FF0000")
        val stringRep = textProperties.toString()

        assertTrue(stringRep.contains("TextProperties"))
        assertTrue(stringRep.contains("Test"))
        assertTrue(stringRep.contains("#FF0000"))
    }

    @Test
    fun `given TextProperties with ActionProperties when setting actionProperties then property is updated`() {
        val textProperties = TextProperties()
        val actionProperties = mockk<ActionProperties>()

        textProperties.actionProperties = actionProperties

        assertEquals(actionProperties, textProperties.actionProperties)
    }

    @Test
    fun `given all CraftDAlign enum values when verifying they exist then all are accessible`() {
        assertEquals(CraftDAlign.LEFT, enumValueOf<CraftDAlign>("LEFT"))
        assertEquals(CraftDAlign.CENTER, enumValueOf<CraftDAlign>("CENTER"))
        assertEquals(CraftDAlign.RIGHT, enumValueOf<CraftDAlign>("RIGHT"))
    }

    @Test
    fun `given all CraftDTextStyle enum values when verifying they exist then all are accessible`() {
        assertEquals(CraftDTextStyle.NORMAL, enumValueOf<CraftDTextStyle>("NORMAL"))
        assertEquals(CraftDTextStyle.BOLD, enumValueOf<CraftDTextStyle>("BOLD"))
        assertEquals(CraftDTextStyle.ITALIC, enumValueOf<CraftDTextStyle>("ITALIC"))
    }

    @Test
    fun `given TextProperties with actionProperties when verifying mutability then can reassign`() {
        val firstAction = mockk<ActionProperties>()
        val secondAction = mockk<ActionProperties>()
        val textProperties = TextProperties(actionProperties = firstAction)

        assertEquals(firstAction, textProperties.actionProperties)

        textProperties.actionProperties = secondAction
        assertEquals(secondAction, textProperties.actionProperties)
    }

    @Test
    fun `given TextProperties with null values when checking nullable fields then all are null`() {
        val textProperties = TextProperties(
            text = null,
            textColorHex = null,
            align = null,
            textSize = null,
            backgroundHex = null,
            textStyle = null,
            actionProperties = null,
            textHtml = null
        )

        assertNull(textProperties.text)
        assertNull(textProperties.textColorHex)
        assertNull(textProperties.align)
        assertNull(textProperties.textSize)
        assertNull(textProperties.backgroundHex)
        assertNull(textProperties.textStyle)
        assertNull(textProperties.actionProperties)
        assertNull(textProperties.textHtml)
    }

    @Test
    fun `given TextProperties with empty strings when creating then strings are preserved`() {
        val textProperties = TextProperties(
            text = "",
            textColorHex = "",
            textSize = "",
            backgroundHex = "",
            textHtml = ""
        )

        assertEquals("", textProperties.text)
        assertEquals("", textProperties.textColorHex)
        assertEquals("", textProperties.textSize)
        assertEquals("", textProperties.backgroundHex)
        assertEquals("", textProperties.textHtml)
    }

    @Test
    fun `given TextProperties copy operation when preserving textAllCaps then boolean value is maintained`() {
        val original = TextProperties(textAllCaps = true)
        val copied = original.copy()

        assertTrue(copied.textAllCaps!!)
    }

    @Test
    fun `given TextProperties with all fields populated when calling copy without parameters then creates exact duplicate`() {
        val actionProperties = mockk<ActionProperties>()
        val original = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            actionProperties = actionProperties,
            textHtml = "<b>Bold</b>"
        )
        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given TextProperties instances when comparing same instance to itself then equals returns true`() {
        val textProperties = TextProperties(text = "Test")

        assertEquals(textProperties, textProperties)
        assertTrue(textProperties == textProperties)
    }

    @Test
    fun `given TextProperties with boolean false when copying and changing to true then reflects change`() {
        val original = TextProperties(textAllCaps = false)
        val copied = original.copy(textAllCaps = true)

        assertFalse(original.textAllCaps!!)
        assertTrue(copied.textAllCaps!!)
    }

    @Test
    fun `given TextProperties instances with null and non-null text when comparing then not equal`() {
        val first = TextProperties(text = null)
        val second = TextProperties(text = "Text")

        assertNotEquals(first, second)
    }

    @Test
    fun `given TextProperties with all align values when creating each then all are stored correctly`() {
        val alignLeft = TextProperties(align = CraftDAlign.LEFT)
        val alignCenter = TextProperties(align = CraftDAlign.CENTER)
        val alignRight = TextProperties(align = CraftDAlign.RIGHT)

        assertEquals(CraftDAlign.LEFT, alignLeft.align)
        assertEquals(CraftDAlign.CENTER, alignCenter.align)
        assertEquals(CraftDAlign.RIGHT, alignRight.align)
    }

    @Test
    fun `given TextProperties with all textStyle values when creating each then all are stored correctly`() {
        val styleRegular = TextProperties(textStyle = CraftDTextStyle.NORMAL)
        val styleBold = TextProperties(textStyle = CraftDTextStyle.BOLD)
        val styleItalic = TextProperties(textStyle = CraftDTextStyle.ITALIC)

        assertEquals(CraftDTextStyle.NORMAL, styleRegular.textStyle)
        assertEquals(CraftDTextStyle.BOLD, styleBold.textStyle)
        assertEquals(CraftDTextStyle.ITALIC, styleItalic.textStyle)
    }
}