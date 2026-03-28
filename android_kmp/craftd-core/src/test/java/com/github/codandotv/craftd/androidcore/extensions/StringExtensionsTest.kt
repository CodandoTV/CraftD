package com.github.codandotv.craftd.androidcore.extensions

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class StringExtensionsTest {

    @Test
    fun `given Companion object when empty then returns empty string`() {
        val result = String.empty()
        assertEquals("", result)
    }

    @Test
    fun `given Companion object when empty then returns string with zero length`() {
        val result = String.empty()
        assertEquals(0, result.length)
    }

    @Test
    fun `given Companion object when empty then result is not null`() {
        val result = String.empty()
        assert(result != null)
    }

    @Test
    fun `given Companion object when empty then result equals empty literal`() {
        val result = String.empty()
        assert(result == "")
    }

    @Test
    fun `given Companion object when empty is called multiple times then returns consistent results`() {
        val result1 = String.empty()
        val result2 = String.empty()
        assertEquals(result1, result2)
    }

    @Test
    fun `given Companion object when empty then result can be used in string concatenation`() {
        val result = String.empty()
        val concatenated = result + "test"
        assertEquals("test", concatenated)
    }

    @Test
    fun `given Companion object when empty then result isEmpty returns true`() {
        val result = String.empty()
        assert(result.isEmpty())
    }

    @Test
    fun `given Companion object when empty then result isBlank returns true`() {
        val result = String.empty()
        assert(result.isBlank())
    }

    @Test
    fun `given Companion object when empty then result toCharArray returns empty array`() {
        val result = String.empty()
        assertEquals(0, result.toCharArray().size)
    }

    @Test
    fun `given Companion object when empty then result hashCode is consistent`() {
        val result1 = String.empty()
        val result2 = String.empty()
        assertEquals(result1.hashCode(), result2.hashCode())
    }
}