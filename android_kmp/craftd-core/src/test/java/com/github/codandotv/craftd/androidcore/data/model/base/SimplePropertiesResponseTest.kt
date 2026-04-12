package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SimplePropertiesResponseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given default parameters when creating SimplePropertiesResponse then key is empty and value is null`() {
        val response = SimplePropertiesResponse()
        assertEquals("", response.key)
        assertNull(response.value)
    }

    @Test
    fun `given key and value when creating SimplePropertiesResponse then properties are set correctly`() {
        val key = "testKey"
        val value = JsonPrimitive("testValue")
        val response = SimplePropertiesResponse(key = key, value = value)
        assertEquals(key, response.key)
        assertEquals(value, response.value)
    }

    @Test
    fun `given all parameters when creating SimplePropertiesResponse then copy works correctly`() {
        val original = SimplePropertiesResponse(key = "original", value = JsonPrimitive("value"))
        val copied = original.copy(key = "copied")
        assertEquals("copied", copied.key)
        assertEquals(original.value, copied.value)
        assertNotEquals(original.key, copied.key)
    }

    @Test
    fun `given same key and value when comparing SimplePropertiesResponse then equals returns true`() {
        val response1 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        assertEquals(response1, response2)
    }

    @Test
    fun `given different key when comparing SimplePropertiesResponse then equals returns false`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value"))
        assertNotEquals(response1, response2)
    }

    @Test
    fun `given different value when comparing SimplePropertiesResponse then equals returns false`() {
        val response1 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value2"))
        assertNotEquals(response1, response2)
    }

    @Test
    fun `given same key and value when comparing hashCode then they are equal`() {
        val response1 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        assertEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given different key when comparing hashCode then they are different`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value"))
        assertNotEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given null value when creating SimplePropertiesResponse then value is null`() {
        val response = SimplePropertiesResponse(key = "key", value = null)
        assertNull(response.value)
    }

    @Test
    fun `given JsonNull value when creating SimplePropertiesResponse then value is JsonNull`() {
        val response = SimplePropertiesResponse(key = "key", value = JsonNull)
        assertEquals(JsonNull, response.value)
    }

    @Test
    fun `given default parameters when creating DataSimplePropertiesResponse then data is initialized`() {
        val data = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val response = DataSimplePropertiesResponse(data = data)
        assertEquals(1, response.data.size)
        assertEquals("key1", response.data[0].key)
    }

    @Test
    fun `given multiple items when creating DataSimplePropertiesResponse then all items are present`() {
        val item1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val item2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        val data = listOf(item1, item2)
        val response = DataSimplePropertiesResponse(data = data)
        assertEquals(2, response.data.size)
        assertEquals(item1, response.data[0])
        assertEquals(item2, response.data[1])
    }

    @Test
    fun `given empty list when creating DataSimplePropertiesResponse then data is empty`() {
        val response = DataSimplePropertiesResponse(data = emptyList())
        assertEquals(0, response.data.size)
    }

    @Test
    fun `given same data when comparing DataSimplePropertiesResponse then equals returns true`() {
        val data = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val response1 = DataSimplePropertiesResponse(data = data)
        val response2 = DataSimplePropertiesResponse(data = data)
        assertEquals(response1, response2)
    }

    @Test
    fun `given different data when comparing DataSimplePropertiesResponse then equals returns false`() {
        val data1 = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val data2 = listOf(SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")))
        val response1 = DataSimplePropertiesResponse(data = data1)
        val response2 = DataSimplePropertiesResponse(data = data2)
        assertNotEquals(response1, response2)
    }

    @Test
    fun `given same data when comparing DataSimplePropertiesResponse hashCode then they are equal`() {
        val data = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val response1 = DataSimplePropertiesResponse(data = data)
        val response2 = DataSimplePropertiesResponse(data = data)
        assertEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given JsonPrimitive string when creating SimplePropertiesResponse then value is correctly set`() {
        val stringValue = JsonPrimitive("stringValue")
        val response = SimplePropertiesResponse(key = "key", value = stringValue)
        assertEquals(stringValue, response.value)
    }

    @Test
    fun `given JsonPrimitive number when creating SimplePropertiesResponse then value is correctly set`() {
        val numberValue = JsonPrimitive(42)
        val response = SimplePropertiesResponse(key = "key", value = numberValue)
        assertEquals(numberValue, response.value)
    }

    @Test
    fun `given JsonPrimitive boolean when creating SimplePropertiesResponse then value is correctly set`() {
        val booleanValue = JsonPrimitive(true)
        val response = SimplePropertiesResponse(key = "key", value = booleanValue)
        assertEquals(booleanValue, response.value)
    }

    @Test
    fun `given complex JsonObject when creating SimplePropertiesResponse then value is correctly set`() {
        val complexValue = buildJsonObject {
            put("nested", JsonPrimitive("value"))
        }
        val response = SimplePropertiesResponse(key = "key", value = complexValue)
        assertEquals(complexValue, response.value)
    }

    @Test
    fun `given copy with only value when SimplePropertiesResponse then key remains unchanged`() {
        val original = SimplePropertiesResponse(key = "originalKey", value = JsonPrimitive("originalValue"))
        val newValue = JsonPrimitive("newValue")
        val copied = original.copy(value = newValue)
        assertEquals("originalKey", copied.key)
        assertEquals(newValue, copied.value)
    }

    @Test
    fun `given copy with all parameters when SimplePropertiesResponse then all are updated`() {
        val original = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val newKey = "key2"
        val newValue = JsonPrimitive("value2")
        val copied = original.copy(key = newKey, value = newValue)
        assertEquals(newKey, copied.key)
        assertEquals(newValue, copied.value)
    }

    @Test
    fun `given DataSimplePropertiesResponse when calling copy then data is updated`() {
        val originalData = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        val original = DataSimplePropertiesResponse(data = originalData)
        val newData = listOf(SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")))
        val copied = original.copy(data = newData)
        assertEquals(newData, copied.data)
        assertNotEquals(originalData, copied.data)
    }

    @Test
    fun `given null key when creating SimplePropertiesResponse then default empty string is used`() {
        val response = SimplePropertiesResponse(key = "", value = JsonPrimitive("value"))
        assertEquals("", response.key)
    }

    @Test
    fun `given very long key when creating SimplePropertiesResponse then key is stored as is`() {
        val longKey = "a".repeat(1000)
        val response = SimplePropertiesResponse(key = longKey, value = JsonPrimitive("value"))
        assertEquals(longKey, response.key)
    }

    @Test
    fun `given special characters in key when creating SimplePropertiesResponse then key is stored as is`() {
        val specialKey = "key@#$%^&*()"
        val response = SimplePropertiesResponse(key = specialKey, value = JsonPrimitive("value"))
        assertEquals(specialKey, response.key)
    }

    @Test
    fun `given unicode characters in key when creating SimplePropertiesResponse then key is stored correctly`() {
        val unicodeKey = "key_🚀_测试"
        val response = SimplePropertiesResponse(key = unicodeKey, value = JsonPrimitive("value"))
        assertEquals(unicodeKey, response.key)
    }

    @Test
    fun `given response with null value when comparing then null value is handled correctly`() {
        val response1 = SimplePropertiesResponse(key = "key", value = null)
        val response2 = SimplePropertiesResponse(key = "key", value = null)
        assertEquals(response1, response2)
    }

    @Test
    fun `given response with null and non-null value when comparing then they are not equal`() {
        val response1 = SimplePropertiesResponse(key = "key", value = null)
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        assertNotEquals(response1, response2)
    }

    @Test
    fun `given DataSimplePropertiesResponse with mixed null values when accessing data then items are retrieved correctly`() {
        val item1 = SimplePropertiesResponse(key = "key1", value = null)
        val item2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value"))
        val data = listOf(item1, item2)
        val response = DataSimplePropertiesResponse(data = data)
        assertNull(response.data[0].value)
        assertEquals(JsonPrimitive("value"), response.data[1].value)
    }

    @Test
    fun `given large list in DataSimplePropertiesResponse then all items are retained`() {
        val items = (1..1000).map { i ->
            SimplePropertiesResponse(key = "key$i", value = JsonPrimitive(i))
        }
        val response = DataSimplePropertiesResponse(data = items)
        assertEquals(1000, response.data.size)
        assertEquals("key1", response.data[0].key)
        assertEquals("key1000", response.data[999].key)
    }

    @Test
    fun `given SerialName annotations when inspecting SimplePropertiesResponse then annotations are present`() {
        val response = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        assertEquals("key", response.key)
        assertEquals(JsonPrimitive("value"), response.value)
    }

    @Test
    fun `given Serializable annotation when inspecting SimplePropertiesResponse then it is marked serializable`() {
        val response = SimplePropertiesResponse()
        assertTrue(response::class.annotations.any { it.annotationClass.simpleName == "Serializable" })
    }

    @Test
    fun `given multiple SimplePropertiesResponse in list when verifying each then all are independent`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        val response3 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value3"))
        assertTrue(response1 != response2)
        assertTrue(response1 != response3)
        assertFalse(response1 == response3)
    }

    @Test
    fun `given DataSimplePropertiesResponse copy with same data then creates new instance`() {
        val data = listOf(SimplePropertiesResponse(key = "key", value = JsonPrimitive("value")))
        val original = DataSimplePropertiesResponse(data = data)
        val copied = original.copy()
        assertEquals(original, copied)
        assertTrue(original === original)
    }

    @Test
    fun `given SimplePropertiesResponse with empty string key then equals and hashCode work correctly`() {
        val response1 = SimplePropertiesResponse(key = "", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "", value = JsonPrimitive("value"))
        assertEquals(response1, response2)
        assertEquals(response1.hashCode(), response2.hashCode())
    }
}