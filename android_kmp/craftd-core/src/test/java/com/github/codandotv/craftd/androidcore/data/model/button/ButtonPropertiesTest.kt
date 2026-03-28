package com.github.codandotv.craftd.androidcore.data.model.button

import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign

@RunWith(JUnit4::class)
class ButtonPropertiesTest {

    @Test
    fun `given all parameters when creating ButtonProperties then all fields are set correctly`() {
        val actionProps = mockk<ActionProperties>()
        val properties = ButtonProperties(
            text = "Click Me",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#FF0000",
            actionProperties = actionProps
        )

        assertEquals("Click Me", properties.text)
        assertEquals("#FFFFFF", properties.textColorHex)
        assertEquals(CraftDAlign.CENTER, properties.align)
        assertEquals(CraftDAlign.CENTER, properties.textAlign)
        assertEquals("16sp", properties.textSize)
        assertTrue(properties.textAllCaps == true)
        assertTrue(properties.fillMaxSize == true)
        assertEquals("#FF0000", properties.backgroundHex)
        assertEquals(actionProps, properties.actionProperties)
    }

    @Test
    fun `given default values when creating ButtonProperties then all fields have expected defaults`() {
        val properties = ButtonProperties()

        assertNull(properties.text)
        assertNull(properties.textColorHex)
        assertNull(properties.align)
        assertNull(properties.textAlign)
        assertNull(properties.textSize)
        assertFalse(properties.textAllCaps == true)
        assertFalse(properties.fillMaxSize == true)
        assertNull(properties.backgroundHex)
        assertNull(properties.actionProperties)
    }

    @Test
    fun `given only text when creating ButtonProperties then text is set and others are null`() {
        val properties = ButtonProperties(text = "Button Text")

        assertEquals("Button Text", properties.text)
        assertNull(properties.textColorHex)
        assertNull(properties.align)
    }

    @Test
    fun `given ButtonProperties with all fields when calling copy then returns new instance with changed fields`() {
        val original = ButtonProperties(
            text = "Original",
            textColorHex = "#000000",
            align = CraftDAlign.START,
            textSize = "14sp"
        )

        val copied = original.copy(
            text = "Modified",
            textColorHex = "#FFFFFF"
        )

        assertEquals("Modified", copied.text)
        assertEquals("#FFFFFF", copied.textColorHex)
        assertEquals(CraftDAlign.START, copied.align)
        assertEquals("14sp", copied.textSize)
        assertNotSame(original, copied)
    }

    @Test
    fun `given ButtonProperties with same values when calling equals then returns true`() {
        val props1 = ButtonProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER
        )
        val props2 = ButtonProperties(
            text = "Test",
            textColorHex = "#FF0000",
            align = CraftDAlign.CENTER
        )

