package com.github.codandotv.craftd.androidcore.data.model.button

import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

@RunWith(JUnit4::class)
class ButtonPropertiesTest {

    @Test
    fun `given all parameters when constructing ButtonProperties then creates instance with all fields`() {
        val actionProperties = mockk<ActionProperties>()
        val button = ButtonProperties(
            text = "Click me",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#000000",
            actionProperties = actionProperties
        )

        assertEquals("Click me", button.text)
        assertEquals("#FFFFFF", button.textColorHex)
        assertEquals(CraftDAlign.CENTER, button.align)
        assertEquals(CraftDAlign.CENTER, button.textAlign)
        assertEquals("16sp", button.textSize)
        assertEquals(true, button.textAllCaps)
        assertEquals(true, button.fillMaxSize)
        assertEquals("#000000", button.backgroundHex)
        assertEquals(actionProperties, button.actionProperties)
    }

    @Test
    fun `given no parameters when constructing ButtonProperties then creates instance with default values`() {
        val button = ButtonProperties()

        assertEquals(null, button.text)
        assertEquals(null, button.textColorHex)
        assertEquals(null, button.align)
        assertEquals(null, button.textAlign)
        assertEquals(null, button.textSize)
        assertEquals(false, button.textAllCaps)
        assertEquals(false, button.fillMaxSize)
        assertEquals(null, button.backgroundHex)
        assertEquals(null, button.actionProperties)
    }

    @Test
    fun `given partial parameters when constructing ButtonProperties then creates instance with mixed values`() {
        val button = ButtonProperties(
            text = "Submit",
            textColorHex = "#FF0000",
            textSize = "18sp"
        )

        assertEquals("Submit", button.text)
        assertEquals("#FF0000", button.textColorHex)
        assertEquals("18sp", button.textSize)
        assertEquals(null, button.align)
        assertEquals(null, button.textAlign)
        assertEquals(false, button.textAllCaps)
        assertEquals(false, button.fillMaxSize)
        assertEquals(null, button.backgroundHex)
        assertEquals(null, button.actionProperties)
    }

    @Test
    fun `given ButtonProperties instance when calling copy with new text then returns new instance with updated text`() {
        val original = ButtonProperties(text = "Original")
        val copied = original.copy(text = "Updated")

        assertEquals("Updated", copied.text)
        assertEquals("Original", original.text)
    }

    @Test
    fun `given ButtonProperties instance when calling copy with new color then returns new instance with updated color`() {
        val original = ButtonProperties(textColorHex = "#000000")
        val copied = original.copy(textColorHex = "#FFFFFF")

        assertEquals("#FFFFFF", copied.textColorHex)
        assertEquals("#000000", original.textColorHex)
    }

    @Test
    fun `given ButtonProperties instance when calling copy with multiple fields then returns new instance with all updates`() {
        val actionProperties = mockk<ActionProperties>()
        val original = ButtonProperties(
            text = "Original",
            textColorHex = "#000000",
            align = CraftDAlign.LEFT
        )
        val copied = original.copy(
            text = "Updated",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            actionProperties = actionProperties
        )

        assertEquals("Updated", copied.text)
        assertEquals("#FFFFFF", copied.textColorHex)
        assertEquals(CraftDAlign.CENTER, copied.align)
        assertEquals(actionProperties, copied.actionProperties)

        assertEquals("Original", original.text)
        assertEquals("#000000", original.textColorHex)
        assertEquals(CraftDAlign.LEFT, original.align)
        assertEquals(null, original.actionProperties)
    }

