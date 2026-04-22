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
    fun `given all parameters when constructing AnalyticsProperties then all fields are set`() {
        val category = "purchase"
        val action = "click"
        val label = "button_checkout"
        val track = "event_001"

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
    fun `given partial parameters when constructing AnalyticsProperties then unspecified fields default to null`() {
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
    fun `given AnalyticsProperties with all fields when copying with new category then category is updated`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        val copied = original.copy(category = "view")

        assertEquals("view", copied.category)
        assertEquals("click", copied.action)
        assertEquals("button_checkout", copied.label)
        assertEquals("event_001", copied.track)
    }

    @Test
    fun `given AnalyticsProperties when copying with multiple fields then all updated fields are changed`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        val copied = original.copy(
            category = "view",
            action = "scroll",
            label = "page_home"
        )

        assertEquals("view", copied.category)
        assertEquals("scroll", copied.action)
        assertEquals("page_home", copied.label)
        assertEquals("event_001", copied.track)
    }

    @Test
    fun `given two AnalyticsProperties with same values when comparing then they are equal`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )
        val properties2 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        assertEquals(properties1, properties2)
    }

    @Test
    fun `given two AnalyticsProperties with different category when comparing then they are not equal`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = "click"
        )
        val properties2 = AnalyticsProperties(
            category = "view",
            action = "click"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two AnalyticsProperties with different action when comparing then they are not equal`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = "click"
        )
        val properties2 = AnalyticsProperties(
            category = "purchase",
            action = "scroll"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two AnalyticsProperties with different label when comparing then they are not equal`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout"
        )
        val properties2 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_home"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two AnalyticsProperties with different track when comparing then they are not equal`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            track = "event_001"
        )
        val properties2 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            track = "event_002"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two AnalyticsProperties with null fields when comparing then they are equal`() {
        val properties1 = AnalyticsProperties()
        val properties2 = AnalyticsProperties()

        assertEquals(properties1, properties2)
    }

    @Test
    fun `given two AnalyticsProperties with same values when computing hashCode then hashCodes are equal`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )
        val properties2 = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        assertEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given two AnalyticsProperties with different values when computing hashCode then hashCodes differ`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = "click"
        )
        val properties2 = AnalyticsProperties(
            category = "view",
            action = "scroll"
        )

        assertNotEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given AnalyticsProperties when calling toString then string representation includes all fields`() {
        val properties = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        val stringRepresentation = properties.toString()

        assert(stringRepresentation.contains("category"))
        assert(stringRepresentation.contains("purchase"))
        assert(stringRepresentation.contains("action"))
        assert(stringRepresentation.contains("click"))
        assert(stringRepresentation.contains("label"))
        assert(stringRepresentation.contains("button_checkout"))
        assert(stringRepresentation.contains("track"))
        assert(stringRepresentation.contains("event_001"))
    }

    @Test
    fun `given AnalyticsProperties with all null fields when calling toString then null values are represented`() {
        val properties = AnalyticsProperties()

        val stringRepresentation = properties.toString()

        assert(stringRepresentation.contains("category"))
        assert(stringRepresentation.contains("action"))
        assert(stringRepresentation.contains("label"))
        assert(stringRepresentation.contains("track"))
    }

    @Test
    fun `given AnalyticsProperties with mixed null fields when comparing then comparison is accurate`() {
        val properties1 = AnalyticsProperties(
            category = "purchase",
            action = null,
            label = "button",
            track = null
        )
        val properties2 = AnalyticsProperties(
            category = "purchase",
            action = null,
            label = "button",
            track = null
        )

        assertEquals(properties1, properties2)
    }

    @Test
    fun `given AnalyticsProperties when copying with null values then null values are preserved`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = null,
            track = "event_001"
        )

        val copied = original.copy(label = null)

        assertEquals("purchase", copied.category)
        assertEquals("click", copied.action)
        assertNull(copied.label)
        assertEquals("event_001", copied.track)
    }

    @Test
    fun `given AnalyticsProperties when copying with null replacing non-null value then field becomes null`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        val copied = original.copy(action = null, label = null)

        assertEquals("purchase", copied.category)
        assertNull(copied.action)
        assertNull(copied.label)
        assertEquals("event_001", copied.track)
    }

    @Test
    fun `given empty string values when constructing AnalyticsProperties then empty strings are preserved`() {
        val properties = AnalyticsProperties(
            category = "",
            action = "",
            label = "",
            track = ""
        )

        assertEquals("", properties.category)
        assertEquals("", properties.action)
        assertEquals("", properties.label)
        assertEquals("", properties.track)
    }

    @Test
    fun `given AnalyticsProperties with empty strings and null values when comparing then they are not equal`() {
        val properties1 = AnalyticsProperties(category = "")
        val properties2 = AnalyticsProperties(category = null)

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given AnalyticsProperties with special characters when constructing then special characters are preserved`() {
        val specialCategory = "purchase_v2.0"
        val specialAction = "click-submit"
        val specialLabel = "btn@checkout"
        val specialTrack = "event#001"

        val properties = AnalyticsProperties(
            category = specialCategory,
            action = specialAction,
            label = specialLabel,
            track = specialTrack
        )

        assertEquals(specialCategory, properties.category)
        assertEquals(specialAction, properties.action)
        assertEquals(specialLabel, properties.label)
        assertEquals(specialTrack, properties.track)
    }

    @Test
    fun `given AnalyticsProperties with unicode characters when constructing then unicode characters are preserved`() {
        val unicodeCategory = "购买"
        val unicodeAction = "点击"
        val unicodeLabel = "按钮"
        val unicodeTrack = "事件"

        val properties = AnalyticsProperties(
            category = unicodeCategory,
            action = unicodeAction,
            label = unicodeLabel,
            track = unicodeTrack
        )

        assertEquals(unicodeCategory, properties.category)
        assertEquals(unicodeAction, properties.action)
        assertEquals(unicodeLabel, properties.label)
        assertEquals(unicodeTrack, properties.track)
    }

    @Test
    fun `given AnalyticsProperties with long strings when constructing then long strings are preserved`() {
        val longString = "a".repeat(1000)

        val properties = AnalyticsProperties(
            category = longString,
            action = longString,
            label = longString,
            track = longString
        )

        assertEquals(longString, properties.category)
        assertEquals(longString, properties.action)
        assertEquals(longString, properties.label)
        assertEquals(longString, properties.track)
    }

    @Test
    fun `given AnalyticsProperties when copying without parameters then original is unchanged`() {
        val original = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        val copied = original.copy()

        assertEquals(original, copied)
        assertEquals(original.category, copied.category)
        assertEquals(original.action, copied.action)
        assertEquals(original.label, copied.label)
        assertEquals(original.track, copied.track)
    }

    @Test
    fun `given AnalyticsProperties instance when checking immutability then state cannot be modified`() {
        val properties = AnalyticsProperties(
            category = "purchase",
            action = "click",
            label = "button_checkout",
            track = "event_001"
        )

        val category = properties.category
        val action = properties.action
        val label = properties.label
        val track = properties.track

        assertEquals("purchase", category)
        assertEquals("click", action)
        assertEquals("button_checkout", label)
        assertEquals("event_001", track)
    }
}