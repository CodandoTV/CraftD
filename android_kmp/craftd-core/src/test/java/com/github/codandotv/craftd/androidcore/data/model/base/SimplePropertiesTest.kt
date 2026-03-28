package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

@RunWith(JUnit4::class)
class SimplePropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given default constructor when creating SimpleProperties then key is empty and value is null`() {
        val properties = SimpleProperties()

        assertEquals("", properties.key)
        assertNull(properties.value)
    }

    @Test
    fun `given key and value when creating SimpleProperties then all fields are set correctly`() {
        val testKey = "testKey"
        val testValue = JsonPrimitive("testValue")

        val properties = SimpleProperties(key = testKey, value = testValue)

        assertEquals(testKey, properties.key)
        assertEquals(testValue, properties.value)
    }

    @Test
    fun `given only key when creating SimpleProperties then value defaults to null`() {
        val testKey = "onlyKey"

        val properties = SimpleProperties(key = testKey)

        assertEquals(testKey, properties.key)
        assertNull(properties.value)
    }

    @Test
    fun `given only value when creating SimpleProperties then key defaults to empty string`() {
        val testValue = JsonPrimitive(42)

        val properties = SimpleProperties(value = testValue)

        assertEquals("", properties.key)
        assertEquals(testValue, properties.value)
    }

    @Test
    fun `given two SimpleProperties with same key and value when calling equals then returns true`() {
        val key = "sameKey"
        val value = JsonPrimitive("sameValue")

        val properties1 = SimpleProperties(key = key, value = value)
        val properties2 = SimpleProperties(key = key, value = value)

        assertEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with different keys when calling equals then returns false`() {
        val value = JsonPrimitive("sameValue")

        val properties1 = SimpleProperties(key = "key1", value = value)
        val properties2 = SimpleProperties(key = "key2", value = value)

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with different values when calling equals then returns false`() {
        val key = "sameKey"

        val properties1 = SimpleProperties(key = key, value = JsonPrimitive("value1"))
        val properties2 = SimpleProperties(key = key, value = JsonPrimitive("value2"))

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with null and non-null values when calling equals then returns false`() {
        val key = "sameKey"

        val properties1 = SimpleProperties(key = key, value = null)
        val properties2 = SimpleProperties(key = key, value = JsonPrimitive("value"))

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with same content when calling hashCode then returns same hash`() {
        val key = "sameKey"
        val value = JsonPrimitive("sameValue")

        val properties1 = SimpleProperties(key = key, value = value)
        val properties2 = SimpleProperties(key = key, value = value)

        assertEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given two SimpleProperties with different content when calling hashCode then likely returns different hash`() {
        val value = JsonPrimitive("sameValue")

        val properties1 = SimpleProperties(key = "key1", value = value)
        val properties2 = SimpleProperties(key = "key2", value = value)

        assertNotEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given SimpleProperties with all fields when calling copy with no args then returns equal object`() {
        val original = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))

        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given SimpleProperties when calling copy with new key then returns object with updated key`() {
        val original = SimpleProperties(key = "originalKey", value = JsonPrimitive("testValue"))
        val newKey = "newKey"

        val copied = original.copy(key = newKey)

        assertEquals(newKey, copied.key)
        assertEquals(original.value, copied.value)
    }

    @Test
    fun `given SimpleProperties when calling copy with new value then returns object with updated value`() {
        val original = SimpleProperties(key = "testKey", value = JsonPrimitive("originalValue"))
        val newValue = JsonPrimitive("newValue")

        val copied = original.copy(value = newValue)

        assertEquals(original.key, copied.key)
        assertEquals(newValue, copied.value)
    }

    @Test
    fun `given SimpleProperties when calling copy with new key and value then returns object with both updated`() {
        val original = SimpleProperties(key = "originalKey", value = JsonPrimitive("originalValue"))
        val newKey = "newKey"
        val newValue = JsonPrimitive("newValue")

        val copied = original.copy(key = newKey, value = newValue)

        assertEquals(newKey, copied.key)
        assertEquals(newValue, copied.value)
    }

    @Test
    fun `given SimpleProperties when calling copy with null value then returns object with null value`() {
        val original = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))

        val copied = original.copy(value = null)

        assertEquals(original.key, copied.key)
        assertNull(copied.value)
    }

    @Test
    fun `given SimpleProperties when calling toString then returns valid string representation`() {
        val properties = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))

        val stringRep = properties.toString()

        assert(stringRep.contains("testKey"))
        assert(stringRep.contains("SimpleProperties"))
    }

    @Test
    fun `given SimpleProperties with empty key when constructing then key field is empty string`() {
        val properties = SimpleProperties(key = "", value = JsonPrimitive("value"))

        assertEquals("", properties.key)
    }

    @Test
    fun `given SimpleProperties with null value when constructing then value field is null`() {
        val properties = SimpleProperties(key = "key", value = null)

        assertNull(properties.value)
    }

    @Test
    fun `given two identical SimpleProperties when comparing them then both equality and hashCode match`() {
        val key = "identicalKey"
        val value = JsonPrimitive(true)

        val props1 = SimpleProperties(key = key, value = value)
        val props2 = SimpleProperties(key = key, value = value)

        assertEquals(props1, props2)
        assertEquals(props1.hashCode(), props2.hashCode())
    }

    @Test
    fun `given SimpleProperties with complex JsonElement when copying then preserves complex value`() {
        val complexValue = JsonPrimitive(123.45)
        val original = SimpleProperties(key = "complexKey", value = complexValue)

        val copied = original.copy()

        assertEquals(original, copied)
        assertEquals(complexValue, copied.value)
    }

    @Test
    fun `given multiple SimpleProperties with same key but different values when storing in set then handles distinctness correctly`() {
        val properties1 = SimpleProperties(key = "key", value = JsonPrimitive("value1"))
        val properties2 = SimpleProperties(key = "key", value = JsonPrimitive("value2"))

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given SimpleProperties created with default params when accessing fields then fields match defaults`() {
        val defaultProps = SimpleProperties()

        assertEquals("", defaultProps.key)
        assertNull(defaultProps.value)
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive string value when accessing value then returns correct JsonElement`() {
        val stringValue = JsonPrimitive("testString")
        val properties = SimpleProperties(key = "key", value = stringValue)

        assertEquals(stringValue, properties.value)
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive numeric value when accessing value then returns correct JsonElement`() {
        val numericValue = JsonPrimitive(42)
        val properties = SimpleProperties(key = "key", value = numericValue)

        assertEquals(numericValue, properties.value)
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive boolean value when accessing value then returns correct JsonElement`() {
        val boolValue = JsonPrimitive(false)
        val properties = SimpleProperties(key = "key", value = boolValue)

        assertEquals(boolValue, properties.value)
    }

    @Test
    fun `given SimpleProperties instance when checking immutability contract then data class provides immutability`() {
        val original = SimpleProperties(key = "immutable", value = JsonPrimitive("value"))
        val modified = original.copy(key = "changed")

        assertNotEquals(original, modified)
        assertEquals("immutable", original.key)
    }

    @Test
    fun `given two SimpleProperties with whitespace-different keys when comparing then they are not equal`() {
        val properties1 = SimpleProperties(key = "key ", value = JsonPrimitive("value"))
        val properties2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))

        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given SimpleProperties copy when modifying copy then original remains unchanged`() {
        val original = SimpleProperties(key = "original", value = JsonPrimitive("value"))
        val modified = original.copy(key = "modified")

        assertEquals("original", original.key)
        assertEquals("modified", modified.key)
    }
}