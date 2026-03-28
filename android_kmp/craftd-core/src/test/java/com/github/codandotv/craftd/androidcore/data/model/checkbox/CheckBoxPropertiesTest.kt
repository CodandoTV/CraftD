package com.github.codandotv.craftd.androidcore.data.model.checkbox

import io.mockk.mockk
import kotlinx.serialization.json.JsonElement
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

@RunWith(JUnit4::class)
class CheckBoxPropertiesTest {

    @Test
    fun `given all parameters when creating CheckBoxProperties then returns instance with all fields set`() {
        val text = "Test CheckBox"
        val align = CraftDAlign.CENTER
        val textAlign = CraftDAlign.LEFT
        val enable = true
        val hasItRightText = true
        val actionProperties = mockk<ActionProperties>()
        val styleProperties = mockk<StyleProperties>()

        val checkBoxProperties = CheckBoxProperties(
            text = text,
            align = align,
            textAlign = textAlign,
            enable = enable,
            hasItRightText = hasItRightText,
            actionProperties = actionProperties,
            styleProperties = styleProperties
        )

        assertEquals(text, checkBoxProperties.text)
        assertEquals(align, checkBoxProperties.align)
        assertEquals(textAlign, checkBoxProperties.textAlign)
        assertEquals(enable, checkBoxProperties.enable)
        assertEquals(hasItRightText, checkBoxProperties.hasItRightText)
        assertEquals(actionProperties, checkBoxProperties.actionProperties)
        assertEquals(styleProperties, checkBoxProperties.styleProperties)
    }

    @Test
    fun `given default parameters when creating CheckBoxProperties then returns instance with default values`() {
        val checkBoxProperties = CheckBoxProperties()

        assertNull(checkBoxProperties.text)
        assertNull(checkBoxProperties.align)
        assertNull(checkBoxProperties.textAlign)
        assertEquals(false, checkBoxProperties.enable)
        assertEquals(false, checkBoxProperties.hasItRightText)
        assertNull(checkBoxProperties.actionProperties)
        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties when calling copy with modified text then returns new instance with updated text`() {
        val original = CheckBoxProperties(text = "Original", enable = true)
        val copied = original.copy(text = "Modified")

        assertEquals("Modified", copied.text)
        assertEquals(true, copied.enable)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given CheckBoxProperties when calling copy with all parameters then returns new instance with all fields updated`() {
        val original = CheckBoxProperties(text = "Original")
        val newActionProperties = mockk<ActionProperties>()
        val newStyleProperties = mockk<StyleProperties>()

        val copied = original.copy(
            text = "Modified",
            align = CraftDAlign.RIGHT,
            textAlign = CraftDAlign.CENTER,
            enable = true,
            hasItRightText = true,
            actionProperties = newActionProperties,
            styleProperties = newStyleProperties
        )

        assertEquals("Modified", copied.text)
        assertEquals(CraftDAlign.RIGHT, copied.align)
        assertEquals(CraftDAlign.CENTER, copied.textAlign)
        assertEquals(true, copied.enable)
        assertEquals(true, copied.hasItRightText)
        assertEquals(newActionProperties, copied.actionProperties)
        assertEquals(newStyleProperties, copied.styleProperties)
    }

    @Test
    fun `given two CheckBoxProperties with same values when comparing then returns true for equals`() {
        val first = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.CENTER,
            enable = true
        )
        val second = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.CENTER,
            enable = true
        )

