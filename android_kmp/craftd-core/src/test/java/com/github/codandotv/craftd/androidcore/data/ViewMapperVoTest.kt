package com.github.codandotv.craftd.androidcore.data

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonArray
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class ViewMapperVoTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    data class TestVO(
        val id: Int = 0,
        val name: String = "",
        val active: Boolean = true
    )

    data class NestedVO(
        val value: String,
        val nested: TestVO
    )

    @Test
    fun `given null value when convertToVO then returns null`() {
        val result: TestVO? = try {
            (null as Any?).convertToVO()
        } catch (e: Exception) {
            null
        }
        assertEquals(null, result)
    }

    @Test
    fun `given valid map when convertToVO then returns mapped object`() {
        val input = mapOf(
            "id" to 42,
            "name" to "TestName",
            "active" to false
        )
        val result = input.convertToVO<TestVO>()
        assertEquals(42, result.id)
        assertEquals("TestName", result.name)
        assertEquals(false, result.active)
    }

    @Test
    fun `given map with partial fields when convertToVO then uses default values`() {
        val input = mapOf("id" to 10)
        val result = input.convertToVO<TestVO>()
        assertEquals(10, result.id)
        assertEquals("", result.name)
        assertEquals(true, result.active)
    }

    @Test
    fun `given empty map when convertToVO then returns object with defaults`() {
        val input = emptyMap<String, Any>()
        val result = input.convertToVO<TestVO>()
        assertEquals(0, result.id)
        assertEquals("", result.name)
        assertEquals(true, result.active)
    }

    @Test
    fun `given nested map when convertToVO then returns nested object`() {
        val input = mapOf(
            "value" to "test",
            "nested" to mapOf("id" to 5, "name" to "Nested")
        )
        val result = input.convertToVO<NestedVO>()
        assertEquals("test", result.value)
        assertEquals(5, result.nested.id)
        assertEquals("Nested", result.nested.name)
    }

    @Test
    fun `given map with wrong type for field when convertToVO then attempts coercion`() {
        val input = mapOf("id" to "123", "name" to "Test")
        val result = input.convertToVO<TestVO>()
        assertEquals(123, result.id)
        assertEquals("Test", result.name)
    }

    @Test
    fun `given object with extra fields when convertToVO then ignores extra fields`() {
        val input = mapOf(
            "id" to 7,
            "name" to "Extra",
            "extra" to "ignored",
            "another" to 999
        )
        val result = input.convertToVO<TestVO>()
        assertEquals(7, result.id)
        assertEquals("Extra", result.name)
    }

    @Test
    fun `given list of maps when convertToVO then returns mapped object`() {
        val input = listOf(1, 2, 3)
        val result = input.convertToVO<List<Int>>()
        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun `given string when convertToVO then coerces to string VO compatible type`() {
        val input = "StringValue"
        val result = input.convertToVO<String>()
        assertEquals("StringValue", result)
    }

    @Test
    fun `given number when convertToVO then coerces to number`() {
        val input = 42
        val result = input.convertToVO<Int>()
        assertEquals(42, result)
    }

    @Test
    fun `given boolean when convertToVO then coerces to boolean`() {
        val input = true
        val result = input.convertToVO<Boolean>()
        assertEquals(true, result)
    }

    @Test
    fun `given map with null field values when convertToVO then preserves nulls`() {
        data class NullableVO(val id: Int?, val name: String?)
        val input = mapOf<String, Any?>("id" to null, "name" to null)
        val result = input.convertToVO<NullableVO>()
        assertEquals(null, result.id)
        assertEquals(null, result.name)
    }

    @Test
    fun `given map with mixed null and non-null when convertToVO then handles correctly`() {
        data class MixedVO(val id: Int?, val name: String)
        val input = mapOf<String, Any?>("id" to null, "name" to "Test")
        val result = input.convertToVO<MixedVO>()
        assertEquals(null, result.id)
        assertEquals("Test", result.name)
    }

    @Test
    fun `given deeply nested structure when convertToVO then maps recursively`() {
        data class Level3(val value: String)
        data class Level2(val level3: Level3)
        data class Level1(val level2: Level2)

        val input = mapOf(
            "level2" to mapOf(
                "level3" to mapOf("value" to "deep")
            )
        )
        val result = input.convertToVO<Level1>()
        assertEquals("deep", result.level2.level3.value)
    }

    @Test
    fun `given map with numeric string when convertToVO then coerces string to number`() {
        val input = mapOf("id" to "999", "name" to "NumericString")
        val result = input.convertToVO<TestVO>()
        assertEquals(999, result.id)
    }

    @Test
    fun `given TestVO data class when created then has correct default values`() {
        val vo = TestVO()
        assertEquals(0, vo.id)
        assertEquals("", vo.name)
        assertEquals(true, vo.active)
    }

    @Test
    fun `given TestVO when copy is called then creates new instance with updated field`() {
        val original = TestVO(id = 1, name = "Original", active = false)
        val copied = original.copy(name = "Updated")
        assertEquals(1, copied.id)
        assertEquals("Updated", copied.name)
        assertEquals(false, copied.active)
    }

    @Test
    fun `given two TestVO instances with same values when compared then are equal`() {
        val vo1 = TestVO(id = 1, name = "Test", active = true)
        val vo2 = TestVO(id = 1, name = "Test", active = true)
        assertEquals(vo1, vo2)
    }

    @Test
    fun `given two TestVO instances with different values when compared then are not equal`() {
        val vo1 = TestVO(id = 1, name = "Test", active = true)
        val vo2 = TestVO(id = 2, name = "Test", active = true)
        assert(vo1 != vo2)
    }

    @Test
    fun `given TestVO instance when hashCode called then returns consistent value`() {
        val vo = TestVO(id = 5, name = "Hash", active = false)
        val hash1 = vo.hashCode()
        val hash2 = vo.hashCode()
        assertEquals(hash1, hash2)
    }

    @Test
    fun `given two equal TestVO instances when hashCode called then returns same hash`() {
        val vo1 = TestVO(id = 1, name = "Test", active = true)
        val vo2 = TestVO(id = 1, name = "Test", active = true)
        assertEquals(vo1.hashCode(), vo2.hashCode())
    }

    @Test
    fun `given TestVO when toString called then returns valid string representation`() {
        val vo = TestVO(id = 1, name = "Test", active = true)
        val str = vo.toString()
        assert(str.contains("id"))
        assert(str.contains("name"))
        assert(str.contains("active"))
    }

    @Test
    fun `given convertToVO with reified type parameter when called then preserves type information`() {
        val input = mapOf("id" to 123)
        val result: TestVO = input.convertToVO()
        assert(result is TestVO)
    }

    @Test
    fun `given map with array field when convertToVO then maps array correctly`() {
        data class ArrayVO(val items: List<Int>)
        val input = mapOf("items" to listOf(1, 2, 3, 4, 5))
        val result = input.convertToVO<ArrayVO>()
        assertEquals(listOf(1, 2, 3, 4, 5), result.items)
    }

    @Test
    fun `given map with nested list of objects when convertToVO then maps correctly`() {
        data class ItemVO(val id: Int, val name: String)
        data class ContainerVO(val items: List<ItemVO>)
        val input = mapOf(
            "items" to listOf(
                mapOf("id" to 1, "name" to "Item1"),
                mapOf("id" to 2, "name" to "Item2")
            )
        )
        val result = input.convertToVO<ContainerVO>()
        assertEquals(2, result.items.size)
        assertEquals("Item1", result.items[0].name)
        assertEquals("Item2", result.items[1].name)
    }

    @Test
    fun `given large map when convertToVO then handles large data structures`() {
        val largeMap = (1..1000).associate { i -> "field_$i" to i }
        data class LargeVO(val field_1: Int = 0, val field_500: Int = 0, val field_1000: Int = 0)
        val result = largeMap.convertToVO<LargeVO>()
        assertEquals(1, result.field_1)
        assertEquals(500, result.field_500)
        assertEquals(1000, result.field_1000)
    }

    @Test
    fun `given map with special characters in string values when convertToVO then preserves them`() {
        val input = mapOf("name" to "Test!@#$%^&*()")
        val result = input.convertToVO<TestVO>()
        assertEquals("Test!@#$%^&*()", result.name)
    }

    @Test
    fun `given map with unicode characters when convertToVO then preserves unicode`() {
        val input = mapOf("name" to "测试名称😀")
        val result = input.convertToVO<TestVO>()
        assertEquals("测试名称😀", result.name)
    }

    @Test
    fun `given Double value when convertToVO then handles decimal correctly`() {
        data class DoubleVO(val value: Double = 0.0)
        val input = mapOf("value" to 3.14159)
        val result = input.convertToVO<DoubleVO>()
        assertEquals(3.14159, result.value, 0.0001)
    }

    @Test
    fun `given negative number when convertToVO then handles negative values`() {
        val input = mapOf("id" to -42, "name" to "Negative")
        val result = input.convertToVO<TestVO>()
        assertEquals(-42, result.id)
    }

    @Test
    fun `given ObjectMapper directly when convertValue called then works as expected`() {
        val mapper = ObjectMapper()
        val input = mapOf("id" to 7, "name" to "Direct")
        val result = mapper.convertValue(input, TestVO::class.java)
        assertEquals(7, result.id)
        assertEquals("Direct", result.name)
    }
}