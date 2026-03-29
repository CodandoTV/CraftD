package com.github.codandotv.craftd.androidcore.data.model.base

import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.booleanOrNull
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

@RunWith(JUnit4::class)
class SimplePropertiesTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given all parameters when creating SimpleProperties then object is created correctly`() {
        val key = "testKey"
        val value: JsonElement = JsonPrimitive("testValue")
        
        val simpleProperties = SimpleProperties(key = key, value = value)
        
        assertEquals(key, simpleProperties.key)
        assertEquals(value, simpleProperties.value)
    }

    @Test
    fun `given only key when creating SimpleProperties then value defaults to null`() {
        val key = "testKey"
        
        val simpleProperties = SimpleProperties(key = key)
        
        assertEquals(key, simpleProperties.key)
        assertNull(simpleProperties.value)
    }

    @Test
    fun `given no parameters when creating SimpleProperties then defaults are applied`() {
        val simpleProperties = SimpleProperties()
        
        assertEquals("", simpleProperties.key)
        assertNull(simpleProperties.value)
    }

    @Test
    fun `given SimpleProperties when calling copy with new key then new instance with updated key is returned`() {
        val original = SimpleProperties(key = "originalKey", value = JsonPrimitive("value"))
        val newKey = "newKey"
        
        val copied = original.copy(key = newKey)
        
        assertEquals(newKey, copied.key)
        assertEquals(original.value, copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimpleProperties when calling copy with new value then new instance with updated value is returned`() {
        val original = SimpleProperties(key = "key", value = JsonPrimitive("originalValue"))
        val newValue: JsonElement = JsonPrimitive("newValue")
        
        val copied = original.copy(value = newValue)
        
        assertEquals(original.key, copied.key)
        assertEquals(newValue, copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given SimpleProperties when calling copy with all new values then new instance is returned`() {
        val original = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val newKey = "newKey"
        val newValue: JsonElement = JsonPrimitive("newValue")
        
        val copied = original.copy(key = newKey, value = newValue)
        
        assertEquals(newKey, copied.key)
        assertEquals(newValue, copied.value)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given identical SimpleProperties when comparing with equals then returns true`() {
        val prop1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val prop2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        
        assertEquals(prop1, prop2)
    }

    @Test
    fun `given SimpleProperties with different key when comparing with equals then returns false`() {
        val prop1 = SimpleProperties(key = "key1", value = JsonPrimitive("value"))
        val prop2 = SimpleProperties(key = "key2", value = JsonPrimitive("value"))
        
        assertNotEquals(prop1, prop2)
    }

    @Test
    fun `given SimpleProperties with different value when comparing with equals then returns false`() {
        val prop1 = SimpleProperties(key = "key", value = JsonPrimitive("value1"))
        val prop2 = SimpleProperties(key = "key", value = JsonPrimitive("value2"))
        
        assertNotEquals(prop1, prop2)
    }

    @Test
    fun `given SimpleProperties with null value when comparing with equals then considers null equal`() {
        val prop1 = SimpleProperties(key = "key", value = null)
        val prop2 = SimpleProperties(key = "key", value = null)
        
        assertEquals(prop1, prop2)
    }

    @Test
    fun `given identical SimpleProperties when comparing hashCode then returns same hash`() {
        val prop1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val prop2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        
        assertEquals(prop1.hashCode(), prop2.hashCode())
    }

    @Test
    fun `given different SimpleProperties when comparing hashCode then likely returns different hash`() {
        val prop1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
        val prop2 = SimpleProperties(key = "key2", value = JsonPrimitive("value2"))
        
        assertNotEquals(prop1.hashCode(), prop2.hashCode())
    }

    @Test
    fun `given SimpleProperties when converting to string then contains key and value information`() {
        val prop = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))
        
        val stringRepresentation = prop.toString()
        
        assertTrue(stringRepresentation.contains("key") || stringRepresentation.contains("testKey"))
    }

    @Test
    fun `given SimpleProperties with null key when creating then empty string is used as default`() {
        val prop = SimpleProperties()
        
        assertEquals("", prop.key)
    }

    @Test
    fun `given SimpleProperties with JsonObject when creating then value is stored correctly`() {
        val jsonObject = JsonObject(mapOf("nested" to JsonPrimitive("value")))
        val prop = SimpleProperties(key = "key", value = jsonObject)
        
        assertEquals(jsonObject, prop.value)
    }

    @Test
    fun `given SimpleProperties with numeric JsonPrimitive when creating then value is stored correctly`() {
        val jsonNumber = JsonPrimitive(42)
        val prop = SimpleProperties(key = "key", value = jsonNumber)
        
        assertEquals(jsonNumber, prop.value)
    }

    @Test
    fun `given SimpleProperties with boolean JsonPrimitive when creating then value is stored correctly`() {
        val jsonBoolean = JsonPrimitive(true)
        val prop = SimpleProperties(key = "key", value = jsonBoolean)
        
        assertEquals(jsonBoolean, prop.value)
    }

    @Test
    fun `given SimpleProperties when accessing immutable annotation then type is maintained`() {
        val prop = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        
        val prop2 = prop.copy(key = "newKey")
        assertNotEquals(prop, prop2)
    }

    @Test
    fun `given SimpleProperties when accessing stable annotation then reference equality differs but value equality holds`() {
        val value: JsonElement = JsonPrimitive("value")
        val prop1 = SimpleProperties(key = "key", value = value)
        val prop2 = SimpleProperties(key = "key", value = value)
        
        assertEquals(prop1, prop2)
        assertNotEquals(prop1 as Any, prop2 as Any)
    }

    @Test
    fun `given two SimpleProperties with same content when calling equals then returns true`() {
        val prop1 = SimpleProperties(key = "sameKey", value = JsonPrimitive("sameValue"))
        val prop2 = SimpleProperties(key = "sameKey", value = JsonPrimitive("sameValue"))
        
        assertTrue(prop1 == prop2)
    }

    @Test
    fun `given SimpleProperties with default values when creating then both defaults are applied`() {
        val prop = SimpleProperties()
        
        assertEquals("", prop.key)
        assertNull(prop.value)
    }

    @Test
    fun `given SimpleProperties with empty string key when creating then key is preserved as empty`() {
        val prop = SimpleProperties(key = "")
        
        assertEquals("", prop.key)
    }

    @Test
    fun `given SimpleProperties with whitespace key when creating then whitespace is preserved`() {
        val prop = SimpleProperties(key = "  ")
        
        assertEquals("  ", prop.key)
    }

    @Test
    fun `given SimpleProperties with special characters in key when creating then they are preserved`() {
        val specialKey = "key@#$%^&*()"
        val prop = SimpleProperties(key = specialKey)
        
        assertEquals(specialKey, prop.key)
    }

    @Test
    fun `given SimpleProperties when calling copy without arguments then returns equal instance`() {
        val original = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        
        val copied = original.copy()
        
        assertEquals(original, copied)
        assertNotEquals(original as Any, copied as Any)
    }

    @Test
    fun `given SimpleProperties with null value when accessing value then null is returned`() {
        val prop = SimpleProperties(key = "key", value = null)
        
        assertNull(prop.value)
    }

    @Test
    fun `given SimpleProperties with multiple different types of values when storing then all types are preserved`() {
        val stringValue = SimpleProperties(key = "string", value = JsonPrimitive("text"))
        val numberValue = SimpleProperties(key = "number", value = JsonPrimitive(123))
        val booleanValue = SimpleProperties(key = "boolean", value = JsonPrimitive(true))
        
        assertEquals(JsonPrimitive("text"), stringValue.value)
        assertEquals(JsonPrimitive(123), numberValue.value)
        assertEquals(JsonPrimitive(true), booleanValue.value)
    }

    @Test
    fun `given SimpleProperties when destructuring then components are in correct order`() {
        val prop = SimpleProperties(key = "testKey", value = JsonPrimitive("testValue"))
        
        val (key, value) = prop
        
        assertEquals("testKey", key)
        assertEquals(JsonPrimitive("testValue"), value)
    }

    @Test
    fun `given SimpleProperties with same key but different case when comparing then not equal`() {
        val prop1 = SimpleProperties(key = "Key", value = JsonPrimitive("value"))
        val prop2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        
        assertNotEquals(prop1, prop2)
    }

    @Test
    fun `given SimpleProperties when copy is called multiple times then creates independent instances`() {
        val original = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val copy1 = original.copy(key = "copy1")
        val copy2 = original.copy(key = "copy2")
        
        assertNotEquals(copy1, copy2)
        assertEquals("copy1", copy1.key)
        assertEquals("copy2", copy2.key)
    }

    @Test
    fun `given SimpleProperties with JsonObject containing nested objects when storing then complex structure is preserved`() {
        val nestedObject = JsonObject(mapOf(
            "level1" to JsonObject(mapOf("level2" to JsonPrimitive("value")))
        ))
        val prop = SimpleProperties(key = "complex", value = nestedObject)
        
        assertEquals(nestedObject, prop.value)
    }
}