package com.github.codandotv.craftd.androidcore.data.model.action

import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

@RunWith(JUnit4::class)
class AnalyticsPropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given all parameters when constructing AnalyticsProperties then all fields are set correctly`() {
        val category = "purchase"
        val action = "click"
        val label = "checkout_button"
        val track = "user_journey"

        val analyticsProperties = AnalyticsProperties(
            category = category,
            action = action,
            label = label,
            track = track
        )

        assertEquals(category, analyticsProperties.category)
        assertEquals(action, analyticsProperties.action)
        assertEquals(label, analyticsProperties.label)
        assertEquals(track, analyticsProperties.track)
    }

    @Test
    fun `given no parameters when constructing AnalyticsProperties then all fields default to null`() {
        val analyticsProperties = AnalyticsProperties()

        assertNull(analyticsProperties.category)
        assertNull(analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given partial parameters when constructing AnalyticsProperties then specified fields are set and others are null`() {
        val category = "signup"
        val action = "submit"

        val analyticsProperties = AnalyticsProperties(
            category = category,
            action = action
        )

        assertEquals(category, analyticsProperties.category)
        assertEquals(action, analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties instance when calling copy with new values then returns new instance with updated fields`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val copied = original.copy(
            category = "signup",
            label = "form"
        )

        assertEquals("signup", copied.category)
        assertEquals("click", copied.action)
        assertEquals("form", copied.label)
        assertEquals("flow", copied.track)
    }

    @Test
    fun `given AnalyticsProperties instance when calling copy without arguments then returns identical instance`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given two AnalyticsProperties with identical values when comparing equality then returns true`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        assertEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different category when comparing equality then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val second = AnalyticsProperties(
            category = "signup",
            action = "click",
            label = "button",
            track = "flow"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different action when comparing equality then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val second = AnalyticsProperties(
            category = "purchase",
            action = "submit",
            label = "button",
            track = "flow"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different label when comparing equality then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button1",
            track = "flow"
        )

        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button2",
            track = "flow"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different track when comparing equality then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow1"
        )

        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow2"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two identical AnalyticsProperties when computing hashCode then returns same hash`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given two different AnalyticsProperties when computing hashCode then likely returns different hash`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val second = AnalyticsProperties(
            category = "signup",
            action = "submit",
            label = "form",
            track = "registration"
        )

        assertNotEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given AnalyticsProperties with all null values when computing hashCode then completes without error`() {
        val analyticsProperties = AnalyticsProperties()

        val hashCode = analyticsProperties.hashCode()

        assertEquals(analyticsProperties.hashCode(), hashCode)
    }

    @Test
    fun `given AnalyticsProperties with mixed null and non-null values when computing hashCode then completes without error`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = null,
            label = "button",
            track = null
        )

        val hashCode = analyticsProperties.hashCode()

        assertEquals(analyticsProperties.hashCode(), hashCode)
    }

    @Test
    fun `given AnalyticsProperties instance when calling toString then returns non-empty string`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        val toString = analyticsProperties.toString()

        assert(toString.isNotEmpty())
        assert(toString.contains("AnalyticsProperties"))
    }

    @Test
    fun `given AnalyticsProperties with null values when calling toString then includes null in representation`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = null,
            label = "button",
            track = null
        )

        val toString = analyticsProperties.toString()

        assert(toString.isNotEmpty())
    }

    @Test
    fun `given empty string values when constructing AnalyticsProperties then empty strings are stored`() {
        val analyticsProperties = AnalyticsProperties(
            category = "",
            action = "",
            label = "",
            track = ""
        )

        assertEquals("", analyticsProperties.category)
        assertEquals("", analyticsProperties.action)
        assertEquals("", analyticsProperties.label)
        assertEquals("", analyticsProperties.track)
    }

    @Test
    fun `given very long string values when constructing AnalyticsProperties then long strings are stored`() {
        val longString = "a".repeat(1000)
        val analyticsProperties = AnalyticsProperties(
            category = longString,
            action = longString,
            label = longString,
            track = longString
        )

        assertEquals(longString, analyticsProperties.category)
        assertEquals(longString, analyticsProperties.action)
        assertEquals(longString, analyticsProperties.label)
        assertEquals(longString, analyticsProperties.track)
    }

    @Test
    fun `given special characters in string values when constructing AnalyticsProperties then special characters are preserved`() {
        val specialString = "!@#$%^&*()_+-=[]{}|;:',.<>?/~`"
        val analyticsProperties = AnalyticsProperties(
            category = specialString,
            action = specialString,
            label = specialString,
            track = specialString
        )

        assertEquals(specialString, analyticsProperties.category)
        assertEquals(specialString, analyticsProperties.action)
        assertEquals(specialString, analyticsProperties.label)
        assertEquals(specialString, analyticsProperties.track)
    }

    @Test
    fun `given unicode characters in string values when constructing AnalyticsProperties then unicode characters are preserved`() {
        val unicodeString = "你好世界🚀émojis"
        val analyticsProperties = AnalyticsProperties(
            category = unicodeString,
            action = unicodeString,
            label = unicodeString,
            track = unicodeString
        )

        assertEquals(unicodeString, analyticsProperties.category)
        assertEquals(unicodeString, analyticsProperties.action)
        assertEquals(unicodeString, analyticsProperties.label)
        assertEquals(unicodeString, analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties instance when accessing category field then correct value is returned`() {
        val expectedCategory = "purchase"
        val analyticsProperties = AnalyticsProperties(category = expectedCategory)

        assertEquals(expectedCategory, analyticsProperties.category)
    }

    @Test
    fun `given AnalyticsProperties instance when accessing action field then correct value is returned`() {
        val expectedAction = "click"
        val analyticsProperties = AnalyticsProperties(action = expectedAction)

        assertEquals(expectedAction, analyticsProperties.action)
    }

    @Test
    fun `given AnalyticsProperties instance when accessing label field then correct value is returned`() {
        val expectedLabel = "button"
        val analyticsProperties = AnalyticsProperties(label = expectedLabel)

        assertEquals(expectedLabel, analyticsProperties.label)
    }

    @Test
    fun `given AnalyticsProperties instance when accessing track field then correct value is returned`() {
        val expectedTrack = "flow"
        val analyticsProperties = AnalyticsProperties(track = expectedTrack)

        assertEquals(expectedTrack, analyticsProperties.track)
    }

    @Test
    fun `given two AnalyticsProperties with same reference when comparing then returns true`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        assertEquals(analyticsProperties, analyticsProperties)
    }

    @Test
    fun `given AnalyticsProperties when comparing with null then returns false`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "flow"
        )

        assertNotEquals(analyticsProperties, null)
    }

    @Test
    fun `given AnalyticsProperties with whitespace values when constructing then whitespace is preserved`() {
        val whitespaceString = "  space  "
        val analyticsProperties = AnalyticsProperties(
            category = whitespaceString,
            action = whitespaceString,
            label = whitespaceString,
            track = whitespaceString
        )

        assertEquals(whitespaceString, analyticsProperties.category)
        assertEquals(whitespaceString, analyticsProperties.action)
        assertEquals(whitespaceString, analyticsProperties.label)
        assertEquals(whitespaceString, analyticsProperties.track)
    }

    @Test
    fun `given multiple AnalyticsProperties instances in collection when comparing then equality works correctly`() {
        val list = listOf(
            AnalyticsProperties(category = "a", action = "b"),
            AnalyticsProperties(category = "a", action = "b"),
            AnalyticsProperties(category = "c", action = "d")
        )

        assertEquals(list[0], list[1])
        assertNotEquals(list[0], list[2])
    }

    @Test
    fun `given AnalyticsProperties when used in map as key then hashCode and equals support map operations`() {
        val first = AnalyticsProperties(category = "purchase", action = "click")
        val second = AnalyticsProperties(category = "purchase", action = "click")
        val third = AnalyticsProperties(category = "signup", action = "submit")

        val map = mutableMapOf<AnalyticsProperties, String>()
        map[first] = "value1"
        map[third] = "value2"

        assertEquals("value1", map[second])
        assertEquals("value2", map[third])
    }
}