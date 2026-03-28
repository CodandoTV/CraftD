package com.github.codandotv.craftd.androidcore.data

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ViewMapperTest {

    @Test
    fun `given null JsonElement when convertToElement then returns null`() {
        val result: String? = (null as JsonElement?).convertToElement()
        assertNull(result)
    }

    @Test
    fun `given valid JsonElement when convertToElement then returns deserialized object`() {
        val jsonElement = JsonPrimitive("test_string")
        val result: String? = jsonElement.convertToElement()
        assertEquals("test_string", result)
    }

    @Test
    fun `given valid JsonElement with complex object when convertToElement then returns deserialized object`() {
        val jsonElement = buildJsonObject {
            put("key", "testKey")
            put("value", "testValue")
        }
        val result: SimplePropertiesResponse? = jsonElement.convertToElement()
        assertEquals("testKey", result?.key)
        assertEquals("testValue", result?.value)
    }

    @Test
    fun `given malformed JsonElement when convertToElement then returns null`() {
        val jsonElement = JsonPrimitive(123)
        val result: String? = jsonElement.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given JsonElement with unknown keys when convertToElement then ignores unknown keys`() {
        val jsonElement = buildJsonObject {
            put("key", "testKey")
            put("value", "testValue")
            put("unknownField", "should_be_ignored")
        }
        val result: SimplePropertiesResponse? = jsonElement.convertToElement()
        assertEquals("testKey", result?.key)
        assertEquals("testValue", result?.value)
    }

    @Test
    fun `given empty list when toListSimpleProperties then returns empty list`() {
        val input: List<SimplePropertiesResponse> = emptyList()
        val result = input.toListSimpleProperties()
        assertEquals(0, result.size)
    }

    @Test
    fun `given list with single item when toListSimpleProperties then returns list with single SimpleProperties`() {
        val input = listOf(
            SimplePropertiesResponse(key = "key1", value = "value1")
        )
        val result = input.toListSimpleProperties()
        assertEquals(1, result.size)
        assertEquals("key1", result[0].key)
        assertEquals("value1", result[0].value)
    }

    @Test
    fun `given list with multiple items when toListSimpleProperties then returns list with all SimpleProperties`() {
        val input = listOf(
            SimplePropertiesResponse(key = "key1", value = "value1"),
            SimplePropertiesResponse(key = "key2", value = "value2"),
            SimplePropertiesResponse(key = "key3", value = "value3")
        )
        val result = input.toListSimpleProperties()
        assertEquals(3, result.size)
        assertEquals("key1", result[0].key)
        assertEquals("value1", result[0].value)
        assertEquals("key2", result[1].key)
        assertEquals("value2", result[1].value)
        assertEquals("key3", result[2].key)
        assertEquals("value3", result[2].value)
    }

    @Test
    fun `given SimplePropertiesResponse when toSimpleProperties then returns SimpleProperties with same key and value`() {
        val input = SimplePropertiesResponse(key = "testKey", value = "testValue")
        val result = input.toSimpleProperties()
        assertEquals("testKey", result.key)
        assertEquals("testValue", result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with null value when toSimpleProperties then returns SimpleProperties with null value`() {
        val input = SimplePropertiesResponse(key = "testKey", value = null)
        val result = input.toSimpleProperties()
        assertEquals("testKey", result.key)
        assertNull(result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with empty string key when toSimpleProperties then returns SimpleProperties`() {
        val input = SimplePropertiesResponse(key = "", value = "testValue")
        val result = input.toSimpleProperties()
        assertEquals("", result.key)
        assertEquals("testValue", result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with JsonElement value when toSimpleProperties then returns SimpleProperties`() {
        val jsonValue = JsonPrimitive("jsonString")
        val input = SimplePropertiesResponse(key = "jsonKey", value = jsonValue)
        val result = input.toSimpleProperties()
        assertEquals("jsonKey", result.key)
        assertEquals(jsonValue, result.value)
    }

    @Test
    fun `given list with mixed value types when toListSimpleProperties then maps all correctly`() {
        val input = listOf(
            SimplePropertiesResponse(key = "stringKey", value = "stringValue"),
            SimplePropertiesResponse(key = "numberKey", value = JsonPrimitive(42)),
            SimplePropertiesResponse(key = "nullKey", value = null)
        )
        val result = input.toListSimpleProperties()
        assertEquals(3, result.size)
        assertEquals("stringKey", result[0].key)
        assertEquals("stringValue", result[0].value)
        assertEquals("numberKey", result[1].key)
        assertEquals(JsonPrimitive(42), result[1].value)
        assertEquals("nullKey", result[2].key)
        assertNull(result[2].value)
    }

    @Test
    fun `given SimplePropertiesResponse with JsonElement object when toSimpleProperties then preserves JsonElement`() {
        val jsonObject = buildJsonObject {
            put("nested", "value")
        }
        val input = SimplePropertiesResponse(key = "objectKey", value = jsonObject)
        val result = input.toSimpleProperties()
        assertEquals("objectKey", result.key)
        assertEquals(jsonObject, result.value)
    }

    @Test
    fun `given JsonElement primitive number when convertToElement to Int then returns integer`() {
        val jsonElement = JsonPrimitive(123)
        val result: Int? = jsonElement.convertToElement()
        assertEquals(123, result)
    }

    @Test
    fun `given JsonElement primitive boolean when convertToElement to Boolean then returns boolean`() {
        val jsonElement = JsonPrimitive(true)
        val result: Boolean? = jsonElement.convertToElement()
        assertEquals(true, result)
    }

    @Test
    fun `given list of SimplePropertiesResponse with special characters when toListSimpleProperties then preserves characters`() {
        val input = listOf(
            SimplePropertiesResponse(key = "key@#$%", value = "value!@#$%^&*()"),
            SimplePropertiesResponse(key = "key\nwith\nnewlines", value = "value with spaces  ")
        )
        val result = input.toListSimpleProperties()
        assertEquals(2, result.size)
        assertEquals("key@#$%", result[0].key)
        assertEquals("value!@#$%^&*()", result[0].value)
        assertEquals("key\nwith\nnewlines", result[1].key)
        assertEquals("value with spaces  ", result[1].value)
    }

    @Test
    fun `given JsonElement when convertToElement with reified type inference then correctly resolves type`() {
        val jsonElement = buildJsonObject {
            put("key", "responseKey")
            put("value", "responseValue")
        }
        val result = jsonElement.convertToElement<SimplePropertiesResponse>()
        assertEquals("responseKey", result?.key)
        assertEquals("responseValue", result?.value)
    }

    @Test
    fun `given exception during deserialization when convertToElement then returns null instead of throwing`() {
        val jsonElement = JsonPrimitive("not_a_number")
        val result: Int? = jsonElement.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given SimplePropertiesResponse copy function when called with new key then returns new instance with updated key`() {
        val original = SimplePropertiesResponse(key = "originalKey", value = "value")
        val copied = original.copy(key = "newKey")
        assertEquals("newKey", copied.key)
        assertEquals("value", copied.value)
    }

    @Test
    fun `given SimplePropertiesResponse copy function when called with new value then returns new instance with updated value`() {
        val original = SimplePropertiesResponse(key = "key", value = "originalValue")
        val copied = original.copy(value = "newValue")
        assertEquals("key", copied.key)
        assertEquals("newValue", copied.value)
    }

    @Test
    fun `given two SimpleProperties with same key and value when compared then equals returns true`() {
        val properties1 = SimpleProperties(key = "key", value = "value")
        val properties2 = SimpleProperties(key = "key", value = "value")
        assertEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with different key when compared then equals returns false`() {
        val properties1 = SimpleProperties(key = "key1", value = "value")
        val properties2 = SimpleProperties(key = "key2", value = "value")
        assertTrue(properties1 != properties2)
    }

    @Test
    fun `given two SimpleProperties with different value when compared then equals returns false`() {
        val properties1 = SimpleProperties(key = "key", value = "value1")
        val properties2 = SimpleProperties(key = "key", value = "value2")
        assertTrue(properties1 != properties2)
    }

    @Test
    fun `given two SimpleProperties with same content when hashCode called then hash codes are equal`() {
        val properties1 = SimpleProperties(key = "key", value = "value")
        val properties2 = SimpleProperties(key = "key", value = "value")
        assertEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given SimpleProperties when constructed with all params then stores values correctly`() {
        val key = "testKey"
        val value = "testValue"
        val properties = SimpleProperties(key = key, value = value)
        assertEquals(key, properties.key)
        assertEquals(value, properties.value)
    }

    @Test
    fun `given SimpleProperties with null value when constructed then stores null correctly`() {
        val properties = SimpleProperties(key = "key", value = null)
        assertNull(properties.value)
    }
}