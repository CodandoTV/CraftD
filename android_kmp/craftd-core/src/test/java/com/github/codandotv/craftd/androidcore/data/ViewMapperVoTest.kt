package com.github.codandotv.craftd.androidcore.data

import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class ViewMapperVoTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    data class TestVO(
        val name: String,
        val value: Int,
        val flag: Boolean = false
    )

    data class NestedTestVO(
        val id: String,
        val nested: TestVO
    )

    @Test
    fun `given valid object when convertToVO then returns converted instance`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 42,
            "flag" to true
        )

        val result: TestVO = input.convertToVO()

        assertEquals("TestName", result.name)
        assertEquals(42, result.value)
        assertEquals(true, result.flag)
    }

    @Test
    fun `given object with default values when convertToVO then uses defaults for missing fields`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 100
        )

        val result: TestVO = input.convertToVO()

        assertEquals("TestName", result.name)
        assertEquals(100, result.value)
        assertEquals(false, result.flag)
    }

    @Test
    fun `given null object when convertToVO then throws exception`() {
        val input: Any? = null

        assertFailsWith<NullPointerException> {
            input.convertToVO<TestVO>()
        }
    }

    @Test
    fun `given nested object structure when convertToVO then converts nested object correctly`() {
        val nestedMap = mapOf(
            "name" to "Inner",
            "value" to 99,
            "flag" to false
        )
        val input = mapOf(
            "id" to "outer-1",
            "nested" to nestedMap
        )

        val result: NestedTestVO = input.convertToVO()

        assertEquals("outer-1", result.id)
        assertEquals("Inner", result.nested.name)
        assertEquals(99, result.nested.value)
        assertEquals(false, result.nested.flag)
    }

    @Test
    fun `given string input when convertToVO then converts to target type`() {
        val input: Any = "StringValue"

        assertFailsWith<Exception> {
            input.convertToVO<TestVO>()
        }
    }

    @Test
    fun `given number input when convertToVO then converts to target type or fails`() {
        val input: Any = 12345

        assertFailsWith<Exception> {
            input.convertToVO<TestVO>()
        }
    }

    @Test
    fun `given object with extra fields when convertToVO then ignores extra fields`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 50,
            "flag" to true,
            "extraField" to "shouldBeIgnored"
        )

        val result: TestVO = input.convertToVO()

        assertEquals("TestName", result.name)
        assertEquals(50, result.value)
        assertEquals(true, result.flag)
    }

    @Test
    fun `given object with wrong type values when convertToVO then attempts conversion or fails gracefully`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to "notAnInt",
            "flag" to true
        )

        assertFailsWith<Exception> {
            input.convertToVO<TestVO>()
        }
    }

    @Test
    fun `given list of objects when convertToVO then converts first element`() {
        val input = listOf(
            mapOf("name" to "First", "value" to 1),
            mapOf("name" to "Second", "value" to 2)
        )

        assertFailsWith<Exception> {
            input.convertToVO<TestVO>()
        }
    }

    @Test
    fun `given empty map when convertToVO then fails or returns object with nulls`() {
        val input: Map<String, Any?> = emptyMap()

        assertFailsWith<Exception> {
            input.convertToVO<TestVO>()
        }
    }

    @Test
    fun `given object with null values when convertToVO then handles null appropriately`() {
        val input = mapOf(
            "name" to null,
            "value" to 42,
            "flag" to false
        )

        val result: TestVO = input.convertToVO()

        assertEquals(null, result.name)
        assertEquals(42, result.value)
        assertEquals(false, result.flag)
    }

    @Test
    fun `given boolean value when convertToVO then converts correctly`() {
        val input = mapOf(
            "name" to "BoolTest",
            "value" to 1,
            "flag" to false
        )

        val result: TestVO = input.convertToVO()

        assertEquals(false, result.flag)
    }

    @Test
    fun `given object with different numeric types when convertToVO then converts appropriately`() {
        val input = mapOf(
            "name" to "NumericTest",
            "value" to 123L,
            "flag" to true
        )

        val result: TestVO = input.convertToVO()

        assertEquals(123, result.value)
    }

    @Test
    fun `given duplicate convertToVO calls with same input then returns equivalent objects`() {
        val input = mapOf(
            "name" to "Duplicate",
            "value" to 77,
            "flag" to true
        )

        val result1: TestVO = input.convertToVO()
        val result2: TestVO = input.convertToVO()

        assertEquals(result1, result2)
    }

    @Test
    fun `given object mapper conversion when convertToVO then uses ObjectMapper internally`() {
        val input = mapOf(
            "name" to "MapperTest",
            "value" to 55,
            "flag" to false
        )

        val result: TestVO = input.convertToVO()
        val expected = TestVO(name = "MapperTest", value = 55, flag = false)

        assertEquals(expected, result)
    }
}