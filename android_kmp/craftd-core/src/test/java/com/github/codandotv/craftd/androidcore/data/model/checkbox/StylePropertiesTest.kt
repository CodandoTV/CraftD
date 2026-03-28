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
    fun `given all parameters when constructing StyleProperties then all fields are set correctly`() {
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
    fun `given only checkedColor when constructing StyleProperties then uncheckedColor defaults to null`() {
        val checkedColor = "#FF0000"

        val styleProperties = StyleProperties(checkedColor = checkedColor)

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given only uncheckedColor when constructing StyleProperties then checkedColor defaults to null`() {
        val uncheckedColor = "#00FF00"

        val styleProperties = StyleProperties(uncheckedColor = uncheckedColor)

        assertNull(styleProperties.checkedColor)
        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given no parameters when constructing StyleProperties then both fields default to null`() {
        val styleProperties = StyleProperties()

        assertNull(styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties when calling copy with new values then returns new instance with updated fields`() {
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
    fun `given StyleProperties when calling copy without parameters then returns identical instance`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given two StyleProperties with same values when comparing then equals returns true`() {
        val first = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val second = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals(first, second)
    }

    @Test
    fun `given two StyleProperties with different checkedColor when comparing then equals returns false`() {
        val first = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val second = StyleProperties(
            checkedColor = "#0000FF",
            uncheckedColor = "#00FF00"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two StyleProperties with different uncheckedColor when comparing then equals returns false`() {
        val first = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val second = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#FF00FF"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two StyleProperties with all null values when comparing then equals returns true`() {
        val first = StyleProperties()
        val second = StyleProperties()

        assertEquals(first, second)
    }

    @Test
    fun `given two StyleProperties with same values when comparing hashCode then hashCode is equal`() {
        val first = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val second = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given two StyleProperties with different values when comparing hashCode then hashCode may differ`() {
        val first = StyleProperties(checkedColor = "#FF0000")
        val second = StyleProperties(checkedColor = "#0000FF")

        assertNotEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given StyleProperties with null checkedColor when calling toString then includes null`() {
        val styleProperties = StyleProperties(uncheckedColor = "#00FF00")

        val result = styleProperties.toString()

        assert(result.contains("checkedColor"))
        assert(result.contains("null"))
    }

    @Test
    fun `given StyleProperties with all values when calling toString then includes all field values`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val result = styleProperties.toString()

        assert(result.contains("checkedColor"))
        assert(result.contains("uncheckedColor"))
        assert(result.contains("#FF0000"))
        assert(result.contains("#00FF00"))
    }

    @Test
    fun `given StyleProperties when destructuring then components are returned in correct order`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val (checkedColor, uncheckedColor) = styleProperties

        assertEquals("#FF0000", checkedColor)
        assertEquals("#00FF00", uncheckedColor)
    }

    @Test
    fun `given StyleProperties with mixed null and non-null values when calling copy with null then overwrites with null`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val copied = original.copy(checkedColor = null)

        assertNull(copied.checkedColor)
        assertEquals("#00FF00", copied.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with complex color hex when constructing then preserves exact value`() {
        val complexColor = "#FFAABBCC"

        val styleProperties = StyleProperties(
            checkedColor = complexColor,
            uncheckedColor = complexColor
        )

        assertEquals(complexColor, styleProperties.checkedColor)
        assertEquals(complexColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties when comparing with different object type then equals returns false`() {
        val styleProperties = StyleProperties(checkedColor = "#FF0000")
        val otherObject = "#FF0000"

        val result = styleProperties.equals(otherObject)

        assertEquals(false, result)
    }

    @Test
    fun `given StyleProperties when comparing with itself then equals returns true`() {
        val styleProperties = StyleProperties(checkedColor = "#FF0000")

        assertEquals(styleProperties, styleProperties)
    }

    @Test
    fun `given two StyleProperties instances when creating separate instances with same parameters then they are equal`() {
        val params = Pair("#FF0000", "#00FF00")

        val first = StyleProperties(checkedColor = params.first, uncheckedColor = params.second)
        val second = StyleProperties(checkedColor = params.first, uncheckedColor = params.second)

        assertEquals(first, second)
        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given StyleProperties when calling copy multiple times then creates independent instances`() {
        val original = StyleProperties(checkedColor = "#FF0000")

        val copy1 = original.copy(uncheckedColor = "#00FF00")
        val copy2 = original.copy(uncheckedColor = "#0000FF")

        assertEquals("#00FF00", copy1.uncheckedColor)
        assertEquals("#0000FF", copy2.uncheckedColor)
        assertNotEquals(copy1, copy2)
    }

    @Test
    fun `given StyleProperties with empty string color when constructing then preserves empty string`() {
        val styleProperties = StyleProperties(
            checkedColor = "",
            uncheckedColor = ""
        )

        assertEquals("", styleProperties.checkedColor)
        assertEquals("", styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties with whitespace color when constructing then preserves whitespace`() {
        val styleProperties = StyleProperties(
            checkedColor = "   ",
            uncheckedColor = "   "
        )

        assertEquals("   ", styleProperties.checkedColor)
        assertEquals("   ", styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties instances when used in list then equality works correctly for list operations`() {
        val stylePropertiesList = listOf(
            StyleProperties(checkedColor = "#FF0000"),
            StyleProperties(checkedColor = "#00FF00"),
            StyleProperties(checkedColor = "#FF0000")
        )

        assert(stylePropertiesList.contains(StyleProperties(checkedColor = "#FF0000")))
        assertEquals(2, stylePropertiesList.filter { it.checkedColor == "#FF0000" }.size)
    }

    @Test
    fun `given StyleProperties when calling equals with null then returns false`() {
        val styleProperties = StyleProperties(checkedColor = "#FF0000")

        val result = styleProperties.equals(null)

        assertEquals(false, result)
    }

    @Test
    fun `given StyleProperties with unicode color representation when constructing then preserves unicode`() {
        val unicodeColor = "#️⃣FF0000"

        val styleProperties = StyleProperties(checkedColor = unicodeColor)

        assertEquals(unicodeColor, styleProperties.checkedColor)
    }
}