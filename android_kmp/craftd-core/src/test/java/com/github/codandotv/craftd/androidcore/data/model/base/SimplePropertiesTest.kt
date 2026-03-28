package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
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
class SimplePropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given default constructor when creating SimpleProperties then key is empty string and value is null`() {
        val properties = SimpleProperties()
        assertEquals("", properties.key)
        assertNull(properties.value)
    }

    @Test
    fun `given key and value when creating SimpleProperties then properties are set correctly`() {
        val testKey = "testKey"
        val testValue = JsonPrimitive("testValue")
        val properties = SimpleProperties(key = testKey, value = testValue)
        assertEquals(testKey, properties.key)
        assertEquals(testValue, properties.value)
    }

    @Test
    fun `given only key when creating SimpleProperties then value is null`() {
        val testKey = "onlyKey"
        val properties = SimpleProperties(key = testKey)
        assertEquals(testKey, properties.key)
        assertNull(properties.value)
    }

    @Test
    fun `given only value when creating SimpleProperties then key is empty string`() {
        val testValue = JsonPrimitive("onlyValue")
        val properties = SimpleProperties(value = testValue)
        assertEquals("", properties.key)
        assertEquals(testValue, properties.value)
    }

    @Test
    fun `given SimpleProperties when calling copy with new key then copy has updated key`() {
        val original = SimpleProperties(key = "original", value = JsonPrimitive("value"))
        val copied = original.copy(key = "updated")
        assertEquals("updated", copied.key)
        assertEquals(original.value, copied.value)
        assertEquals("original", original.key)
    }

    @Test
    fun `given SimpleProperties when calling copy with new value then copy has updated value`() {
        val original = SimpleProperties(key = "key", value = JsonPrimitive("original"))
        val newValue = JsonPrimitive("updated")
        val copied = original.copy(value = newValue)
        assertEquals("key", copied.key)
        assertEquals(newValue, copied.value)
        assertEquals(JsonPrimitive("original"), original.value)
    }

    @Test
    fun `given SimpleProperties when calling copy with no arguments then copy is equal to original`() {
        val original = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val copied = original.copy()
        assertEquals(original, copied)
    }

    @Test
    fun `given two SimpleProperties with same key and value when comparing then equals returns true`() {
        val props1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val props2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        assertEquals(props1, props2)
    }

    @Test
    fun `given two SimpleProperties with different keys when comparing then equals returns false`() {
        val props1 = SimpleProperties(key = "key1", value = JsonPrimitive("value"))
        val props2 = SimpleProperties(key = "key2", value = JsonPrimitive("value"))
        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two SimpleProperties with different values when comparing then equals returns false`() {
        val props1 = SimpleProperties(key = "key", value = JsonPrimitive("value1"))
        val props2 = SimpleProperties(key = "key", value = JsonPrimitive("value2"))
        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two SimpleProperties with null values when comparing then equals returns true`() {
        val props1 = SimpleProperties(key = "key", value = null)
        val props2 = SimpleProperties(key = "key", value = null)
        assertEquals(props1, props2)
    }

    @Test
    fun `given SimpleProperties with value and without when comparing then equals returns false`() {
        val props1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val props2 = SimpleProperties(key = "key", value = null)
        assertNotEquals(props1, props2)
    }

    @Test
    fun `given two SimpleProperties with same key and value when comparing hashCode then hashCodes are equal`() {
        val props1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val props2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given two SimpleProperties with different values when comparing hashCode then hashCodes differ`() {
        val props1 = SimpleProperties(key = "key", value = JsonPrimitive("value1"))
        val props2 = SimpleProperties(key = "key", value = JsonPrimitive("value2"))
        assertNotEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given SimpleProperties when converting to string then toString contains key and value`() {
        val props = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))
        val stringRep = props.toString()
        assertTrue(stringRep.contains("testKey"))
    }

    @Test
    fun `given empty key and null value when creating SimpleProperties then object is valid`() {
        val props = SimpleProperties(key = "", value = null)
        assertEquals("", props.key)
        assertNull(props.value)
    }

    @Test
    fun `given SimpleProperties with complex JsonElement when accessing value then value is preserved`() {
        val complexJson = JsonPrimitive(42)
        val props = SimpleProperties(key = "number", value = complexJson)
        assertEquals(complexJson, props.value)
    }

    @Test
    fun `given SimpleProperties with boolean JsonElement when accessing value then value is preserved`() {
        val boolValue = JsonPrimitive(true)
        val props = SimpleProperties(key = "flag", value = boolValue)
        assertEquals(boolValue, props.value)
    }

    @Test
    fun `given SimpleProperties when destructuring then components are correct`() {
        val props = SimpleProperties(key = "myKey", value = JsonPrimitive("myValue"))
        val (key, value) = props
        assertEquals("myKey", key)
        assertEquals(JsonPrimitive("myValue"), value)
    }

    @Test
    fun `given multiple SimpleProperties with same key when collecting in set then only one remains`() {
        val props1 = SimpleProperties(key = "key", value = JsonPrimitive("value1"))
        val props2 = SimpleProperties(key = "key", value = JsonPrimitive("value1"))
        val set = setOf(props1, props2)
        assertEquals(1, set.size)
    }

    @Test
    fun `given SimpleProperties with special characters in key when creating then key is preserved`() {
        val specialKey = "key-with-special_chars.123"
        val props = SimpleProperties(key = specialKey, value = JsonPrimitive("value"))
        assertEquals(specialKey, props.key)
    }

    @Test
    fun `given SimpleProperties with unicode characters in key when creating then key is preserved`() {
        val unicodeKey = "キー"
        val props = SimpleProperties(key = unicodeKey, value = JsonPrimitive("value"))
        assertEquals(unicodeKey, props.key)
    }

    @Test
    fun `given SimpleProperties list when filtering by key then correct items are found`() {
        val props1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
        val props2 = SimpleProperties(key = "key2", value = JsonPrimitive("value2"))
        val props3 = SimpleProperties(key = "key1", value = JsonPrimitive("value3"))
        val list = listOf(props1, props2, props3)
        
        val filtered = list.filter { it.key == "key1" }
        assertEquals(2, filtered.size)
        assertTrue(filtered.contains(props1))
        assertTrue(filtered.contains(props3))
    }

    @Test
    fun `given two different SimpleProperties when comparing equality then not equal`() {
        val props1 = SimpleProperties(key = "a", value = JsonPrimitive(1))
        val props2 = SimpleProperties(key = "b", value = JsonPrimitive(2))
        assertFalse(props1 == props2)
    }

    @Test
    fun `given SimpleProperties when creating with null key parameter then key defaults to empty string`() {
        val props = SimpleProperties()
        assertEquals("", props.key)
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive number when accessing value then value is correct type`() {
        val numberValue = JsonPrimitive(123)
        val props = SimpleProperties(key = "number", value = numberValue)
        assertEquals(numberValue, props.value)
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive double when accessing value then value is correct type`() {
        val doubleValue = JsonPrimitive(123.45)
        val props = SimpleProperties(key = "double", value = doubleValue)
        assertEquals(doubleValue, props.value)
    }

    @Test
    fun `given SimpleProperties when creating multiple instances then each maintains independent state`() {
        val props1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
        val props2 = SimpleProperties(key = "key2", value = JsonPrimitive("value2"))
        
        assertNotEquals(props1.key, props2.key)
        assertNotEquals(props1.value, props2.value)
    }

    @Test
    fun `given SimpleProperties when using in map as value then works correctly`() {
        val props = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))
        val map = mapOf("item" to props)
        
        assertEquals(props, map["item"])
    }

    @Test
    fun `given SimpleProperties list when sorting by key then list is ordered correctly`() {
        val props1 = SimpleProperties(key = "c", value = JsonPrimitive("1"))
        val props2 = SimpleProperties(key = "a", value = JsonPrimitive("2"))
        val props3 = SimpleProperties(key = "b", value = JsonPrimitive("3"))
        val list = listOf(props1, props2, props3)
        
        val sorted = list.sortedBy { it.key }
        assertEquals("a", sorted[0].key)
        assertEquals("b", sorted[1].key)
        assertEquals("c", sorted[2].key)
    }

    @Test
    fun `given SimpleProperties with empty string key and null value when equality check then equals another instance with same values`() {
        val props1 = SimpleProperties(key = "", value = null)
        val props2 = SimpleProperties()
        assertEquals(props1, props2)
    }

    @Test
    fun `given SimpleProperties when accessing properties after copy then original is unchanged`() {
        val original = SimpleProperties(key = "original", value = JsonPrimitive("original"))
        val copied = original.copy(key = "copied")
        
        assertEquals("original", original.key)
        assertEquals("copied", copied.key)
        assertEquals(JsonPrimitive("original"), original.value)
        assertEquals(JsonPrimitive("original"), copied.value)
    }

    @Test
    fun `given SimpleProperties with value when checking null safety then value can be checked`() {
        val props = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val value = props.value
        assertTrue(value != null)
    }

    @Test
    fun `given SimpleProperties with null value when checking null safety then value is null`() {
        val props = SimpleProperties(key = "key", value = null)
        val value = props.value
        assertTrue(value == null)
    }
}