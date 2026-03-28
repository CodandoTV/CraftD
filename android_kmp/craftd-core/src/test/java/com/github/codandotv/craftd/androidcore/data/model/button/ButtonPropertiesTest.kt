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
    fun `given all parameters when creating ButtonProperties then all fields are set correctly`() {
        val actionProperties = mockk<ActionProperties>()
        val buttonProperties = ButtonProperties(
            text = "Click Me",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.START,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#000000",
            actionProperties = actionProperties
        )

        assertEquals("Click Me", buttonProperties.text)
        assertEquals("#FFFFFF", buttonProperties.textColorHex)
        assertEquals(CraftDAlign.CENTER, buttonProperties.align)
        assertEquals(CraftDAlign.START, buttonProperties.textAlign)
        assertEquals("16sp", buttonProperties.textSize)
        assertTrue(buttonProperties.textAllCaps!!)
        assertTrue(buttonProperties.fillMaxSize!!)
        assertEquals("#000000", buttonProperties.backgroundHex)
        assertEquals(actionProperties, buttonProperties.actionProperties)
    }

    @Test
    fun `given no parameters when creating ButtonProperties then default values are applied`() {
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
    fun `given partial parameters when creating ButtonProperties then specified fields are set and others use defaults`() {
        val buttonProperties = ButtonProperties(
            text = "Submit",
            backgroundHex = "#FF5722"
        )

        assertEquals("Submit", buttonProperties.text)
        assertNull(buttonProperties.textColorHex)
        assertNull(buttonProperties.align)
        assertNull(buttonProperties.textAlign)
        assertNull(buttonProperties.textSize)
        assertFalse(buttonProperties.textAllCaps!!)
        assertFalse(buttonProperties.fillMaxSize!!)
        assertEquals("#FF5722", buttonProperties.backgroundHex)
        assertNull(buttonProperties.actionProperties)
    }

    @Test
    fun `given ButtonProperties with copy when modifying fields then original remains unchanged`() {
        val original = ButtonProperties(
            text = "Original",
            backgroundHex = "#000000"
        )
        val copied = original.copy(text = "Modified")

        assertEquals("Original", original.text)
        assertEquals("Modified", copied.text)
        assertEquals("#000000", original.backgroundHex)
        assertEquals("#000000", copied.backgroundHex)
    }

    @Test
    fun `given two ButtonProperties with same values when comparing equals then returns true`() {
        val actionProperties = mockk<ActionProperties>()
        val button1 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = false,
            backgroundHex = "#000000",
            actionProperties = actionProperties
        )
        val button2 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = false,
            backgroundHex = "#000000",
            actionProperties = actionProperties
        )

        assertEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with different values when comparing equals then returns false`() {
        val button1 = ButtonProperties(text = "Click 1", backgroundHex = "#000000")
        val button2 = ButtonProperties(text = "Click 2", backgroundHex = "#FFFFFF")

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given two ButtonProperties with same values when comparing hashCode then returns same hash`() {
        val actionProperties = mockk<ActionProperties>()
        val button1 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = false,
            backgroundHex = "#000000",
            actionProperties = actionProperties
        )
        val button2 = ButtonProperties(
            text = "Click",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = false,
            backgroundHex = "#000000",
            actionProperties = actionProperties
        )

        assertEquals(button1.hashCode(), button2.hashCode())
    }

    @Test
    fun `given two ButtonProperties with different values when comparing hashCode then hash may differ`() {
        val button1 = ButtonProperties(text = "Click 1", backgroundHex = "#000000")
        val button2 = ButtonProperties(text = "Click 2", backgroundHex = "#FFFFFF")

        assertNotEquals(button1.hashCode(), button2.hashCode())
    }

    @Test
    fun `given ButtonProperties when calling copy with all new values then creates new instance with updated values`() {
        val original = ButtonProperties(
            text = "Original",
            textColorHex = "#000000",
            align = CraftDAlign.START,
            textAlign = CraftDAlign.START,
            textSize = "12sp",
            textAllCaps = false,
            fillMaxSize = false,
            backgroundHex = "#FFFFFF"
        )
        val actionProperties = mockk<ActionProperties>()
        val copied = original.copy(
            text = "Updated",
            textColorHex = "#FF0000",
            align = CraftDAlign.END,
            textAlign = CraftDAlign.END,
            textSize = "20sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#00FF00",
            actionProperties = actionProperties
        )

        assertEquals("Updated", copied.text)
        assertEquals("#FF0000", copied.textColorHex)
        assertEquals(CraftDAlign.END, copied.align)
        assertEquals(CraftDAlign.END, copied.textAlign)
        assertEquals("20sp", copied.textSize)
        assertTrue(copied.textAllCaps!!)
        assertTrue(copied.fillMaxSize!!)
        assertEquals("#00FF00", copied.backgroundHex)
        assertEquals(actionProperties, copied.actionProperties)
    }

    @Test
    fun `given ButtonProperties when calling toString then returns valid string representation`() {
        val buttonProperties = ButtonProperties(
            text = "Button",
            backgroundHex = "#000000"
        )

        val stringRepresentation = buttonProperties.toString()
        assertTrue(stringRepresentation.contains("ButtonProperties"))
        assertTrue(stringRepresentation.contains("text=Button"))
    }

    @Test
    fun `given null text when creating ButtonProperties then text field is null`() {
        val buttonProperties = ButtonProperties(text = null)

        assertNull(buttonProperties.text)
    }

    @Test
    fun `given empty string text when creating ButtonProperties then text field is empty string`() {
        val buttonProperties = ButtonProperties(text = "")

        assertEquals("", buttonProperties.text)
    }

    @Test
    fun `given null textColorHex when creating ButtonProperties then textColorHex field is null`() {
        val buttonProperties = ButtonProperties(textColorHex = null)

        assertNull(buttonProperties.textColorHex)
    }

    @Test
    fun `given null align when creating ButtonProperties then align field is null`() {
        val buttonProperties = ButtonProperties(align = null)

        assertNull(buttonProperties.align)
    }

    @Test
    fun `given CraftDAlign CENTER when creating ButtonProperties then align is CENTER`() {
        val buttonProperties = ButtonProperties(align = CraftDAlign.CENTER)

        assertEquals(CraftDAlign.CENTER, buttonProperties.align)
    }

    @Test
    fun `given CraftDAlign START when creating ButtonProperties then align is START`() {
        val buttonProperties = ButtonProperties(align = CraftDAlign.START)

        assertEquals(CraftDAlign.START, buttonProperties.align)
    }

    @Test
    fun `given CraftDAlign END when creating ButtonProperties then align is END`() {
        val buttonProperties = ButtonProperties(align = CraftDAlign.END)

        assertEquals(CraftDAlign.END, buttonProperties.align)
    }

    @Test
    fun `given null textAlign when creating ButtonProperties then textAlign field is null`() {
        val buttonProperties = ButtonProperties(textAlign = null)

        assertNull(buttonProperties.textAlign)
    }

    @Test
    fun `given null textSize when creating ButtonProperties then textSize field is null`() {
        val buttonProperties = ButtonProperties(textSize = null)

        assertNull(buttonProperties.textSize)
    }

    @Test
    fun `given textAllCaps true when creating ButtonProperties then textAllCaps is true`() {
        val buttonProperties = ButtonProperties(textAllCaps = true)

        assertTrue(buttonProperties.textAllCaps!!)
    }

    @Test
    fun `given textAllCaps false when creating ButtonProperties then textAllCaps is false`() {
        val buttonProperties = ButtonProperties(textAllCaps = false)

        assertFalse(buttonProperties.textAllCaps!!)
    }

    @Test
    fun `given fillMaxSize true when creating ButtonProperties then fillMaxSize is true`() {
        val buttonProperties = ButtonProperties(fillMaxSize = true)

        assertTrue(buttonProperties.fillMaxSize!!)
    }

    @Test
    fun `given fillMaxSize false when creating ButtonProperties then fillMaxSize is false`() {
        val buttonProperties = ButtonProperties(fillMaxSize = false)

        assertFalse(buttonProperties.fillMaxSize!!)
    }

    @Test
    fun `given null backgroundHex when creating ButtonProperties then backgroundHex field is null`() {
        val buttonProperties = ButtonProperties(backgroundHex = null)

        assertNull(buttonProperties.backgroundHex)
    }

    @Test
    fun `given null actionProperties when creating ButtonProperties then actionProperties field is null`() {
        val buttonProperties = ButtonProperties(actionProperties = null)

        assertNull(buttonProperties.actionProperties)
    }

    @Test
    fun `given ActionProperties when creating ButtonProperties then actionProperties is set`() {
        val actionProperties = mockk<ActionProperties>()
        val buttonProperties = ButtonProperties(actionProperties = actionProperties)

        assertEquals(actionProperties, buttonProperties.actionProperties)
    }

    @Test
    fun `given ButtonProperties with mutable actionProperties when modifying actionProperties then field is updated`() {
        val buttonProperties = ButtonProperties()
        val actionProperties = mockk<ActionProperties>()

        buttonProperties.actionProperties = actionProperties

        assertEquals(actionProperties, buttonProperties.actionProperties)
    }

    @Test
    fun `given multiple ButtonProperties with different actionProperties when comparing equals then returns false`() {
        val actionProperties1 = mockk<ActionProperties>()
        val actionProperties2 = mockk<ActionProperties>()
        val button1 = ButtonProperties(actionProperties = actionProperties1)
        val button2 = ButtonProperties(actionProperties = actionProperties2)

        assertNotEquals(button1, button2)
    }

    @Test
    fun `given ButtonProperties when accessing immutable annotation then class is immutable`() {
        val buttonProperties = ButtonProperties(text = "Test")

        assertTrue(buttonProperties::class.annotations.any { it.annotationClass.simpleName == "Immutable" })
    }

    @Test
    fun `given ButtonProperties when accessing stable annotation then class is stable`() {
        val buttonProperties = ButtonProperties(text = "Test")

        assertTrue(buttonProperties::class.annotations.any { it.annotationClass.simpleName == "Stable" })
    }

    @Test
    fun `given ButtonProperties when accessing serializable annotation then class is serializable`() {
        val buttonProperties = ButtonProperties(text = "Test")

        assertTrue(buttonProperties::class.annotations.any { it.annotationClass.simpleName == "Serializable" })
    }

    @Test
    fun `given all enum values for CraftDAlign when accessing then all constants exist`() {
        val center = enumValueOf<CraftDAlign>("CENTER")
        val start = enumValueOf<CraftDAlign>("START")
        val end = enumValueOf<CraftDAlign>("END")

        assertEquals(CraftDAlign.CENTER, center)
        assertEquals(CraftDAlign.START, start)
        assertEquals(CraftDAlign.END, end)
    }

    @Test
    fun `given ButtonProperties with complex text when storing and retrieving then preserves special characters`() {
        val complexText = "Click Me! @#\$%^&*()"
        val buttonProperties = ButtonProperties(text = complexText)

        assertEquals(complexText, buttonProperties.text)
    }

    @Test
    fun `given ButtonProperties with unicode text when storing and retrieving then preserves unicode characters`() {
        val unicodeText = "你好世界 🌍 مرحبا"
        val buttonProperties = ButtonProperties(text = unicodeText)

        assertEquals(unicodeText, buttonProperties.text)
    }

    @Test
    fun `given ButtonProperties when calling copy without parameters then creates equal instance`() {
        val original = ButtonProperties(
            text = "Test",
            backgroundHex = "#000000",
            textAllCaps = true
        )
        val copied = original.copy()

        assertEquals(original, copied)
        assertNotSame(original, copied)
    }

    @Test
    fun `given two ButtonProperties with null actionProperties when comparing then returns true`() {
        val button1 = ButtonProperties(text = "Test", actionProperties = null)
        val button2 = ButtonProperties(text = "Test", actionProperties = null)

        assertEquals(button1, button2)
    }

    @Test
    fun `given ButtonProperties with all null optional fields when creating then all nulls are preserved`() {
        val buttonProperties = ButtonProperties(
            text = null,
            textColorHex = null,
            align = null,
            textAlign = null,
            textSize = null,
            backgroundHex = null,
            actionProperties = null
        )

        assertNull(buttonProperties.text)
        assertNull(buttonProperties.textColorHex)
        assertNull(buttonProperties.align)
        assertNull(buttonProperties.textAlign)
        assertNull(buttonProperties.textSize)
        assertNull(buttonProperties.backgroundHex)
        assertNull(buttonProperties.actionProperties)
    }

    private fun assertNotSame(obj1: Any, obj2: Any) {
        assertTrue(obj1 !== obj2)
    }
}