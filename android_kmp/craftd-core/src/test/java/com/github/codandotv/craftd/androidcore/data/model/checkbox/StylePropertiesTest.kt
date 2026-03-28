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
    fun `given default constructor when creating StyleProperties then all fields are null`() {
        val styleProperties = StyleProperties()

        assertNull(styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given checkedColor parameter when creating StyleProperties then checkedColor is set`() {
        val checkedColor = "#FF0000"
        val styleProperties = StyleProperties(checkedColor = checkedColor)

        assertEquals(checkedColor, styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given uncheckedColor parameter when creating StyleProperties then uncheckedColor is set`() {
        val uncheckedColor = "#00FF00"
        val styleProperties = StyleProperties(uncheckedColor = uncheckedColor)

        assertNull(styleProperties.checkedColor)
        assertEquals(uncheckedColor, styleProperties.uncheckedColor)
    }

    @Test
    fun `given both parameters when creating StyleProperties then both fields are set`() {
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
    fun `given StyleProperties when calling copy with new checkedColor then returns new instance with updated checkedColor`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val newColor = "#0000FF"

        val copy = original.copy(checkedColor = newColor)

        assertEquals(newColor, copy.checkedColor)
        assertEquals(original.uncheckedColor, copy.uncheckedColor)
        assertNotEquals(original, copy)
    }

    @Test
    fun `given StyleProperties when calling copy with new uncheckedColor then returns new instance with updated uncheckedColor`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val newColor = "#0000FF"

        val copy = original.copy(uncheckedColor = newColor)

        assertEquals(original.checkedColor, copy.checkedColor)
        assertEquals(newColor, copy.uncheckedColor)
        assertNotEquals(original, copy)
    }

    @Test
    fun `given StyleProperties when calling copy without parameters then returns identical instance`() {
        val original = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val copy = original.copy()

        assertEquals(original, copy)
    }

    @Test
    fun `given same StyleProperties when comparing with equals then returns true`() {
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
    fun `given different StyleProperties checkedColor when comparing with equals then returns false`() {
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
    fun `given different StyleProperties uncheckedColor when comparing with equals then returns false`() {
        val first = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val second = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#0000FF"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given StyleProperties with all null when comparing with equals then returns true`() {
        val first = StyleProperties()
        val second = StyleProperties()

        assertEquals(first, second)
    }

    @Test
    fun `given same StyleProperties when calling hashCode then returns same hash`() {
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
    fun `given different StyleProperties when calling hashCode then may return different hash`() {
        val first = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val second = StyleProperties(
            checkedColor = "#0000FF",
            uncheckedColor = "#00FF00"
        )

        assertNotEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given StyleProperties when calling toString then contains class name and field values`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        val result = styleProperties.toString()

        assert(result.contains("StyleProperties"))
        assert(result.contains("#FF0000"))
        assert(result.contains("#00FF00"))
    }

    @Test
    fun `given null checkedColor when creating StyleProperties then checkedColor remains null`() {
        val styleProperties = StyleProperties(
            checkedColor = null,
            uncheckedColor = "#00FF00"
        )

        assertNull(styleProperties.checkedColor)
        assertEquals("#00FF00", styleProperties.uncheckedColor)
    }

    @Test
    fun `given null uncheckedColor when creating StyleProperties then uncheckedColor remains null`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = null
        )

        assertEquals("#FF0000", styleProperties.checkedColor)
        assertNull(styleProperties.uncheckedColor)
    }

    @Test
    fun `given empty string checkedColor when creating StyleProperties then checkedColor is empty string`() {
        val styleProperties = StyleProperties(
            checkedColor = "",
            uncheckedColor = "#00FF00"
        )

        assertEquals("", styleProperties.checkedColor)
        assertEquals("#00FF00", styleProperties.uncheckedColor)
    }

    @Test
    fun `given empty string uncheckedColor when creating StyleProperties then uncheckedColor is empty string`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = ""
        )

        assertEquals("#FF0000", styleProperties.checkedColor)
        assertEquals("", styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties when destructuring then values are correctly assigned`() {
        val (checkedColor, uncheckedColor) = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertEquals("#FF0000", checkedColor)
        assertEquals("#00FF00", uncheckedColor)
    }

    @Test
    fun `given StyleProperties in collection when using equals then collection operations work correctly`() {
        val styleProperties = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )
        val list = listOf(styleProperties)

        assert(list.contains(
            StyleProperties(
                checkedColor = "#FF0000",
                uncheckedColor = "#00FF00"
            )
        ))
    }

    @Test
    fun `given StyleProperties with special characters in colors when creating instance then values are preserved`() {
        val styleProperties = StyleProperties(
            checkedColor = "#ABC123DEF",
            uncheckedColor = "#XYZ789"
        )

        assertEquals("#ABC123DEF", styleProperties.checkedColor)
        assertEquals("#XYZ789", styleProperties.uncheckedColor)
    }

    @Test
    fun `given StyleProperties when comparing null checkedColor with non-null then not equal`() {
        val first = StyleProperties(
            checkedColor = null,
            uncheckedColor = "#00FF00"
        )
        val second = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given StyleProperties when comparing null uncheckedColor with non-null then not equal`() {
        val first = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = null
        )
        val second = StyleProperties(
            checkedColor = "#FF0000",
            uncheckedColor = "#00FF00"
        )

        assertNotEquals(first, second)
    }
}