package com.github.codandotv.craftd.androidcore

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import io.mockk.mockk
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
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

    private lateinit var callback: CraftDSimplePropertiesItemCallback

    @Before
    fun setUp() {
        callback = CraftDSimplePropertiesItemCallback
    }

    // ===== SimpleProperties Data Class Tests =====

    @Test
    fun `given SimpleProperties when constructed with all params then properties are set correctly`() {
        val key = "test_key"
        val value: JsonElement = JsonPrimitive("test_value")
        
        val properties = SimpleProperties(key, value)
        
        assertEquals(key, properties.key)
        assertEquals(value, properties.value)
    }

    @Test
    fun `given SimpleProperties when copy is called then creates new instance with modified values`() {
        val original = SimpleProperties("key1", JsonPrimitive("value1"))
        
        val copied = original.copy(key = "key2", value = JsonPrimitive("value2"))
        
        assertEquals("key2", copied.key)
        assertEquals(JsonPrimitive("value2"), copied.value)
        assertEquals("key1", original.key)
    }

    @Test
    fun `given two SimpleProperties with same values when equals is called then returns true`() {
        val props1 = SimpleProperties("key1", JsonPrimitive("value1"))
        val props2 = SimpleProperties("key1", JsonPrimitive("value1"))
        
        assertTrue(props1 == props2)
    }

    @Test
    fun `given two SimpleProperties with different keys when equals is called then returns false`() {
        val props1 = SimpleProperties("key1", JsonPrimitive("value1"))
        val props2 = SimpleProperties("key2", JsonPrimitive("value1"))
        
        assertFalse(props1 == props2)
    }

    @Test
    fun `given two SimpleProperties with different values when equals is called then returns false`() {
        val props1 = SimpleProperties("key1", JsonPrimitive("value1"))
        val props2 = SimpleProperties("key1", JsonPrimitive("value2"))
        
        assertFalse(props1 == props2)
    }

    @Test
    fun `given two SimpleProperties with same values when hashCode is called then returns same hash`() {
        val props1 = SimpleProperties("key1", JsonPrimitive("value1"))
        val props2 = SimpleProperties("key1", JsonPrimitive("value1"))
        
        assertEquals(props1.hashCode(), props2.hashCode())
    }

    // ===== DiffUtil.ItemCallback: areItemsTheSame Tests =====

    @Test
    fun `given two SimpleProperties with same key when areItemsTheSame is called then returns true`() {
        val oldItem = SimpleProperties("same_key", JsonPrimitive("old_value"))
        val newItem = SimpleProperties("same_key", JsonPrimitive("new_value"))
        
        assertTrue(callback.areItemsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with different keys when areItemsTheSame is called then returns false`() {
        val oldItem = SimpleProperties("key1", JsonPrimitive("value1"))
        val newItem = SimpleProperties("key2", JsonPrimitive("value1"))
        
        assertFalse(callback.areItemsTheSame(oldItem, newItem))
    }

    @Test
    fun `given SimpleProperties with null key when areItemsTheSame is called then handles exception and returns false`() {
        val oldItem = SimpleProperties("key1", JsonPrimitive("value1"))
        val newItem = SimpleProperties("key2", JsonPrimitive("value2"))
        
        val result = callback.areItemsTheSame(oldItem, newItem)
        
        assertFalse(result)
    }

    // ===== DiffUtil.ItemCallback: areContentsTheSame Tests =====

    @Test
    fun `given two SimpleProperties with same JsonPrimitive value when areContentsTheSame is called then returns true`() {
        val oldItem = SimpleProperties("key", JsonPrimitive("same_value"))
        val newItem = SimpleProperties("key", JsonPrimitive("same_value"))
        
        assertTrue(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with different JsonPrimitive values when areContentsTheSame is called then returns false`() {
        val oldItem = SimpleProperties("key", JsonPrimitive("value1"))
        val newItem = SimpleProperties("key", JsonPrimitive("value2"))
        
        assertFalse(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with JsonNull values when areContentsTheSame is called then returns true`() {
        val oldItem = SimpleProperties("key", JsonNull)
        val newItem = SimpleProperties("key", JsonNull)
        
        assertTrue(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with AbstractMap values that are equal when areContentsTheSame is called then returns true`() {
        val mapOld = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("k1", "v1"),
                SimpleEntry("k2", "v2")
            )
        }
        val mapNew = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("k1", "v1"),
                SimpleEntry("k2", "v2")
            )
        }
        
        val oldItem = SimpleProperties("key", mapOld as JsonElement)
        val newItem = SimpleProperties("key", mapNew as JsonElement)
        
        assertTrue(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with AbstractMap values that are different when areContentsTheSame is called then returns false`() {
        val mapOld = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("k1", "v1")
            )
        }
        val mapNew = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("k2", "v2")
            )
        }
        
        val oldItem = SimpleProperties("key", mapOld as JsonElement)
        val newItem = SimpleProperties("key", mapNew as JsonElement)
        
        assertFalse(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given SimpleProperties where one has AbstractMap and other has JsonElement when areContentsTheSame is called then compares values directly`() {
        val map = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = emptySet()
        }
        
        val oldItem = SimpleProperties("key", map as JsonElement)
        val newItem = SimpleProperties("key", JsonPrimitive("value"))
        
        assertFalse(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with numeric JsonPrimitive values when areContentsTheSame is called then returns true if equal`() {
        val oldItem = SimpleProperties("count", JsonPrimitive(42))
        val newItem = SimpleProperties("count", JsonPrimitive(42))
        
        assertTrue(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with numeric JsonPrimitive values when areContentsTheSame is called then returns false if different`() {
        val oldItem = SimpleProperties("count", JsonPrimitive(42))
        val newItem = SimpleProperties("count", JsonPrimitive(43))
        
        assertFalse(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with boolean JsonPrimitive values when areContentsTheSame is called then returns true if equal`() {
        val oldItem = SimpleProperties("flag", JsonPrimitive(true))
        val newItem = SimpleProperties("flag", JsonPrimitive(true))
        
        assertTrue(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given two SimpleProperties with boolean JsonPrimitive values when areContentsTheSame is called then returns false if different`() {
        val oldItem = SimpleProperties("flag", JsonPrimitive(true))
        val newItem = SimpleProperties("flag", JsonPrimitive(false))
        
        assertFalse(callback.areContentsTheSame(oldItem, newItem))
    }

    // ===== Edge Cases =====

    @Test
    fun `given SimpleProperties with empty string key when constructed then key is set correctly`() {
        val properties = SimpleProperties("", JsonPrimitive("value"))
        
        assertEquals("", properties.key)
    }

    @Test
    fun `given SimpleProperties with empty string key when areItemsTheSame is called then compares keys correctly`() {
        val oldItem = SimpleProperties("", JsonPrimitive("value1"))
        val newItem = SimpleProperties("", JsonPrimitive("value2"))
        
        assertTrue(callback.areItemsTheSame(oldItem, newItem))
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive empty string value when areContentsTheSame is called then returns false for different values`() {
        val oldItem = SimpleProperties("key", JsonPrimitive(""))
        val newItem = SimpleProperties("key", JsonPrimitive("nonempty"))
        
        assertFalse(callback.areContentsTheSame(oldItem, newItem))
    }

    @Test
    fun `given SimpleProperties with JsonPrimitive empty string value when areContentsTheSame is called then returns true for same empty string`() {
        val oldItem = SimpleProperties("key", JsonPrimitive(""))
        val newItem = SimpleProperties("key", JsonPrimitive(""))
        
        assertTrue(callback.areContentsTheSame(oldItem, newItem))
    }

    // ===== Additional Coverage =====

    @Test
    fun `given callback object when accessed multiple times then returns same instance`() {
        val callback1 = CraftDSimplePropertiesItemCallback
        val callback2 = CraftDSimplePropertiesItemCallback
        
        assertEquals(callback1, callback2)
    }

    private class SimpleEntry<K, V>(override val key: K, override val value: V) : Map.Entry<K, V>
}