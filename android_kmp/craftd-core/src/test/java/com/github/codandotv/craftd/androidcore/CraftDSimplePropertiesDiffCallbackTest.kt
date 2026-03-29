package com.github.codandotv.craftd.androidcore

import androidx.recyclerview.widget.DiffUtil
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import io.mockk.mockk
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.AbstractMap
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class CraftDSimplePropertiesDiffCallbackTest {

    private lateinit var callback: DiffUtil.ItemCallback<SimpleProperties>

    @Before
    fun setup() {
        callback = CraftDSimplePropertiesItemCallback
    }

    inner class SimplePropertiesTests {

        @Test
        fun `given SimpleProperties when created with all params then initializes correctly`() {
            val key = "testKey"
            val value = JsonPrimitive("testValue")
            val properties = SimpleProperties(key = key, value = value)

            assertEquals(key, properties.key)
            assertEquals(value, properties.value)
        }

        @Test
        fun `given SimpleProperties when created with null value then initializes correctly`() {
            val key = "testKey"
            val properties = SimpleProperties(key = key, value = null)

            assertEquals(key, properties.key)
            assertEquals(null, properties.value)
        }

        @Test
        fun `given SimpleProperties when copy is called then returns new instance with updated fields`() {
            val original = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val copied = original.copy(key = "key2", value = JsonPrimitive("value2"))

            assertEquals("key2", copied.key)
            assertEquals(JsonPrimitive("value2"), copied.value)
            assertEquals("key1", original.key)
        }

        @Test
        fun `given SimpleProperties when copy is called with partial params then preserves original fields`() {
            val value = JsonPrimitive("original")
            val original = SimpleProperties(key = "key1", value = value)
            val copied = original.copy(key = "key2")

            assertEquals("key2", copied.key)
            assertEquals(value, copied.value)
        }

        @Test
        fun `given two SimpleProperties with same key and value when equals is called then returns true`() {
            val props1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val props2 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))

            assertTrue(props1 == props2)
        }

        @Test
        fun `given two SimpleProperties with different keys when equals is called then returns false`() {
            val props1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val props2 = SimpleProperties(key = "key2", value = JsonPrimitive("value1"))

            assertFalse(props1 == props2)
        }

        @Test
        fun `given two SimpleProperties with different values when equals is called then returns false`() {
            val props1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val props2 = SimpleProperties(key = "key1", value = JsonPrimitive("value2"))

            assertFalse(props1 == props2)
        }

        @Test
        fun `given SimpleProperties with null value when equals is called then handles null correctly`() {
            val props1 = SimpleProperties(key = "key1", value = null)
            val props2 = SimpleProperties(key = "key1", value = null)

            assertTrue(props1 == props2)
        }

        @Test
        fun `given SimpleProperties when hashCode is called then returns consistent value`() {
            val props1 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val props2 = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))

            assertEquals(props1.hashCode(), props2.hashCode())
        }

        @Test
        fun `given SimpleProperties when toString is called then returns string representation`() {
            val props = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val stringRep = props.toString()

            assertTrue(stringRep.contains("key1"))
        }
    }

    inner class AreItemsTheSameTests {

        @Test
        fun `given same key when areItemsTheSame then returns true`() {
            val oldItem = SimpleProperties(key = "sameKey", value = JsonPrimitive("value1"))
            val newItem = SimpleProperties(key = "sameKey", value = JsonPrimitive("value2"))

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different keys when areItemsTheSame then returns false`() {
            val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val newItem = SimpleProperties(key = "key2", value = JsonPrimitive("value1"))

            assertFalse(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given null values when areItemsTheSame then returns true for same null key`() {
            val oldItem = SimpleProperties(key = "key1", value = null)
            val newItem = SimpleProperties(key = "key1", value = null)

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given exception during comparison when areItemsTheSame then returns false`() {
            val oldItem = mockk<SimpleProperties>()
            val newItem = mockk<SimpleProperties>()

            io.mockk.every { oldItem.key } throws RuntimeException("Test exception")

            assertFalse(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given empty keys when areItemsTheSame then returns true for matching empty keys`() {
            val oldItem = SimpleProperties(key = "", value = JsonPrimitive("value1"))
            val newItem = SimpleProperties(key = "", value = JsonPrimitive("value2"))

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }
    }

    inner class AreContentsTheSameTests {

        @Test
        fun `given same JsonPrimitive value when areContentsTheSame then returns true`() {
            val value = JsonPrimitive("sameValue")
            val oldItem = SimpleProperties(key = "key1", value = value)
            val newItem = SimpleProperties(key = "key2", value = value)

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different JsonPrimitive values when areContentsTheSame then returns false`() {
            val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
            val newItem = SimpleProperties(key = "key1", value = JsonPrimitive("value2"))

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given same AbstractMap values when areContentsTheSame then returns true`() {
            val map1 = linkedMapOf<String, Any>("key" to "value")
            val map2 = linkedMapOf<String, Any>("key" to "value")
            val oldItem = SimpleProperties(key = "key1", value = map1 as JsonElement)
            val newItem = SimpleProperties(key = "key2", value = map2 as JsonElement)

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different AbstractMap values when areContentsTheSame then returns false`() {
            val map1 = linkedMapOf<String, Any>("key" to "value1")
            val map2 = linkedMapOf<String, Any>("key" to "value2")
            val oldItem = SimpleProperties(key = "key1", value = map1 as JsonElement)
            val newItem = SimpleProperties(key = "key2", value = map2 as JsonElement)

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given null values when areContentsTheSame then returns true`() {
            val oldItem = SimpleProperties(key = "key1", value = null)
            val newItem = SimpleProperties(key = "key2", value = null)

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given one null and one non-null value when areContentsTheSame then returns false`() {
            val oldItem = SimpleProperties(key = "key1", value = null)
            val newItem = SimpleProperties(key = "key2", value = JsonPrimitive("value"))

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given JsonObject values when areContentsTheSame then returns true for identical objects`() {
            val jsonObj1 = buildJsonObject { put("key", "value") }
            val jsonObj2 = buildJsonObject { put("key", "value") }
            val oldItem = SimpleProperties(key = "key1", value = jsonObj1)
            val newItem = SimpleProperties(key = "key2", value = jsonObj2)

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given JsonObject values when areContentsTheSame then returns false for different objects`() {
            val jsonObj1 = buildJsonObject { put("key", "value1") }
            val jsonObj2 = buildJsonObject { put("key", "value2") }
            val oldItem = SimpleProperties(key = "key1", value = jsonObj1)
            val newItem = SimpleProperties(key = "key2", value = jsonObj2)

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given numeric JsonPrimitive values when areContentsTheSame then returns true for same number`() {
            val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive(42))
            val newItem = SimpleProperties(key = "key2", value = JsonPrimitive(42))

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given numeric JsonPrimitive values when areContentsTheSame then returns false for different numbers`() {
            val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive(42))
            val newItem = SimpleProperties(key = "key2", value = JsonPrimitive(43))

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given boolean JsonPrimitive values when areContentsTheSame then returns true for same boolean`() {
            val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive(true))
            val newItem = SimpleProperties(key = "key2", value = JsonPrimitive(true))

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given boolean JsonPrimitive values when areContentsTheSame then returns false for different booleans`() {
            val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive(true))
            val newItem = SimpleProperties(key = "key2", value = JsonPrimitive(false))

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given empty AbstractMap values when areContentsTheSame then returns true`() {
            val map1 = linkedMapOf<String, Any>()
            val map2 = linkedMapOf<String, Any>()
            val oldItem = SimpleProperties(key = "key1", value = map1 as JsonElement)
            val newItem = SimpleProperties(key = "key2", value = map2 as JsonElement)

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given AbstractMap and JsonObject when areContentsTheSame then compares correctly`() {
            val map = linkedMapOf<String, Any>("key" to "value")
            val jsonObj = buildJsonObject { put("key", "value") }
            val oldItem = SimpleProperties(key = "key1", value = map as JsonElement)
            val newItem = SimpleProperties(key = "key2", value = jsonObj)

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }
    }

    inner class CallbackObjectTests {

        @Test
        fun `given CraftDSimplePropertiesItemCallback when accessed then is singleton instance`() {
            val instance1 = CraftDSimplePropertiesItemCallback
            val instance2 = CraftDSimplePropertiesItemCallback

            assertTrue(instance1 === instance2)
        }

        @Test
        fun `given CraftDSimplePropertiesItemCallback when cast to ItemCallback then implements interface correctly`() {
            val callback: DiffUtil.ItemCallback<SimpleProperties> = CraftDSimplePropertiesItemCallback

            assertTrue(callback is DiffUtil.ItemCallback<*>)
        }
    }

    inner class EdgeCaseTests {

        @Test
        fun `given very long keys when areItemsTheSame then compares correctly`() {
            val longKey = "k".repeat(10000)
            val oldItem = SimpleProperties(key = longKey, value = JsonPrimitive("value1"))
            val newItem = SimpleProperties(key = longKey, value = JsonPrimitive("value2"))

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given special characters in key when areItemsTheSame then compares correctly`() {
            val specialKey = "key!@#$%^&*()"
            val oldItem = SimpleProperties(key = specialKey, value = JsonPrimitive("value1"))
            val newItem = SimpleProperties(key = specialKey, value = JsonPrimitive("value2"))

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given unicode characters in value when areContentsTheSame then compares correctly`() {
            val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive("值"))
            val newItem = SimpleProperties(key = "key2", value = JsonPrimitive("値"))

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given whitespace in key when areItemsTheSame then compares correctly`() {
            val keyWithSpace = "key with spaces"
            val oldItem = SimpleProperties(key = keyWithSpace, value = JsonPrimitive("value1"))
            val newItem = SimpleProperties(key = keyWithSpace, value = JsonPrimitive("value2"))

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given nested JsonObject when areContentsTheSame then compares correctly`() {
            val nested1 = buildJsonObject {
                put("outer", buildJsonObject { put("inner", "value") })
            }
            val nested2 = buildJsonObject {
                put("outer", buildJsonObject { put("inner", "value") })
            }
            val oldItem = SimpleProperties(key = "key1", value = nested1)
            val newItem = SimpleProperties(key = "key2", value = nested2)

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given nested JsonObject with different inner values when areContentsTheSame then returns false`() {
            val nested1 = buildJsonObject {
                put("outer", buildJsonObject { put("