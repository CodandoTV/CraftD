package com.github.codandotv.craftd.androidcore.data.model.action

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ActionPropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given all parameters when constructing ActionProperties then all fields are set correctly`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("test_data")
        val analytics = AnalyticsProperties(eventName = "test_event")

        val action = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )

        assertEquals(deeplink, action.deeplink)
        assertEquals(actionData, action.actionData)
        assertEquals(analytics, action.analytics)
    }

    @Test
    fun `given only deeplink when constructing ActionProperties then other fields are null`() {
        val deeplink = "https://example.com"

        val action = ActionProperties(deeplink = deeplink)

        assertEquals(deeplink, action.deeplink)
        assertNull(action.actionData)
        assertNull(action.analytics)
    }

    @Test
    fun `given only actionData when constructing ActionProperties then other fields are null`() {
        val actionData = JsonPrimitive("test_data")

        val action = ActionProperties(actionData = actionData)

        assertNull(action.deeplink)
        assertEquals(actionData, action.actionData)
        assertNull(action.analytics)
    }

    @Test
    fun `given only analytics when constructing ActionProperties then other fields are null`() {
        val analytics = AnalyticsProperties(eventName = "test_event")

        val action = ActionProperties(analytics = analytics)

        assertNull(action.deeplink)
        assertNull(action.actionData)
        assertEquals(analytics, action.analytics)
    }

    @Test
    fun `given no parameters when constructing ActionProperties then all fields are null`() {
        val action = ActionProperties()

        assertNull(action.deeplink)
        assertNull(action.actionData)
        assertNull(action.analytics)
    }

    @Test
    fun `given ActionProperties with all fields when calling copy with no arguments then returns identical object`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("test_data")
        val analytics = AnalyticsProperties(eventName = "test_event")
        val action = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )

        val copied = action.copy()

        assertEquals(action, copied)
        assertEquals(action.deeplink, copied.deeplink)
        assertEquals(action.actionData, copied.actionData)
        assertEquals(action.analytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when calling copy with new deeplink then returns object with updated deeplink`() {
        val originalAction = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("test_data")
        )
        val newDeeplink = "https://newexample.com"

        val copied = originalAction.copy(deeplink = newDeeplink)

        assertEquals(newDeeplink, copied.deeplink)
        assertEquals(originalAction.actionData, copied.actionData)
    }

    @Test
    fun `given ActionProperties when calling copy with new actionData then returns object with updated actionData`() {
        val originalAction = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("test_data")
        )
        val newActionData = JsonPrimitive("new_data")

        val copied = originalAction.copy(actionData = newActionData)

        assertEquals(originalAction.deeplink, copied.deeplink)
        assertEquals(newActionData, copied.actionData)
    }

    @Test
    fun `given ActionProperties when calling copy with new analytics then returns object with updated analytics`() {
        val originalAction = ActionProperties(
            deeplink = "https://example.com",
            analytics = AnalyticsProperties(eventName = "old_event")
        )
        val newAnalytics = AnalyticsProperties(eventName = "new_event")

        val copied = originalAction.copy(analytics = newAnalytics)

        assertEquals(originalAction.deeplink, copied.deeplink)
        assertEquals(newAnalytics, copied.analytics)
    }

    @Test
    fun `given two ActionProperties with same values when comparing with equals then returns true`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("test_data")
        val analytics = AnalyticsProperties(eventName = "test_event")

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
        assertTrue(action1 == action2)
    }

    @Test
    fun `given two ActionProperties with different deeplink when comparing with equals then returns false`() {
        val action1 = ActionProperties(deeplink = "https://example.com")
        val action2 = ActionProperties(deeplink = "https://different.com")

        assertNotEquals(action1, action2)
        assertFalse(action1 == action2)
    }

    @Test
    fun `given two ActionProperties with different actionData when comparing with equals then returns false`() {
        val action1 = ActionProperties(actionData = JsonPrimitive("data1"))
        val action2 = ActionProperties(actionData = JsonPrimitive("data2"))

        assertNotEquals(action1, action2)
        assertFalse(action1 == action2)
    }

    @Test
    fun `given two ActionProperties with different analytics when comparing with equals then returns false`() {
        val action1 = ActionProperties(analytics = AnalyticsProperties(eventName = "event1"))
        val action2 = ActionProperties(analytics = AnalyticsProperties(eventName = "event2"))

        assertNotEquals(action1, action2)
        assertFalse(action1 == action2)
    }

    @Test
    fun `given two ActionProperties with same values when comparing hashCode then returns same hash`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("test_data")
        val analytics = AnalyticsProperties(eventName = "test_event")

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
    fun `given two ActionProperties with different values when comparing hashCode then may return different hash`() {
        val action1 = ActionProperties(deeplink = "https://example.com")
        val action2 = ActionProperties(deeplink = "https://different.com")

        assertNotEquals(action1.hashCode(), action2.hashCode())
    }

    @Test
    fun `given ActionProperties when calling toString then returns string representation`() {
        val action = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("test_data")
        )

        val stringRepresentation = action.toString()

        assertNotNull(stringRepresentation)
        assertTrue(stringRepresentation.contains("ActionProperties"))
        assertTrue(stringRepresentation.contains("deeplink"))
    }

    @Test
    fun `given null deeplink when constructing ActionProperties then deeplink is null`() {
        val action = ActionProperties(deeplink = null)

        assertNull(action.deeplink)
    }

    @Test
    fun `given null actionData when constructing ActionProperties then actionData is null`() {
        val action = ActionProperties(actionData = null)

        assertNull(action.actionData)
    }

    @Test
    fun `given null analytics when constructing ActionProperties then analytics is null`() {
        val action = ActionProperties(analytics = null)

        assertNull(action.analytics)
    }

    @Test
    fun `given empty string deeplink when constructing ActionProperties then deeplink is set correctly`() {
        val action = ActionProperties(deeplink = "")

        assertEquals("", action.deeplink)
    }

    @Test
    fun `given JsonObject actionData when constructing ActionProperties then actionData is set correctly`() {
        val actionData = buildJsonObject {
            put("key", JsonPrimitive("value"))
        }
        val action = ActionProperties(actionData = actionData)

        assertEquals(actionData, action.actionData)
    }

    @Test
    fun `given ActionProperties with null fields when calling copy with all fields then returns new object with all fields`() {
        val action = ActionProperties()
        val newDeeplink = "https://example.com"
        val newActionData = JsonPrimitive("data")
        val newAnalytics = AnalyticsProperties(eventName = "event")

        val copied = action.copy(
            deeplink = newDeeplink,
            actionData = newActionData,
            analytics = newAnalytics
        )

        assertEquals(newDeeplink, copied.deeplink)
        assertEquals(newActionData, copied.actionData)
        assertEquals(newAnalytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when calling copy to set fields to null then returns object with null fields`() {
        val action = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = AnalyticsProperties(eventName = "event")
        )

        val copied = action.copy(
            deeplink = null,
            actionData = null,
            analytics = null
        )

        assertNull(copied.deeplink)
        assertNull(copied.actionData)
        assertNull(copied.analytics)
    }

    @Test
    fun `given two ActionProperties objects when comparing with different object type then returns false`() {
        val action = ActionProperties(deeplink = "https://example.com")

        assertNotEquals(action, "not_an_action_properties")
        assertFalse(action == "not_an_action_properties")
    }

    @Test
    fun `given ActionProperties with complex JsonObject when comparing for equality then comparison works correctly`() {
        val complexJson = buildJsonObject {
            put("nested", buildJsonObject {
                put("key", JsonPrimitive("value"))
            })
        }

        val action1 = ActionProperties(actionData = complexJson)
        val action2 = ActionProperties(actionData = complexJson)

        assertEquals(action1, action2)
    }

    @Test
    fun `given ActionProperties with JsonPrimitive numbers when comparing for equality then comparison works correctly`() {
        val actionData1 = JsonPrimitive(123)
        val actionData2 = JsonPrimitive(123)

        val action1 = ActionProperties(actionData = actionData1)
        val action2 = ActionProperties(actionData = actionData2)

        assertEquals(action1, action2)
    }

    @Test
    fun `given ActionProperties with JsonPrimitive boolean when comparing for equality then comparison works correctly`() {
        val actionData1 = JsonPrimitive(true)
        val actionData2 = JsonPrimitive(true)

        val action1 = ActionProperties(actionData = actionData1)
        val action2 = ActionProperties(actionData = actionData2)

        assertEquals(action1, action2)
    }

    @Test
    fun `given ActionProperties with different JsonPrimitive boolean when comparing for equality then comparison returns false`() {
        val actionData1 = JsonPrimitive(true)
        val actionData2 = JsonPrimitive(false)

        val action1 = ActionProperties(actionData = actionData1)
        val action2 = ActionProperties(actionData = actionData2)

        assertNotEquals(action1, action2)
    }

    @Test
    fun `given multiple ActionProperties copies when modifying one then others remain unchanged`() {
        val originalAction = ActionProperties(deeplink = "https://example.com")

        val copy1 = originalAction.copy()
        val copy2 = originalAction.copy(deeplink = "https://different.com")

        assertEquals("https://example.com", copy1.deeplink)
        assertEquals("https://different.com", copy2.deeplink)
        assertEquals("https://example.com", originalAction.deeplink)
    }

    @Test
    fun `given ActionProperties with special characters in deeplink when constructing then deeplink is preserved`() {
        val deeplink = "https://example.com/path?query=value&other=123#fragment"
        val action = ActionProperties(deeplink = deeplink)

        assertEquals(deeplink, action.deeplink)
    }

    @Test
    fun `given ActionProperties with unicode in deeplink when constructing then deeplink is preserved`() {
        val deeplink = "https://example.com/路径"
        val action = ActionProperties(deeplink = deeplink)

        assertEquals(deeplink, action.deeplink)
    }

    @Test
    fun `given ActionProperties when calling copy preserves immutability`() {
        val action1 = ActionProperties(deeplink = "https://example.com")
        val action2 = action1.copy()

        assertEquals(action1.deeplink, action2.deeplink)
        assertFalse(action1 === action2)
    }

    @Test
    fun `given ActionProperties with all null fields when comparing with another all null then returns true`() {
        val action1 = ActionProperties()
        val action2 = ActionProperties()

        assertEquals(action1, action2)
    }
}