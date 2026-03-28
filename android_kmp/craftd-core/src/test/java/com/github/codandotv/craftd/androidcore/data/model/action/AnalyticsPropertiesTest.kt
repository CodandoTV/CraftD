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
    fun `given all parameters when creating AnalyticsProperties then all fields are set correctly`() {
        val category = "test_category"
        val action = "test_action"
        val label = "test_label"
        val track = "test_track"

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
    fun `given no parameters when creating AnalyticsProperties then all fields are null`() {
        val analyticsProperties = AnalyticsProperties()

        assertNull(analyticsProperties.category)
        assertNull(analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given partial parameters when creating AnalyticsProperties then only specified fields are set`() {
        val category = "test_category"
        val track = "test_track"

        val analyticsProperties = AnalyticsProperties(
            category = category,
            track = track
        )

        assertEquals(category, analyticsProperties.category)
        assertNull(analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertEquals(track, analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties when calling copy with new category then new instance has updated category`() {
        val original = AnalyticsProperties(
            category = "original_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val copied = original.copy(category = "new_category")

        assertEquals("new_category", copied.category)
        assertEquals("test_action", copied.action)
        assertEquals("test_label", copied.label)
        assertEquals("test_track", copied.track)
        assertNotEquals(original.category, copied.category)
    }

    @Test
    fun `given AnalyticsProperties when calling copy with new action then new instance has updated action`() {
        val original = AnalyticsProperties(
            category = "test_category",
            action = "original_action",
            label = "test_label",
            track = "test_track"
        )

        val copied = original.copy(action = "new_action")

        assertEquals("test_category", copied.category)
        assertEquals("new_action", copied.action)
        assertEquals("test_label", copied.label)
        assertEquals("test_track", copied.track)
    }

    @Test
    fun `given AnalyticsProperties when calling copy with new label then new instance has updated label`() {
        val original = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "original_label",
            track = "test_track"
        )

        val copied = original.copy(label = "new_label")

        assertEquals("test_category", copied.category)
        assertEquals("test_action", copied.action)
        assertEquals("new_label", copied.label)
        assertEquals("test_track", copied.track)
    }

    @Test
    fun `given AnalyticsProperties when calling copy with new track then new instance has updated track`() {
        val original = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "original_track"
        )

        val copied = original.copy(track = "new_track")

        assertEquals("test_category", copied.category)
        assertEquals("test_action", copied.action)
        assertEquals("test_label", copied.label)
        assertEquals("new_track", copied.track)
    }

    @Test
    fun `given AnalyticsProperties when calling copy with all new values then new instance has all updated fields`() {
        val original = AnalyticsProperties(
            category = "original_category",
            action = "original_action",
            label = "original_label",
            track = "original_track"
        )

        val copied = original.copy(
            category = "new_category",
            action = "new_action",
            label = "new_label",
            track = "new_track"
        )

        assertEquals("new_category", copied.category)
        assertEquals("new_action", copied.action)
        assertEquals("new_label", copied.label)
        assertEquals("new_track", copied.track)
    }

    @Test
    fun `given identical AnalyticsProperties instances when comparing then equals returns true`() {
        val properties1 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        assertEquals(properties1, properties2)
    }

    @Test
    fun `given AnalyticsProperties with different category when comparing then equals returns false`() {
        val properties1 = AnalyticsProperties(
            category = "category1",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "category2",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given AnalyticsProperties with different action when comparing then equals returns false`() {
        val properties1 = AnalyticsProperties(
            category = "test_category",
            action = "action1",
            label = "test_label",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "test_category",
            action = "action2",
            label = "test_label",
            track = "test_track"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given AnalyticsProperties with different label when comparing then equals returns false`() {
        val properties1 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "label1",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "label2",
            track = "test_track"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given AnalyticsProperties with different track when comparing then equals returns false`() {
        val properties1 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "track1"
        )

        val properties2 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "track2"
        )

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given identical AnalyticsProperties instances when calling hashCode then hash codes are equal`() {
        val properties1 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        assertEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given AnalyticsProperties with different values when calling hashCode then hash codes may differ`() {
        val properties1 = AnalyticsProperties(
            category = "category1",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "category2",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        assertNotEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given AnalyticsProperties with null values when calling hashCode then hash code is computed`() {
        val properties = AnalyticsProperties()

        val hashCode = properties.hashCode()

        assertEquals(0, hashCode)
    }

    @Test
    fun `given AnalyticsProperties when calling equals with same instance then returns true`() {
        val properties = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        assertEquals(properties, properties)
    }

    @Test
    fun `given AnalyticsProperties when calling equals with null then returns false`() {
        val properties = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        assertNotEquals(properties, null)
    }

    @Test
    fun `given AnalyticsProperties when calling equals with different type then returns false`() {
        val properties = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        assertNotEquals(properties, "not_analytics_properties")
    }

    @Test
    fun `given multiple AnalyticsProperties in a set when they are equal then set contains only one instance`() {
        val properties1 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val set = setOf(properties1, properties2)

        assertEquals(1, set.size)
    }

    @Test
    fun `given multiple AnalyticsProperties in a set when they are different then set contains all instances`() {
        val properties1 = AnalyticsProperties(
            category = "category1",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val properties2 = AnalyticsProperties(
            category = "category2",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val set = setOf(properties1, properties2)

        assertEquals(2, set.size)
    }

    @Test
    fun `given AnalyticsProperties when calling toString then string representation is generated`() {
        val properties = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val stringRepresentation = properties.toString()

        assert(stringRepresentation.contains("category"))
        assert(stringRepresentation.contains("action"))
        assert(stringRepresentation.contains("label"))
        assert(stringRepresentation.contains("track"))
    }

    @Test
    fun `given AnalyticsProperties with null fields when calling toString then string representation is generated`() {
        val properties = AnalyticsProperties()

        val stringRepresentation = properties.toString()

        assert(stringRepresentation.isNotEmpty())
    }

    @Test
    fun `given AnalyticsProperties when destructuring then all fields are accessible`() {
        val properties = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val (category, action, label, track) = properties

        assertEquals("test_category", category)
        assertEquals("test_action", action)
        assertEquals("test_label", label)
        assertEquals("test_track", track)
    }

    @Test
    fun `given AnalyticsProperties with only category when constructing then other fields have default null values`() {
        val analyticsProperties = AnalyticsProperties(category = "test_category")

        assertEquals("test_category", analyticsProperties.category)
        assertNull(analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties with only action when constructing then other fields have default null values`() {
        val analyticsProperties = AnalyticsProperties(action = "test_action")

        assertNull(analyticsProperties.category)
        assertEquals("test_action", analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties with only label when constructing then other fields have default null values`() {
        val analyticsProperties = AnalyticsProperties(label = "test_label")

        assertNull(analyticsProperties.category)
        assertNull(analyticsProperties.action)
        assertEquals("test_label", analyticsProperties.label)
        assertNull(analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties with only track when constructing then other fields have default null values`() {
        val analyticsProperties = AnalyticsProperties(track = "test_track")

        assertNull(analyticsProperties.category)
        assertNull(analyticsProperties.action)
        assertNull(analyticsProperties.label)
        assertEquals("test_track", analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties with empty string values when constructing then fields are set to empty strings`() {
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
    fun `given AnalyticsProperties with special characters when constructing then fields are set correctly`() {
        val analyticsProperties = AnalyticsProperties(
            category = "test@category#123",
            action = "test.action-456",
            label = "test label with spaces",
            track = "test/track\\with\\slashes"
        )

        assertEquals("test@category#123", analyticsProperties.category)
        assertEquals("test.action-456", analyticsProperties.action)
        assertEquals("test label with spaces", analyticsProperties.label)
        assertEquals("test/track\\with\\slashes", analyticsProperties.track)
    }

    @Test
    fun `given AnalyticsProperties when copying without parameters then original is returned as new instance`() {
        val original = AnalyticsProperties(
            category = "test_category",
            action = "test_action",
            label = "test_label",
            track = "test_track"
        )

        val copied = original.copy()

        assertEquals(original, copied)
        assertNotEquals(original, copied)
    }
}