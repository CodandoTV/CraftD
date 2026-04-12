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
        assertTrue(result.isEmpty())
    }

    @Test
    fun `given String Companion when empty then returns exact empty string instance`() {
        val result1 = String.empty()
        val result2 = String.empty()
        assertEquals(result1, result2)
    }

    @Test
    fun `given String Companion when empty then returned string has length zero`() {
        val result = String.empty()
        assertEquals(0, result.length)
    }

    @Test
    fun `given String Companion when empty then returned string is not null`() {
        val result = String.empty()
        assert(result != null)
    }

    @Test
    fun `given String Companion when empty multiple times then all results are equal`() {
        val result1 = String.empty()
        val result2 = String.empty()
        val result3 = String.empty()
        assertEquals(result1, result2)
        assertEquals(result2, result3)
    }

    @Test
    fun `given String Companion when empty then returned string can be used in comparisons`() {
        val emptyStr = String.empty()
        assertTrue(emptyStr == "")
    }

    @Test
    fun `given String Companion when empty then returned string behaves like empty string literal`() {
        val result = String.empty()
        val literal = ""
        assertEquals(result, literal)
        assertEquals(result.hashCode(), literal.hashCode())
    }

    @Test
    fun `given String Companion when empty then concatenation produces expected result`() {
        val emptyStr = String.empty()
        val concatenated = emptyStr + "test"
        assertEquals("test", concatenated)
    }

    @Test
    fun `given String Companion when empty then string operations work correctly`() {
        val result = String.empty()
        assertTrue(result.isBlank())
        assertTrue(result.all { false })
    }

    @Test
    fun `given String Companion when empty then string representation is consistent`() {
        val result = String.empty()
        assertEquals("", result.toString())
    }
}