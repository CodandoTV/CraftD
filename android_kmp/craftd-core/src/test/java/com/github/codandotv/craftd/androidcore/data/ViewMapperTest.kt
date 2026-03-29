package com.github.codandotv.craftd.androidcore.data

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonObject
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNull
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse

@RunWith(JUnit4::class)
class ViewMapperTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    data class TestSerializableClass(
        val name: String,
        val age: Int
    )

    @Test
    fun `given null JsonElement when convertToElement then returns null`() {
        val result: TestSerializableClass? = null.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given valid JsonElement when convertToElement then returns deserialized object`() {
        val jsonElement = buildJsonObject {
            put("name", "John")
            put("age", 30)
        }
        val result: TestSerializableClass? = jsonElement.convertToElement()
        assertEquals("John", result?.name)
        assertEquals(30, result?.age)
    }

    @Test
    fun `given JsonElement with extra unknown keys when convertToElement then returns deserialized object ignoring unknown keys`() {
        val jsonElement = buildJsonObject {
            put("name", "Jane")
            put("age", 25)
            put("unknownKey", "unknownValue")
        }
        val result: TestSerializableClass? = jsonElement.convertToElement()
        assertEquals("Jane", result?.name)
        assertEquals(25, result?.age)
    }

    @Test
    fun `given malformed JsonElement when convertToElement then returns null`() {
        val jsonElement = JsonPrimitive("invalid")
        val result: TestSerializableClass? = jsonElement.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given JsonElement with wrong type when convertToElement then returns null`() {
        val jsonElement = buildJsonObject {
            put("name", "Test")
            put("age", "notAnInt")
        }
        val result: TestSerializableClass? = jsonElement.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given JsonNull when convertToElement then returns null`() {
        val result: TestSerializableClass? = JsonNull.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given empty list when toListSimpleProperties then returns empty list`() {
        val input: List<SimplePropertiesResponse> = emptyList()
        val result = input.toListSimpleProperties()
        assertEquals(0, result.size)
    }

    @Test
    fun `given list with single item when toListSimpleProperties then returns mapped list with one element`() {
        val input = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        )
        val result = input.toListSimpleProperties()
        assertEquals(1, result.size)
        assertEquals("key1", result[0].key)
        assertEquals(JsonPrimitive("value1"), result[0].value)
    }

    @Test
    fun `given list with multiple items when toListSimpleProperties then returns mapped list`() {
        val input = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")),
            SimplePropertiesResponse(key = "key3", value = JsonPrimitive("value3"))
        )
        val result = input.toListSimpleProperties()
        assertEquals(3, result.size)
        assertEquals("key1", result[0].key)
        assertEquals("key2", result[1].key)
        assertEquals("key3", result[2].key)
    }

    @Test
    fun `given list with null values when toListSimpleProperties then returns mapped list with null values`() {
        val input = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonNull),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        )
        val result = input.toListSimpleProperties()
        assertEquals(2, result.size)
        assertEquals(JsonNull, result[0].value)
        assertEquals(JsonPrimitive("value2"), result[1].value)
    }

    @Test
    fun `given SimplePropertiesResponse when toSimpleProperties then returns SimpleProperties with mapped values`() {
        val input = SimplePropertiesResponse(key = "testKey", value = JsonPrimitive("testValue"))
        val result = input.toSimpleProperties()
        assertEquals("testKey", result.key)
        assertEquals(JsonPrimitive("testValue"), result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with JsonNull when toSimpleProperties then returns SimpleProperties with JsonNull`() {
        val input = SimplePropertiesResponse(key = "testKey", value = JsonNull)
        val result = input.toSimpleProperties()
        assertEquals("testKey", result.key)
        assertEquals(JsonNull, result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with complex JsonElement when toSimpleProperties then returns SimpleProperties with same JsonElement`() {
        val complexJson = buildJsonObject {
            put("nested", "value")
            putJsonObject("inner") {
                put("deep", "data")
            }
        }
        val input = SimplePropertiesResponse(key = "complexKey", value = complexJson)
        val result = input.toSimpleProperties()
        assertEquals("complexKey", result.key)
        assertEquals(complexJson, result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with empty string key when toSimpleProperties then returns SimpleProperties with empty key`() {
        val input = SimplePropertiesResponse(key = "", value = JsonPrimitive("value"))
        val result = input.toSimpleProperties()
        assertEquals("", result.key)
        assertEquals(JsonPrimitive("value"), result.value)
    }

    @Test
    fun `given JsonElement with numeric value when convertToElement then deserializes correctly`() {
        val jsonElement = buildJsonObject {
            put("name", "Test")
            put("age", 42)
        }
        val result: TestSerializableClass? = jsonElement.convertToElement()
        assertEquals("Test", result?.name)
        assertEquals(42, result?.age)
    }

    @Test
    fun `given JsonElement with boolean value when convertToElement then deserializes correctly`() {
        data class BooleanClass(val flag: Boolean)
        val jsonElement = buildJsonObject {
            put("flag", true)
        }
        val result: BooleanClass? = jsonElement.convertToElement()
        assertEquals(true, result?.flag)
    }

    @Test
    fun `given list with duplicate keys when toListSimpleProperties then returns all items including duplicates`() {
        val input = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value2"))
        )
        val result = input.toListSimpleProperties()
        assertEquals(2, result.size)
        assertEquals("key1", result[0].key)
        assertEquals("key1", result[1].key)
    }
}