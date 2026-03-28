package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDAlignTest {

    @Test
    fun `given CraftDAlign CENTER when accessed then returns CENTER constant`() {
        val align = CraftDAlign.CENTER
        assertEquals(CraftDAlign.CENTER, align)
        assertEquals("CENTER", align.name)
    }

    @Test
    fun `given CraftDAlign LEFT when accessed then returns LEFT constant`() {
        val align = CraftDAlign.LEFT
        assertEquals(CraftDAlign.LEFT, align)
        assertEquals("LEFT", align.name)
    }

    @Test
    fun `given CraftDAlign RIGHT when accessed then returns RIGHT constant`() {
        val align = CraftDAlign.RIGHT
        assertEquals(CraftDAlign.RIGHT, align)
        assertEquals("RIGHT", align.name)
    }

    @Test
    fun `given CraftDAlign TOP when accessed then returns TOP constant`() {
        val align = CraftDAlign.TOP
        assertEquals(CraftDAlign.TOP, align)
        assertEquals("TOP", align.name)
    }

    @Test
    fun `given CraftDAlign BOTTOM when accessed then returns BOTTOM constant`() {
        val align = CraftDAlign.BOTTOM
        assertEquals(CraftDAlign.BOTTOM, align)
        assertEquals("BOTTOM", align.name)
    }

    @Test
    fun `given string CENTER when enumValueOf then returns CraftDAlign CENTER`() {
        val align = enumValueOf<CraftDAlign>("CENTER")
        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given string LEFT when enumValueOf then returns CraftDAlign LEFT`() {
        val align = enumValueOf<CraftDAlign>("LEFT")
        assertEquals(CraftDAlign.LEFT, align)
    }

    @Test
    fun `given string RIGHT when enumValueOf then returns CraftDAlign RIGHT`() {
        val align = enumValueOf<CraftDAlign>("RIGHT")
        assertEquals(CraftDAlign.RIGHT, align)
    }

    @Test
    fun `given string TOP when enumValueOf then returns CraftDAlign TOP`() {
        val align = enumValueOf<CraftDAlign>("TOP")
        assertEquals(CraftDAlign.TOP, align)
    }

    @Test
    fun `given string BOTTOM when enumValueOf then returns CraftDAlign BOTTOM`() {
        val align = enumValueOf<CraftDAlign>("BOTTOM")
        assertEquals(CraftDAlign.BOTTOM, align)
    }

    @Test
    fun `given all enum values when accessed then contains exactly five constants`() {
        val values = CraftDAlign.values()
        assertEquals(5, values.size)
    }

    @Test
    fun `given all enum values when checked then contains CENTER LEFT RIGHT TOP BOTTOM`() {
        val values = CraftDAlign.values().toList()
        assertEquals(listOf(CraftDAlign.CENTER, CraftDAlign.LEFT, CraftDAlign.RIGHT, CraftDAlign.TOP, CraftDAlign.BOTTOM), values)
    }

    @Test
    fun `given CraftDAlign CENTER when compared to CENTER then returns true`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.CENTER
        assertEquals(align1, align2)
    }

    @Test
    fun `given CraftDAlign LEFT when compared to LEFT then returns true`() {
        val align1 = CraftDAlign.LEFT
        val align2 = CraftDAlign.LEFT
        assertEquals(align1, align2)
    }

    @Test
    fun `given CraftDAlign CENTER when compared to LEFT then returns false`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.LEFT
        assertNotNull(align1)
        assertNotNull(align2)
        assertEquals(false, align1 == align2)
    }

    @Test
    fun `given CraftDAlign CENTER when hashCode called then returns consistent value`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.CENTER
        assertEquals(align1.hashCode(), align2.hashCode())
    }

    @Test
    fun `given CraftDAlign CENTER and LEFT when hashCode called then may differ`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.LEFT
        assertNotNull(align1.hashCode())
        assertNotNull(align2.hashCode())
    }

    @Test
    fun `given CraftDAlign CENTER when ordinal accessed then returns zero`() {
        val align = CraftDAlign.CENTER
        assertEquals(0, align.ordinal)
    }

    @Test
    fun `given CraftDAlign LEFT when ordinal accessed then returns one`() {
        val align = CraftDAlign.LEFT
        assertEquals(1, align.ordinal)
    }

    @Test
    fun `given CraftDAlign RIGHT when ordinal accessed then returns two`() {
        val align = CraftDAlign.RIGHT
        assertEquals(2, align.ordinal)
    }

    @Test
    fun `given CraftDAlign TOP when ordinal accessed then returns three`() {
        val align = CraftDAlign.TOP
        assertEquals(3, align.ordinal)
    }

    @Test
    fun `given CraftDAlign BOTTOM when ordinal accessed then returns four`() {
        val align = CraftDAlign.BOTTOM
        assertEquals(4, align.ordinal)
    }

    @Test
    fun `given CraftDAlign CENTER when toString called then returns CENTER`() {
        val align = CraftDAlign.CENTER
        assertEquals("CENTER", align.toString())
    }

    @Test
    fun `given CraftDAlign LEFT when toString called then returns LEFT`() {
        val align = CraftDAlign.LEFT
        assertEquals("LEFT", align.toString())
    }

    @Test
    fun `given all enum constants when compared with valueOf then returns same instance`() {
        val centerFromValueOf = CraftDAlign.valueOf("CENTER")
        val centerDirect = CraftDAlign.CENTER
        assertEquals(centerFromValueOf, centerDirect)
    }

    @Test
    fun `given enum when iterated then all five values are present`() {
        val alignments = mutableListOf<CraftDAlign>()
        for (align in CraftDAlign.values()) {
            alignments.add(align)
        }
        assertEquals(5, alignments.size)
        assertEquals(true, alignments.contains(CraftDAlign.CENTER))
        assertEquals(true, alignments.contains(CraftDAlign.LEFT))
        assertEquals(true, alignments.contains(CraftDAlign.RIGHT))
        assertEquals(true, alignments.contains(CraftDAlign.TOP))
        assertEquals(true, alignments.contains(CraftDAlign.BOTTOM))
    }
}