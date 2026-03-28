package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDTextStyleTest {

    @Test
    fun `given CraftDTextStyle enum when accessing BOLD constant then returns BOLD`() {
        val style = CraftDTextStyle.BOLD
        assertEquals(CraftDTextStyle.BOLD, style)
        assertEquals("BOLD", style.name)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing ITALIC constant then returns ITALIC`() {
        val style = CraftDTextStyle.ITALIC
        assertEquals(CraftDTextStyle.ITALIC, style)
        assertEquals("ITALIC", style.name)
    }

    @Test
    fun `given CraftDTextStyle enum when accessing NORMAL constant then returns NORMAL`() {
        val style = CraftDTextStyle.NORMAL
        assertEquals(CraftDTextStyle.NORMAL, style)
        assertEquals("NORMAL", style.name)
    }

    @Test
    fun `given valid enum name when using enumValueOf then returns corresponding enum constant`() {
        val boldStyle = enumValueOf<CraftDTextStyle>("BOLD")
        assertEquals(CraftDTextStyle.BOLD, boldStyle)

        val italicStyle = enumValueOf<CraftDTextStyle>("ITALIC")
        assertEquals(CraftDTextStyle.ITALIC, italicStyle)

        val normalStyle = enumValueOf<CraftDTextStyle>("NORMAL")
        assertEquals(CraftDTextStyle.NORMAL, normalStyle)
    }

    @Test
    fun `given CraftDTextStyle enum when checking values then contains all three constants`() {
        val values = CraftDTextStyle.values()
        assertEquals(3, values.size)
        assertNotNull(values.find { it == CraftDTextStyle.BOLD })
        assertNotNull(values.find { it == CraftDTextStyle.ITALIC })
        assertNotNull(values.find { it == CraftDTextStyle.NORMAL })
    }

    @Test
    fun `given two same CraftDTextStyle constants when comparing then they are equal`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.BOLD
        assertEquals(style1, style2)
    }

    @Test
    fun `given two different CraftDTextStyle constants when comparing then they are not equal`() {
        val style1 = CraftDTextStyle.BOLD
        val style2 = CraftDTextStyle.ITALIC
        assertNotEqual(style1, style2)
    }

    @Test
    fun `given CraftDTextStyle BOLD when calling hashCode twice then returns same hash`() {
        val style = CraftDTextStyle.BOLD
        val hash1 = style.hashCode()
        val hash2 = style.hashCode()
        assertEquals(hash1, hash2)
    }

    @Test
    fun `given two equal CraftDTextStyle constants when comparing hashCode then they are equal`() {
        val style1 = CraftDTextStyle.NORMAL
        val style2 = CraftDTextStyle.NORMAL
        assertEquals(style1.hashCode(), style2.hashCode())
    }

    @Test
    fun `given CraftDTextStyle enum when calling toString then returns enum name`() {
        assertEquals("BOLD", CraftDTextStyle.BOLD.toString())
        assertEquals("ITALIC", CraftDTextStyle.ITALIC.toString())
        assertEquals("NORMAL", CraftDTextStyle.NORMAL.toString())
    }

    @Test
    fun `given CraftDTextStyle enum when accessing ordinal then returns correct position`() {
        assertEquals(0, CraftDTextStyle.BOLD.ordinal)
        assertEquals(1, CraftDTextStyle.ITALIC.ordinal)
        assertEquals(2, CraftDTextStyle.NORMAL.ordinal)
    }

    @Test
    fun `given CraftDTextStyle when iterating values then all constants are present`() {
        val styleSet = mutableSetOf<CraftDTextStyle>()
        for (style in CraftDTextStyle.values()) {
            styleSet.add(style)
        }
        assertEquals(3, styleSet.size)
        assert(styleSet.contains(CraftDTextStyle.BOLD))
        assert(styleSet.contains(CraftDTextStyle.ITALIC))
        assert(styleSet.contains(CraftDTextStyle.NORMAL))
    }

    private fun assertNotEqual(expected: Any, actual: Any) {
        assert(expected != actual)
    }
}