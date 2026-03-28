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
    fun `given all params when constructing CheckBoxProperties then all fields are set correctly`() {
        val actionProps = mockk<ActionProperties>()
        val styleProps = mockk<StyleProperties>()
        val align = CraftDAlign.START
        val textAlign = CraftDAlign.CENTER

        val checkBoxProps = CheckBoxProperties(
            text = "Test Text",
            align = align,
            textAlign = textAlign,
            enable = true,
            hasItRightText = true,
            actionProperties = actionProps,
            styleProperties = styleProps
        )

        assertEquals("Test Text", checkBoxProps.text)
        assertEquals(align, checkBoxProps.align)
        assertEquals(textAlign, checkBoxProps.textAlign)
        assertTrue(checkBoxProps.enable == true)
        assertTrue(checkBoxProps.hasItRightText == true)
        assertEquals(actionProps, checkBoxProps.actionProperties)
        assertEquals(styleProps, checkBoxProps.styleProperties)
    }

    @Test
    fun `given default constructor when constructing CheckBoxProperties then default values are applied`() {
        val checkBoxProps = CheckBoxProperties()

        assertNull(checkBoxProps.text)
        assertNull(checkBoxProps.align)
        assertNull(checkBoxProps.textAlign)
        assertFalse(checkBoxProps.enable == true)
        assertFalse(checkBoxProps.hasItRightText == true)
        assertNull(checkBoxProps.actionProperties)
        assertNull(checkBoxProps.styleProperties)
    }

    @Test
    fun `given partial params when constructing CheckBoxProperties then specified fields are set and others use defaults`() {
        val checkBoxProps = CheckBoxProperties(
            text = "Partial",
            enable = true
        )

        assertEquals("Partial", checkBoxProps.text)
        assertTrue(checkBoxProps.enable == true)
        assertNull(checkBoxProps.align)
        assertNull(checkBoxProps.textAlign)
        assertFalse(checkBoxProps.hasItRightText == true)
        assertNull(checkBoxProps.actionProperties)
        assertNull(checkBoxProps.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties when using copy with all params then creates new instance with updated values`() {
        val original = CheckBoxProperties(
            text = "Original",
            align = CraftDAlign.START,
            enable = true,
            hasItRightText = false
        )
        val actionProps = mockk<ActionProperties>()
        val styleProps = mockk<StyleProperties>()

        val copied = original.copy(
            text = "Copied",
            align = CraftDAlign.END,
            textAlign = CraftDAlign.CENTER,
            enable = false,
            hasItRightText = true,
            actionProperties = actionProps,
            styleProperties = styleProps
        )

        assertEquals("Copied", copied.text)
        assertEquals(CraftDAlign.END, copied.align)
        assertEquals(CraftDAlign.CENTER, copied.textAlign)
        assertFalse(copied.enable == true)
        assertTrue(copied.hasItRightText == true)
        assertEquals(actionProps, copied.actionProperties)
        assertEquals(styleProps, copied.styleProperties)
    }

    @Test
    fun `given CheckBoxProperties when using copy with partial params then only specified fields are updated`() {
        val original = CheckBoxProperties(
            text = "Original",
            align = CraftDAlign.START,
            enable = true
        )

        val copied = original.copy(text = "Updated")

        assertEquals("Updated", copied.text)
        assertEquals(CraftDAlign.START, copied.align)
        assertTrue(copied.enable == true)
    }

    @Test
    fun `given two CheckBoxProperties with same values when comparing with equals then returns true`() {
        val props1 = CheckBoxProperties(
            text = "Same",
            align = CraftDAlign.CENTER,
            enable = true
        )
        val props2 = CheckBoxProperties(
            text = "Same",
            align = CraftDAlign.CENTER,
            enable = true
        )

        assertEquals(props1, props2)
    }

    @Test
    fun `given two CheckBoxProperties with different values when comparing with equals then returns false`() {
        val props1 = CheckBoxProperties(text = "Text1", enable = true)
        val props2 = CheckBoxProperties(text = "Text2", enable = false)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two CheckBoxProperties with different align when comparing with equals then returns false`() {
        val props1 = CheckBoxProperties(align = CraftDAlign.START)
        val props2 = CheckBoxProperties(align = CraftDAlign.END)

        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two CheckBoxProperties with same values when comparing hashCode then hashCode values are equal`() {
        val props1 = CheckBoxProperties(
            text = "Same",
            align = CraftDAlign.CENTER,
            enable = true
        )
        val props2 = CheckBoxProperties(
            text = "Same",
            align = CraftDAlign.CENTER,
            enable = true
        )

        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given two CheckBoxProperties with different values when comparing hashCode then hashCode values may differ`() {
        val props1 = CheckBoxProperties(text = "Text1")
        val props2 = CheckBoxProperties(text = "Text2")

        assertTrue(props1.hashCode() != props2.hashCode())
    }

    @Test
    fun `given CheckBoxProperties when calling toString then returns string representation`() {
        val checkBoxProps = CheckBoxProperties(
            text = "Test",
            enable = true
        )

        val stringRep = checkBoxProps.toString()

        assertTrue(stringRep.contains("CheckBoxProperties"))
        assertTrue(stringRep.contains("text=Test"))
        assertTrue(stringRep.contains("enable=true"))
    }

    @Test
    fun `given null text when constructing CheckBoxProperties then text is null`() {
        val checkBoxProps = CheckBoxProperties(text = null)

        assertNull(checkBoxProps.text)
    }

    @Test
    fun `given null align when constructing CheckBoxProperties then align is null`() {
        val checkBoxProps = CheckBoxProperties(align = null)

        assertNull(checkBoxProps.align)
    }

    @Test
    fun `given null textAlign when constructing CheckBoxProperties then textAlign is null`() {
        val checkBoxProps = CheckBoxProperties(textAlign = null)

        assertNull(checkBoxProps.textAlign)
    }

    @Test
    fun `given null actionProperties when constructing CheckBoxProperties then actionProperties is null`() {
        val checkBoxProps = CheckBoxProperties(actionProperties = null)

        assertNull(checkBoxProps.actionProperties)
    }

    @Test
    fun `given null styleProperties when constructing CheckBoxProperties then styleProperties is null`() {
        val checkBoxProps = CheckBoxProperties(styleProperties = null)

        assertNull(checkBoxProps.styleProperties)
    }

    @Test
    fun `given all CraftDAlign values when checking enum constants then all values exist`() {
        val alignValues = listOf(
            CraftDAlign.START,
            CraftDAlign.CENTER,
            CraftDAlign.END
        )

        assertTrue(alignValues.isNotEmpty())
        for (value in alignValues) {
            val enumValue = enumValueOf<CraftDAlign>(value.name)
            assertEquals(value, enumValue)
        }
    }

    @Test
    fun `given false boolean values when constructing CheckBoxProperties then enable and hasItRightText are false`() {
        val checkBoxProps = CheckBoxProperties(
            enable = false,
            hasItRightText = false
        )

        assertFalse(checkBoxProps.enable == true)
        assertFalse(checkBoxProps.hasItRightText == true)
    }

    @Test
    fun `given true boolean values when constructing CheckBoxProperties then enable and hasItRightText are true`() {
        val checkBoxProps = CheckBoxProperties(
            enable = true,
            hasItRightText = true
        )

        assertTrue(checkBoxProps.enable == true)
        assertTrue(checkBoxProps.hasItRightText == true)
    }

    @Test
    fun `given multiple align values when constructing different CheckBoxProperties then each has correct align`() {
        val checkBoxStart = CheckBoxProperties(align = CraftDAlign.START)
        val checkBoxCenter = CheckBoxProperties(align = CraftDAlign.CENTER)
        val checkBoxEnd = CheckBoxProperties(align = CraftDAlign.END)

        assertEquals(CraftDAlign.START, checkBoxStart.align)
        assertEquals(CraftDAlign.CENTER, checkBoxCenter.align)
        assertEquals(CraftDAlign.END, checkBoxEnd.align)
    }

    @Test
    fun `given multiple textAlign values when constructing different CheckBoxProperties then each has correct textAlign`() {
        val checkBoxStart = CheckBoxProperties(textAlign = CraftDAlign.START)
        val checkBoxCenter = CheckBoxProperties(textAlign = CraftDAlign.CENTER)
        val checkBoxEnd = CheckBoxProperties(textAlign = CraftDAlign.END)

        assertEquals(CraftDAlign.START, checkBoxStart.textAlign)
        assertEquals(CraftDAlign.CENTER, checkBoxCenter.textAlign)
        assertEquals(CraftDAlign.END, checkBoxEnd.textAlign)
    }

    @Test
    fun `given actionProperties when setting actionProperties then field is updated`() {
        val checkBoxProps = CheckBoxProperties()
        val actionProps = mockk<ActionProperties>()

        val updated = checkBoxProps.copy(actionProperties = actionProps)

        assertEquals(actionProps, updated.actionProperties)
    }

    @Test
    fun `given styleProperties when setting styleProperties then field is updated`() {
        val checkBoxProps = CheckBoxProperties()
        val styleProps = mockk<StyleProperties>()

        val updated = checkBoxProps.copy(styleProperties = styleProps)

        assertEquals(styleProps, updated.styleProperties)
    }

    @Test
    fun `given empty text when constructing CheckBoxProperties then text is empty string`() {
        val checkBoxProps = CheckBoxProperties(text = "")

        assertEquals("", checkBoxProps.text)
    }

    @Test
    fun `given long text when constructing CheckBoxProperties then text is stored correctly`() {
        val longText = "A".repeat(1000)
        val checkBoxProps = CheckBoxProperties(text = longText)

        assertEquals(longText, checkBoxProps.text)
    }

    @Test
    fun `given special characters in text when constructing CheckBoxProperties then text is stored correctly`() {
        val specialText = "Test@#$%^&*()_+-=[]{}|;:',.<>?/\\`~"
        val checkBoxProps = CheckBoxProperties(text = specialText)

        assertEquals(specialText, checkBoxProps.text)
    }

    @Test
    fun `given unicode text when constructing CheckBoxProperties then text is stored correctly`() {
        val unicodeText = "こんにちは世界 🚀"
        val checkBoxProps = CheckBoxProperties(text = unicodeText)

        assertEquals(unicodeText, checkBoxProps.text)
    }

    @Test
    fun `given CheckBoxProperties with actionProperties when comparing two instances then both have same actionProperties`() {
        val actionProps = mockk<ActionProperties>()
        val props1 = CheckBoxProperties(actionProperties = actionProps)
        val props2 = CheckBoxProperties(actionProperties = actionProps)

        assertEquals(props1, props2)
    }

    @Test
    fun `given CheckBoxProperties with styleProperties when comparing two instances then both have same styleProperties`() {
        val styleProps = mockk<StyleProperties>()
        val props1 = CheckBoxProperties(styleProperties = styleProps)
        val props2 = CheckBoxProperties(styleProperties = styleProps)

        assertEquals(props1, props2)
    }

    @Test
    fun `given CheckBoxProperties when using copy multiple times then creates independent instances`() {
        val original = CheckBoxProperties(text = "Original")
        val copy1 = original.copy(text = "Copy1")
        val copy2 = original.copy(text = "Copy2")

        assertEquals("Original", original.text)
        assertEquals("Copy1", copy1.text)
        assertEquals("Copy2", copy2.text)
    }

    @Test
    fun `given CheckBoxProperties with all fields null when constructing then all fields are null except defaults`() {
        val checkBoxProps = CheckBoxProperties(
            text = null,
            align = null,
            textAlign = null,
            actionProperties = null,
            styleProperties = null
        )

        assertNull(checkBoxProps.text)
        assertNull(checkBoxProps.align)
        assertNull(checkBoxProps.textAlign)
        assertNull(checkBoxProps.actionProperties)
        assertNull(checkBoxProps.styleProperties)
    }
}