package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
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
import kotlin.test.assertTrue

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
    fun `given only key when creating SimpleProperties then key is set and value is null`() {
        val testKey = "onlyKey"
        val properties = SimpleProperties(key = testKey)
        
        assertEquals(testKey, properties.key)
        assertNull(properties.value)
    }

    @Test
    fun `given only value when creating SimpleProperties then value is set and key is empty string`() {
        val testValue = JsonPrimitive(123)
        val properties = SimpleProperties(value = testValue)
        
        assertEquals("", properties.key)
        assertEquals(testValue, properties.value)
    }

    @Test
    fun `given SimpleProperties instance when calling copy with new key then new instance has updated key`() {
        val original = SimpleProperties(key = "original", value = JsonPrimitive("value"))
        val copied = original.copy(key = "modified")
        
        assertEquals("modified", copied.key)
        assertEquals(original.value, copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimpleProperties instance when calling copy with new value then new instance has updated value`() {
        val originalValue = JsonPrimitive("original")
        val newValue = JsonPrimitive("modified")
        val original = SimpleProperties(key = "key", value = originalValue)
        val copied = original.copy(value = newValue)
        
        assertEquals(original.key, copied.key)
        assertEquals(newValue, copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimpleProperties instance when calling copy with both parameters then new instance has both updated`() {
        val original = SimpleProperties(key = "original", value = JsonPrimitive("value"))
        val copied = original.copy(key = "newKey", value = JsonPrimitive("newValue"))
        
        assertEquals("newKey", copied.key)
        assertEquals(JsonPrimitive("newValue"), copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given two SimpleProperties with same key and value when comparing then they are equal`() {
        val properties1 = SimpleProperties(key = "sameKey", value = JsonPrimitive("sameValue"))
        val properties2 = SimpleProperties(key = "sameKey", value = JsonPrimitive("sameValue"))
        
        assertEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with different keys when comparing then they are not equal`() {
        val properties1 = SimpleProperties(key = "key1", value = JsonPrimitive("value"))
        val properties2 = SimpleProperties(key = "key2", value = JsonPrimitive("value"))
        
        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with different values when comparing then they are not equal`() {
        val properties1 = SimpleProperties(key = "key", value = JsonPrimitive("value1"))
        val properties2 = SimpleProperties(key = "key", value = JsonPrimitive("value2"))
        
        assertNotEquals(properties1, properties2)
    }

    @Test
    fun `given two SimpleProperties with null values when comparing then they are equal`() {
        val properties1 = SimpleProperties(key = "key", value = null)
        val properties2 = SimpleProperties(key = "key", value = null)
        
        assertEquals(properties1, properties2)
    }

    @Test
    fun `given SimpleProperties instance when calling hashCode with same properties then hash codes are equal`() {
        val properties1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val properties2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        
        assertEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given SimpleProperties instance when calling hashCode with different properties then hash codes may differ`() {
        val properties1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
        val properties2 = SimpleProperties(key = "key2", value = JsonPrimitive("value2"))
        
        assertNotEquals(properties1.hashCode(), properties2.hashCode())
    }

    @Test
    fun `given SimpleProperties with JsonNull value when comparing then properties work correctly`() {
        val properties1 = SimpleProperties(key = "key", value = JsonNull)
        val properties2 = SimpleProperties(key = "key", value = JsonNull)
        
        assertEquals(properties1, properties2)
    }

    @Test
    fun `given SimpleProperties with complex JsonElement when constructing then value is stored correctly`() {
        val jsonValue = JsonPrimitive(true)
        val properties = SimpleProperties(key = "boolKey", value = jsonValue)
        
        assertEquals(jsonValue, properties.value)
        assertTrue(properties.value?.jsonPrimitive?.boolean ?: false)
    }

    @Test
    fun `given SimpleProperties with numeric JsonElement when constructing then value is stored correctly`() {
        val jsonValue = JsonPrimitive(42)
        val properties = SimpleProperties(key = "numKey", value = jsonValue)
        
        assertEquals(jsonValue, properties.value)
        assertEquals(42, properties.value?.jsonPrimitive?.int)
    }

    @Test
    fun `given empty key when creating SimpleProperties then key field is empty string`() {
        val properties = SimpleProperties(key = "", value = JsonPrimitive("value"))
        
        assertEquals("", properties.key)
    }

    @Test
    fun `given null value when creating SimpleProperties then value field is null`() {
        val properties = SimpleProperties(key = "key", value = null)
        
        assertNull(properties.value)
    }

    @Test
    fun `given SimpleProperties when toString is called then it contains key and value information`() {
        val properties = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))
        val stringRepresentation = properties.toString()
        
        assertTrue(stringRepresentation.contains("testKey"))
        assertTrue(stringRepresentation.contains("testValue"))
    }

    @Test
    fun `given default SimpleProperties when checking fields then key is empty and value is null`() {
        val default = SimpleProperties()
        
        assertEquals("", default.key)
        assertNull(default.value)
    }

    @Test
    fun `given two identical SimpleProperties when checking equality then equals returns true`() {
        val props = SimpleProperties(key = "identical", value = JsonPrimitive("identical"))
        
        assertTrue(props == props)
    }

    @Test
    fun `given SimpleProperties with whitespace key when constructing then whitespace is preserved`() {
        val keyWithSpace = "key with spaces"
        val properties = SimpleProperties(key = keyWithSpace, value = JsonPrimitive("value"))
        
        assertEquals(keyWithSpace, properties.key)
    }

    @Test
    fun `given SimpleProperties copy without parameters when called then identical instance is created`() {
        val original = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val copied = original.copy()
        
        assertEquals(original, copied)
        assertEquals(original.key, copied.key)
        assertEquals(original.value, copied.value)
    }

    @Test
    fun `given SimpleProperties with special characters in key when constructing then key is preserved`() {
        val specialKey = "key@#$%^&*()"
        val properties = SimpleProperties(key = specialKey, value = JsonPrimitive("value"))
        
        assertEquals(specialKey, properties.key)
    }

    @Test
    fun `given two SimpleProperties instances when using them in a set then deduplication works`() {
        val props1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val props2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val propSet = setOf(props1, props2)
        
        assertEquals(1, propSet.size)
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive string when accessing value then string content is accessible`() {
        val stringValue = "testString"
        val properties = SimpleProperties(key = "stringKey", value = JsonPrimitive(stringValue))
        
        assertEquals(stringValue, properties.value?.jsonPrimitive?.content)
    }

    @Test
    fun `given SimpleProperties with float JsonElement when constructing then float value is stored`() {
        val floatValue = 3.14f
        val properties = SimpleProperties(key = "floatKey", value = JsonPrimitive(floatValue))
        
        assertEquals(floatValue, properties.value?.jsonPrimitive?.float)
    }

    @Test
    fun `given SimpleProperties when data class methods are available then they function correctly`() {
        val properties = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        
        assertTrue(properties.key.isNotEmpty())
        assertTrue(properties.value != null)
    }

    @Test
    fun `given SimpleProperties with null value when checking nullability then value is null`() {
        val properties = SimpleProperties(key = "key", value = null)
        
        assertTrue(properties.value == null)
    }

    @Test
    fun `given two SimpleProperties with JsonNull value when comparing then they are equal`() {
        val properties1 = SimpleProperties(key = "key", value = JsonNull)
        val properties2 = SimpleProperties(key = "key", value = JsonNull)
        
        assertEquals(properties1, properties2)
    }
}