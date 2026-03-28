package com.github.codandotv.craftd.androidcore.extensions

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class StringExtensionsTest {

    @Test
    fun `given String Companion when empty is called then returns empty string`() {
        val result = String.empty()
        assertEquals("", result)
    }

    @Test
    fun `given String Companion when empty is called multiple times then each call returns empty string`() {
        val result1 = String.empty()
        val result2 = String.empty()
        assertEquals("", result1)
        assertEquals("", result2)
        assertEquals(result1, result2)
    }

    @Test
    fun `given String Companion when empty is called then result has length zero`() {
        val result = String.empty()
        assertEquals(0, result.length)
    }

    @Test
    fun `given String Companion when empty is called then result is not null`() {
        val result = String.empty()
        assert(result != null)
    }

    @Test
    fun `given String Companion when empty is called then result isEmpty is true`() {
        val result = String.empty()
        assert(result.isEmpty())
    }

    @Test
    fun `given empty string from extension when comparing with literal empty string then they are equal`() {
        val result = String.empty()
        assertEquals("", result)
    }

    @Test
    fun `given empty string from extension when comparing with non empty string then they are not equal`() {
        val result = String.empty()
        assert(result != "non-empty")
    }

    @Test
    fun `given String Companion when empty is called then result can be used in string interpolation`() {
        val emptyStr = String.empty()
        val interpolated = "prefix${emptyStr}suffix"
        assertEquals("prefixsuffix", interpolated)
    }

    @Test
    fun `given String Companion when empty is called then result can be concatenated`() {
        val emptyStr = String.empty()
        val concatenated = emptyStr + "test"
        assertEquals("test", concatenated)
    }

    @Test
    fun `given String Companion when empty is called then result hash code is consistent`() {
        val result1 = String.empty()
        val result2 = String.empty()
        assertEquals(result1.hashCode(), result2.hashCode())
    }

    @Test
    fun `given String Companion when empty is called then result is equal to another empty string`() {
        val result = String.empty()
        val otherEmpty = ""
        assertEquals(result, otherEmpty)
    }
}