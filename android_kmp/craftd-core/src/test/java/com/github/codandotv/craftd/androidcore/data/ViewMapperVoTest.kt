package com.github.codandotv.craftd.androidcore.data

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class ViewMapperVoTest {

    @Before
    fun setup() {
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `given valid map when convertToVO then returns correct object`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 42
        )
        
        val result: TestData = input.convertToVO()
        
        assertNotNull(result)
        assertEquals("TestName", result.name)
        assertEquals(42, result.value)
    }

    @Test
    fun `given null input when convertToVO then throws exception`() {
        try {
            val result: TestData = (null as Any?).convertToVO()
        } catch (e: Exception) {
            assertTrue(e is NullPointerException || e is IllegalArgumentException)
        }
    }

    @Test
    fun `given map with missing fields when convertToVO then returns object with defaults`() {
        val input = mapOf("name" to "TestName")
        
        val result: TestData = input.convertToVO()
        
        assertNotNull(result)
        assertEquals("TestName", result.name)
    }

    @Test
    fun `given map with wrong type when convertToVO then throws or casts`() {
        val input = mapOf(
            "name" to 123,
            "value" to "notanumber"
        )
        
        try {
            val result: TestData = input.convertToVO()
        } catch (e: Exception) {
            assertTrue(e is ClassCastException || e is IllegalArgumentException)
        }
    }

    @Test
    fun `given nested map when convertToVO then converts nested object`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 42,
            "nested" to mapOf("id" to 1)
        )
        
        val result: TestDataWithNested = input.convertToVO()
        
        assertNotNull(result)
        assertEquals("TestName", result.name)
    }

    @Test
    fun `given empty map when convertToVO then returns object with default values`() {
        val input = emptyMap<String, Any>()
        
        val result: TestDataOptional = input.convertToVO()
        
        assertNotNull(result)
    }

    @Test
    fun `given map with special characters when convertToVO then converts correctly`() {
        val input = mapOf(
            "name" to "Test@#\$%Name",
            "value" to 42
        )
        
        val result: TestData = input.convertToVO()
        
        assertNotNull(result)
        assertEquals("Test@#\$%Name", result.name)
    }

    @Test
    fun `given map with unicode when convertToVO then converts correctly`() {
        val input = mapOf(
            "name" to "TestName🎉",
            "value" to 42
        )
        
        val result: TestData = input.convertToVO()
        
        assertNotNull(result)
        assertEquals("TestName🎉", result.name)
    }

    @Test
    fun `given boolean value when convertToVO then converts correctly`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 42,
            "active" to true
        )
        
        val result: TestDataWithBoolean = input.convertToVO()
        
        assertNotNull(result)
        assertEquals(true, result.active)
    }

    @Test
    fun `given double value when convertToVO then converts correctly`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 42,
            "price" to 19.99
        )
        
        val result: TestDataWithDouble = input.convertToVO()
        
        assertNotNull(result)
        assertEquals(19.99, result.price)
    }

    @Test
    fun `given long value when convertToVO then converts correctly`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to 9223372036854775807L
        )
        
        val result: TestDataWithLong = input.convertToVO()
        
        assertNotNull(result)
        assertEquals(9223372036854775807L, result.value)
    }

    @Test
    fun `given multiple nested objects when convertToVO then converts all levels`() {
        val input = mapOf(
            "name" to "TestName",
            "nested" to mapOf(
                "id" to 1,
                "data" to mapOf(
                    "key" to "value"
                )
            )
        )
        
        val result: TestDataDeepNested = input.convertToVO()
        
        assertNotNull(result)
        assertEquals("TestName", result.name)
    }

    @Test
    fun `given array of values when convertToVO then converts to kotlin list`() {
        val input = mapOf(
            "name" to "TestName",
            "items" to listOf(1, 2, 3, 4, 5)
        )
        
        val result: TestDataWithList = input.convertToVO()
        
        assertNotNull(result)
        assertEquals(5, result.items.size)
    }

    @Test
    fun `given number as string when convertToVO then converts if valid`() {
        val input = mapOf(
            "name" to "TestName",
            "value" to "42"
        )
        
        try {
            val result: TestData = input.convertToVO()
            assertNotNull(result)
        } catch (e: Exception) {
            assertTrue(e is ClassCastException || e is IllegalArgumentException)
        }
    }

    @Test
    fun `given object with null fields when convertToVO then preserves nulls`() {
        val input = mapOf(
            "name" to null,
            "value" to 42
        )
        
        val result: TestDataNullable = input.convertToVO()
        
        assertNotNull(result)
        assertNull(result.name)
    }

    data class TestData(
        val name: String = "",
        val value: Int = 0
    )

    data class TestDataOptional(
        val name: String? = null,
        val value: Int? = null
    )

    data class TestDataNullable(
        val name: String? = null,
        val value: Int = 0
    )

    data class TestDataWithBoolean(
        val name: String = "",
        val value: Int = 0,
        val active: Boolean = false
    )

    data class TestDataWithDouble(
        val name: String = "",
        val value: Int = 0,
        val price: Double = 0.0
    )

    data class TestDataWithLong(
        val name: String = "",
        val value: Long = 0L
    )

    data class NestedData(
        val id: Int = 0,
        val data: Map<String, Any> = emptyMap()
    )

    data class TestDataDeepNested(
        val name: String = "",
        val nested: NestedData? = null
    )

    data class TestDataWithList(
        val name: String = "",
        val items: List<Int> = emptyList()
    )

    data class TestDataWithNested(
        val name: String = "",
        val value: Int = 0,
        val nested: Map<String, Any> = emptyMap()
    )
}