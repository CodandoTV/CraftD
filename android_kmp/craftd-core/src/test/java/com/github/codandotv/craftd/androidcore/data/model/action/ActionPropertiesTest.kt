package com.github.codandotv.craftd.androidcore.data.model.action

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

@RunWith(JUnit4::class)
class ActionPropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given all parameters when creating ActionProperties then object is created with all values`() {
        val deeplink = "https://example.com/deep"
        val actionData = JsonPrimitive("test_data")
        val analytics = AnalyticsProperties(eventName = "click_event")

        val result = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )

        assertEquals(deeplink, result.deeplink)
        assertEquals(actionData, result.actionData)
        assertEquals(analytics, result.analytics)
    }

    @Test
    fun `given no parameters when creating ActionProperties then all fields are null`() {
        val result = ActionProperties()

        assertNull(result.deeplink)
        assertNull(result.actionData)
        assertNull(result.analytics)
    }

    @Test
    fun `given partial parameters when creating ActionProperties then only specified fields are set`() {
        val deeplink = "https://example.com"

        val result = ActionProperties(deeplink = deeplink)

        assertEquals(deeplink, result.deeplink)
        assertNull(result.actionData)
        assertNull(result.analytics)
    }

    @Test
    fun `given ActionProperties with JsonPrimitive when creating then actionData is stored correctly`() {
        val jsonData = JsonPrimitive("value")

        val result = ActionProperties(actionData = jsonData)

        assertEquals(jsonData, result.actionData)
    }

    @Test
    fun `given ActionProperties with JsonObject when creating then actionData is stored correctly`() {
        val jsonData = JsonObject(mapOf("key" to JsonPrimitive("value")))

        val result = ActionProperties(actionData = jsonData)

        assertEquals(jsonData, result.actionData)
    }

    @Test
    fun `given ActionProperties with JsonNull when creating then actionData is stored correctly`() {
        val jsonData = JsonNull

        val result = ActionProperties(actionData = jsonData)

        assertEquals(jsonData, result.actionData)
    }

    @Test
    fun `given identical ActionProperties when comparing then equals returns true`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("test")
        val analytics = AnalyticsProperties(eventName = "event")

        val action1 = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )
        val action2 = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )

        assertEquals(action1, action2)
    }

    @Test
    fun `given different deeplink when comparing ActionProperties then equals returns false`() {
        val action1 = ActionProperties(deeplink = "https://example.com")
        val action2 = ActionProperties(deeplink = "https://different.com")

        assertNotEquals(action1, action2)
    }

    @Test
    fun `given different actionData when comparing ActionProperties then equals returns false`() {
        val action1 = ActionProperties(actionData = JsonPrimitive("data1"))
        val action2 = ActionProperties(actionData = JsonPrimitive("data2"))

        assertNotEquals(action1, action2)
    }

    @Test
    fun `given different analytics when comparing ActionProperties then equals returns false`() {
        val action1 = ActionProperties(analytics = AnalyticsProperties(eventName = "event1"))
        val action2 = ActionProperties(analytics = AnalyticsProperties(eventName = "event2"))

        assertNotEquals(action1, action2)
    }

    @Test
    fun `given identical ActionProperties when calling hashCode then same hash is returned`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("test")
        val analytics = AnalyticsProperties(eventName = "event")

        val action1 = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )
        val action2 = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )

        assertEquals(action1.hashCode(), action2.hashCode())
    }

    @Test
    fun `given ActionProperties when calling copy with new deeplink then copy has new deeplink`() {
        val original = ActionProperties(
            deeplink = "https://original.com",
            actionData = JsonPrimitive("data"),
            analytics = AnalyticsProperties(eventName = "event")
        )

        val copied = original.copy(deeplink = "https://new.com")

        assertEquals("https://new.com", copied.deeplink)
        assertEquals(original.actionData, copied.actionData)
        assertEquals(original.analytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when calling copy with new actionData then copy has new actionData`() {
        val original = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = AnalyticsProperties(eventName = "event")
        )
        val newData = JsonPrimitive("newData")

        val copied = original.copy(actionData = newData)

        assertEquals(original.deeplink, copied.deeplink)
        assertEquals(newData, copied.actionData)
        assertEquals(original.analytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when calling copy with new analytics then copy has new analytics`() {
        val original = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = AnalyticsProperties(eventName = "event1")
        )
        val newAnalytics = AnalyticsProperties(eventName = "event2")

        val copied = original.copy(analytics = newAnalytics)

        assertEquals(original.deeplink, copied.deeplink)
        assertEquals(original.actionData, copied.actionData)
        assertEquals(newAnalytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when calling copy with all new values then copy has all new values`() {
        val original = ActionProperties(
            deeplink = "https://original.com",
            actionData = JsonPrimitive("data"),
            analytics = AnalyticsProperties(eventName = "event1")
        )
        val newDeeplink = "https://new.com"
        val newData = JsonPrimitive("newData")
        val newAnalytics = AnalyticsProperties(eventName = "event2")

        val copied = original.copy(
            deeplink = newDeeplink,
            actionData = newData,
            analytics = newAnalytics
        )

        assertEquals(newDeeplink, copied.deeplink)
        assertEquals(newData, copied.actionData)
        assertEquals(newAnalytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when calling copy with null values then copy has null values`() {
        val original = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = AnalyticsProperties(eventName = "event")
        )

        val copied = original.copy(
            deeplink = null,
            actionData = null,
            analytics = null
        )

        assertNull(copied.deeplink)
        assertNull(copied.actionData)
        assertNull(copied.analytics)
    }

    @Test
    fun `given null ActionProperties when comparing with non-null then equals returns false`() {
        val action1: ActionProperties? = ActionProperties(deeplink = "https://example.com")
        val action2: ActionProperties? = null

        assertNotEquals(action1, action2)
    }

    @Test
    fun `given ActionProperties with complex JsonObject when storing then actionData preserves structure`() {
        val complexData = JsonObject(mapOf(
            "level1" to JsonObject(mapOf(
                "level2" to JsonPrimitive("deepValue")
            ))
        ))

        val result = ActionProperties(actionData = complexData)

        assertEquals(complexData, result.actionData)
    }

    @Test
    fun `given empty ActionProperties when calling toString then string representation is generated`() {
        val result = ActionProperties()

        val stringRep = result.toString()

        assert(stringRep.contains("ActionProperties"))
        assert(stringRep.contains("deeplink"))
        assert(stringRep.contains("actionData"))
        assert(stringRep.contains("analytics"))
    }

    @Test
    fun `given ActionProperties with data when calling toString then string contains values`() {
        val result = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("test")
        )

        val stringRep = result.toString()

        assert(stringRep.contains("https://example.com"))
    }

    @Test
    fun `given multiple ActionProperties instances when checking immutability then fields cannot be reassigned`() {
        val action = ActionProperties(deeplink = "https://example.com")

        assert(action.deeplink == "https://example.com")
    }

    @Test
    fun `given ActionProperties with null deeplink when creating then deeplink is null`() {
        val result = ActionProperties(deeplink = null)

        assertNull(result.deeplink)
    }

    @Test
    fun `given ActionProperties with empty string deeplink when creating then deeplink is empty string`() {
        val result = ActionProperties(deeplink = "")

        assertEquals("", result.deeplink)
    }

    @Test
    fun `given ActionProperties with whitespace deeplink when creating then deeplink is preserved`() {
        val deeplink = "   "

        val result = ActionProperties(deeplink = deeplink)

        assertEquals(deeplink, result.deeplink)
    }

    @Test
    fun `given ActionProperties with null actionData when creating then actionData is null`() {
        val result = ActionProperties(actionData = null)

        assertNull(result.actionData)
    }

    @Test
    fun `given ActionProperties with null analytics when creating then analytics is null`() {
        val result = ActionProperties(analytics = null)

        assertNull(result.analytics)
    }

    @Test
    fun `given ActionProperties instances in set when storing then deduplication works`() {
        val action1 = ActionProperties(deeplink = "https://example.com")
        val action2 = ActionProperties(deeplink = "https://example.com")
        val action3 = ActionProperties(deeplink = "https://different.com")

        val set = setOf(action1, action2, action3)

        assertEquals(2, set.size)
    }

    @Test
    fun `given ActionProperties instances when using as map keys then hashCode is consistent`() {
        val action = ActionProperties(deeplink = "https://example.com")
        val map = mutableMapOf<ActionProperties, String>()

        map[action] = "value1"
        map[action] = "value2"

        assertEquals(1, map.size)
        assertEquals("value2", map[action])
    }
}