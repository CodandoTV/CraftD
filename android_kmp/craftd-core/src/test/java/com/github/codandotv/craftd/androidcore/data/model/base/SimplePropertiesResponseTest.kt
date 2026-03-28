package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

@RunWith(JUnit4::class)
class SimplePropertiesResponseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given all params when constructing SimplePropertiesResponse then returns instance with correct values`() {
        val key = "test_key"
        val value = JsonPrimitive("test_value")

        val response = SimplePropertiesResponse(key = key, value = value)

        assertEquals(key, response.key)
        assertEquals(value, response.value)
    }

    @Test
    fun `given no params when constructing SimplePropertiesResponse then uses default values`() {
        val response = SimplePropertiesResponse()

        assertEquals("", response.key)
        assertNull(response.value)
    }

    @Test
    fun `given partial params when constructing SimplePropertiesResponse then applies correct defaults`() {
        val key = "partial_key"
        val response = SimplePropertiesResponse(key = key)

        assertEquals(key, response.key)
        assertNull(response.value)
    }

    @Test
    fun `given SimplePropertiesResponse when calling copy with new key then returns new instance with updated key`() {
        val original = SimplePropertiesResponse(key = "original", value = JsonPrimitive("value"))
        val value = JsonPrimitive("value")
        val copied = original.copy(key = "updated")

        assertEquals("updated", copied.key)
        assertEquals(value, copied.value)
        assertNotEquals(original.key, copied.key)
    }

    @Test
    fun `given SimplePropertiesResponse when calling copy with new value then returns new instance with updated value`() {
        val original = SimplePropertiesResponse(key = "key", value = JsonPrimitive("original"))
        val newValue = JsonPrimitive("updated")
        val copied = original.copy(value = newValue)

        assertEquals("key", copied.key)
        assertEquals(newValue, copied.value)
        assertNotEquals(original.value, copied.value)
    }

    @Test
    fun `given SimplePropertiesResponse when calling copy with all params then returns new instance with all updates`() {
        val original = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val newKey = "key2"
        val newValue = JsonPrimitive("value2")
        val copied = original.copy(key = newKey, value = newValue)

        assertEquals(newKey, copied.key)
        assertEquals(newValue, copied.value)
    }

    @Test
    fun `given two SimplePropertiesResponse with same values when comparing equality then returns true`() {
        val key = "key"
        val value = JsonPrimitive("value")
        val response1 = SimplePropertiesResponse(key = key, value = value)
        val response2 = SimplePropertiesResponse(key = key, value = value)

        assertEquals(response1, response2)
    }

    @Test
    fun `given two SimplePropertiesResponse with different keys when comparing equality then returns false`() {
        val value = JsonPrimitive("value")
        val response1 = SimplePropertiesResponse(key = "key1", value = value)
        val response2 = SimplePropertiesResponse(key = "key2", value = value)

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given two SimplePropertiesResponse with different values when comparing equality then returns false`() {
        val key = "key"
        val response1 = SimplePropertiesResponse(key = key, value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = key, value = JsonPrimitive("value2"))

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse with null value when comparing equality then works correctly`() {
        val response1 = SimplePropertiesResponse(key = "key", value = null)
        val response2 = SimplePropertiesResponse(key = "key", value = null)

        assertEquals(response1, response2)
    }

    @Test
    fun `given two equal SimplePropertiesResponse when comparing hashCode then returns same hash`() {
        val key = "key"
        val value = JsonPrimitive("value")
        val response1 = SimplePropertiesResponse(key = key, value = value)
        val response2 = SimplePropertiesResponse(key = key, value = value)

        assertEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given two different SimplePropertiesResponse when comparing hashCode then likely returns different hash`() {
        val response1 = SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))

        assertNotEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given all params when constructing DataSimplePropertiesResponse then returns instance with correct data list`() {
        val data = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        )
        val response = DataSimplePropertiesResponse(data = data)

        assertEquals(data, response.data)
        assertEquals(2, response.data.size)
    }

    @Test
    fun `given empty list when constructing DataSimplePropertiesResponse then returns instance with empty data`() {
        val data = emptyList<SimplePropertiesResponse>()
        val response = DataSimplePropertiesResponse(data = data)

        assertEquals(data, response.data)
        assertEquals(0, response.data.size)
    }

    @Test
    fun `given DataSimplePropertiesResponse when calling copy with new data then returns new instance with updated data`() {
        val original = DataSimplePropertiesResponse(
            data = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        )
        val newData = listOf(
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")),
            SimplePropertiesResponse(key = "key3", value = JsonPrimitive("value3"))
        )
        val copied = original.copy(data = newData)

        assertEquals(newData, copied.data)
        assertEquals(2, copied.data.size)
    }

    @Test
    fun `given two DataSimplePropertiesResponse with same data when comparing equality then returns true`() {
        val data = listOf(SimplePropertiesResponse(key = "key", value = JsonPrimitive("value")))
        val response1 = DataSimplePropertiesResponse(data = data)
        val response2 = DataSimplePropertiesResponse(data = data)

        assertEquals(response1, response2)
    }

    @Test
    fun `given two DataSimplePropertiesResponse with different data when comparing equality then returns false`() {
        val response1 = DataSimplePropertiesResponse(
            data = listOf(SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")))
        )
        val response2 = DataSimplePropertiesResponse(
            data = listOf(SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")))
        )

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given two equal DataSimplePropertiesResponse when comparing hashCode then returns same hash`() {
        val data = listOf(SimplePropertiesResponse(key = "key", value = JsonPrimitive("value")))
        val response1 = DataSimplePropertiesResponse(data = data)
        val response2 = DataSimplePropertiesResponse(data = data)

        assertEquals(response1.hashCode(), response2.hashCode())
    }

    @Test
    fun `given SimplePropertiesResponse with string value when extracting string then returns correct value`() {
        val stringValue = "test_string"
        val response = SimplePropertiesResponse(key = "key", value = JsonPrimitive(stringValue))

        val extracted = response.value?.jsonPrimitive?.content

        assertEquals(stringValue, extracted)
    }

    @Test
    fun `given SimplePropertiesResponse with number value when extracting number then returns correct value`() {
        val numberValue = 42
        val response = SimplePropertiesResponse(key = "key", value = JsonPrimitive(numberValue))

        val extracted = response.value?.jsonPrimitive?.int

        assertEquals(numberValue, extracted)
    }

    @Test
    fun `given SimplePropertiesResponse with boolean value when extracting boolean then returns correct value`() {
        val booleanValue = true
        val response = SimplePropertiesResponse(key = "key", value = JsonPrimitive(booleanValue))

        val extracted = response.value?.jsonPrimitive?.boolean

        assertEquals(booleanValue, extracted)
    }

    @Test
    fun `given SimplePropertiesResponse with null value when accessing value then returns null`() {
        val response = SimplePropertiesResponse(key = "key", value = null)

        assertNull(response.value)
    }

    @Test
    fun `given SimplePropertiesResponse list when filtering by key then returns correct subset`() {
        val responses = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")),
            SimplePropertiesResponse(key = "key3", value = JsonPrimitive("value3"))
        )

        val filtered = responses.filter { it.key == "key2" }

        assertEquals(1, filtered.size)
        assertEquals("key2", filtered.first().key)
    }

    @Test
    fun `given SimplePropertiesResponse list when mapping keys then returns all keys`() {
        val responses = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")),
            SimplePropertiesResponse(key = "key3", value = JsonPrimitive("value3"))
        )

        val keys = responses.map { it.key }

        assertEquals(listOf("key1", "key2", "key3"), keys)
    }

    @Test
    fun `given DataSimplePropertiesResponse with multiple items when accessing specific item then returns correct response`() {
        val response = DataSimplePropertiesResponse(
            data = listOf(
                SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
                SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
            )
        )

        assertEquals("key1", response.data[0].key)
        assertEquals("key2", response.data[1].key)
    }

    @Test
    fun `given SimplePropertiesResponse when converting to string representation then contains key and value info`() {
        val response = SimplePropertiesResponse(key = "test_key", value = JsonPrimitive("test_value"))

        val stringRep = response.toString()

        assert(stringRep.contains("test_key"))
    }

    @Test
    fun `given two SimplePropertiesResponse with same key different value when comparing then returns false`() {
        val key = "same_key"
        val response1 = SimplePropertiesResponse(key = key, value = JsonPrimitive("value1"))
        val response2 = SimplePropertiesResponse(key = key, value = JsonPrimitive("value2"))

        assertNotEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse with default values when comparing to identical default then returns true`() {
        val response1 = SimplePropertiesResponse()
        val response2 = SimplePropertiesResponse()

        assertEquals(response1, response2)
    }

    @Test
    fun `given SimplePropertiesResponse with complex json object when accessing value then returns correct element`() {
        val jsonObject = """{"nested":"value"}""".let {
            kotlinx.serialization.json.Json.parseToJsonElement(it)
        }
        val response = SimplePropertiesResponse(key = "key", value = jsonObject)

        assertEquals(jsonObject, response.value)
    }

    @Test
    fun `given DataSimplePropertiesResponse when iterating data then visits all items`() {
        val responses = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2")),
            SimplePropertiesResponse(key = "key3", value = JsonPrimitive("value3"))
        )
        val data = DataSimplePropertiesResponse(data = responses)

        var count = 0
        for (item in data.data) {
            count++
            assert(item.key.isNotEmpty())
        }

        assertEquals(3, count)
    }

    @Test
    fun `given SimplePropertiesResponse list with duplicates when deduplicating by key then returns unique items`() {
        val responses = listOf(
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key1", value = JsonPrimitive("value1")),
            SimplePropertiesResponse(key = "key2", value = JsonPrimitive("value2"))
        )

        val unique = responses.distinctBy { it.key }

        assertEquals(2, unique.size)
    }

    @Test
    fun `given SimplePropertiesResponse when using in map then works correctly as key`() {
        val response1 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val response2 = SimplePropertiesResponse(key = "key", value = JsonPrimitive("value"))
        val map = mapOf(response1 to "first")

        assert(map.containsKey(response2))
    }

    @Test
    fun `given empty DataSimplePropertiesResponse when checking data when accessing data then returns empty list`() {
        val response = DataSimplePropertiesResponse(data = emptyList())

        assertEquals(0, response.data.size)
        assert(response.data.isEmpty())
    }
}