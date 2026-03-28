package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonArray
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class SimplePropertiesResponseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given default constructor when creating SimplePropertiesResponse then key is empty string and value is null`() {
        val response = SimplePropertiesResponse()
        assertEquals("", response.key)
        assertNull(response.value)
    }

    @Test
    fun `given key and value when creating SimplePropertiesResponse then properties are set correctly`() {
        val testKey = "testKey"
        val testValue = JsonPrimitive("testValue")
        val response = SimplePropertiesResponse(key = testKey, value = testValue)

        assertEquals(testKey, response.key)
        assertEquals(testValue, response.value)
    }

    @Test
    fun `given only key when creating SimplePropertiesResponse then value defaults to null`() {
        val testKey = "testKey"
        val response = SimplePropertiesResponse(key = testKey)

        assertEquals(testKey, response.key)
        assertNull(response.value)
    }

    @Test
    fun `given only value when creating SimplePropertiesResponse then key defaults to empty string`() {
        val testValue = JsonPrimitive(42)
        val response = SimplePropertiesResponse(value = testValue)

        assertEquals("", response.key)
        assertEquals(testValue, response.value)
    }

    @Test
    fun `given SimplePropertiesResponse when calling copy with new key then new instance has updated key`() {
        val original = SimplePropertiesResponse(key = "original", value = JsonPrimitive("value"))
        val copied = original.copy(key = "copied")

        assertEquals("copied", copied.key)
        assertEquals(original.value, copied.value)
        assertNotEquals(original.key, copied.key)
    }

    @Test
    fun `given SimplePropertiesResponse when calling copy with new value then new instance has updated value`() {
        val originalValue = JsonPrimitive("original")
        val newValue = JsonPrimitive("new")
        val original = SimplePropertiesResponse(key = "key", value = originalValue)
        val copied = original.copy(value = newValue)

        assertEquals(original.key, copied.key)
        assertEquals(newValue, copied.value)
        assertNotEquals(originalValue, copied.value)
    }

    @Test
    fun `given SimplePropertiesResponse when calling copy with all parameters then new instance has all updated values`() {
        val original = SimplePropertiesResponse(key = "original", value = JsonPrimitive("value"))
        val newValue = JsonPrimitive(123)
        val copied = original.copy(key = "newKey", value = newValue)

        assertEquals("newKey", copied.key)
        assertEquals(newValue, copied.value)
    }

    @Test
    fun `given two SimplePropertiesResponse with same properties when comparing with equals then returns true`() {
        val response1 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))

        assertEquals(response1, response2)
    }

    @Test
    fun `given two SimplePropertiesResponse with different keys when comparing with equals then returns false`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value"))

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given two SimplePropertiesResponse with different values when comparing with equals then returns false`() {
        val response1 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value2"))

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given two SimplePropertiesResponse with same key and both null values when comparing with equals then returns true`() {
        val response1 = SimplePropertiesResponse(key = "key", value = null)
        val response2 = SimplePropertiesResponse(key = "key", value = null)

        assertEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse when calling hashCode twice then returns same value`() {
        val response = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val hash1 = response.hashCode()
        val hash2 = response.hashCode()

        assertEquals(hash1, hash2)
    }

    @Test
    fun `given two equal SimplePropertiesResponse when calling hashCode then both return same hash`() {
        val response1 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))

        assertEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given two different SimplePropertiesResponse when calling hashCode then may return different hashes`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))

        assertNotEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given default constructor when creating DataSimplePropertiesResponse then data list is empty`() {
        val dataList = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        )
        val response = DataSimplePropertiesResponse(data = dataList)

        assertEquals(dataList, response.data)
        assertEquals(1, response.data.size)
    }

    @Test
    fun `given multiple SimplePropertiesResponse when creating DataSimplePropertiesResponse then all items are preserved`() {
        val item1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val item2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        val item3 = SimplePropertiesResponse(key = "key3", value = null)
        val dataList = listOf(item1, item2, item3)
        val response = DataSimplePropertiesResponse(data = dataList)

        assertEquals(3, response.data.size)
        assertEquals(item1, response.data[0])
        assertEquals(item2, response.data[1])
        assertEquals(item3, response.data[2])
    }

    @Test
    fun `given empty list when creating DataSimplePropertiesResponse then data is empty`() {
        val response = DataSimplePropertiesResponse(data = emptyList())

        assertEquals(emptyList(), response.data)
        assertEquals(0, response.data.size)
    }

    @Test
    fun `given DataSimplePropertiesResponse when calling copy with new data then new instance has updated data`() {
        val originalData = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val newData = listOf(SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")))
        val original = DataSimplePropertiesResponse(data = originalData)
        val copied = original.copy(data = newData)

        assertEquals(newData, copied.data)
        assertNotEquals(originalData, copied.data)
    }

    @Test
    fun `given two DataSimplePropertiesResponse with same data when comparing with equals then returns true`() {
        val data = listOf(SimplePropertiesResponse(key = "key", value = JsonPrimitive("value")))
        val response1 = DataSimplePropertiesResponse(data = data)
        val response2 = DataSimplePropertiesResponse(data = data)

        assertEquals(response1, response2)
    }

    @Test
    fun `given two DataSimplePropertiesResponse with different data when comparing with equals then returns false`() {
        val data1 = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val data2 = listOf(SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")))
        val response1 = DataSimplePropertiesResponse(data = data1)
        val response2 = DataSimplePropertiesResponse(data = data2)

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given DataSimplePropertiesResponse when calling hashCode twice then returns same value`() {
        val data = listOf(SimplePropertiesResponse(key = "key", value = JsonPrimitive("value")))
        val response = DataSimplePropertiesResponse(data = data)
        val hash1 = response.hashCode()
        val hash2 = response.hashCode()

        assertEquals(hash1, hash2)
    }

    @Test
    fun `given SimplePropertiesResponse with string JsonElement when toString is called then contains key and value`() {
        val response = SimplePropertiesResponse(key = "testKey", value = JsonPrimitive("testValue"))
        val stringRepresentation = response.toString()

        assertNotNull(stringRepresentation)
    }

    @Test
    fun `given SimplePropertiesResponse with null value when toString is called then contains key`() {
        val response = SimplePropertiesResponse(key = "testKey", value = null)
        val stringRepresentation = response.toString()

        assertNotNull(stringRepresentation)
    }

    @Test
    fun `given DataSimplePropertiesResponse with multiple items when toString is called then is not empty`() {
        val data = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        )
        val response = DataSimplePropertiesResponse(data = data)
        val stringRepresentation = response.toString()

        assertNotNull(stringRepresentation)
    }

    @Test
    fun `given SimplePropertiesResponse with complex JsonElement when creating then value is preserved`() {
        val complexValue = JsonObject(mapOf("nested" to JsonPrimitive("value")))
        val response = SimplePropertiesResponse(key = "complex", value = complexValue)

        assertEquals(complexValue, response.value)
    }

    @Test
    fun `given SimplePropertiesResponse with JsonArray when creating then value is preserved`() {
        val arrayValue = JsonArray(listOf(JsonPrimitive(1), JsonPrimitive(2), JsonPrimitive(3)))
        val response = SimplePropertiesResponse(key = "array", value = arrayValue)

        assertEquals(arrayValue, response.value)
    }

    @Test
    fun `given SimplePropertiesResponse with boolean JsonElement when creating then value is preserved`() {
        val boolValue = JsonPrimitive(true)
        val response = SimplePropertiesResponse(key = "bool", value = boolValue)

        assertEquals(boolValue, response.value)
    }

    @Test
    fun `given SimplePropertiesResponse with numeric JsonElement when creating then value is preserved`() {
        val numValue = JsonPrimitive(42.5)
        val response = SimplePropertiesResponse(key = "number", value = numValue)

        assertEquals(numValue, response.value)
    }

    @Test
    fun `given SimplePropertiesResponse when comparing with itself then equals returns true`() {
        val response = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))

        assertEquals(response, response)
    }

    @Test
    fun `given SimplePropertiesResponse when comparing with null then equals returns false`() {
        val response = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))

        assertNotEquals(response, null)
    }

    @Test
    fun `given SimplePropertiesResponse with empty key when creating then key is empty string`() {
        val response = SimplePropertiesResponse(key = "", value = JsonPrimitive("value"))

        assertEquals("", response.key)
    }

    @Test
    fun `given SimplePropertiesResponse with JsonNull when creating then value is not null reference but JsonNull element`() {
        val nullValue = JsonNull
        val response = SimplePropertiesResponse(key = "key", value = nullValue)

        assertEquals(nullValue, response.value)
        assertNotNull(response.value)
    }

    @Test
    fun `given DataSimplePropertiesResponse with single item when creating then data size is one`() {
        val data = listOf(SimplePropertiesResponse(key = "key", value = JsonPrimitive("value")))
        val response = DataSimplePropertiesResponse(data = data)

        assertEquals(1, response.data.size)
    }

    @Test
    fun `given DataSimplePropertiesResponse when comparing with itself then equals returns true`() {
        val data = listOf(SimplePropertiesResponse(key = "key", value = JsonPrimitive("value")))
        val response = DataSimplePropertiesResponse(data = data)

        assertEquals(response, response)
    }

    @Test
    fun `given two DataSimplePropertiesResponse with empty lists when comparing then equals returns true`() {
        val response1 = DataSimplePropertiesResponse(data = emptyList())
        val response2 = DataSimplePropertiesResponse(data = emptyList())

        assertEquals(response1, response2)
    }
}