package com.github.codandotv.craftd.androidcore.data.model.checkbox

import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDAlign
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class CheckBoxPropertiesTest {

    @Test
    fun `given default parameters when creating CheckBoxProperties then all fields have default values`() {
        val properties = CheckBoxProperties()

        assertNull(properties.text)
        assertNull(properties.align)
        assertNull(properties.textAlign)
        assertFalse(properties.enable!!)
        assertFalse(properties.hasItRightText!!)
        assertNull(properties.actionProperties)
        assertNull(properties.styleProperties)
    }

    @Test
    fun `given all parameters when creating CheckBoxProperties then all fields are set correctly`() {
        val actionProps = mockk<ActionProperties>()
        val styleProps = mockk<StyleProperties>()
        val properties = CheckBoxProperties(
            text = "Test CheckBox",
            align = CraftDAlign.START,
            textAlign = CraftDAlign.CENTER,
            enable = true,
            hasItRightText = true,
            actionProperties = actionProps,
            styleProperties = styleProps
        )

        assertEquals("Test CheckBox", properties.text)
        assertEquals(CraftDAlign.START, properties.align)
        assertEquals(CraftDAlign.CENTER, properties.textAlign)
        assertTrue(properties.enable!!)
        assertTrue(properties.hasItRightText!!)
        assertEquals(actionProps, properties.actionProperties)
        assertEquals(styleProps, properties.styleProperties)
    }

    @Test
    fun `given partial parameters when creating CheckBoxProperties then non-provided fields use defaults`() {
        val properties = CheckBoxProperties(
            text = "Partial",
            enable = true
        )

        assertEquals("Partial", properties.text)
        assertTrue(properties.enable!!)
        assertNull(properties.align)
        assertNull(properties.textAlign)
        assertFalse(properties.hasItRightText!!)
        assertNull(properties.actionProperties)
        assertNull(properties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties when calling copy with new text then returns new instance with updated text`() {
        val original = CheckBoxProperties(text = "Original")
        val copied = original.copy(text = "Updated")

        assertEquals("Updated", copied.text)
        assertNull(copied.align)
        assertNotEquals(original.text, copied.text)
    }

    @Test
    fun `given CheckBoxProperties when calling copy with multiple parameters then returns new instance with all updates`() {
        val original = CheckBoxProperties(text = "Original", enable = false)
        val actionProps = mockk<ActionProperties>()
        val copied = original.copy(
            text = "New Text",
            enable = true,
            align = CraftDAlign.END,
            actionProperties = actionProps
        )

        assertEquals("New Text", copied.text)
        assertTrue(copied.enable!!)
        assertEquals(CraftDAlign.END, copied.align)
        assertEquals(actionProps, copied.actionProperties)
    }

    @Test
    fun `given identical CheckBoxProperties when comparing with equals then returns true`() {
        val actionProps = mockk<ActionProperties>()
        val props1 = CheckBoxProperties(
            text = "Same",
            align = CraftDAlign.START,
            enable = true,
            actionProperties = actionProps
        )
        val props2 = CheckBoxProperties(
            text = "Same",
            align = CraftDAlign.START,
            enable = true,
            actionProperties = actionProps
        )

        assertEquals(props1, props2)
    }

    @Test
    fun `given different CheckBoxProperties when comparing with equals then returns false`() {
        val props1 = CheckBoxProperties(text = "Text1", enable = true)
        val props2 = CheckBoxProperties(text = "Text2", enable = false)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given CheckBoxProperties with different text when comparing then returns false`() {
        val props1 = CheckBoxProperties(text = "Text A")
        val props2 = CheckBoxProperties(text = "Text B")

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given CheckBoxProperties with different align when comparing then returns false`() {
        val props1 = CheckBoxProperties(align = CraftDAlign.START)
        val props2 = CheckBoxProperties(align = CraftDAlign.CENTER)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given CheckBoxProperties with different enable when comparing then returns false`() {
        val props1 = CheckBoxProperties(enable = true)
        val props2 = CheckBoxProperties(enable = false)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given identical CheckBoxProperties when calling hashCode then returns same hash`() {
        val actionProps = mockk<ActionProperties>()
        val props1 = CheckBoxProperties(
            text = "Same",
            enable = true,
            actionProperties = actionProps
        )
        val props2 = CheckBoxProperties(
            text = "Same",
            enable = true,
            actionProperties = actionProps
        )

        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given different CheckBoxProperties when calling hashCode then may return different hash`() {
        val props1 = CheckBoxProperties(text = "Text1", enable = true)
        val props2 = CheckBoxProperties(text = "Text2", enable = false)

        assertNotEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given CheckBoxProperties when creating with null actionProperties then actionProperties is null`() {
        val properties = CheckBoxProperties(actionProperties = null)

        assertNull(properties.actionProperties)
    }

    @Test
    fun `given CheckBoxProperties when creating with null styleProperties then styleProperties is null`() {
        val properties = CheckBoxProperties(styleProperties = null)

        assertNull(properties.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties when accessing all CraftDAlign variants then no exceptions thrown`() {
        val alignValues = listOf(
            CraftDAlign.START,
            CraftDAlign.CENTER,
            CraftDAlign.END
        )

        for (align in alignValues) {
            val properties = CheckBoxProperties(align = align)
            assertEquals(align, properties.align)
        }
    }

    @Test
    fun `given CheckBoxProperties with boolean fields when toggling values then reflects correctly`() {
        var properties = CheckBoxProperties(enable = true, hasItRightText = false)
        assertEquals(true, properties.enable)
        assertEquals(false, properties.hasItRightText)

        properties = properties.copy(enable = false, hasItRightText = true)
        assertEquals(false, properties.enable)
        assertEquals(true, properties.hasItRightText)
    }

    @Test
    fun `given CheckBoxProperties when modifying actionProperties var then reflects change`() {
        val properties = CheckBoxProperties()
        assertNull(properties.actionProperties)

        val newActionProps = mockk<ActionProperties>()
        properties.actionProperties = newActionProps
        assertEquals(newActionProps, properties.actionProperties)
    }

    @Test
    fun `given CheckBoxProperties when modifying styleProperties var then reflects change`() {
        val properties = CheckBoxProperties()
        assertNull(properties.styleProperties)

        val newStyleProps = mockk<StyleProperties>()
        properties.styleProperties = newStyleProps
        assertEquals(newStyleProps, properties.styleProperties)
    }

    @Test
    fun `given empty string text when creating CheckBoxProperties then text is preserved`() {
        val properties = CheckBoxProperties(text = "")

        assertEquals("", properties.text)
    }

    @Test
    fun `given null text when creating CheckBoxProperties then text is null`() {
        val properties = CheckBoxProperties(text = null)

        assertNull(properties.text)
    }

    @Test
    fun `given CheckBoxProperties with all null optional fields when comparing equality then considers content`() {
        val props1 = CheckBoxProperties(
            text = null,
            align = null,
            textAlign = null,
            actionProperties = null,
            styleProperties = null
        )
        val props2 = CheckBoxProperties(
            text = null,
            align = null,
            textAlign = null,
            actionProperties = null,
            styleProperties = null
        )

        assertEquals(props1, props2)
    }

    @Test
    fun `given CheckBoxProperties copy when not modifying actionProperties then retains original reference`() {
        val actionProps = mockk<ActionProperties>()
        val original = CheckBoxProperties(
            text = "Original",
            actionProperties = actionProps
        )
        val copied = original.copy(text = "Updated")

        assertEquals(actionProps, copied.actionProperties)
        assertEquals(original.actionProperties, copied.actionProperties)
    }

    @Test
    fun `given CheckBoxProperties copy when replacing actionProperties then has new reference`() {
        val oldActionProps = mockk<ActionProperties>()
        val newActionProps = mockk<ActionProperties>()
        val original = CheckBoxProperties(actionProperties = oldActionProps)
        val copied = original.copy(actionProperties = newActionProps)

        assertEquals(newActionProps, copied.actionProperties)
        assertNotEquals(oldActionProps, copied.actionProperties)
    }

    @Test
    fun `given multiple CheckBoxProperties with different textAlign when comparing then reflects differences`() {
        val props1 = CheckBoxProperties(textAlign = CraftDAlign.START)
        val props2 = CheckBoxProperties(textAlign = CraftDAlign.CENTER)
        val props3 = CheckBoxProperties(textAlign = CraftDAlign.END)

        assertNotEquals(props1, props2)
        assertNotEquals(props2, props3)
        assertNotEquals(props1, props3)
    }

    @Test
    fun `given CheckBoxProperties when accessing default enable value then returns false`() {
        val properties = CheckBoxProperties()

        assertEquals(false, properties.enable)
    }

    @Test
    fun `given CheckBoxProperties when accessing default hasItRightText value then returns false`() {
        val properties = CheckBoxProperties()

        assertEquals(false, properties.hasItRightText)
    }

    @Test
    fun `given CheckBoxProperties with all fields populated when converting to string then no exceptions thrown`() {
        val actionProps = mockk<ActionProperties>()
        val styleProps = mockk<StyleProperties>()
        val properties = CheckBoxProperties(
            text = "Test",
            align = CraftDAlign.START,
            textAlign = CraftDAlign.CENTER,
            enable = true,
            hasItRightText = true,
            actionProperties = actionProps,
            styleProperties = styleProps
        )

        val stringRepresentation = properties.toString()
        assertTrue(stringRepresentation.isNotEmpty())
    }

    @Test
    fun `given two CheckBoxProperties instances when one is modified then original remains unchanged`() {
        val original = CheckBoxProperties(text = "Original", enable = false)
        val modified = original.copy(text = "Modified", enable = true)

        assertEquals("Original", original.text)
        assertEquals(false, original.enable)
        assertEquals("Modified", modified.text)
        assertEquals(true, modified.enable)
    }
}