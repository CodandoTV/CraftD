package com.github.codandotv.craftd.androidcore.data.model.checkbox

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
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
    fun `given all parameters when creating StyleProperties then initializes correctly`() {
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
    fun `given null parameters when creating StyleProperties then initializes with defaults`() {
        val styleProperties = StyleProperties()

        assertNull(styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given only checkedColor when creating StyleProperties then uncheckedColor is null`() {
        val checkedColor = "#FF0000"

        val styleProperties = StyleProperties(checkedColor = checkedColor)

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given only uncheckedColor when creating StyleProperties then checkedColor is null`() {
        val uncheckedColor = "#00FF00"

        val styleProperties = StyleProperties(uncheckedColor = uncheckedColor)

        assertNull(styleProperties.checkedColor)
        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with values when calling copy then returns new instance with updated values`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val copied = original.copy(checkedColor = "#0000FF")

        assertEquals("#0000FF", copied.checkedColor)
        assertEquals("#00FF00", copied.uncheckedColor)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given StyleProperties with values when calling copy with no arguments then returns equal instance`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given two StyleProperties with same values when comparing equals then returns true`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with different checkedColor when comparing equals then returns false`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#0000FF",
            uncheckedColor = "#00FF00"
        )

        assertNotEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with different uncheckedColor when comparing equals then returns false`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#0000FF"
        )

        assertNotEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with all null values when comparing equals then returns true`() {
        val styleProperties1 = StyleProperties()
        val styleProperties2 = StyleProperties()

        assertEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with null and non-null values when comparing equals then returns false`() {
        val styleProperties1 = StyleProperties(checkedColor = "#FF0000")
        val styleProperties2 = StyleProperties()

        assertNotEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given two StyleProperties with same values when comparing hashCode then returns same hash`() {
        val styleProperties1 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals(styleProperties1.hashCode(), styleProperties2.hashCode())
    }

    @Test
    fun `given two StyleProperties with different values when comparing hashCode then likely returns different hash`() {
        val styleProperties1 = StyleProperties(checkedColor = "#FF0000")
        val styleProperties2 = StyleProperties(checkedColor = "#0000FF")

        assertNotEquals(styleProperties1.hashCode(), styleProperties2.hashCode())
    }

    @Test
    fun `given StyleProperties with values when calling toString then returns string representation`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val stringRepresentation = styleProperties.toString()

        assert(stringRepresentation.contains("StyleProperties"))
        assert(stringRepresentation.contains("#FF0000"))
        assert(stringRepresentation.contains("#00FF00"))
    }

    @Test
    fun `given StyleProperties with null values when calling toString then returns string representation`() {
        val styleProperties = StyleProperties()

        val stringRepresentation = styleProperties.toString()

        assert(stringRepresentation.contains("StyleProperties"))
    }

    @Test
    fun `given StyleProperties instance when accessing checkedColor property then returns correct value`() {
        val checkedColor = "#FF0000"
        val styleProperties = StyleProperties(checkedColor = checkedColor)

        assertEquals(checkedColor, styleProperties.checkedColor)
    }

    @Test
    fun `given StyleProperties instance when accessing uncheckedColor property then returns correct value`() {
        val uncheckedColor = "#00FF00"
        val styleProperties = StyleProperties(uncheckedColor = uncheckedColor)

        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with same instance when comparing equals then returns true`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals(styleProperties, styleProperties)
    }

    @Test
    fun `given StyleProperties when comparing with different type then returns false`() {
        val styleProperties = StyleProperties(checkedColor = "#FF0000")

        assertNotEquals<Any?>(styleProperties, "#FF0000")
    }

    @Test
    fun `given multiple StyleProperties copies when comparing all equals then all are equal`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val copy1 = original.copy()
        val copy2 = original.copy()

        assertEquals(copy1, copy2)
        assertEquals(original, copy1)
        assertEquals(original, copy2)
    }

    @Test
    fun `given StyleProperties with empty strings when initializing then accepts empty strings`() {
        val styleProperties = StyleProperties(
            checkedColor = "",
            uncheckedColor = ""
        )

        assertEquals("", styleProperties.checkedColor)
        assertEquals("", styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with whitespace values when initializing then accepts whitespace`() {
        val checkedColor = "   "
        val uncheckedColor = "\t"

        val styleProperties = StyleProperties(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with long color values when initializing then accepts long strings`() {
        val longColor = "#" + "FF".repeat(100)

        val styleProperties = StyleProperties(
            checkedColor = longColor,
            uncheckedColor = longColor
        )

        assertEquals(longColor, styleProperties.checkedColor)
        assertEquals(longColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with special characters when initializing then accepts special characters`() {
        val specialColor = "#FF0000!@#$%"

        val styleProperties = StyleProperties(checkedColor = specialColor)

        assertEquals(specialColor, styleProperties.checkedColor)
    }

    @Test
    fun `given two identical StyleProperties when using in collection then both are equal`() {
        val styleProperties1 = StyleProperties(checkedColor = "#FF0000")
        val styleProperties2 = StyleProperties(checkedColor = "#FF0000")

        val list1 = listOf(styleProperties1)
        val list2 = listOf(styleProperties2)

        assertEquals(list1, list2)
    }

    @Test
    fun `given StyleProperties when creating with named arguments in any order then initializes correctly`() {
        val styleProperties1 = StyleProperties(
            uncheckedColor = "#00FF00",
            checkedColor = "#FF0000"
        )
        val styleProperties2 = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals(styleProperties1, styleProperties2)
    }

    @Test
    fun `given StyleProperties when copying and modifying both properties then reflects changes correctly`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val modified = original.copy(
            checkedColor = "#0000FF",
            uncheckedColor = "#FFFF00"
        )

        assertEquals("#0000FF", modified.checkedColor)
        assertEquals("#FFFF00", modified.uncheckedColor)
        assertEquals("#FF0000", original.checkedColor)
        assertEquals("#00FF00", original.uncheckedColor)
    }

    @Test
    fun `given StyleProperties when destructuring then extracts values correctly`() {
        val (checkedColor, uncheckedColor) = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals("#FF0000", checkedColor)
        assertEquals("#00FF00", uncheckedColor)
    }
}