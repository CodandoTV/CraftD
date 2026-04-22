package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDTextStyleTest {

    @Test
    fun `given BOLD constant when accessed then returns valid enum value`() {
        val result = CraftDTextStyle.BOLD
        assertNotNull(result)
        assertEquals(CraftDTextStyle.BOLD, result)
    }

    @Test
    fun `given ITALIC constant when accessed then returns valid enum value`() {
        val result = CraftDTextStyle.ITALIC
        assertNotNull(result)
        assertEquals(CraftDTextStyle.ITALIC, result)
    }

    @Test
    fun `given NORMAL constant when accessed then returns valid enum value`() {
        val result = CraftDTextStyle.NORMAL
        assertNotNull(result)
        assertEquals(CraftDTextStyle.NORMAL, result)
    }

    @Test
    fun `given BOLD string when converted via enumValueOf then returns BOLD enum`() {
        val result = enumValueOf<CraftDTextStyle>("BOLD")
        assertEquals(CraftDTextStyle.BOLD, result)
    }

    @Test
    fun `given ITALIC string when converted via enumValueOf then returns ITALIC enum`() {
        val result = enumValueOf<CraftDTextStyle>("ITALIC")
        assertEquals(CraftDTextStyle.ITALIC, result)
    }

    @Test
    fun `given NORMAL string when converted via enumValueOf then returns NORMAL enum`() {
        val result = enumValueOf<CraftDTextStyle>("NORMAL")
        assertEquals(CraftDTextStyle.NORMAL, result)
    }

    @Test
    fun `given all enum values when listed then returns exactly three constants`() {
        val values = CraftDTextStyle.values()
        assertEquals(3, values.size)
    }

    @Test
    fun `given all enum values when listed then contains BOLD ITALIC NORMAL`() {
        val values = CraftDTextStyle.values().toList()
        assertEquals(true, values.contains(CraftDTextStyle.BOLD))
        assertEquals(true, values.contains(CraftDTextStyle.ITALIC))
        assertEquals(true, values.contains(CraftDTextStyle.NORMAL))
    }

    @Test
    fun `given BOLD enum when compared to itself then returns true for equality`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.BOLD
        assertEquals(style1, style2)
    }

    @Test
    fun `given BOLD enum when compared to ITALIC then returns false for equality`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.ITALIC
        assertEquals(false, style1 == style2)
    }

    @Test
    fun `given BOLD enum when hashCode called then returns consistent value`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.BOLD
        assertEquals(style1.hashCode(), style2.hashCode())
    }

    @Test
    fun `given BOLD enum when converted to string then returns BOLD`() {
        val result = CraftDTextStyle.BOLD.toString()
        assertEquals("BOLD", result)
    }

    @Test
    fun `given ITALIC enum when converted to string then returns ITALIC`() {
        val result = CraftDTextStyle.ITALIC.toString()
        assertEquals("ITALIC", result)
    }

    @Test
    fun `given NORMAL enum when converted to string then returns NORMAL`() {
        val result = CraftDTextStyle.NORMAL.toString()
        assertEquals("NORMAL", result)
    }

    @Test
    fun `given BOLD enum when name property accessed then returns BOLD`() {
        val result = CraftDTextStyle.BOLD.name
        assertEquals("BOLD", result)
    }

    @Test
    fun `given ITALIC enum when ordinal property accessed then returns one`() {
        val result = CraftDTextStyle.ITALIC.ordinal
        assertEquals(1, result)
    }

    @Test
    fun `given NORMAL enum when ordinal property accessed then returns two`() {
        val result = CraftDTextStyle.NORMAL.ordinal
        assertEquals(2, result)
    }

    @Test
    fun `given BOLD enum when ordinal property accessed then returns zero`() {
        val result = CraftDTextStyle.BOLD.ordinal
        assertEquals(0, result)
    }

    @Test
    fun `given enum values when used in set then all unique values present`() {
        val styleSet = setOf(
            CraftDTextStyle.BOLD,
            CraftDTextStyle.ITALIC,
            CraftDTextStyle.NORMAL,
            CraftDTextStyle.BOLD
        )
        assertEquals(3, styleSet.size)
    }

    @Test
    fun `given enum values when iterated then all three constants accessible`() {
        var count = 0
        for (style in CraftDTextStyle.values()) {
            count++
        }
        assertEquals(3, count)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given invalid string when converted via enumValueOf then throws IllegalArgumentException`() {
        enumValueOf<CraftDTextStyle>("INVALID")
    }

    @Test
    fun `given BOLD enum when used in when expression then returns correct branch`() {
        val result = when (CraftDTextStyle.BOLD) {
            CraftDTextStyle.BOLD -> "bold_style"
            CraftDTextStyle.ITALIC -> "italic_style"
            CraftDTextStyle.NORMAL -> "normal_style"
        }
        assertEquals("bold_style", result)
    }

    @Test
    fun `given ITALIC enum when used in when expression then returns correct branch`() {
        val result = when (CraftDTextStyle.ITALIC) {
            CraftDTextStyle.BOLD -> "bold_style"
            CraftDTextStyle.ITALIC -> "italic_style"
            CraftDTextStyle.NORMAL -> "normal_style"
        }
        assertEquals("italic_style", result)
    }

    @Test
    fun `given NORMAL enum when used in when expression then returns correct branch`() {
        val result = when (CraftDTextStyle.NORMAL) {
            CraftDTextStyle.BOLD -> "bold_style"
            CraftDTextStyle.ITALIC -> "italic_style"
            CraftDTextStyle.NORMAL -> "normal_style"
        }
        assertEquals("normal_style", result)
    }
}