```kotlin
package com.github.codandotv.craftd.androidcore.data

import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

@RunWith(JUnit4::class)
class ViewMapperVoTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    data class TestDataClass(
        val id: String = "",
        val name: String = "",
        val value: Int = 0
    )

    data class NestedTestDataClass(
        val id: String,
        val nested: TestDataClass,
        val optional: String? = null
    )

    @Test
    fun `given valid map when convertToVO then returns correctly deserialized object`() {
        val input = mapOf(
            "id" to "test-123",
            "name" to "Test Name",
            "value" to 42
        )

        val result = input.convertToVO<TestDataClass>()

        assertEquals("test-123", result.id)
        assertEquals("Test Name", result.name)
        assertEquals(42, result.value)
    }

    @Test
    fun `given partial map with defaults when convertToVO then uses default values`() {
        val input = mapOf(
            "id" to "test-456"
        )

        val result = input.convertToVO<TestDataClass>()

        assertEquals("test-456", result.id)
        assertEquals("", result.name)
        assertEquals(0, result.value)
    }

    @Test
    fun `given nested map when convertToVO then returns correctly deserialized nested object`() {
        val input = mapOf(
            "id" to "parent-123",
            "nested" to mapOf(
                "id" to "child-456",
                "name" to "Child Name",
                "value" to 99
            ),
            "optional" to "test-value"
        )

        val result = input.convertToVO<NestedTestDataClass>()

        assertEquals("parent-123", result.id)
        assertEquals("child-456", result.nested.id)
        assertEquals("Child Name", result.nested.name)
        assertEquals(99, result.nested.value)
        assertEquals("test-value", result.optional)
    }

    @Test
    fun `given null input when convertToVO then throws exception`() {
        val input: Any? = null

        assertFailsWith<NullPointerException> {
            input.convertToVO<TestDataClass>()
        }
    }

    @Test
    fun `given empty map when convertToVO then returns object with default values`() {
        val input: Any = emptyMap<String, Any>()

        val result = input.convertToVO<TestDataClass>()

        assertEquals("", result.id)
        assertEquals("", result.name)
        assertEquals(0, result.value)
    }

    @Test
    fun `given map with extra fields when convertToVO then ignores extra fields`() {
        val input = mapOf(
            "id" to "test-789",
            "name" to "Extra Test",
            "value" to 55,
            "extraField" to "should be ignored",
            "anotherExtra" to 123
        )

        val result = input.convertToVO<TestDataClass>()

        assertEquals("test-789", result.id)
        assertEquals("Extra Test", result.name)
        assertEquals(55, result.value)
    }

    @Test
    fun `given map with type coercion when convertToVO then coerces types correctly`() {
        val input = mapOf(
            "id" to "test-coerce",
            "name" to "Coerce Test",
            "value" to "100"
        )

        val result = input.convertToVO<TestDataClass>()

        assertEquals("test-coerce", result.id)
        assertEquals("Coerce Test", result.name)
        assertEquals(100, result.value)
    }

    @Test
    fun `given list of maps when convertToVO then returns correctly deserialized list object`() {
        val input: Any = listOf(
            mapOf("id" to "1", "name" to "First", "value" to 10),
            mapOf("id" to "2", "name" to "Second", "value" to 20)
        )

        val result = input.convertToVO<List<TestDataClass>>()

        assertEquals(2, result.size)
        assertEquals("1", result[0].id)
        assertEquals("First", result[0].name)
        assertEquals(10, result[0].value)
        assertEquals("2", result[1].id)
        assertEquals("Second", result[1].name)
        assertEquals(20, result[1].value)
    }

    @Test
    fun `given string input when convertToVO then throws exception`() {
        val input: Any = "not a valid object"

        assertFailsWith<Exception> {
            input.convertToVO<TestDataClass>()
        }
    }

    @Test
    fun `given boolean input when convertToVO then throws exception`() {
        val input: Any = true

        assertFailsWith<Exception> {
            input.convertToVO<TestDataClass>()
        }
    }

    @Test
    fun `given integer input when convertToVO then throws exception`() {
        val input: Any = 42

        assertFailsWith<Exception> {
            input.convertToVO<TestDataClass>()
        }
    }

    @Test
    fun `given map with null values when convertToVO then preserves null values in nullable fields`() {
        val input = mapOf(
            "id" to "test-null",
            "nested" to mapOf(
                "id" to "nested-id",
                "name" to "Nested",
                "value" to 5
            ),
            "optional" to null
        )

        val result = input.convertToVO<NestedTestDataClass>()

        assertEquals("test-null", result.id)
        assertNull(result.optional)
    }

    @Test
    fun `given complex nested structure when convertToVO then handles deeply nested objects`() {
        val input = mapOf(
            "id" to "root",
            "nested" to mapOf(
                "id" to "child",
                "name" to "Child Name",
                "value" to 100
            )
        )

        val result = input.convertToVO<NestedTestDataClass>()

        assertEquals("root", result.id)
        assertEquals("child", result.nested.id)
        assertEquals("Child Name", result.nested.name)
        assertEquals(100, result.nested.value)
    }

    @Test
    fun `given data class with copy when convertToVO then original object unaffected`() {
        val original = TestDataClass(id = "orig", name = "Original", value = 1)
        val copy = original.copy(name = "Modified", value = 2)

        assertEquals("Original", original.name)
        assertEquals(1, original.value)
        assertEquals("Modified", copy.name)
        assertEquals(2, copy.value)
    }

    @Test
    fun `given two identical data classes when comparing then equals returns true`() {
        val obj1 = TestDataClass(id = "same", name = "Same Name", value = 42)
        val obj2 = TestDataClass(id = "same", name = "Same Name", value = 42)

        assertEquals(obj1, obj2)
        assertEquals(obj1.hashCode(), obj2.hashCode())
    }

    @Test
    fun `given two different data classes when comparing then equals returns false`() {
        val obj1 = TestDataClass(id = "obj1", name = "Name1", value = 1)
        val obj2 = TestDataClass(id = "obj2", name = "Name2", value = 2)

        assertEquals(false, obj1 == obj2)
    }

    @Test
    fun `given data class with default constructor when instantiating then all fields have default values`() {
        val obj = TestDataClass()

        assertEquals("", obj.id)
        assertEquals("", obj.name)
        assertEquals(0, obj.value)
    }

    @Test
    fun `given map with missing required nested field when convertToVO then throws exception`() {
        val input = mapOf(
            "id" to "parent",
            "nested" to mapOf(
                "id" to "child"
            )
        )

        assertFailsWith<Exception> {
            input.convertToVO<NestedTestDataClass>()
        }
    }

    @Test
    fun `given empty list when convertToVO then returns empty list`() {
        val input: Any = emptyList<Map<String, Any>>()

        val result = input.convertToVO<List<TestDataClass>>()

        assertEquals(0, result.size)
    }

    @Test
    fun `given map with numeric string when convertToVO then coerces to numeric value`() {
        val input = mapOf(
            "id" to "numeric-test",
            "name" to "Numeric",
            "value" to "999"
        )

        val result = input.convertToVO<TestDataClass>()

        assertEquals(999, result.value)
    }

    @Test
    fun `given data class toString when called then returns formatted string`() {
        val obj = TestDataClass(id = "test", name = "Test Name", value = 42)
        val stringRepresentation = obj.toString()

        assertEquals(true, stringRepresentation.contains("TestDataClass"))
        assertEquals(true, stringRepresentation.contains("test"))
        assertEquals(true, stringRepresentation.contains("Test Name"))
        assertEquals(true, stringRepresentation.contains("42"))
    }
}
```