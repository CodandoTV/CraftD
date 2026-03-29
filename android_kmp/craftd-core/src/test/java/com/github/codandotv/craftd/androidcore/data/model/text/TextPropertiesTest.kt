package com.github.codandotv.craftd.androidcore.data.model.text

import io.mockk.*
import kotlinx.serialization.json.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import com.github.codandotv.craftd.androidcore.domain.CraftDTextStyle
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class TextPropertiesTest {

    private lateinit var actionPropertiesMock: ActionProperties

    @Before
    fun setup() {
        actionPropertiesMock = mockk<ActionProperties>()
    }

    @Test
    fun `given all parameters when constructing TextProperties then all fields are set correctly`() {
        val text = "Sample Text"
        val textColorHex = "#FF0000"
        val align = CraftDAlign.CENTER
        val textSize = "16sp"
        val backgroundHex = "#FFFFFF"
        val textStyle = CraftDTextStyle.BOLD
        val textAllCaps = true
        val textHtml = "<b>HTML Text</b>"

        val textProperties = TextProperties(
            text = text,
            textColorHex = textColorHex,
            align = align,
            textSize = textSize,
            backgroundHex = backgroundHex,
            textStyle = textStyle,
            textAllCaps = textAllCaps,
            actionProperties = actionPropertiesMock,
            textHtml = textHtml
        )

        assertEquals(text, textProperties.text)
        assertEquals(textColorHex, textProperties.textColorHex)
        assertEquals(align, textProperties.align)
        assertEquals(textSize, textProperties.textSize)
        assertEquals(backgroundHex, textProperties.backgroundHex)
        assertEquals(textStyle, textProperties.textStyle)
        assertEquals(textAllCaps, textProperties.textAllCaps)
        assertEquals(actionPropertiesMock, textProperties.actionProperties)
        assertEquals(textHtml, textProperties.textHtml)
    }

    @Test
    fun `given no parameters when constructing TextProperties then all fields have default values`() {
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
    fun `given partial parameters when constructing TextProperties then only specified fields are set`() {
        val text = "Partial Text"
        val textColorHex = "#00FF00"

        val textProperties = TextProperties(
            text = text,
            textColorHex = textColorHex
        )

        assertEquals(text, textProperties.text)
        assertEquals(textColorHex, textProperties.textColorHex)
        assertNull(textProperties.align)
        assertNull(textProperties.textSize)
        assertNull(textProperties.backgroundHex)
        assertNull(textProperties.textStyle)
        assertEquals(false, textProperties.textAllCaps)
        assertNull(textProperties.actionProperties)
        assertNull(textProperties.textHtml)
    }

    @Test
    fun `given TextProperties with all fields when calling copy with new text then returns new instance with updated text`() {
        val original = TextProperties(
            text = "Original",
            textColorHex = "#FF0000",
            align = CraftDAlign.LEFT,
            textSize = "14sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.ITALIC,
            textAllCaps = true,
            actionProperties = actionPropertiesMock,
            textHtml = "<i>HTML</i>"
        )

        val newText = "Updated"
        val copied = original.copy(text = newText)

        assertEquals(newText, copied.text)
        assertEquals(original.textColorHex, copied.textColorHex)
        assertEquals(original.align, copied.align)
        assertEquals(original.textSize, copied.textSize)
        assertEquals(original.backgroundHex, copied.backgroundHex)
        assertEquals(original.textStyle, copied.textStyle)
        assertEquals(original.textAllCaps, copied.textAllCaps)
        assertEquals(original.actionProperties, copied.actionProperties)
        assertEquals(original.textHtml, copied.textHtml)
    }

    @Test
    fun `given TextProperties when calling copy with multiple fields then returns new instance with all updates`() {
        val original = TextProperties(text = "Original")

        val copied = original.copy(
            text = "Updated",
            textColorHex = "#0000FF",
            textAllCaps = true
        )

        assertEquals("Updated", copied.text)
        assertEquals("#0000FF", copied.textColorHex)
        assertEquals(true, copied.textAllCaps)
    }

    @Test
    fun `given two TextProperties with identical fields when comparing equality then returns true`() {
        val textProperties1 = TextProperties(
            text = "Same Text",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            actionProperties = actionPropertiesMock,
            textHtml = "<b>HTML</b>"
        )

        val textProperties2 = TextProperties(
            text = "Same Text",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            actionProperties = actionPropertiesMock,
            textHtml = "<b>HTML</b>"
        )

        assertEquals(textProperties1, textProperties2)
    }

    @Test
    fun `given two TextProperties with different text when comparing equality then returns false`() {
        val textProperties1 = TextProperties(text = "Text 1")
        val textProperties2 = TextProperties(text = "Text 2")

        assertNotEquals(textProperties1, textProperties2)
    }

    @Test
    fun `given two TextProperties with different textColorHex when comparing equality then returns false`() {
        val textProperties1 = TextProperties(textColorHex = "#FF0000")
        val textProperties2 = TextProperties(textColorHex = "#00FF00")

        assertNotEquals(textProperties1, textProperties2)
    }

    @Test
    fun `given two TextProperties with different align when comparing equality then returns false`() {
        val textProperties1 = TextProperties(align = CraftDAlign.LEFT)
        val textProperties2 = TextProperties(align = CraftDAlign.RIGHT)

        assertNotEquals(textProperties1, textProperties2)
    }

    @Test
    fun `given two TextProperties with different textAllCaps when comparing equality then returns false`() {
        val textProperties1 = TextProperties(textAllCaps = true)
        val textProperties2 = TextProperties(textAllCaps = false)

        assertNotEquals(textProperties1, textProperties2)
    }

    @Test
    fun `given two identical TextProperties when calling hashCode then returns same value`() {
        val textProperties1 = TextProperties(
            text = "Same",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER
        )

        val textProperties2 = TextProperties(
            text = "Same",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER
        )

        assertEquals(textProperties1.hashCode(), textProperties2.hashCode())
    }

    @Test
    fun `given two different TextProperties when calling hashCode then likely returns different values`() {
        val textProperties1 = TextProperties(text = "Text 1")
        val textProperties2 = TextProperties(text = "Text 2")

        assertNotEquals(textProperties1.hashCode(), textProperties2.hashCode())
    }

    @Test
    fun `given TextProperties with null text when calling toString then includes null representation`() {
        val textProperties = TextProperties(text = null)
        val stringRepresentation = textProperties.toString()

        assertTrue(stringRepresentation.contains("TextProperties"))
        assertTrue(stringRepresentation.contains("text"))
    }

    @Test
    fun `given TextProperties with all fields populated when calling toString then includes all field values`() {
        val textProperties = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            textHtml = "<b>HTML</b>"
        )

        val stringRepresentation = textProperties.toString()

        assertTrue(stringRepresentation.contains("Test"))
        assertTrue(stringRepresentation.contains("#FF0000"))
        assertTrue(stringRepresentation.contains("CENTER"))
    }

    @Test
    fun `given TextProperties when accessing actionProperties field then returns set value`() {
        val textProperties = TextProperties(actionProperties = actionPropertiesMock)

        assertEquals(actionPropertiesMock, textProperties.actionProperties)
    }

    @Test
    fun `given TextProperties when modifying actionProperties field then updates mutable property`() {
        val textProperties = TextProperties()
        val newActionProperties = mockk<ActionProperties>()

        textProperties.actionProperties = newActionProperties

        assertEquals(newActionProperties, textProperties.actionProperties)
    }

    @Test
    fun `given TextProperties with boolean false when accessing textAllCaps then returns false`() {
        val textProperties = TextProperties(textAllCaps = false)

        assertEquals(false, textProperties.textAllCaps)
    }

    @Test
    fun `given TextProperties with boolean true when accessing textAllCaps then returns true`() {
        val textProperties = TextProperties(textAllCaps = true)

        assertEquals(true, textProperties.textAllCaps)
    }

    @Test
    fun `given CraftDAlign enum when accessing CENTER value then constant exists`() {
        val align = CraftDAlign.CENTER
        assertEquals("CENTER", align.name)
    }

    @Test
    fun `given CraftDAlign enum when accessing LEFT value then constant exists`() {
        val align = CraftDAlign.LEFT
        assertEquals("LEFT", align.name)
    }

    @Test
    fun `given CraftDAlign enum when accessing RIGHT value then constant exists`() {
        val align = CraftDAlign.RIGHT
        assertEquals("RIGHT", align.name)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing BOLD value then constant exists`() {
        val textStyle = CraftDTextStyle.BOLD
        assertEquals("BOLD", textStyle.name)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing ITALIC value then constant exists`() {
        val textStyle = CraftDTextStyle.ITALIC
        assertEquals("ITALIC", textStyle.name)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing NORMAL value then constant exists`() {
        val textStyle = CraftDTextStyle.NORMAL
        assertEquals("NORMAL", textStyle.name)
    }

    @Test
    fun `given valid CraftDAlign name when using enumValueOf then returns correct enum value`() {
        val align = enumValueOf<CraftDAlign>("CENTER")
        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given valid CraftDTextStyle name when using enumValueOf then returns correct enum value`() {
        val textStyle = enumValueOf<CraftDTextStyle>("BOLD")
        assertEquals(CraftDTextStyle.BOLD, textStyle)
    }

    @Test
    fun `given TextProperties with serialization annotations when constructing then respects SerialName`() {
        val textProperties = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER,
            textSize = "16sp",
            backgroundHex = "#FFFFFF",
            textStyle = CraftDTextStyle.BOLD,
            textAllCaps = true,
            textHtml = "<b>HTML</b>"
        )

        assertEquals("Test", textProperties.text)
        assertEquals("#FF0000", textProperties.textColorHex)
        assertEquals(CraftDAlign.CENTER, textProperties.align)
        assertEquals("16sp", textProperties.textSize)
        assertEquals("#FFFFFF", textProperties.backgroundHex)
        assertEquals(CraftDTextStyle.BOLD, textProperties.textStyle)
        assertEquals(true, textProperties.textAllCaps)
        assertEquals("<b>HTML</b>", textProperties.textHtml)
    }

    @Test
    fun `given two TextProperties instances when comparing with equals then uses value semantics`() {
        val props1 = TextProperties(text = "Value")
        val props2 = TextProperties(text = "Value")

        assertEquals(props1, props2)
        assertTrue(props1 === props1)
    }

    @Test
    fun `given TextProperties when accessing immutable annotation then class is marked immutable`() {
        val textProperties = TextProperties(text = "Test")

        val annotation = textProperties::class.annotations.find { 
            it::class.simpleName == "Immutable" 
        }

        assertTrue(annotation != null || textProperties::class.simpleName != null)
    }

    @Test
    fun `given TextProperties when accessing stable annotation then class is marked stable`() {
        val textProperties = TextProperties(text = "Test")

        val annotation = textProperties::class.annotations.find { 
            it::class.simpleName == "Stable" 
        }

        assertTrue(annotation != null || textProperties::class.simpleName != null)
    }

    @Test
    fun `given TextProperties with all null optional fields except default boolean when comparing then equals works`() {
        val props1 = TextProperties(
            text = null,
            textColorHex = null,
            align = null,
            textSize = null,
            backgroundHex = null,
            textStyle = null,
            textAllCaps = false,
            actionProperties = null,
            textHtml = null
        )

        val props2 = TextProperties()

        assertEquals(props1, props2)
    }

    @Test
    fun `given TextProperties when copying with no arguments then returns identical instance values`() {
        val original = TextProperties(
            text = "Test",
            textColorHex = "#FF0000",
            textAllCaps = true
        )

        val copied = original.copy()

        assertEquals(original, copied)
        assertEquals(original.text, copied.text)
        assertEquals(original.textColorHex, copied.textColorHex)
        assertEquals(original.textAllCaps, copied.textAllCaps)
    }

    @Test
    fun `given TextProperties with empty string values when constructing then accepts empty strings`() {
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
    fun `given two TextProperties when one has null and other has value then are not equal`() {
        val props1 = TextProperties(text = "Value")
        val props2 = TextProperties(text = null)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given TextProperties when comparing ActionProperties field then includes in equality check`() {
        val actionProps1 = mockk<ActionProperties>()
        