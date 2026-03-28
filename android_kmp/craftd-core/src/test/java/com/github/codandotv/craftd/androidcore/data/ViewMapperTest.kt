package com.github.codandotv.craftd.androidcore.data

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNull

@RunWith(JUnit4::class)
class ViewMapperTest {

    @Test
    fun `given null JsonElement when convertToElement then returns null`() {
        val result: String? = null.convertToElement<String>()
        assertNull(result)
    }

    @Test
    fun `given valid string JsonElement when convertToElement then returns deserialized string`() {
        val jsonElement = JsonPrimitive("test_value")
        val result: String? = jsonElement.convertToElement<String>()
        assertEquals("test_value", result)
    }

    @Test
    fun `given valid int JsonElement when convertToElement then returns deserialized int`() {
        val jsonElement = JsonPrimitive(42)
        val result: Int? = jsonElement.convertToElement<Int>()
        assertEquals(42, result)
    }

    @Test
    fun `given valid boolean JsonElement when convertToElement then returns deserialized boolean`() {
        val jsonElement = JsonPrimitive(true)
        val result: Boolean? = jsonElement.convertToElement<Boolean>()
        assertEquals(true, result)
    }

    @Test
    fun `given valid complex object JsonElement when convertToElement then returns deserialized object`() {
        val jsonElement = buildJsonObject {
            put("name", "test")
            put("age", 25)
        }
        val result: Map<String, JsonElement>? = jsonElement.convertToElement<Map<String, JsonElement>>()
        assertEquals("test", result?.get("name")?.jsonPrimitive?.content)
        assertEquals("25", result?.get("age")?.jsonPrimitive?.content)
    }

    @Test
    fun `given JsonElement with unknown keys when convertToElement then ignores unknown keys and returns object`() {
        val jsonElement = buildJsonObject {
            put("name", "test")
            put("unknownField", "value")
        }
        val result: Map<String, JsonElement>? = jsonElement.convertToElement<Map<String, JsonElement>>()
        assertEquals("test", result?.get("name")?.jsonPrimitive?.content)
    }

    @Test
    fun `given malformed JsonElement when convertToElement then returns null`() {
        val jsonElement = JsonPrimitive("not_a_number")
        val result: Int? = jsonElement.convertToElement<Int>()
        assertNull(result)
    }

    @Test
    fun `given empty list when toListSimpleProperties then returns empty list`() {
        val input: List<SimplePropertiesResponse> = emptyList()
        val result = input.toListSimpleProperties()
        assertEquals(emptyList(), result)
    }

    @Test
    fun `given single item list when toListSimpleProperties then returns list with single SimpleProperties`() {
        val input = listOf(
            SimplePropertiesResponse(
                key = "key1",
                value = JsonPrimitive("value1")
            )
        )
        val result = input.toListSimpleProperties()
        assertEquals(1, result.size)
        assertEquals("key1", result[0].key)
        assertEquals(JsonPrimitive("value1"), result[0].value)
    }

    @Test
    fun `given multiple items list when toListSimpleProperties then returns list with all SimpleProperties`() {
        val input = listOf(
            SimplePropertiesResponse(
                key = "key1",
                value = JsonPrimitive("value1")
            ),
            SimplePropertiesResponse(
                key = "key2",
                value = JsonPrimitive("value2")
            ),
            SimplePropertiesResponse(
                key = "key3",
                value = JsonPrimitive(123)
            )
        )
        val result = input.toListSimpleProperties()
        assertEquals(3, result.size)
        assertEquals("key1", result[0].key)
        assertEquals("key2", result[1].key)
        assertEquals("key3", result[2].key)
        assertEquals(JsonPrimitive("value1"), result[0].value)
        assertEquals(JsonPrimitive("value2"), result[1].value)
        assertEquals(JsonPrimitive(123), result[2].value)
    }

    @Test
    fun `given list with null values when toListSimpleProperties then returns list with null values preserved`() {
        val jsonElement = JsonPrimitive("null")
        val input = listOf(
            SimplePropertiesResponse(
                key = "key1",
                value = jsonElement
            )
        )
        val result = input.toListSimpleProperties()
        assertEquals(1, result.size)
        assertEquals("key1", result[0].key)
        assertEquals(jsonElement, result[0].value)
    }

    @Test
    fun `given SimplePropertiesResponse when toSimpleProperties then returns SimpleProperties with same key and value`() {
        val input = SimplePropertiesResponse(
            key = "testKey",
            value = JsonPrimitive("testValue")
        )
        val result = input.toSimpleProperties()
        assertEquals("testKey", result.key)
        assertEquals(JsonPrimitive("testValue"), result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with numeric value when toSimpleProperties then returns SimpleProperties with numeric value`() {
        val input = SimplePropertiesResponse(
            key = "numericKey",
            value = JsonPrimitive(999)
        )
        val result = input.toSimpleProperties()
        assertEquals("numericKey", result.key)
        assertEquals(JsonPrimitive(999), result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with boolean value when toSimpleProperties then returns SimpleProperties with boolean value`() {
        val input = SimplePropertiesResponse(
            key = "boolKey",
            value = JsonPrimitive(false)
        )
        val result = input.toSimpleProperties()
        assertEquals("boolKey", result.key)
        assertEquals(JsonPrimitive(false), result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with complex object value when toSimpleProperties then returns SimpleProperties with object value`() {
        val complexObject = buildJsonObject {
            put("nested", "object")
        }
        val input = SimplePropertiesResponse(
            key = "complexKey",
            value = complexObject
        )
        val result = input.toSimpleProperties()
        assertEquals("complexKey", result.key)
        assertEquals(complexObject, result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with empty string key when toSimpleProperties then returns SimpleProperties with empty string key`() {
        val input = SimplePropertiesResponse(
            key = "",
            value = JsonPrimitive("value")
        )
        val result = input.toSimpleProperties()
        assertEquals("", result.key)
        assertEquals(JsonPrimitive("value"), result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with special characters in key when toSimpleProperties then returns SimpleProperties preserving special characters`() {
        val input = SimplePropertiesResponse(
            key = "key-with_special.chars",
            value = JsonPrimitive("value")
        )
        val result = input.toSimpleProperties()
        assertEquals("key-with_special.chars", result.key)
    }

    @Test
    fun `given JsonElement with double value when convertToElement then returns deserialized double`() {
        val jsonElement = JsonPrimitive(3.14)
        val result: Double? = jsonElement.convertToElement<Double>()
        assertEquals(3.14, result)
    }

    @Test
    fun `given JsonElement with long value when convertToElement then returns deserialized long`() {
        val jsonElement = JsonPrimitive(9999999999L)
        val result: Long? = jsonElement.convertToElement<Long>()
        assertEquals(9999999999L, result)
    }

    @Test
    fun `given type mismatch JsonElement when convertToElement then returns null`() {
        val jsonElement = JsonPrimitive("not_a_boolean")
        val result: Boolean? = jsonElement.convertToElement<Boolean>()
        assertNull(result)
    }

    @Test
    fun `given JsonElement with array when convertToElement then returns deserialized list`() {
        val json = Json.parseToJsonElement("""["a", "b", "c"]""")
        val result: List<String>? = json.convertToElement<List<String>>()
        assertEquals(listOf("a", "b", "c"), result)
    }

    @Test
    fun `given JsonElement with nested object when convertToElement then returns deserialized nested structure`() {
        val json = Json.parseToJsonElement("""{"outer": {"inner": "value"}}""")
        val result: Map<String, Any>? = json.convertToElement<Map<String, Any>>()
        assertEquals(true, result != null)
    }
}