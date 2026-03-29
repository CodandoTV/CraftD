package com.github.codandotv.craftd.androidcore.extensions

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class StringExtensionsTest {

    @Test
    fun `given String Companion when empty then returns empty string`() {
        val result = String.empty()
        assertEquals("", result)
    }

    @Test
    fun `given String Companion when empty then returns string with length zero`() {
        val result = String.empty()
        assertEquals(0, result.length)
    }

    @Test
    fun `given String Companion when empty then returns string that is empty`() {
        val result = String.empty()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `given String Companion when empty then returns string that equals literal empty string`() {
        val result = String.empty()
        val expected = ""
        assertEquals(expected, result)
    }

    @Test
    fun `given String Companion when empty then returns consistent result on multiple calls`() {
        val result1 = String.empty()
        val result2 = String.empty()
        assertEquals(result1, result2)
    }

    @Test
    fun `given String Companion when empty then result is not blank`() {
        val result = String.empty()
        assertEquals("", result)
        assertTrue(!result.isNotEmpty())
    }

    @Test
    fun `given String Companion when empty then result can be used in string operations`() {
        val result = String.empty()
        val concatenated = result + "test"
        assertEquals("test", concatenated)
    }

    @Test
    fun `given String Companion when empty then result has correct hashCode`() {
        val result = String.empty()
        val expected = ""
        assertEquals(expected.hashCode(), result.hashCode())
    }
}