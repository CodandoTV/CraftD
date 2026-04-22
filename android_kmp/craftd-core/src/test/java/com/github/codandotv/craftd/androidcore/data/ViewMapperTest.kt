package com.github.codandotv.craftd.androidcore.data

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse
import io.mockk.MockKAnnotations
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.Serializable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class ViewMapperTest {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given null JsonElement when convertToElement then returns null`() {
        val result: String? = null.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given valid JsonElement for string when convertToElement then returns deserialized string`() {
        val jsonElement = JsonPrimitive("test_value")
        val result: String? = jsonElement.convertToElement()
        assertEquals("test_value", result)
    }

    @Test
    fun `given valid JsonElement for number when convertToElement then returns deserialized int`() {
        val jsonElement = JsonPrimitive(42)
        val result: Int? = jsonElement.convertToElement()
        assertEquals(42, result)
    }

    @Test
    fun `given valid JsonElement for boolean when convertToElement then returns deserialized boolean`() {
        val jsonElement = JsonPrimitive(true)
        val result: Boolean? = jsonElement.convertToElement()
        assertEquals(true, result)
    }

    @Test
    fun `given valid complex JsonElement when convertToElement then returns deserialized object`() {
        @Serializable
        data class TestData(val name: String, val age: Int)

        val jsonElement = buildJsonObject {
            put("name", "John")
            put("age", 30)
        }
        val result: TestData? = jsonElement.convertToElement()
        assertNotNull(result)
        assertEquals("John", result.name)
        assertEquals(30, result.age)
    }

    @Test
    fun `given JsonElement with unknown keys when convertToElement then ignores unknown keys and returns object`() {
        @Serializable
        data class TestData(val name: String)

        val jsonElement = buildJsonObject {
            put("name", "John")
            put("unknown_field", "value")
        }
        val result: TestData? = jsonElement.convertToElement()
        assertNotNull(result)
        assertEquals("John", result.name)
    }

    @Test
    fun `given malformed JsonElement when convertToElement then returns null`() {
        @Serializable
        data class TestData(val age: Int)

        val jsonElement = JsonPrimitive("not_a_number")
        val result: TestData? = jsonElement.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given empty list when toListSimpleProperties then returns empty list`() {
        val input: List<SimplePropertiesResponse> = emptyList()
        val result = input.toListSimpleProperties()
        assertEquals(0, result.size)
    }

    @Test
    fun `given single SimplePropertiesResponse when toListSimpleProperties then returns list with single SimpleProperties`() {
        val input = listOf(
            SimplePropertiesResponse(
                key = "test_key",
                value = JsonPrimitive("test_value")
            )
        )
        val result = input.toListSimpleProperties()
        assertEquals(1, result.size)
        assertEquals("test_key", result[0].key)
        assertEquals(JsonPrimitive("test_value"), result[0].value)
    }

    @Test
    fun `given multiple SimplePropertiesResponse when toListSimpleProperties then returns list with all SimpleProperties`() {
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
                value = JsonPrimitive("value3")
            )
        )
        val result = input.toListSimpleProperties()
        assertEquals(3, result.size)
        assertEquals("key1", result[0].key)
        assertEquals("key2", result[1].key)
        assertEquals("key3", result[2].key)
    }

    @Test
    fun `given SimplePropertiesResponse with null value when toListSimpleProperties then preserves null value`() {
        val input = listOf(
            SimplePropertiesResponse(
                key = "test_key",
                value = JsonPrimitive(null)
            )
        )
        val result = input.toListSimpleProperties()
        assertEquals(1, result.size)
        assertEquals("test_key", result[0].key)
        assertNotNull(result[0].value)
    }

    @Test
    fun `given SimplePropertiesResponse when toSimpleProperties then returns SimpleProperties with same key and value`() {
        val input = SimplePropertiesResponse(
            key = "test_key",
            value = JsonPrimitive("test_value")
        )
        val result = input.toSimpleProperties()
        assertEquals("test_key", result.key)
        assertEquals(JsonPrimitive("test_value"), result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with number value when toSimpleProperties then correctly maps value`() {
        val input = SimplePropertiesResponse(
            key = "numeric_key",
            value = JsonPrimitive(123)
        )
        val result = input.toSimpleProperties()
        assertEquals("numeric_key", result.key)
        assertEquals(JsonPrimitive(123), result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with complex value when toSimpleProperties then preserves complex JsonElement`() {
        val complexValue = buildJsonObject {
            put("nested", "data")
        }
        val input = SimplePropertiesResponse(
            key = "complex_key",
            value = complexValue
        )
        val result = input.toSimpleProperties()
        assertEquals("complex_key", result.key)
        assertEquals(complexValue, result.value)
    }

    @Test
    fun `given SimplePropertiesResponse with empty string key when toSimpleProperties then maps empty key`() {
        val input = SimplePropertiesResponse(
            key = "",
            value = JsonPrimitive("value")
        )
        val result = input.toSimpleProperties()
        assertEquals("", result.key)
        assertEquals(JsonPrimitive("value"), result.value)
    }

    @Test
    fun `given JsonElement with deeply nested structure when convertToElement then deserializes correctly`() {
        @Serializable
        data class Inner(val value: String)

        @Serializable
        data class Outer(val inner: Inner)

        val jsonElement = buildJsonObject {
            put("inner", buildJsonObject {
                put("value", "nested_value")
            })
        }
        val result: Outer? = jsonElement.convertToElement()
        assertNotNull(result)
        assertEquals("nested_value", result.inner.value)
    }

    @Test
    fun `given JsonElement array when convertToElement then deserializes to list`() {
        val jsonElement = Json.parseToJsonElement("""["item1", "item2", "item3"]""")
        val result: List<String>? = jsonElement.convertToElement()
        assertNotNull(result)
        assertEquals(3, result.size)
        assertEquals("item1", result[0])
        assertEquals("item2", result[1])
        assertEquals("item3", result[2])
    }

    @Test
    fun `given wrong type JsonElement when convertToElement then returns null`() {
        @Serializable
        data class TestData(val id: Int)

        val jsonElement = buildJsonObject {
            put("id", "not_an_int")
        }
        val result: TestData? = jsonElement.convertToElement()
        assertNull(result)
    }

    @Test
    fun `given multiple SimplePropertiesResponse with mixed value types when toListSimpleProperties then preserves all value types`() {
        val input = listOf(
            SimplePropertiesResponse(key = "string_key", value = JsonPrimitive("string_value")),
            SimplePropertiesResponse(key = "int_key", value = JsonPrimitive(42)),
            SimplePropertiesResponse(key = "bool_key", value = JsonPrimitive(true)),
            SimplePropertiesResponse(key = "double_key", value = JsonPrimitive(3.14))
        )
        val result = input.toListSimpleProperties()
        assertEquals(4, result.size)
        assertEquals("string_key", result[0].key)
        assertEquals("int_key", result[1].key)
        assertEquals("bool_key", result[2].key)
        assertEquals("double_key", result[3].key)
    }
}