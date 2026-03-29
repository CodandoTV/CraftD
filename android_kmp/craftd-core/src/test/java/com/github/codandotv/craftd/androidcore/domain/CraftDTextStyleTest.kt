package com.github.codandotv.craftd.androidcore.domain

import io.mockk.junit4.MockKRule
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDTextStyleTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given BOLD enum constant when accessed then returns correct name`() {
        assertEquals("BOLD", CraftDTextStyle.BOLD.name)
    }

    @Test
    fun `given ITALIC enum constant when accessed then returns correct name`() {
        assertEquals("ITALIC", CraftDTextStyle.ITALIC.name)
    }

    @Test
    fun `given NORMAL enum constant when accessed then returns correct name`() {
        assertEquals("NORMAL", CraftDTextStyle.NORMAL.name)
    }

    @Test
    fun `given BOLD string when converting to enum then returns BOLD constant`() {
        val result = enumValueOf<CraftDTextStyle>("BOLD")
        assertEquals(CraftDTextStyle.BOLD, result)
    }

    @Test
    fun `given ITALIC string when converting to enum then returns ITALIC constant`() {
        val result = enumValueOf<CraftDTextStyle>("ITALIC")
        assertEquals(CraftDTextStyle.ITALIC, result)
    }

    @Test
    fun `given NORMAL string when converting to enum then returns NORMAL constant`() {
        val result = enumValueOf<CraftDTextStyle>("NORMAL")
        assertEquals(CraftDTextStyle.NORMAL, result)
    }

    @Test
    fun `given all enum values when iterating then contains three constants`() {
        val values = CraftDTextStyle.values()
        assertEquals(3, values.size)
        assertNotNull(values.find { it == CraftDTextStyle.BOLD })
        assertNotNull(values.find { it == CraftDTextStyle.ITALIC })
        assertNotNull(values.find { it == CraftDTextStyle.NORMAL })
    }

    @Test
    fun `given BOLD constant when comparing ordinal then returns zero`() {
        assertEquals(0, CraftDTextStyle.BOLD.ordinal)
    }

    @Test
    fun `given ITALIC constant when comparing ordinal then returns one`() {
        assertEquals(1, CraftDTextStyle.ITALIC.ordinal)
    }

    @Test
    fun `given NORMAL constant when comparing ordinal then returns two`() {
        assertEquals(2, CraftDTextStyle.NORMAL.ordinal)
    }

    @Test
    fun `given two same enum constants when comparing with equals then returns true`() {
        val first = CraftDTextStyle.BOLD
        val second = CraftDTextStyle.BOLD
        assertEquals(first, second)
    }

    @Test
    fun `given two different enum constants when comparing with equals then returns false`() {
        val first = CraftDTextStyle.BOLD
        val second = CraftDTextStyle.ITALIC
        assertNotEqual(first, second)
    }

    @Test
    fun `given BOLD constant when calling hashCode then returns consistent value`() {
        val firstHash = CraftDTextStyle.BOLD.hashCode()
        val secondHash = CraftDTextStyle.BOLD.hashCode()
        assertEquals(firstHash, secondHash)
    }

    @Test
    fun `given two different enum constants when comparing hashCode then may differ`() {
        val boldHash = CraftDTextStyle.BOLD.hashCode()
        val italicHash = CraftDTextStyle.ITALIC.hashCode()
        assertNotEqual(boldHash, italicHash)
    }

    @Test
    fun `given BOLD enum when calling toString then returns BOLD string`() {
        val result = CraftDTextStyle.BOLD.toString()
        assertEquals("BOLD", result)
    }

    @Test
    fun `given ITALIC enum when calling toString then returns ITALIC string`() {
        val result = CraftDTextStyle.ITALIC.toString()
        assertEquals("ITALIC", result)
    }

    @Test
    fun `given NORMAL enum when calling toString then returns NORMAL string`() {
        val result = CraftDTextStyle.NORMAL.toString()
        assertEquals("NORMAL", result)
    }

    @Test
    fun `given enum values when checking count then exactly three values exist`() {
        assertEquals(3, CraftDTextStyle.values().size)
    }

    @Test
    fun `given BOLD constant when comparing reference equality then returns true for same instance`() {
        val first = CraftDTextStyle.BOLD
        val second = CraftDTextStyle.BOLD
        assertEquals(first === second, true)
    }

    @Test
    fun `given different enum constants when comparing with when expression then returns correct branch`() {
        val style = CraftDTextStyle.ITALIC
        val result = when (style) {
            CraftDTextStyle.BOLD -> "bold"
            CraftDTextStyle.ITALIC -> "italic"
            CraftDTextStyle.NORMAL -> "normal"
        }
        assertEquals("italic", result)
    }

    @Test
    fun `given NORMAL constant when comparing with when expression then returns normal branch`() {
        val style = CraftDTextStyle.NORMAL
        val result = when (style) {
            CraftDTextStyle.BOLD -> "bold"
            CraftDTextStyle.ITALIC -> "italic"
            CraftDTextStyle.NORMAL -> "normal"
        }
        assertEquals("normal", result)
    }

    private fun <T> assertNotEqual(first: T, second: T) {
        val isEqual = first == second
        assertEquals(false, isEqual)
    }
}