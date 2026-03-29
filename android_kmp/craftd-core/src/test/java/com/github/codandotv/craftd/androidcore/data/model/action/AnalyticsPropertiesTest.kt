package com.github.codandotv.craftd.androidcore.data.model.action

import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class AnalyticsPropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given all null parameters when constructing AnalyticsProperties then all fields are null`() {
        val analyticsProperties = AnalyticsProperties()

        assertNull(analyticsProperties.category)
        assertNull(analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given all parameters when constructing AnalyticsProperties then all fields are set correctly`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_main",
            track = "event_123"
        )

        assertEquals("purchase", analyticsProperties.category)
        assertEquals("click", analyticsProperties.action)
        assertEquals("button_main", analyticsProperties.label)
        assertEquals("event_123", analyticsProperties.track)
    }

    @Test
    fun `given partial parameters when constructing AnalyticsProperties then specified fields are set and others are null`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = "click"
        )

        assertEquals("purchase", analyticsProperties.category)
        assertEquals("click", analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties with category when copying with new action then new instance has updated action and same category`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )

        val copied = original.copy(action = "submit")

        assertEquals("purchase", copied.category)
        assertEquals("submit", copied.action)
        assertEquals("button", copied.label)
        assertEquals("event_1", copied.track)
    }

    @Test
    fun `given AnalyticsProperties when copying without parameters then returns equivalent instance`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )

        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given two AnalyticsProperties with same values when comparing equals then returns true`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )

        assertEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different category when comparing equals then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "view",
            action = "click",
            label = "button",
            track = "event_1"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different action when comparing equals then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "purchase",
            action = "submit",
            label = "button",
            track = "event_1"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different label when comparing equals then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "link",
            track = "event_1"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with different track when comparing equals then returns false`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_2"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with all null values when comparing equals then returns true`() {
        val first = AnalyticsProperties()
        val second = AnalyticsProperties()

        assertEquals(first, second)
    }

    @Test
    fun `given two AnalyticsProperties with same values when comparing hashCode then returns same hash`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )

        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given two AnalyticsProperties with different values when comparing hashCode then likely returns different hash`() {
        val first = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "view",
            action = "scroll",
            label = "page",
            track = "event_2"
        )

        assertNotEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given AnalyticsProperties when calling toString then returns string representation`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )

        val result = analyticsProperties.toString()

        assertTrue(result.contains("category"))
        assertTrue(result.contains("purchase"))
        assertTrue(result.contains("action"))
        assertTrue(result.contains("click"))
        assertTrue(result.contains("label"))
        assertTrue(result.contains("button"))
        assertTrue(result.contains("track"))
        assertTrue(result.contains("event_1"))
    }

    @Test
    fun `given AnalyticsProperties with null category when calling toString then includes null category`() {
        val analyticsProperties = AnalyticsProperties(
            category = null,
            action = "click",
            label = "button",
            track = "event_1"
        )

        val result = analyticsProperties.toString()

        assertTrue(result.contains("category"))
        assertTrue(result.contains("null") || result.contains("null"))
    }

    @Test
    fun `given AnalyticsProperties with empty string values when constructing then all empty strings are set`() {
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
    fun `given AnalyticsProperties with mixed null and empty string when comparing equals then returns false`() {
        val first = AnalyticsProperties(
            category = null,
            action = "click",
            label = "button",
            track = "event_1"
        )
        val second = AnalyticsProperties(
            category = "",
            action = "click",
            label = "button",
            track = "event_1"
        )

        assertNotEquals(first, second)
    }

    @Test
    fun `given AnalyticsProperties when copying all fields then returns new instance with same values`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )

        val copied = original.copy(
            category = original.category,
            action = original.action,
            label = original.label,
            track = original.track
        )

        assertEquals(original, copied)
        assertTrue(original === original)
        assertFalse(original === copied)
    }

    @Test
    fun `given AnalyticsProperties with special characters when constructing then special characters are preserved`() {
        val analyticsProperties = AnalyticsProperties(
            category = "purchase_event@2024",
            action = "click-submit",
            label = "btn[main]",
            track = "event#123"
        )

        assertEquals("purchase_event@2024", analyticsProperties.category)
        assertEquals("click-submit", analyticsProperties.action)
        assertEquals("btn[main]", analyticsProperties.label)
        assertEquals("event#123", analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties with unicode characters when constructing then unicode characters are preserved`() {
        val analyticsProperties = AnalyticsProperties(
            category = "购买",
            action = "点击",
            label = "按钮",
            track = "事件"
        )

        assertEquals("购买", analyticsProperties.category)
        assertEquals("点击", analyticsProperties.action)
        assertEquals("按钮", analyticsProperties.label)
        assertEquals("事件", analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties when copying with null values then all fields become null`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button",
            track = "event_1"
        )

        val copied = original.copy(
            category = null,
            action = null,
            label = null,
            track = null
        )

        assertNull(copied.category)
        assertNull(copied.action)
        assertNull(copied.label)
        assertNull(copied.track)
    }

    @Test
    fun `given AnalyticsProperties with long strings when constructing then long strings are handled`() {
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
    fun `given AnalyticsProperties with whitespace when constructing then whitespace is preserved`() {
        val analyticsProperties = AnalyticsProperties(
            category = "  purchase  ",
            action = "  click  ",
            label = "  button  ",
            track = "  event  "
        )

        assertEquals("  purchase  ", analyticsProperties.category)
        assertEquals("  click  ", analyticsProperties.action)
        assertEquals("  button  ", analyticsProperties.label)
        assertEquals("  event  ", analyticsProperties.track)
    }

    @Test
    fun `given multiple AnalyticsProperties instances in collection when comparing then equals works correctly`() {
        val properties = listOf(
            AnalyticsProperties(category = "purchase", action = "click"),
            AnalyticsProperties(category = "view", action = "scroll"),
            AnalyticsProperties(category = "purchase", action = "click")
        )

        assertEquals(properties[0], properties[2])
        assertNotEquals(properties[0], properties[1])
    }
}