        assertEquals(props1, props2)
    }

    @Test
    fun `given ButtonProperties with different values when calling equals then returns false`() {
        val props1 = ButtonProperties(text = "Test")
        val props2 = ButtonProperties(text = "Different")

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given ButtonProperties with same values when calling hashCode then returns same code`() {
        val props1 = ButtonProperties(
            text = "Test",
            textColorHex = "#FF0000"
        )
        val props2 = ButtonProperties(
            text = "Test",
            textColorHex = "#FF0000"
        )

        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given ButtonProperties with different values when calling hashCode then returns different code`() {
        val props1 = ButtonProperties(text = "Test1")
        val props2 = ButtonProperties(text = "Test2")

        assertNotEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given ButtonProperties when checking CraftDAlign enum then CENTER exists`() {
        val align = CraftDAlign.CENTER
        assertNotNull(align)
    }

    @Test
    fun `given ButtonProperties when checking CraftDAlign enum then START exists`() {
        val align = CraftDAlign.START
        assertNotNull(align)
    }

    @Test
    fun `given ButtonProperties when checking CraftDAlign enum then END exists`() {
        val align = CraftDAlign.END
        assertNotNull(align)
    }

    @Test
    fun `given boolean field textAllCaps when set to true then value is true`() {
        val properties = ButtonProperties(textAllCaps = true)
        assertTrue(properties.textAllCaps == true)
    }

    @Test
    fun `given boolean field textAllCaps when set to false then value is false`() {
        val properties = ButtonProperties(textAllCaps = false)
        assertFalse(properties.textAllCaps == true)
    }

    @Test
    fun `given boolean field fillMaxSize when set to true then value is true`() {
        val properties = ButtonProperties(fillMaxSize = true)
        assertTrue(properties.fillMaxSize == true)
    }

    @Test
    fun `given boolean field fillMaxSize when set to false then value is false`() {
        val properties = ButtonProperties(fillMaxSize = false)
        assertFalse(properties.fillMaxSize == true)
    }

    @Test
    fun `given multiple ButtonProperties instances when comparing equality with null fields then handles correctly`() {
        val props1 = ButtonProperties(
            text = null,
            textColorHex = null,
            align = null
        )
        val props2 = ButtonProperties(
            text = null,
            textColorHex = null,
            align = null
        )

        assertEquals(props1, props2)
    }

    @Test
    fun `given ButtonProperties with actionProperties when copying then actionProperties is preserved`() {
        val actionProps = mockk<ActionProperties>()
        val original = ButtonProperties(
            text = "Test",
            actionProperties = actionProps
        )

        val copied = original.copy(text = "Modified")

        assertEquals(actionProps, copied.actionProperties)
    }

    @Test
    fun `given ButtonProperties with actionProperties when setting new actionProperties then value is updated`() {
        val actionProps1 = mockk<ActionProperties>()
        val actionProps2 = mockk<ActionProperties>()
        val properties = ButtonProperties(actionProperties = actionProps1)

        properties.actionProperties = actionProps2

        assertEquals(actionProps2, properties.actionProperties)
    }

    @Test
    fun `given ButtonProperties when checking immutability annotation then class is marked Immutable`() {
        val properties = ButtonProperties()
        val immutableAnnotation = ButtonProperties::class.annotations
            .any { it.annotationClass.simpleName == "Immutable" }

        assertTrue(immutableAnnotation)
    }

    @Test
    fun `given ButtonProperties when checking stable annotation then class is marked Stable`() {
        val properties = ButtonProperties()
        val stableAnnotation = ButtonProperties::class.annotations
            .any { it.annotationClass.simpleName == "Stable" }

        assertTrue(stableAnnotation)
    }

    @Test
    fun `given ButtonProperties when checking serializable annotation then class is marked Serializable`() {
        val properties = ButtonProperties()
        val serializableAnnotation = ButtonProperties::class.annotations
            .any { it.annotationClass.simpleName == "Serializable" }

        assertTrue(serializableAnnotation)
    }

    @Test
    fun `given ButtonProperties with all null values when creating instance then all fields are null`() {
        val properties = ButtonProperties(
            text = null,
            textColorHex = null,
            align = null,
            textAlign = null,
            textSize = null,
            backgroundHex = null,
            actionProperties = null
        )

        assertNull(properties.text)
        assertNull(properties.textColorHex)
        assertNull(properties.align)
        assertNull(properties.textAlign)
        assertNull(properties.textSize)
        assertNull(properties.backgroundHex)
        assertNull(properties.actionProperties)
    }

    @Test
    fun `given ButtonProperties with partial fields when copying with all parameters then creates new instance correctly`() {
        val original = ButtonProperties(
            text = "Original",
            textColorHex = "#000000"
        )

        val copied = original.copy(
            text = "New Text",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.START,
            textSize = "18sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#0000FF"
        )

        assertEquals("New Text", copied.text)
        assertEquals("#FFFFFF", copied.textColorHex)
        assertEquals(CraftDAlign.CENTER, copied.align)
        assertEquals(CraftDAlign.START, copied.textAlign)
        assertEquals("18sp", copied.textSize)
        assertTrue(copied.textAllCaps == true)
        assertTrue(copied.fillMaxSize == true)
        assertEquals("#0000FF", copied.backgroundHex)
    }

    @Test
    fun `given two ButtonProperties with complex state when comparing then equality is based on all fields`() {
        val actionProps = mockk<ActionProperties>()
        val props1 = ButtonProperties(
            text = "Button",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#FF0000",
            actionProperties = actionProps
        )

        val props2 = ButtonProperties(
            text = "Button",
            textColorHex = "#FFFFFF",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.CENTER,
            textSize = "16sp",
            textAllCaps = true,
            fillMaxSize = true,
            backgroundHex = "#FF0000",
            actionProperties = actionProps
        )

        assertEquals(props1, props2)
        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given ButtonProperties with different text size when comparing then instances are not equal`() {
        val props1 = ButtonProperties(textSize = "14sp")
        val props2 = ButtonProperties(textSize = "16sp")

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given ButtonProperties with different alignment when comparing then instances are not equal`() {
        val props1 = ButtonProperties(align = CraftDAlign.START)
        val props2 = ButtonProperties(align = CraftDAlign.END)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given ButtonProperties when accessing properties multiple times then returns consistent values`() {
        val properties = ButtonProperties(
            text = "Test",
            textColorHex = "#FF0000",
            backgroundHex = "#00FF00"
        )

        assertEquals("Test", properties.text)
        assertEquals("Test", properties.text)
        assertEquals("#FF0000", properties.textColorHex)
        assertEquals("#FF0000", properties.textColorHex)
        assertEquals("#00FF00", properties.backgroundHex)
        assertEquals("#00FF00", properties.backgroundHex)
    }
}