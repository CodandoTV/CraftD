package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDTextStyleTest {

    @Test
    fun `given BOLD enum when accessing constant then should exist`() {
        val style = CraftDTextStyle.BOLD
        assertEquals(CraftDTextStyle.BOLD, style)
        assertEquals("BOLD", style.name)
    }

    @Test
    fun `given ITALIC enum when accessing constant then should exist`() {
        val style = CraftDTextStyle.ITALIC
        assertEquals(CraftDTextStyle.ITALIC, style)
        assertEquals("ITALIC", style.name)
    }

    @Test
    fun `given NORMAL enum when accessing constant then should exist`() {
        val style = CraftDTextStyle.NORMAL
        assertEquals(CraftDTextStyle.NORMAL, style)
        assertEquals("NORMAL", style.name)
    }

    @Test
    fun `given enum name BOLD when converting via enumValueOf then returns correct constant`() {
        val style = enumValueOf<CraftDTextStyle>("BOLD")
        assertEquals(CraftDTextStyle.BOLD, style)
    }

    @Test
    fun `given enum name ITALIC when converting via enumValueOf then returns correct constant`() {
        val style = enumValueOf<CraftDTextStyle>("ITALIC")
        assertEquals(CraftDTextStyle.ITALIC, style)
    }

    @Test
    fun `given enum name NORMAL when converting via enumValueOf then returns correct constant`() {
        val style = enumValueOf<CraftDTextStyle>("NORMAL")
        assertEquals(CraftDTextStyle.NORMAL, style)
    }

    @Test
    fun `given all enum values when iterating then should have exactly three constants`() {
        val values = CraftDTextStyle.values()
        assertEquals(3, values.size)
    }

    @Test
    fun `given all enum values when checking content then should contain BOLD ITALIC and NORMAL`() {
        val values = CraftDTextStyle.values().toList()
        assertEquals(true, values.contains(CraftDTextStyle.BOLD))
        assertEquals(true, values.contains(CraftDTextStyle.ITALIC))
        assertEquals(true, values.contains(CraftDTextStyle.NORMAL))
    }

    @Test
    fun `given BOLD constant when comparing equality then should equal itself`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.BOLD
        assertEquals(style1, style2)
    }

    @Test
    fun `given BOLD and ITALIC when comparing equality then should not be equal`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.ITALIC
        assertEquals(false, style1 == style2)
    }

    @Test
    fun `given BOLD and NORMAL when comparing equality then should not be equal`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.NORMAL
        assertEquals(false, style1 == style2)
    }

    @Test
    fun `given ITALIC and NORMAL when comparing equality then should not be equal`() {
        val style1 = CraftDTextStyle.ITALIC
        val style2 = CraftDTextStyle.NORMAL
        assertEquals(false, style1 == style2)
    }

    @Test
    fun `given same enum constant when comparing hashCode then should have same hash`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.BOLD
        assertEquals(style1.hashCode(), style2.hashCode())
    }

    @Test
    fun `given different enum constants when comparing hashCode then hash may differ`() {
        val boldHash = CraftDTextStyle.BOLD.hashCode()
        val italicHash = CraftDTextStyle.ITALIC.hashCode()
        assertEquals(false, boldHash == italicHash)
    }

    @Test
    fun `given BOLD enum when calling toString then returns BOLD`() {
        val style = CraftDTextStyle.BOLD
        assertEquals("BOLD", style.toString())
    }

    @Test
    fun `given ITALIC enum when calling toString then returns ITALIC`() {
        val style = CraftDTextStyle.ITALIC
        assertEquals("ITALIC", style.toString())
    }

    @Test
    fun `given NORMAL enum when calling toString then returns NORMAL`() {
        val style = CraftDTextStyle.NORMAL
        assertEquals("NORMAL", style.toString())
    }

    @Test
    fun `given enum when accessing ordinal BOLD then should be zero`() {
        assertEquals(0, CraftDTextStyle.BOLD.ordinal)
    }

    @Test
    fun `given enum when accessing ordinal ITALIC then should be one`() {
        assertEquals(1, CraftDTextStyle.ITALIC.ordinal)
    }

    @Test
    fun `given enum when accessing ordinal NORMAL then should be two`() {
        assertEquals(2, CraftDTextStyle.NORMAL.ordinal)
    }

    @Test
    fun `given all values when checking order then BOLD should come before ITALIC`() {
        val values = CraftDTextStyle.values()
        val boldIndex = values.indexOf(CraftDTextStyle.BOLD)
        val italicIndex = values.indexOf(CraftDTextStyle.ITALIC)
        assertEquals(true, boldIndex < italicIndex)
    }

    @Test
    fun `given all values when checking order then ITALIC should come before NORMAL`() {
        val values = CraftDTextStyle.values()
        val italicIndex = values.indexOf(CraftDTextStyle.ITALIC)
        val normalIndex = values.indexOf(CraftDTextStyle.NORMAL)
        assertEquals(true, italicIndex < normalIndex)
    }

    @Test
    fun `given enum constant when comparing with itself using === then should be same instance`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.BOLD
        assertEquals(true, style1 === style2)
    }

    @Test
    fun `given enum constant when comparing with different constant using === then should be different instances`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.ITALIC
        assertEquals(false, style1 === style2)
    }

    @Test
    fun `given invalid enum name when converting via enumValueOf then should throw exception`() {
        try {
            enumValueOf<CraftDTextStyle>("INVALID")
            assertEquals(true, false)
        } catch (e: IllegalArgumentException) {
            assertNotNull(e)
        }
    }

    @Test
    fun `given empty string when converting via enumValueOf then should throw exception`() {
        try {
            enumValueOf<CraftDTextStyle>("")
            assertEquals(true, false)
        } catch (e: IllegalArgumentException) {
            assertNotNull(e)
        }
    }

    @Test
    fun `given lowercase enum name when converting via enumValueOf then should throw exception`() {
        try {
            enumValueOf<CraftDTextStyle>("bold")
            assertEquals(true, false)
        } catch (e: IllegalArgumentException) {
            assertNotNull(e)
        }
    }

    @Test
    fun `given null when checking enum values then values list should not be null`() {
        val values = CraftDTextStyle.values()
        assertNotNull(values)
    }

    @Test
    fun `given enum values when converting to list then should be mutable`() {
        val values = CraftDTextStyle.values().toMutableList()
        assertEquals(3, values.size)
        values.removeAt(0)
        assertEquals(2, values.size)
    }
}