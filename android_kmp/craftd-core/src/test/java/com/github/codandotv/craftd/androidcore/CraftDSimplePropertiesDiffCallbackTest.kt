package com.github.codandotv.craftd.androidcore

import io.mockk.mockk
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
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

    inner class AreItemsTheSame {

        @Test
        fun `given same key when areItemsTheSame then returns true`() {
            val oldItem = SimpleProperties(
                key = "test_key",
                value = JsonPrimitive("old_value")
            )
            val newItem = SimpleProperties(
                key = "test_key",
                value = JsonPrimitive("new_value")
            )

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different keys when areItemsTheSame then returns false`() {
            val oldItem = SimpleProperties(
                key = "old_key",
                value = JsonPrimitive("value")
            )
            val newItem = SimpleProperties(
                key = "new_key",
                value = JsonPrimitive("value")
            )

            assertFalse(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given exception during comparison when areItemsTheSame then returns false`() {
            val oldItem = mockk<SimpleProperties>()
            val newItem = mockk<SimpleProperties>()

            io.mockk.every { oldItem.key } throws Exception("Comparison failed")

            assertFalse(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given empty string keys when areItemsTheSame then returns true`() {
            val oldItem = SimpleProperties(
                key = "",
                value = JsonPrimitive("value1")
            )
            val newItem = SimpleProperties(
                key = "",
                value = JsonPrimitive("value2")
            )

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }

        @Test
        fun `given null keys when areItemsTheSame then returns true`() {
            val oldItem = SimpleProperties(
                key = null,
                value = JsonPrimitive("value1")
            )
            val newItem = SimpleProperties(
                key = null,
                value = JsonPrimitive("value2")
            )

            assertTrue(callback.areItemsTheSame(oldItem, newItem))
        }
    }

    inner class AreContentsTheSame {

        @Test
        fun `given equal primitive values when areContentsTheSame then returns true`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive("same_value")
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive("same_value")
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different primitive values when areContentsTheSame then returns false`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive("old_value")
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive("new_value")
            )

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given equal AbstractMap values when areContentsTheSame then returns true`() {
            val oldMap = mutableMapOf<String, String>("key" to "value")
            val newMap = mutableMapOf<String, String>("key" to "value")

            val oldItem = SimpleProperties(
                key = "key1",
                value = oldMap as JsonElement
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = newMap as JsonElement
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different AbstractMap values when areContentsTheSame then returns false`() {
            val oldMap = mutableMapOf<String, String>("key" to "old_value")
            val newMap = mutableMapOf<String, String>("key" to "new_value")

            val oldItem = SimpleProperties(
                key = "key1",
                value = oldMap as JsonElement
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = newMap as JsonElement
            )

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given null values when areContentsTheSame then returns true`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = null
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = null
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given one null and one non-null value when areContentsTheSame then returns false`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = null
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive("value")
            )

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given equal JsonObject values when areContentsTheSame then returns true`() {
            val value = JsonObject(mapOf("nested" to JsonPrimitive("data")))
            val oldItem = SimpleProperties(
                key = "key1",
                value = value
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = value
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different JsonObject values when areContentsTheSame then returns false`() {
            val oldValue = JsonObject(mapOf("nested" to JsonPrimitive("old_data")))
            val newValue = JsonObject(mapOf("nested" to JsonPrimitive("new_data")))
            val oldItem = SimpleProperties(
                key = "key1",
                value = oldValue
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = newValue
            )

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given equal numeric JsonPrimitive values when areContentsTheSame then returns true`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(42)
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(42)
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different numeric JsonPrimitive values when areContentsTheSame then returns false`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(42)
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(43)
            )

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given equal boolean JsonPrimitive values when areContentsTheSame then returns true`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(true)
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(true)
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given different boolean JsonPrimitive values when areContentsTheSame then returns false`() {
            val oldItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(true)
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = JsonPrimitive(false)
            )

            assertFalse(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given empty AbstractMap when areContentsTheSame then returns true for equal empty maps`() {
            val oldMap = mutableMapOf<String, String>()
            val newMap = mutableMapOf<String, String>()

            val oldItem = SimpleProperties(
                key = "key1",
                value = oldMap as JsonElement
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = newMap as JsonElement
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }

        @Test
        fun `given AbstractMap with multiple entries when areContentsTheSame then returns true for equal maps`() {
            val oldMap = linkedMapOf<String, String>(
                "key1" to "value1",
                "key2" to "value2",
                "key3" to "value3"
            )
            val newMap = linkedMapOf<String, String>(
                "key1" to "value1",
                "key2" to "value2",
                "key3" to "value3"
            )

            val oldItem = SimpleProperties(
                key = "key1",
                value = oldMap as JsonElement
            )
            val newItem = SimpleProperties(
                key = "key1",
                value = newMap as JsonElement
            )

            assertTrue(callback.areContentsTheSame(oldItem, newItem))
        }
    }

    inner class SimplePropertiesDataClass {

        @Test
        fun `given all parameters when constructing SimpleProperties then object is created correctly`() {
            val key = "test_key"
            val value = JsonPrimitive("test_value")

            val item = SimpleProperties(key = key, value = value)

            assertEquals(key, item.key)
            assertEquals(value, item.value)
        }

        @Test
        fun `given null value when constructing SimpleProperties then object is created with null value`() {
            val key = "test_key"

            val item = SimpleProperties(key = key, value = null)

            assertEquals(key, item.key)
            assertEquals(null, item.value)
        }

        @Test
        fun `given null key when constructing SimpleProperties then object is created with null key`() {
            val value = JsonPrimitive("test_value")

            val item = SimpleProperties(key = null, value = value)

            assertEquals(null, item.key)
            assertEquals(value, item.value)
        }

        @Test
        fun `when calling copy on SimpleProperties then new instance is created with updated values`() {
            val original = SimpleProperties(
                key = "original_key",
                value = JsonPrimitive("original_value")
            )
            val copied = original.copy(key = "new_key")

            assertEquals("new_key", copied.key)
            assertEquals(JsonPrimitive("original_value"), copied.value)
            assertEquals("original_key", original.key)
        }

        @Test
        fun `when calling copy without parameters on SimpleProperties then identical instance properties are created`() {
            val original = SimpleProperties(
                key = "key",
                value = JsonPrimitive("value")
            )
            val copied = original.copy()

            assertEquals(original.key, copied.key)
            assertEquals(original.value, copied.value)
        }

        @Test
        fun `given equal SimpleProperties when comparing equals then returns true`() {
            val item1 = SimpleProperties(
                key = "key",
                value = JsonPrimitive("value")
            )
            val item2 = SimpleProperties(
                key = "key",
                value = JsonPrimitive("value")
            )

            assertEquals(item1, item2)
        }

        @Test
        fun `given different keys when comparing SimpleProperties equals then returns false`() {
            val item1 = SimpleProperties(
                key = "key1",
                value = JsonPrimitive("value")
            )
            val item2 = SimpleProperties(
                key = "key2",
                value = JsonPrimitive("value")
            )

            assertFalse(item1 == item2)
        }

        @Test
        fun `given different values when comparing SimpleProperties equals then returns false`() {
            val item1 = SimpleProperties(
                key = "key",
                value = JsonPrimitive("value1")
            )
            val item2 = SimpleProperties(
                key = "key",
                value = JsonPrimitive("value2")
            )

            assertFalse(item1 == item2)
        }

        @Test
        fun `given equal SimpleProperties when calling hashCode then same hash is produced`() {
            val item1 = SimpleProperties(
                key = "key",
                value = JsonPrimitive("value")
            )
            val item2 = SimpleProperties(
                key = "key",
                value = JsonPrimitive("value")
            )

            assertEquals(item1.hashCode(), item2.hashCode())
        }

        @Test
        fun `given different SimpleProperties when calling hashCode then different hashes should be produced`() {
            val item1 = SimpleProperties(
                key = "key1",
                value = JsonPrimitive("value")
            )
            val item2 = SimpleProperties(
                key = "key2",
                value = JsonPrimitive("value")
            )

            assertFalse(item1.hashCode() == item2.hashCode())
        }
    }

    inner class ObjectSingleton {

        @Test
        fun `when accessing CraftDSimplePropertiesItemCallback then it is a singleton`() {
            val instance1 = CraftDSimplePropertiesItemCallback
            val instance2 = CraftDSimplePropertiesItemCallback

            assertTrue(instance1 === instance2)
        }

        @Test
        fun `when checking CraftDSimplePropertiesItemCallback type then it extends DiffUtil ItemCallback`() {
            assertTrue(CraftDSimplePropertiesItemCallback is androidx.recyclerview.widget.DiffUtil.ItemCallback<*>)
        }
    }
}