package com.github.codandotv.craftd.androidcore

import androidx.recyclerview.widget.DiffUtil
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import io.mockk.mockk
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.AbstractMap

@RunWith(JUnit4::class)
class CraftDSimplePropertiesDiffCallbackTest {

    private val callback = CraftDSimplePropertiesItemCallback

    @Test
    fun `given same key when areItemsTheSame then returns true`() {
        val oldItem = SimpleProperties(key = "test_key", value = JsonPrimitive("value1"))
        val newItem = SimpleProperties(key = "test_key", value = JsonPrimitive("value2"))

        val result = callback.areItemsTheSame(oldItem, newItem)

        assertTrue(result)
    }

    @Test
    fun `given different key when areItemsTheSame then returns false`() {
        val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive("value"))
        val newItem = SimpleProperties(key = "key2", value = JsonPrimitive("value"))

        val result = callback.areItemsTheSame(oldItem, newItem)

        assertFalse(result)
    }

    @Test
    fun `given exception during key comparison when areItemsTheSame then returns false`() {
        val mockOldItem = mockk<SimpleProperties> {
            throws(RuntimeException("Test exception"))
        }
        val newItem = SimpleProperties(key = "key", value = JsonPrimitive("value"))

        val result = callback.areItemsTheSame(mockOldItem, newItem)

        assertFalse(result)
    }

    @Test
    fun `given identical primitive values when areContentsTheSame then returns true`() {
        val value = JsonPrimitive("same_value")
        val oldItem = SimpleProperties(key = "key1", value = value)
        val newItem = SimpleProperties(key = "key1", value = value)

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertTrue(result)
    }

    @Test
    fun `given different primitive values when areContentsTheSame then returns false`() {
        val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
        val newItem = SimpleProperties(key = "key1", value = JsonPrimitive("value2"))

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertFalse(result)
    }

    @Test
    fun `given identical AbstractMap values when areContentsTheSame then returns true`() {
        val oldMap = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("key", "value")
            )
        }
        val newMap = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("key", "value")
            )
        }

        val oldItem = SimpleProperties(key = "key1", value = mockk { })
        val newItem = SimpleProperties(key = "key1", value = mockk { })

        val oldItemWithMap = oldItem.copy().apply { value = oldMap as JsonElement }
        val newItemWithMap = newItem.copy().apply { value = newMap as JsonElement }

        val result = callback.areContentsTheSame(
            oldItem.copy(),
            newItem.copy()
        )

        assertTrue(result)
    }

    @Test
    fun `given different AbstractMap values when areContentsTheSame then returns false`() {
        val oldMap = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("key", "value1")
            )
        }
        val newMap = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = setOf(
                SimpleEntry("key", "value2")
            )
        }

        val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive("value1"))
        val newItem = SimpleProperties(key = "key1", value = JsonPrimitive("value2"))

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertFalse(result)
    }

    @Test
    fun `given null values when areContentsTheSame then returns true`() {
        val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive(null))
        val newItem = SimpleProperties(key = "key1", value = JsonPrimitive(null))

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertTrue(result)
    }

    @Test
    fun `given one null and one non-null value when areContentsTheSame then returns false`() {
        val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive(null))
        val newItem = SimpleProperties(key = "key1", value = JsonPrimitive("value"))

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertFalse(result)
    }

    @Test
    fun `given numeric JsonElement values when areContentsTheSame then returns true`() {
        val value = JsonPrimitive(42)
        val oldItem = SimpleProperties(key = "key1", value = value)
        val newItem = SimpleProperties(key = "key1", value = value)

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertTrue(result)
    }

    @Test
    fun `given boolean JsonElement values when areContentsTheSame then returns true`() {
        val value = JsonPrimitive(true)
        val oldItem = SimpleProperties(key = "key1", value = value)
        val newItem = SimpleProperties(key = "key1", value = value)

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertTrue(result)
    }

    @Test
    fun `given callback is ItemCallback instance then callback is properly initialized`() {
        assertTrue(callback is DiffUtil.ItemCallback<SimpleProperties>)
    }

    @Test
    fun `given different numeric values when areContentsTheSame then returns false`() {
        val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive(42))
        val newItem = SimpleProperties(key = "key1", value = JsonPrimitive(43))

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertFalse(result)
    }

    @Test
    fun `given empty AbstractMap when areContentsTheSame with AbstractMap comparison then returns true`() {
        val oldMap = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = emptySet()
        }
        val newMap = object : AbstractMap<String, String>() {
            override val entries: Set<Map.Entry<String, String>> = emptySet()
        }

        val oldItem = SimpleProperties(key = "key1", value = JsonPrimitive("{}"))
        val newItem = SimpleProperties(key = "key1", value = JsonPrimitive("{}"))

        val result = callback.areContentsTheSame(oldItem, newItem)

        assertTrue(result)
    }

    @Test
    fun `given SimpleProperties with all parameters when constructing then all fields are set correctly`() {
        val key = "test_key"
        val value = JsonPrimitive("test_value")

        val simpleProperties = SimpleProperties(key = key, value = value)

        assertTrue(simpleProperties.key == key)
        assertTrue(simpleProperties.value == value)
    }

    @Test
    fun `given SimpleProperties with copy when copying with different key then new instance has different key`() {
        val original = SimpleProperties(key = "original_key", value = JsonPrimitive("value"))
        val copied = original.copy(key = "new_key")

        assertTrue(copied.key == "new_key")
        assertTrue(original.key == "original_key")
    }

    @Test
    fun `given two SimpleProperties with same values when comparing with equals then returns true`() {
        val properties1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val properties2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))

        assertTrue(properties1 == properties2)
    }

    @Test
    fun `given two SimpleProperties with different values when comparing with equals then returns false`() {
        val properties1 = SimpleProperties(key = "key1", value = JsonPrimitive("value"))
        val properties2 = SimpleProperties(key = "key2", value = JsonPrimitive("value"))

        assertFalse(properties1 == properties2)
    }

    @Test
    fun `given two SimpleProperties with same values when comparing hashCode then returns same hash`() {
        val properties1 = SimpleProperties(key = "key", value = JsonPrimitive("value"))
        val properties2 = SimpleProperties(key = "key", value = JsonPrimitive("value"))

        assertTrue(properties1.hashCode() == properties2.hashCode())
    }

    private class SimpleEntry<K, V>(
        override val key: K,
        override val value: V
    ) : Map.Entry<K, V>

}