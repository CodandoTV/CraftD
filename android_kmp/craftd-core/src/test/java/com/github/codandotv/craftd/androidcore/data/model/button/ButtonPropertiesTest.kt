package com.github.codandotv.craftd.androidcore.data.model.button

import io.mockk.mockk
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

@RunWith(JUnit4::class)
class ButtonPropertiesTest {

    @Test
    fun `given default parameters when creating ButtonProperties then all fields have expected default values`() {
        val buttonProperties = ButtonProperties()

        assertNull(buttonProperties.text)
        assertNull(buttonProperties.textColorHex)
        assertNull(buttonProperties.align)
        assertNull(buttonProperties.textAlign)
        assertNull(buttonProperties.textSize)
        assertFalse(buttonProperties.textAllCaps!!)
        assertFalse(buttonProperties.fillMaxSize!!)
        assertNull(buttonProperties.backgroundHex)
        assertNull(buttonProperties.actionProperties)
    }

    @Test
    fun `given all parameters when creating ButtonProperties then all fields are set correctly`() {
        val action = mockk<ActionProperties>()
        val buttonProperties = ButtonProperties(
            text = "Click Me",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#000000",
            actionProperties = action
        )

        assertEquals("Click Me", buttonProperties.text)
        assertEquals("#FFFFFF", buttonProperties.textColorHex)
        assertEquals(CraftDAlign.CENTER, buttonProperties.align)
        assertEquals(CraftDAlign.CENTER, buttonProperties.textAlign)
        assertEquals("16sp", buttonProperties.textSize)
        assertTrue(buttonProperties.textAllCaps!!)
        assertTrue(buttonProperties.fillMaxSize!!)
        assertEquals("#000000", buttonProperties.backgroundHex)
        assertEquals(action, buttonProperties.actionProperties)
    }

    @Test
    fun `given partial parameters when creating ButtonProperties then specified fields are set and others use defaults`() {
        val buttonProperties = ButtonProperties(
            text = "Submit",
            textColorHex = "#FF0000",
            textSize = "14sp"
        )

        assertEquals("Submit", buttonProperties.text)
        assertEquals("#FF0000", buttonProperties.textColorHex)
        assertNull(buttonProperties.align)
        assertNull(buttonProperties.textAlign)
        assertEquals("14sp", buttonProperties.textSize)
        assertFalse(buttonProperties.textAllCaps!!)
        assertFalse(buttonProperties.fillMaxSize!!)
        assertNull(buttonProperties.backgroundHex)
        assertNull(buttonProperties.actionProperties)
    }

