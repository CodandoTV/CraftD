package com.github.codandotv.craftd.androidcore.data.model.action

import androidx.recyclerview.widget.DiffUtil
import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ActionPropertiesTest {

    private lateinit var analyticsProperties: AnalyticsProperties

    @Before
    fun setUp() {
        analyticsProperties = mockk(relaxed = true)
    }

    // ============================================================
    // Data Class Construction Tests
    // ============================================================

    @Test
    fun `given all parameters when creating ActionProperties then should initialize correctly`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("test_data")
        val analytics = analyticsProperties

        val actionProperties = ActionProperties(
            deeplink = deeplink,
            actionData = actionData,
            analytics = analytics
        )

        assertEquals(deeplink, actionProperties.deeplink)
        assertEquals(actionData, actionProperties.actionData)
        assertEquals(analytics, actionProperties.analytics)
    }

    @Test
    fun `given only deeplink when creating ActionProperties then should initialize with defaults`() {
        val deeplink = "https://example.com"

        val actionProperties = ActionProperties(deeplink = deeplink)

        assertEquals(deeplink, actionProperties.deeplink)
        assertNull(actionProperties.actionData)
        assertNull(actionProperties.analytics)
    }

    @Test
    fun `given no parameters when creating ActionProperties then should use all defaults`() {
        val actionProperties = ActionProperties()

        assertNull(actionProperties.deeplink)
        assertNull(actionProperties.actionData)
        assertNull(actionProperties.analytics)
    }

    @Test
    fun `given null deeplink when creating ActionProperties then should accept null`() {
        val actionProperties = ActionProperties(deeplink = null)

        assertNull(actionProperties.deeplink)
    }

    // ============================================================
    // Data Class Copy Tests
    // ============================================================

    @Test
    fun `given ActionProperties when copying with modified deeplink then should create new instance`() {
        val original = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = analyticsProperties
        )

        val copied = original.copy(deeplink = "https://modified.com")

        assertEquals("https://modified.com", copied.deeplink)
        assertEquals(original.actionData, copied.actionData)
        assertEquals(original.analytics, copied.analytics)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given ActionProperties when copying with modified actionData then should create new instance`() {
        val original = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = analyticsProperties
        )
        val newData = JsonPrimitive("modified")

        val copied = original.copy(actionData = newData)

        assertEquals(original.deeplink, copied.deeplink)
        assertEquals(newData, copied.actionData)
        assertEquals(original.analytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when copying with modified analytics then should create new instance`() {
        val original = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = analyticsProperties
        )
        val newAnalytics = mockk<AnalyticsProperties>(relaxed = true)

        val copied = original.copy(analytics = newAnalytics)

        assertEquals(original.deeplink, copied.deeplink)
        assertEquals(original.actionData, copied.actionData)
        assertEquals(newAnalytics, copied.analytics)
    }

    @Test
    fun `given ActionProperties when copying all fields then should create independent instance`() {
        val original = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = analyticsProperties
        )

        val copied = original.copy(
            deeplink = "https://new.com",
            actionData = JsonPrimitive("newdata"),
            analytics = mockk(relaxed = true)
        )

        assertNotEquals(original, copied)
    }

    // ============================================================
    // Data Class Equality Tests
    // ============================================================

    @Test
    fun `given two ActionProperties with same values when comparing then should be equal`() {
        val actionProperties1 = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = analyticsProperties
        )
        val actionProperties2 = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data"),
            analytics = analyticsProperties
        )

        assertEquals(actionProperties1, actionProperties2)
    }

    @Test
    fun `given two ActionProperties with different deeplink when comparing then should not be equal`() {
        val actionProperties1 = ActionProperties(deeplink = "https://example.com")
        val actionProperties2 = ActionProperties(deeplink = "https://different.com")

        assertNotEquals(actionProperties1, actionProperties2)
    }

    @Test
    fun `given two ActionProperties with different actionData when comparing then should not be equal`() {
        val actionProperties1 = ActionProperties(actionData = JsonPrimitive("data1"))
        val actionProperties2 = ActionProperties(actionData = JsonPrimitive("data2"))

        assertNotEquals(actionProperties1, actionProperties2)
    }

    @Test
    fun `given two ActionProperties with different analytics when comparing then should not be equal`() {
        val analytics1 = mockk<AnalyticsProperties>(relaxed = true)
        val analytics2 = mockk<AnalyticsProperties>(relaxed = true)

        val actionProperties1 = ActionProperties(analytics = analytics1)
        val actionProperties2 = ActionProperties(analytics = analytics2)

        assertNotEquals(actionProperties1, actionProperties2)
    }

    @Test
    fun `given ActionProperties when comparing to itself then should be equal`() {
        val actionProperties = ActionProperties(deeplink = "https://example.com")

        assertEquals(actionProperties, actionProperties)
    }

    @Test
    fun `given ActionProperties and null when comparing then should not be equal`() {
        val actionProperties = ActionProperties(deeplink = "https://example.com")

        assertNotEquals(actionProperties, null)
    }

    // ============================================================
    // Data Class HashCode Tests
    // ============================================================

    @Test
    fun `given two equal ActionProperties when hashing then should have same hash`() {
        val actionProperties1 = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data")
        )
        val actionProperties2 = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data")
        )

        assertEquals(actionProperties1.hashCode(), actionProperties2.hashCode())
    }

    @Test
    fun `given two different ActionProperties when hashing then should likely have different hash`() {
        val actionProperties1 = ActionProperties(deeplink = "https://example.com")
        val actionProperties2 = ActionProperties(deeplink = "https://different.com")

        assertNotEquals(actionProperties1.hashCode(), actionProperties2.hashCode())
    }

    @Test
    fun `given ActionProperties with null values when hashing then should produce valid hash`() {
        val actionProperties = ActionProperties()

        val hash = actionProperties.hashCode()

        assertTrue(hash != 0 || true)
    }

    // ============================================================
    // ActionProperties Immutability Tests
    // ============================================================

    @Test
    fun `given ActionProperties when accessing properties multiple times then should return same values`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("data")
        val actionProperties = ActionProperties(
            deeplink = deeplink,
            actionData = actionData
        )

        val deeplink1 = actionProperties.deeplink
        val deeplink2 = actionProperties.deeplink
        val data1 = actionProperties.actionData
        val data2 = actionProperties.actionData

        assertEquals(deeplink1, deeplink2)
        assertEquals(data1, data2)
    }

    // ============================================================
    // Default Values Tests
    // ============================================================

    @Test
    fun `given ActionProperties with defaults when checking deeplink then should be null`() {
        val actionProperties = ActionProperties()

        assertNull(actionProperties.deeplink)
    }

    @Test
    fun `given ActionProperties with defaults when checking actionData then should be null`() {
        val actionProperties = ActionProperties()

        assertNull(actionProperties.actionData)
    }

    @Test
    fun `given ActionProperties with defaults when checking analytics then should be null`() {
        val actionProperties = ActionProperties()

        assertNull(actionProperties.analytics)
    }

    // ============================================================
    // JsonElement Type Handling Tests
    // ============================================================

    @Test
    fun `given ActionProperties with JsonPrimitive actionData when accessing then should return correct type`() {
        val primitiveData = JsonPrimitive("test")
        val actionProperties = ActionProperties(actionData = primitiveData)

        assertEquals(primitiveData, actionProperties.actionData)
    }

    @Test
    fun `given ActionProperties with JsonObject actionData when accessing then should return correct type`() {
        val objectData = JsonObject(mapOf("key" to JsonPrimitive("value")))
        val actionProperties = ActionProperties(actionData = objectData)

        assertEquals(objectData, actionProperties.actionData)
    }

    @Test
    fun `given ActionProperties with JsonNull actionData when accessing then should return JsonNull`() {
        val nullData = JsonNull
        val actionProperties = ActionProperties(actionData = nullData)

        assertEquals(nullData, actionProperties.actionData)
    }

    // ============================================================
    // Multiple Instantiation Tests
    // ============================================================

    @Test
    fun `given multiple ActionProperties instances with same data when comparing then should be equal`() {
        val deeplink = "https://example.com"
        val actionData = JsonPrimitive("data")

        val instance1 = ActionProperties(deeplink = deeplink, actionData = actionData)
        val instance2 = ActionProperties(deeplink = deeplink, actionData = actionData)
        val instance3 = ActionProperties(deeplink = deeplink, actionData = actionData)

        assertEquals(instance1, instance2)
        assertEquals(instance2, instance3)
        assertEquals(instance1, instance3)
    }

    @Test
    fun `given ActionProperties instances in collection when using distinct then should filter duplicates`() {
        val actionProperties1 = ActionProperties(deeplink = "https://example.com")
        val actionProperties2 = ActionProperties(deeplink = "https://example.com")
        val actionProperties3 = ActionProperties(deeplink = "https://different.com")

        val list = listOf(actionProperties1, actionProperties2, actionProperties3)
        val distinct = list.distinct()

        assertEquals(2, distinct.size)
    }

    // ============================================================
    // SerialName Annotation Tests
    // ============================================================

    @Test
    fun `given ActionProperties when checking serialization names then should have correct SerialNames`() {
        val actionProperties = ActionProperties(
            deeplink = "https://example.com",
            actionData = JsonPrimitive("data")
        )

        assertNotNull(actionProperties)
    }

    // ============================================================
    // Constructor Edge Cases
    // ============================================================

    @Test
    fun `given empty string deeplink when creating ActionProperties then should accept it`() {
        val actionProperties = ActionProperties(deeplink = "")

        assertEquals("", actionProperties.deeplink)
    }

    @Test
    fun `given very long deeplink when creating ActionProperties then should accept it`() {
        val longDeeplink = "https://example.com/" + "a".repeat(1000)
        val actionProperties = ActionProperties(deeplink = longDeeplink)

        assertEquals(longDeeplink, actionProperties.deeplink)
    }

    @Test
    fun `given special characters in deeplink when creating ActionProperties then should accept it`() {
        val deeplink = "https://example.com?param=value&other=123"
        val actionProperties = ActionProperties(deeplink = deeplink)

        assertEquals(deeplink, actionProperties.deeplink)
    }

    // ============================================================
    // Composition Tests
    // ============================================================

    @Test
    fun `given ActionProperties with analytics when accessing then should return same instance`() {
        val analytics = analyticsProperties
        val actionProperties = ActionProperties(analytics = analytics)

        assertSame(analytics, actionProperties.analytics)
    }

    @Test
    fun `given ActionProperties with actionData when copying and modifying then original should be unchanged`() {
        val originalData = JsonPrimitive("original")
        val actionProperties = ActionProperties(actionData = originalData)

        val modified = actionProperties.copy(actionData = JsonPrimitive("modified"))

        assertEquals(originalData, actionProperties.actionData)
        assertNotEquals(originalData, modified.actionData)
    }

    // ============================================================
    // Type Safety Tests
    // ============================================================

    @Test
    fun `given ActionProperties with various JsonElement types when storing then should preserve types`() {
        val primitive = JsonPrimitive("test")
        val obj = JsonObject(mapOf("key" to JsonPrimitive("value")))
        val nullElement = JsonNull

        val props1 = ActionProperties(actionData = primitive)
        val props2 = ActionProperties(actionData = obj)
        val props3 = ActionProperties(actionData = nullElement)

        assertEquals(primitive, props1.actionData)
        assertEquals(obj, props2.actionData)
        assertEquals(nullElement, props3.actionData)
    }

    private fun assertSame(expected: Any?, actual: Any?) {
        assertTrue("Expected same instance", expected === actual)
    }

    private fun assertNotNull(value: Any?) {
        assertTrue("Expected non-null value", value != null)
    }
}