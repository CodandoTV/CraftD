package com.github.codandotv.craftd.androidcore.data.model.checkbox

import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

@RunWith(JUnit4::class)
class StylePropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given all parameters when instantiating StyleProperties then all fields are set correctly`() {
        val checkedColor = "#FF0000"
        val uncheckedColor = "#00FF00"

        val styleProperties = StyleProperties(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given null parameters when instantiating StyleProperties then fields default to null`() {
        val styleProperties = StyleProperties(
            checkedColor = null,
            uncheckedColor = null
        )

        assertNull(styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given no parameters when instantiating StyleProperties then all fields are null`() {
        val styleProperties = StyleProperties()

        assertNull(styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given partial parameters when instantiating StyleProperties then specified fields are set and others are null`() {
        val checkedColor = "#AABBCC"

        val styleProperties = StyleProperties(checkedColor = checkedColor)

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with all fields when calling copy with new values then returns new instance with updated fields`() {
        val original = StyleProperties(
            checkedColor = "#111111",
            uncheckedColor = "#222222"
        )
        val newCheckedColor = "#333333"
        val newUncheckedColor = "#444444"

        val copied = original.copy(
            checkedColor = newCheckedColor,
            uncheckedColor = newUncheckedColor
        )

        assertEquals(newCheckedColor, copied.checkedColor)
        assertEquals(newUncheckedColor, copied.uncheckedColor)
        assertEquals(original.checkedColor, original.checkedColor)
    }

    @Test
    fun `given StyleProperties when calling copy with no parameters then returns identical instance`() {
        val original = StyleProperties(
            checkedColor = "#FFFF00",
            uncheckedColor = "#00FFFF"
        )

        val copied = original.copy()

        assertEquals(original, copied)
        assertEquals(original.checkedColor, copied.checkedColor)
        assertEquals(original.uncheckedColor, copied.uncheckedColor)
    }

    @Test
    fun `given StyleProperties when calling copy with partial parameters then only specified fields are updated`() {
        val original = StyleProperties(
            checkedColor = "#111111",
            uncheckedColor = "#222222"
        )
        val newCheckedColor = "#555555"

        val copied = original.copy(checkedColor = newCheckedColor)

        assertEquals(newCheckedColor, copied.checkedColor)
        assertEquals(original.uncheckedColor, copied.uncheckedColor)
    }

    @Test
    fun `given two StyleProperties with same values when comparing with equals then returns true`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )

        assertEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with different checked color when comparing with equals then returns false`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#111111",
            uncheckedColor = "#DDEEFF"
        )

        assertNotEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with different unchecked color when comparing with equals then returns false`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#999999"
        )

        assertNotEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with all null values when comparing with equals then returns true`() {
        val styleProperties1 = StyleProperties()
        val styleProperties2 = StyleProperties()

        assertEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given StyleProperties with null checked color when comparing with one that has checked color then returns false`() {
        val styleProperties1 = StyleProperties(
            checkedColor = null,
            uncheckedColor = "#DDEEFF"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )

        assertNotEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with same values when calling hashCode then returns same hash`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )

        assertEquals(styleProperties1.hashCode(), styleProperties2.hashCode())
    }

    @Test
    fun `given two StyleProperties with different values when calling hashCode then likely returns different hash`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#111111",
            uncheckedColor = "#999999"
        )

        assertNotEquals(styleProperties1.hashCode(), styleProperties2.hashCode())
    }

    @Test
    fun `given StyleProperties with null values when calling hashCode then returns hash`() {
        val styleProperties1 = StyleProperties()
        val styleProperties2 = StyleProperties()

        assertEquals(styleProperties1.hashCode(), styleProperties2.hashCode())
    }

    @Test
    fun `given StyleProperties when calling toString then returns string representation`() {
        val styleProperties = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )

        val result = styleProperties.toString()

        assert(result.contains("checkedColor"))
        assert(result.contains("uncheckedColor"))
        assert(result.contains("#AABBCC"))
        assert(result.contains("#DDEEFF"))
    }

    @Test
    fun `given StyleProperties with null values when calling toString then returns string representation`() {
        val styleProperties = StyleProperties()

        val result = styleProperties.toString()

        assert(result.contains("StyleProperties"))
    }

    @Test
    fun `given StyleProperties when accessing checked color field then returns correct value`() {
        val checkedColor = "#FF5733"
        val styleProperties = StyleProperties(checkedColor = checkedColor)

        assertEquals(checkedColor, styleProperties.checkedColor)
    }

    @Test
    fun `given StyleProperties when accessing unchecked color field then returns correct value`() {
        val uncheckedColor = "#33FF57"
        val styleProperties = StyleProperties(uncheckedColor = uncheckedColor)

        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given empty string color values when instantiating StyleProperties then accepts empty strings`() {
        val styleProperties = StyleProperties(
            checkedColor = "",
            uncheckedColor = ""
        )

        assertEquals("", styleProperties.checkedColor)
        assertEquals("", styleProperties.uncheckedColor)
    }

    @Test
    fun `given mixed null and non-null color values when comparing two instances then equality works correctly`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = null
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = null
        )

        assertEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given StyleProperties when using in copy with null overrides then sets fields to null`() {
        val original = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )

        val copied = original.copy(
            checkedColor = null,
            uncheckedColor = null
        )

        assertNull(copied.checkedColor)
        assertNull(copied.uncheckedColor)
    }

    @Test
    fun `given StyleProperties instances when comparing with same reference then equals returns true`() {
        val styleProperties = StyleProperties(
            checkedColor = "#AABBCC",
            uncheckedColor = "#DDEEFF"
        )

        assertEquals(styleProperties, styleProperties)
    }

    @Test
    fun `given StyleProperties with long hex color codes when instantiating then accepts valid values`() {
        val checkedColor = "#FFFFFFFF"
        val uncheckedColor = "#00000000"

        val styleProperties = StyleProperties(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with short hex color codes when instantiating then accepts valid values`() {
        val checkedColor = "#FFF"
        val uncheckedColor = "#000"

        val styleProperties = StyleProperties(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }
}