    @Test
    fun `given ButtonProperties when calling copy with new text then returns new instance with updated text`() {
        val original = ButtonProperties(text = "Original", textColorHex = "#FFFFFF")
        val copied = original.copy(text = "Updated")

        assertEquals("Updated", copied.text)
        assertEquals("#FFFFFF", copied.textColorHex)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given ButtonProperties when calling copy with all parameters then returns new instance with all fields updated`() {
        val action = mockk<ActionProperties>()
        val original = ButtonProperties(text = "Original")
        val copied = original.copy(
            text = "Updated",
            textColorHex = "#000000",
            align = CraftDAlign.START,
            textAlign = CraftDAlign.END,
            textSize = "18sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#CCCCCC",
            actionProperties = action
        )

        assertEquals("Updated", copied.text)
        assertEquals("#000000", copied.textColorHex)
        assertEquals(CraftDAlign.START, copied.align)
        assertEquals(CraftDAlign.END, copied.textAlign)
        assertEquals("18sp", copied.textSize)
        assertTrue(copied.textAllCaps!!)
        assertTrue(copied.fillMaxSize!!)
        assertEquals("#CCCCCC", copied.backgroundHex)
        assertEquals(action, copied.actionProperties)
    }

    @Test
    fun `given same ButtonProperties instances when comparing then equals returns true`() {
        val button1 = ButtonProperties(
            text = "Same",
            textColorHex = "#FFFFFF",
            textSize = "16sp"
        )
        val button2 = ButtonProperties(
            text = "Same",
            textColorHex = "#FFFFFF",
            textSize = "16sp"
        )

        assertEquals(button1, button2)
        assertEquals(button1.hashCode(), button2.hashCode())
    }

    @Test
    fun `given different ButtonProperties instances when comparing then equals returns false`() {
        val button1 = ButtonProperties(text = "Button1")
        val button2 = ButtonProperties(text = "Button2")

        assertNotEquals(button1, button2)
        assertNotEquals(button1.hashCode(), button2.hashCode())
    }

    @Test
    fun `given ButtonProperties with different text when comparing then equals returns false`() {
        val button1 = ButtonProperties(
            text = "Text1",
            textColorHex = "#FFFFFF"
        )
        val button2 = ButtonProperties(
            text = "Text2",
            textColorHex = "#FFFFFF"
        )

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given ButtonProperties with different colors when comparing then equals returns false`() {
        val button1 = ButtonProperties(text = "Same", textColorHex = "#FFFFFF")
        val button2 = ButtonProperties(text = "Same", textColorHex = "#000000")

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given ButtonProperties with different booleans when comparing then equals returns false`() {
        val button1 = ButtonProperties(text = "Same", textAllCaps = true)
        val button2 = ButtonProperties(text = "Same", textAllCaps = false)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given ButtonProperties with different ActionProperties when comparing then equals returns false`() {
        val action1 = mockk<ActionProperties>()
        val action2 = mockk<ActionProperties>()
        val button1 = ButtonProperties(text = "Same", actionProperties = action1)
        val button2 = ButtonProperties(text = "Same", actionProperties = action2)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given ButtonProperties when comparing with self then equals returns true`() {
        val button = ButtonProperties(text = "Self")
        assertEquals(button, button)
    }

    @Test
    fun `given ButtonProperties when comparing with null then equals returns false`() {
        val button = ButtonProperties(text = "Button")
        assertNotEquals(button, null)
    }

    @Test
    fun `given ButtonProperties with all null fields when comparing then equals based on boolean defaults`() {
        val button1 = ButtonProperties(
            text = null,
            textColorHex = null,
            textAllCaps = false,
            fillMaxSize = false
        )
        val button2 = ButtonProperties()

        assertEquals(button1, button2)
    }

    @Test
    fun `given ButtonProperties when calling hashCode multiple times then returns same value`() {
        val button = ButtonProperties(text = "Consistent")
        val hash1 = button.hashCode()
        val hash2 = button.hashCode()

        assertEquals(hash1, hash2)
    }

    @Test
    fun `given CraftDAlign enum then START constant exists`() {
        val align = enumValueOf<CraftDAlign>("START")
        assertEquals(CraftDAlign.START, align)
    }

    @Test
    fun `given CraftDAlign enum then END constant exists`() {
        val align = enumValueOf<CraftDAlign>("END")
        assertEquals(CraftDAlign.END, align)
    }

    @Test
    fun `given CraftDAlign enum then CENTER constant exists`() {
        val align = enumValueOf<CraftDAlign>("CENTER")
        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given ButtonProperties with complex ActionProperties when creating then structure is maintained`() {
        val complexAction = mockk<ActionProperties>(relaxed = true)
        val button = ButtonProperties(
            text = "Complex",
            textColorHex = "#123456",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.START,
            textSize = "20sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#ABCDEF",
            actionProperties = complexAction
        )

        assertEquals("Complex", button.text)
        assertEquals("#123456", button.textColorHex)
        assertEquals(CraftDAlign.CENTER, button.align)
        assertEquals(CraftDAlign.START, button.textAlign)
        assertEquals("20sp", button.textSize)
        assertTrue(button.textAllCaps!!)
        assertTrue(button.fillMaxSize!!)
        assertEquals("#ABCDEF", button.backgroundHex)
        assertEquals(complexAction, button.actionProperties)
    }

    @Test
    fun `given ButtonProperties when modifying actionProperties then changes are reflected`() {
        val button = ButtonProperties(text = "Mutable")
        val newAction = mockk<ActionProperties>()
        button.actionProperties = newAction

        assertEquals(newAction, button.actionProperties)
    }

    @Test
    fun `given ButtonProperties with empty strings when creating then fields are set`() {
        val button = ButtonProperties(
            text = "",
            textColorHex = "",
            backgroundHex = ""
        )

        assertEquals("", button.text)
        assertEquals("", button.textColorHex)
        assertEquals("", button.backgroundHex)
    }

    @Test
    fun `given ButtonProperties when copy is called without parameters then returns equal instance`() {
        val original = ButtonProperties(
            text = "Original",
            textColorHex = "#FFFFFF",
            textSize = "16sp"
        )
        val copied = original.copy()

        assertEquals(original, copied)
        assertEquals(original.hashCode(), copied.hashCode())
    }

    @Test
    fun `given multiple ButtonProperties with same data when comparing then all are equal`() {
        val buttons = listOf(
            ButtonProperties(text = "Same", textColorHex = "#FFFFFF"),
            ButtonProperties(text = "Same", textColorHex = "#FFFFFF"),
            ButtonProperties(text = "Same", textColorHex = "#FFFFFF")
        )

        buttons.forEach { button ->
            assertEquals(buttons[0], button)
        }
    }

    @Test
    fun `given ButtonProperties with all boolean flags true when creating then flags are correctly set`() {
        val button = ButtonProperties(
            text = "Flags",
            textAllCaps = true,
            fillMaxSize = true
        )

        assertTrue(button.textAllCaps!!)
        assertTrue(button.fillMaxSize!!)
    }

    @Test
    fun `given ButtonProperties with all boolean flags false when creating then flags are correctly set`() {
        val button = ButtonProperties(
            text = "Flags",
            textAllCaps = false,
            fillMaxSize = false
        )

        assertFalse(button.textAllCaps!!)
        assertFalse(button.fillMaxSize!!)
    }

    @Test
    fun `given ButtonProperties instances when comparing hashCodes of equal objects then codes are identical`() {
        val button1 = ButtonProperties(
            text = "Test",
            textColorHex = "#AABBCC",
            align = CraftDAlign.CENTER
        )
        val button2 = ButtonProperties(
            text = "Test",
            textColorHex = "#AABBCC",
            align = CraftDAlign.CENTER
        )

        assertEquals(button1.hashCode(), button2.hashCode())
    }

    @Test
    fun `given ButtonProperties with special characters in text when creating then text is preserved`() {
        val specialText = "Click! @#\$%^&*()"
        val button = ButtonProperties(text = specialText)

        assertEquals(specialText, button.text)
    }

    @Test
    fun `given ButtonProperties with unicode text when creating then text is preserved`() {
        val unicodeText = "クリック 点击 Нажмите"
        val button = ButtonProperties(text = unicodeText)

        assertEquals(unicodeText, button.text)
    }

    @Test
    fun `given ButtonProperties with long hex color when creating then hex is preserved`() {
        val longHex = "#AABBCCDDEE"
        val button = ButtonProperties(textColorHex = longHex)

        assertEquals(longHex, button.textColorHex)
    }

    @Test
    fun `given ButtonProperties with various text sizes when creating then text sizes are preserved`() {
        val sizes = listOf("10sp", "14dp", "18em", "2.5rem")
        sizes.forEach { size ->
            val button = ButtonProperties(textSize = size)
            assertEquals(size, button.textSize)
        }
    }
}