        assertEquals(first, second)
    }

    @Test
    fun `given two CheckBoxProperties with different values when comparing then returns false for equals`() {
        val first = CheckBoxProperties(text = "Test1", enable = true)
        val second = CheckBoxProperties(text = "Test2", enable = false)

        assertNotEquals(first, second)
    }

    @Test
    fun `given two CheckBoxProperties with same values when getting hashCode then returns same hash`() {
        val first = CheckBoxProperties(text = "Test", enable = true)
        val second = CheckBoxProperties(text = "Test", enable = true)

        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given two CheckBoxProperties with different values when getting hashCode then may return different hash`() {
        val first = CheckBoxProperties(text = "Test1")
        val second = CheckBoxProperties(text = "Test2")

        assertNotEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given CheckBoxProperties when getting string representation then returns valid toString`() {
        val checkBoxProperties = CheckBoxProperties(text = "Test", enable = true)
        val toStringResult = checkBoxProperties.toString()

        assertTrue(toStringResult.contains("CheckBoxProperties"))
        assertTrue(toStringResult.contains("text=Test"))
        assertTrue(toStringResult.contains("enable=true"))
    }

    @Test
    fun `given null enable parameter when creating CheckBoxProperties then defaults to false`() {
        val checkBoxProperties = CheckBoxProperties(enable = null)

        assertEquals(false, checkBoxProperties.enable)
    }

    @Test
    fun `given null hasItRightText parameter when creating CheckBoxProperties then defaults to false`() {
        val checkBoxProperties = CheckBoxProperties(hasItRightText = null)

        assertEquals(false, checkBoxProperties.hasItRightText)
    }

    @Test
    fun `given partial parameters when creating CheckBoxProperties then unspecified fields have default values`() {
        val checkBoxProperties = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.CENTER
        )

        assertEquals("Test", checkBoxProperties.text)
        assertEquals(CraftDAlign.CENTER, checkBoxProperties.align)
        assertNull(checkBoxProperties.textAlign)
        assertEquals(false, checkBoxProperties.enable)
        assertEquals(false, checkBoxProperties.hasItRightText)
        assertNull(checkBoxProperties.actionProperties)
        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties with mutable actionProperties when modifying then reflects change`() {
        val checkBoxProperties = CheckBoxProperties()
        val newActionProperties = mockk<ActionProperties>()

        checkBoxProperties.actionProperties = newActionProperties

        assertEquals(newActionProperties, checkBoxProperties.actionProperties)
    }

    @Test
    fun `given CheckBoxProperties with mutable styleProperties when modifying then reflects change`() {
        val checkBoxProperties = CheckBoxProperties()
        val newStyleProperties = mockk<StyleProperties>()

        checkBoxProperties.styleProperties = newStyleProperties

        assertEquals(newStyleProperties, checkBoxProperties.styleProperties)
    }

    @Test
    fun `given two CheckBoxProperties with null actionProperties when comparing then returns true for equals`() {
        val first = CheckBoxProperties(text = "Test", actionProperties = null)
        val second = CheckBoxProperties(text = "Test", actionProperties = null)

        assertEquals(first, second)
    }

    @Test
    fun `given two CheckBoxProperties with different actionProperties when comparing then returns false for equals`() {
        val actionProperties1 = mockk<ActionProperties>()
        val actionProperties2 = mockk<ActionProperties>()

        val first = CheckBoxProperties(text = "Test", actionProperties = actionProperties1)
        val second = CheckBoxProperties(text = "Test", actionProperties = actionProperties2)

        assertNotEquals(first, second)
    }

    @Test
    fun `given CheckBoxProperties with all nullable fields as null when creating then handles gracefully`() {
        val checkBoxProperties = CheckBoxProperties(
            text = null,
            align = null,
            textAlign = null,
            enable = null,
            hasItRightText = null,
            actionProperties = null,
            styleProperties = null
        )

        assertNull(checkBoxProperties.text)
        assertNull(checkBoxProperties.align)
        assertNull(checkBoxProperties.textAlign)
        assertEquals(false, checkBoxProperties.enable)
        assertEquals(false, checkBoxProperties.hasItRightText)
        assertNull(checkBoxProperties.actionProperties)
        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties with all parameters populated when calling copy with no arguments then returns identical instance`() {
        val original = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.CENTER,
            textAlign = CraftDAlign.LEFT,
            enable = true,
            hasItRightText = true,
            actionProperties = mockk(),
            styleProperties = mockk()
        )
        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given CheckBoxProperties when multiple properties are set then all are preserved`() {
        val actionProps = mockk<ActionProperties>()
        val styleProps = mockk<StyleProperties>()
        val checkBoxProperties = CheckBoxProperties(
            text = "Checkbox Label",
            align = CraftDAlign.START,
            textAlign = CraftDAlign.END,
            enable = true,
            hasItRightText = false,
            actionProperties = actionProps,
            styleProperties = styleProps
        )

        assertEquals("Checkbox Label", checkBoxProperties.text)
        assertEquals(CraftDAlign.START, checkBoxProperties.align)
        assertEquals(CraftDAlign.END, checkBoxProperties.textAlign)
        assertTrue(checkBoxProperties.enable == true)
        assertFalse(checkBoxProperties.hasItRightText == true)
        assertEquals(actionProps, checkBoxProperties.actionProperties)
        assertEquals(styleProps, checkBoxProperties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties with empty string text when creating then preserves empty string`() {
        val checkBoxProperties = CheckBoxProperties(text = "")

        assertEquals("", checkBoxProperties.text)
    }

    @Test
    fun `given CheckBoxProperties with boolean false values when creating then preserves false explicitly`() {
        val checkBoxProperties = CheckBoxProperties(
            enable = false,
            hasItRightText = false
        )

        assertEquals(false, checkBoxProperties.enable)
        assertEquals(false, checkBoxProperties.hasItRightText)
    }

    @Test
    fun `given CheckBoxProperties with boolean true values when creating then preserves true explicitly`() {
        val checkBoxProperties = CheckBoxProperties(
            enable = true,
            hasItRightText = true
        )

        assertEquals(true, checkBoxProperties.enable)
        assertEquals(true, checkBoxProperties.hasItRightText)
    }

    @Test
    fun `given two distinct CheckBoxProperties instances with different align values when comparing then returns false`() {
        val first = CheckBoxProperties(align = CraftDAlign.CENTER)
        val second = CheckBoxProperties(align = CraftDAlign.LEFT)

        assertNotEquals(first, second)
    }

    @Test
    fun `given two distinct CheckBoxProperties instances with different textAlign values when comparing then returns false`() {
        val first = CheckBoxProperties(textAlign = CraftDAlign.CENTER)
        val second = CheckBoxProperties(textAlign = CraftDAlign.RIGHT)

        assertNotEquals(first, second)
    }

    @Test
    fun `given CheckBoxProperties when copy is called multiple times then creates independent instances`() {
        val original = CheckBoxProperties(text = "Original")
        val copy1 = original.copy(text = "Copy1")
        val copy2 = original.copy(text = "Copy2")

        assertEquals("Original", original.text)
        assertEquals("Copy1", copy1.text)
        assertEquals("Copy2", copy2.text)
        assertNotEquals(copy1, copy2)
    }

    @Test
    fun `given CheckBoxProperties with actionProperties when copy without changing actionProperties then preserves reference`() {
        val actionProps = mockk<ActionProperties>()
        val original = CheckBoxProperties(actionProperties = actionProps, text = "Original")
        val copied = original.copy(text = "Modified")

        assertEquals(actionProps, copied.actionProperties)
        assertEquals("Modified", copied.text)
    }

    @Test
    fun `given CheckBoxProperties with styleProperties when copy without changing styleProperties then preserves reference`() {
        val styleProps = mockk<StyleProperties>()
        val original = CheckBoxProperties(styleProperties = styleProps, text = "Original")
        val copied = original.copy(text = "Modified")

        assertEquals(styleProps, copied.styleProperties)
        assertEquals("Modified", copied.text)
    }
}