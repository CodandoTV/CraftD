package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class SimplePropertiesResponseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given default params when SimplePropertiesResponse created then properties are empty string and null`() {
        val response = SimplePropertiesResponse()

        assertEquals("", response.key)
        assertNull(response.value)
    }

    @Test
    fun `given key and value when SimplePropertiesResponse created then properties are set correctly`() {
        val testKey = "testKey"
        val testValue: JsonElement = JsonPrimitive("testValue")

        val response = SimplePropertiesResponse(key = testKey, value = testValue)

        assertEquals(testKey, response.key)
        assertEquals(testValue, response.value)
    }

    @Test
    fun `given only key when SimplePropertiesResponse created then value is null`() {
        val testKey = "onlyKey"

        val response = SimplePropertiesResponse(key = testKey)

        assertEquals(testKey, response.key)
        assertNull(response.value)
    }

    @Test
    fun `given only value when SimplePropertiesResponse created then key is empty string`() {
        val testValue: JsonElement = JsonPrimitive(42)

        val response = SimplePropertiesResponse(value = testValue)

        assertEquals("", response.key)
        assertEquals(testValue, response.value)
    }

    @Test
    fun `given two identical SimplePropertiesResponse when compared then equals returns true`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))

        assertEquals(response1, response2)
    }

    @Test
    fun `given two different SimplePropertiesResponse when compared then equals returns false`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse with different keys when compared then equals returns false`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("same"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("same"))

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse with different values when compared then equals returns false`() {
        val response1 = SimplePropertiesResponse(key = "same", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "same", value = JsonPrimitive("value2"))

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse when hashCode called then consistent hash is returned`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))

        assertEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given SimplePropertiesResponse when copy called with new key then new object with updated key is returned`() {
        val original = SimplePropertiesResponse(key = "original", value = JsonPrimitive("value"))
        val copied = original.copy(key = "updated")

        assertEquals("updated", copied.key)
        assertEquals(original.value, copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimplePropertiesResponse when copy called with new value then new object with updated value is returned`() {
        val original = SimplePropertiesResponse(key = "key", value = JsonPrimitive("original"))
        val newValue = JsonPrimitive("updated")
        val copied = original.copy(value = newValue)

        assertEquals(original.key, copied.key)
        assertEquals(newValue, copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimplePropertiesResponse when copy called with all new params then new object with all updated properties is returned`() {
        val original = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val copied = original.copy(key = "key2", value = JsonPrimitive("value2"))

        assertEquals("key2", copied.key)
        assertEquals(JsonPrimitive("value2"), copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimplePropertiesResponse when copy called without params then identical object is returned`() {
        val original = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given default params when DataSimplePropertiesResponse created then data list is empty`() {
        val response = DataSimplePropertiesResponse(data = emptyList())

        assertTrue(response.data.isEmpty())
    }

    @Test
    fun `given list of SimplePropertiesResponse when DataSimplePropertiesResponse created then data is set correctly`() {
        val items = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        )

        val response = DataSimplePropertiesResponse(data = items)

        assertEquals(items, response.data)
        assertEquals(2, response.data.size)
    }

    @Test
    fun `given two identical DataSimplePropertiesResponse when compared then equals returns true`() {
        val items = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val response1 = DataSimplePropertiesResponse(data = items)
        val response2 = DataSimplePropertiesResponse(data = items)

        assertEquals(response1, response2)
    }

    @Test
    fun `given two DataSimplePropertiesResponse with different data when compared then equals returns false`() {
        val response1 = DataSimplePropertiesResponse(
            data = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        )
        val response2 = DataSimplePropertiesResponse(
            data = listOf(SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")))
        )

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given DataSimplePropertiesResponse when copy called with new data then new object with updated data is returned`() {
        val original = DataSimplePropertiesResponse(
            data = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        )
        val newData = listOf(SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")))
        val copied = original.copy(data = newData)

        assertEquals(newData, copied.data)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimplePropertiesResponse with null value when created then value property is null`() {
        val response = SimplePropertiesResponse(key = "testKey", value = null)

        assertEquals("testKey", response.key)
        assertNull(response.value)
    }

    @Test
    fun `given SimplePropertiesResponse with JsonNull when created then value is JsonNull`() {
        val response = SimplePropertiesResponse(key = "testKey", value = JsonNull)

        assertEquals("testKey", response.key)
        assertEquals(JsonNull, response.value)
    }

    @Test
    fun `given SimplePropertiesResponse with complex JsonElement when created then value is preserved`() {
        val complexJson = buildJsonObject {
            put("nested", JsonPrimitive("value"))
        }
        val response = SimplePropertiesResponse(key = "complex", value = complexJson)

        assertEquals("complex", response.key)
        assertEquals(complexJson, response.value)
    }

    @Test
    fun `given empty string key when SimplePropertiesResponse created then key is empty string`() {
        val response = SimplePropertiesResponse(key = "", value = JsonPrimitive("value"))

        assertEquals("", response.key)
    }

    @Test
    fun `given DataSimplePropertiesResponse with single item when created then data size is one`() {
        val item = SimplePropertiesResponse(key = "single", value = JsonPrimitive("item"))
        val response = DataSimplePropertiesResponse(data = listOf(item))

        assertEquals(1, response.data.size)
        assertEquals(item, response.data[0])
    }

    @Test
    fun `given DataSimplePropertiesResponse with multiple items when created then all items are accessible`() {
        val items = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive(123)),
            SimplePropertiesResponse(key = "key3", value = JsonNull)
        )
        val response = DataSimplePropertiesResponse(data = items)

        assertEquals(3, response.data.size)
        assertEquals("key1", response.data[0].key)
        assertEquals("key2", response.data[1].key)
        assertEquals("key3", response.data[2].key)
    }

    @Test
    fun `given DataSimplePropertiesResponse when hashCode called then consistent hash is returned`() {
        val items = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val response1 = DataSimplePropertiesResponse(data = items)
        val response2 = DataSimplePropertiesResponse(data = items)

        assertEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given SimplePropertiesResponse with numeric JsonElement when created then value is preserved`() {
        val numericValue: JsonElement = JsonPrimitive(42)
        val response = SimplePropertiesResponse(key = "numeric", value = numericValue)

        assertEquals(numericValue, response.value)
        assertEquals(42, response.value?.jsonPrimitive?.content?.toIntOrNull())
    }

    @Test
    fun `given SimplePropertiesResponse with boolean JsonElement when created then value is preserved`() {
        val boolValue: JsonElement = JsonPrimitive(true)
        val response = SimplePropertiesResponse(key = "bool", value = boolValue)

        assertEquals(boolValue, response.value)
    }

    @Test
    fun `given two SimplePropertiesResponse with same key but null values when compared then equals returns true`() {
        val response1 = SimplePropertiesResponse(key = "key", value = null)
        val response2 = SimplePropertiesResponse(key = "key", value = null)

        assertEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse with null and non-null values when compared then not equal`() {
        val response1 = SimplePropertiesResponse(key = "key", value = null)
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))

        assertNotEquals(response1, response2)
    }
}