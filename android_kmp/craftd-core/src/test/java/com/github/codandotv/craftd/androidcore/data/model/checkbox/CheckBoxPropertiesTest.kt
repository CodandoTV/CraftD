package com.github.codandotv.craftd.androidcore.data.model.checkbox

import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CheckBoxPropertiesTest {

    @Test
    fun `given all parameters when creating CheckBoxProperties then all fields are set correctly`() {
        val actionProps = mockk<ActionProperties>()
        val styleProps = mockk<StyleProperties>()

        val checkBoxProperties = CheckBoxProperties(
            text = "Test Label",
            align = CraftDAlign.LEFT,
            textAlign = CraftDAlign.CENTER,
            enable = true,
            hasItRightText = true,
            actionProperties = actionProps,
            styleProperties = styleProps
        )

        assertEquals("Test Label", checkBoxProperties.text)
        assertEquals(CraftDAlign.LEFT, checkBoxProperties.align)
        assertEquals(CraftDAlign.CENTER, checkBoxProperties.textAlign)
        assertTrue(checkBoxProperties.enable!!)
        assertTrue(checkBoxProperties.hasItRightText!!)
        assertEquals(actionProps, checkBoxProperties.actionProperties)
        assertEquals(styleProps, checkBoxProperties.styleProperties)
    }

    @Test
    fun `given default parameters when creating CheckBoxProperties then default values are applied`() {
        val checkBoxProperties = CheckBoxProperties()

        assertNull(checkBoxProperties.text)
        assertNull(checkBoxProperties.align)
        assertNull(checkBoxProperties.textAlign)
        assertFalse(checkBoxProperties.enable!!)
        assertFalse(checkBoxProperties.hasItRightText!!)
        assertNull(checkBoxProperties.actionProperties)
        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given partial parameters when creating CheckBoxProperties then specified fields are set and others use defaults`() {
        val checkBoxProperties = CheckBoxProperties(
            text = "Label",
            enable = true
        )

        assertEquals("Label", checkBoxProperties.text)
        assertNull(checkBoxProperties.align)
        assertNull(checkBoxProperties.textAlign)
        assertTrue(checkBoxProperties.enable!!)
        assertFalse(checkBoxProperties.hasItRightText!!)
        assertNull(checkBoxProperties.actionProperties)
        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties instance when calling copy with new values then new instance has updated fields`() {
        val original = CheckBoxProperties(
            text = "Original",
            align = CraftDAlign.LEFT,
            enable = true
        )
        val actionProps = mockk<ActionProperties>()

        val copied = original.copy(
            text = "Modified",
            actionProperties = actionProps
        )

        assertEquals("Modified", copied.text)
        assertEquals(CraftDAlign.LEFT, copied.align)
        assertTrue(copied.enable!!)
        assertEquals(actionProps, copied.actionProperties)
        assertEquals("Original", original.text)
    }

    @Test
    fun `given two CheckBoxProperties with same values when comparing then equals returns true`() {
        val props1 = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.LEFT,
            enable = true
        )
        val props2 = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.LEFT,
            enable = true
        )

        assertEquals(props1, props2)
    }

    @Test
    fun `given two CheckBoxProperties with different values when comparing then equals returns false`() {
        val props1 = CheckBoxProperties(text = "Test1", enable = true)
        val props2 = CheckBoxProperties(text = "Test2", enable = false)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two CheckBoxProperties with same values when calling hashCode then hash codes are equal`() {
        val props1 = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.CENTER,
            enable = true
        )
        val props2 = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.CENTER,
            enable = true
        )

        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given two CheckBoxProperties with different values when calling hashCode then hash codes may differ`() {
        val props1 = CheckBoxProperties(text = "Test1", enable = true)
        val props2 = CheckBoxProperties(text = "Test2", enable = false)

        assertNotEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given null text when creating CheckBoxProperties then text is null`() {
        val checkBoxProperties = CheckBoxProperties(text = null)

        assertNull(checkBoxProperties.text)
    }

    @Test
    fun `given null align when creating CheckBoxProperties then align is null`() {
        val checkBoxProperties = CheckBoxProperties(align = null)

        assertNull(checkBoxProperties.align)
    }

    @Test
    fun `given null actionProperties when creating CheckBoxProperties then actionProperties is null`() {
        val checkBoxProperties = CheckBoxProperties(actionProperties = null)

        assertNull(checkBoxProperties.actionProperties)
    }

    @Test
    fun `given null styleProperties when creating CheckBoxProperties then styleProperties is null`() {
        val checkBoxProperties = CheckBoxProperties(styleProperties = null)

        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties with all nullable fields as null when creating then instance is valid`() {
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
        assertNull(checkBoxProperties.enable)
        assertNull(checkBoxProperties.hasItRightText)
        assertNull(checkBoxProperties.actionProperties)
        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given empty string text when creating CheckBoxProperties then text is empty string`() {
        val checkBoxProperties = CheckBoxProperties(text = "")

        assertEquals("", checkBoxProperties.text)
    }

    @Test
    fun `given false enable when creating CheckBoxProperties then enable is false`() {
        val checkBoxProperties = CheckBoxProperties(enable = false)

        assertFalse(checkBoxProperties.enable!!)
    }

    @Test
    fun `given true hasItRightText when creating CheckBoxProperties then hasItRightText is true`() {
        val checkBoxProperties = CheckBoxProperties(hasItRightText = true)

        assertTrue(checkBoxProperties.hasItRightText!!)
    }

    @Test
    fun `given multiple CraftDAlign values when creating CheckBoxProperties then align and textAlign are set correctly`() {
        val alignments = listOf(
            CraftDAlign.LEFT,
            CraftDAlign.CENTER,
            CraftDAlign.RIGHT
        )

        alignments.forEach { alignment ->
            val checkBoxProperties = CheckBoxProperties(
                align = alignment,
                textAlign = alignment
            )

            assertEquals(alignment, checkBoxProperties.align)
            assertEquals(alignment, checkBoxProperties.textAlign)
        }
    }

    @Test
    fun `given two CheckBoxProperties instances when one has actionProperties and other does not then equals returns false`() {
        val actionProps = mockk<ActionProperties>()
        val props1 = CheckBoxProperties(actionProperties = actionProps)
        val props2 = CheckBoxProperties(actionProperties = null)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two CheckBoxProperties instances when one has styleProperties and other does not then equals returns false`() {
        val styleProps = mockk<StyleProperties>()
        val props1 = CheckBoxProperties(styleProperties = styleProps)
        val props2 = CheckBoxProperties(styleProperties = null)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given CheckBoxProperties with actionProperties when copying with new actionProperties then new instance has updated actionProperties`() {
        val originalAction = mockk<ActionProperties>()
        val newAction = mockk<ActionProperties>()
        val original = CheckBoxProperties(actionProperties = originalAction)

        val copied = original.copy(actionProperties = newAction)

        assertEquals(newAction, copied.actionProperties)
        assertEquals(originalAction, original.actionProperties)
    }

    @Test
    fun `given CheckBoxProperties with styleProperties when copying with new styleProperties then new instance has updated styleProperties`() {
        val originalStyle = mockk<StyleProperties>()
        val newStyle = mockk<StyleProperties>()
        val original = CheckBoxProperties(styleProperties = originalStyle)

        val copied = original.copy(styleProperties = newStyle)

        assertEquals(newStyle, copied.styleProperties)
        assertEquals(originalStyle, original.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties with mutable actionProperties when modifying then modification is reflected`() {
        val actionProps = mockk<ActionProperties>(relaxed = true)
        val checkBoxProperties = CheckBoxProperties(actionProperties = actionProps)

        checkBoxProperties.actionProperties = null

        assertNull(checkBoxProperties.actionProperties)
    }

    @Test
    fun `given CheckBoxProperties with mutable styleProperties when modifying then modification is reflected`() {
        val styleProps = mockk<StyleProperties>(relaxed = true)
        val checkBoxProperties = CheckBoxProperties(styleProperties = styleProps)

        checkBoxProperties.styleProperties = null

        assertNull(checkBoxProperties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties when checking toString then toString returns valid string representation`() {
        val checkBoxProperties = CheckBoxProperties(text = "Test", enable = true)
        val toStringResult = checkBoxProperties.toString()

        assertTrue(toStringResult.contains("CheckBoxProperties"))
        assertTrue(toStringResult.contains("text"))
        assertTrue(toStringResult.contains("Test"))
    }

    @Test
    fun `given multiple CheckBoxProperties with same data when creating then all instances are equal`() {
        val instances = (1..5).map {
            CheckBoxProperties(
                text = "Test",
                align = CraftDAlign.LEFT,
                enable = true
            )
        }

        instances.forEach { instance ->
            assertEquals(instances[0], instance)
        }
    }

    @Test
    fun `given CheckBoxProperties with complex nested objects when copying then nested objects are preserved`() {
        val actionProps = mockk<ActionProperties>()
        val styleProps = mockk<StyleProperties>()
        val original = CheckBoxProperties(
            text = "Test",
            actionProperties = actionProps,
            styleProperties = styleProps
        )

        val copied = original.copy()

        assertEquals(actionProps, copied.actionProperties)
        assertEquals(styleProps, copied.styleProperties)
        assertEquals(original, copied)
    }
}