    @Test
    fun `given two identical ButtonProperties when comparing with equals then returns true`() {
        val button1 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER
        )
        val button2 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER
        )

        assertEquals(button1, button2)
        assertTrue(button1 == button2)
    }

    @Test
    fun `given two ButtonProperties with different text when comparing with equals then returns false`() {
        val button1 = ButtonProperties(text = "Click")
        val button2 = ButtonProperties(text = "Tap")

        assertNotEquals(button1, button2)
        assertFalse(button1 == button2)
    }

    @Test
    fun `given two ButtonProperties with different colors when comparing with equals then returns false`() {
        val button1 = ButtonProperties(textColorHex = "#FFFFFF")
        val button2 = ButtonProperties(textColorHex = "#000000")

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different align when comparing with equals then returns false`() {
        val button1 = ButtonProperties(align = CraftDAlign.CENTER)
        val button2 = ButtonProperties(align = CraftDAlign.LEFT)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different textAlign when comparing with equals then returns false`() {
        val button1 = ButtonProperties(textAlign = CraftDAlign.CENTER)
        val button2 = ButtonProperties(textAlign = CraftDAlign.RIGHT)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different textSize when comparing with equals then returns false`() {
        val button1 = ButtonProperties(textSize = "16sp")
        val button2 = ButtonProperties(textSize = "18sp")

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different textAllCaps when comparing with equals then returns false`() {
        val button1 = ButtonProperties(textAllCaps = true)
        val button2 = ButtonProperties(textAllCaps = false)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different fillMaxSize when comparing with equals then returns false`() {
        val button1 = ButtonProperties(fillMaxSize = true)
        val button2 = ButtonProperties(fillMaxSize = false)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different backgroundHex when comparing with equals then returns false`() {
        val button1 = ButtonProperties(backgroundHex = "#FFFFFF")
        val button2 = ButtonProperties(backgroundHex = "#000000")

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different actionProperties when comparing with equals then returns false`() {
        val action1 = mockk<ActionProperties>()
        val action2 = mockk<ActionProperties>()
        val button1 = ButtonProperties(actionProperties = action1)
        val button2 = ButtonProperties(actionProperties = action2)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two identical ButtonProperties when calling hashCode then returns same hash`() {
        val button1 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF"
        )
        val button2 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF"
        )

        assertEquals(button1.hashCode(), button2.hashCode())
    }

    @Test
    fun `given two different ButtonProperties when calling hashCode then likely returns different hash`() {
        val button1 = ButtonProperties(text = "Click")
        val button2 = ButtonProperties(text = "Tap")

        assertNotEquals(button1.hashCode(), button2.hashCode())
    }

    @Test
    fun `given ButtonProperties when calling toString then returns string representation`() {
        val button = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF"
        )

        val toString = button.toString()
        assertTrue(toString.contains("ButtonProperties"))
        assertTrue(toString.contains("text=Click"))
        assertTrue(toString.contains("textColorHex=#FFFFFF"))
    }

    @Test
    fun `given ButtonProperties with null actionProperties when checking field then returns null`() {
        val button = ButtonProperties(text = "Click")

        assertEquals(null, button.actionProperties)
    }

    @Test
    fun `given ButtonProperties with actionProperties when checking field then returns actionProperties`() {
        val actionProperties = mockk<ActionProperties>()
        val button = ButtonProperties(actionProperties = actionProperties)

        assertEquals(actionProperties, button.actionProperties)
    }

    @Test
    fun `given ButtonProperties when modifying var actionProperties then updates field`() {
        val button = ButtonProperties()
        val actionProperties = mockk<ActionProperties>()

        assertEquals(null, button.actionProperties)
        button.actionProperties = actionProperties
        assertEquals(actionProperties, button.actionProperties)
    }

    @Test
    fun `given ButtonProperties with all null optional fields when checking default values then returns false for boolean fields`() {
        val button = ButtonProperties(
            text = null,
            textColorHex = null,
            align = null,
            textAlign = null,
            textSize = null,
            textAllCaps = null,
            fillMaxSize = null,
            backgroundHex = null,
            actionProperties = null
        )

        assertEquals(null, button.text)
        assertEquals(null, button.textColorHex)
        assertEquals(null, button.align)
        assertEquals(null, button.textAlign)
        assertEquals(null, button.textSize)
        assertEquals(null, button.textAllCaps)
        assertEquals(null, button.fillMaxSize)
        assertEquals(null, button.backgroundHex)
        assertEquals(null, button.actionProperties)
    }

    @Test
    fun `given ButtonProperties with empty strings when constructing then creates instance with empty strings`() {
        val button = ButtonProperties(
            text = "",
            textColorHex = "",
            textSize = "",
            backgroundHex = ""
        )

        assertEquals("", button.text)
        assertEquals("", button.textColorHex)
        assertEquals("", button.textSize)
        assertEquals("", button.backgroundHex)
    }

    @Test
    fun `given ButtonProperties with boolean true values when constructing then creates instance with true values`() {
        val button = ButtonProperties(
            textAllCaps = true,
            fillMaxSize = true
        )

        assertEquals(true, button.textAllCaps)
        assertEquals(true, button.fillMaxSize)
    }

    @Test
    fun `given ButtonProperties with all CraftDAlign values when constructing then creates instances correctly`() {
        val alignValues = listOf(
            CraftDAlign.LEFT,
            CraftDAlign.CENTER,
            CraftDAlign.RIGHT
        )

        alignValues.forEach { align ->
            val button = ButtonProperties(align = align)
            assertEquals(align, button.align)
        }
    }

    @Test
    fun `given ButtonProperties with all CraftDAlign values for textAlign when constructing then creates instances correctly`() {
        val alignValues = listOf(
            CraftDAlign.LEFT,
            CraftDAlign.CENTER,
            CraftDAlign.RIGHT
        )

        alignValues.forEach { align ->
            val button = ButtonProperties(textAlign = align)
            assertEquals(align, button.textAlign)
        }
    }

    @Test
    fun `given two ButtonProperties instances when one has all fields and other has defaults then they are not equal`() {
        val actionProperties = mockk<ActionProperties>()
        val full = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#000000",
            actionProperties = actionProperties
        )
        val empty = ButtonProperties()

        assertNotEquals(full, empty)
    }

    @Test
    fun `given ButtonProperties when copying without changing any field then returns equal instance`() {
        val original = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF"
        )
        val copied = original.copy()

        assertEquals(original, copied)
        assertTrue(original === original)
        assertFalse(original === copied)
    }

    @Test
    fun `given ButtonProperties with special characters in text when constructing then creates instance with special characters`() {
        val button = ButtonProperties(text = "Click & Tap! @#$%")

        assertEquals("Click & Tap! @#$%", button.text)
    }

    @Test
    fun `given ButtonProperties with hex color codes when constructing then creates instance with hex codes`() {
        val button = ButtonProperties(
            textColorHex = "#FFFFFF",
            backgroundHex = "#000000FF"
        )

        assertEquals("#FFFFFF", button.textColorHex)
        assertEquals("#000000FF", button.backgroundHex)
    }

    @Test
    fun `given ButtonProperties with different text sizes when constructing then creates instances with different sizes`() {
        val sizes = listOf("12sp", "16sp", "20sp", "24sp", "32sp")

        sizes.forEach { size ->
            val button = ButtonProperties(textSize = size)
            assertEquals(size, button.textSize)
        }
    }

    @Test
    fun `given multiple ButtonProperties instances when comparing hashCode consistency then hashCode remains same across multiple calls`() {
        val button = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF"
        )

        val hash1 = button.hashCode()
        val hash2 = button.hashCode()
        val hash3 = button.hashCode()

        assertEquals(hash1, hash2)
        assertEquals(hash2, hash3)
    }

    @Test
    fun `given ButtonProperties when created as immutable then reference cannot be modified externally except for var actionProperties`() {
        val button = ButtonProperties(text = "Original")

        assertEquals("Original", button.text)
    }

    @Test
    fun `given ButtonProperties instances in a collection when checking equality then collection operations work correctly`() {
        val button1 = ButtonProperties(text = "Click")
        val button2 = ButtonProperties(text = "Click")
        val button3 = ButtonProperties(text = "Tap")

        val list = listOf(button1, button3)

        assertTrue(list.contains(button1))
        assertTrue(list.contains(button2))
        assertFalse(list.contains(ButtonProperties(text = "Submit")))
    }

    @Test
    fun `given ButtonProperties with null values when creating multiple copies then all copies maintain null values`() {
        val original = ButtonProperties(text = null, textColorHex = null)

        val copy1 = original.copy()
        val copy2 = original.copy()

        assertEquals(null, copy1.text)
        assertEquals(null, copy1.textColorHex)
        assertEquals(null, copy2.text)
        assertEquals(null, copy2.textColorHex)
        assertEquals(copy1, copy2)
    }
}