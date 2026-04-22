package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDAlignTest {

    @Test
    fun `given CENTER enum constant when accessed then should exist`() {
        val align = CraftDAlign.CENTER
        assertEquals("CENTER", align.name)
    }

    @Test
    fun `given LEFT enum constant when accessed then should exist`() {
        val align = CraftDAlign.LEFT
        assertEquals("LEFT", align.name)
    }

    @Test
    fun `given RIGHT enum constant when accessed then should exist`() {
        val align = CraftDAlign.RIGHT
        assertEquals("RIGHT", align.name)
    }

    @Test
    fun `given TOP enum constant when accessed then should exist`() {
        val align = CraftDAlign.TOP
        assertEquals("TOP", align.name)
    }

    @Test
    fun `given BOTTOM enum constant when accessed then should exist`() {
        val align = CraftDAlign.BOTTOM
        assertEquals("BOTTOM", align.name)
    }

    @Test
    fun `given CENTER constant when converted via enumValueOf then should return CENTER`() {
        val align = enumValueOf<CraftDAlign>("CENTER")
        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given LEFT constant when converted via enumValueOf then should return LEFT`() {
        val align = enumValueOf<CraftDAlign>("LEFT")
        assertEquals(CraftDAlign.LEFT, align)
    }

    @Test
    fun `given RIGHT constant when converted via enumValueOf then should return RIGHT`() {
        val align = enumValueOf<CraftDAlign>("RIGHT")
        assertEquals(CraftDAlign.RIGHT, align)
    }

    @Test
    fun `given TOP constant when converted via enumValueOf then should return TOP`() {
        val align = enumValueOf<CraftDAlign>("TOP")
        assertEquals(CraftDAlign.TOP, align)
    }

    @Test
    fun `given BOTTOM constant when converted via enumValueOf then should return BOTTOM`() {
        val align = enumValueOf<CraftDAlign>("BOTTOM")
        assertEquals(CraftDAlign.BOTTOM, align)
    }

    @Test
    fun `given invalid string when converted via enumValueOf then should throw IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDAlign>("INVALID")
        }
    }

    @Test
    fun `given empty string when converted via enumValueOf then should throw IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDAlign>("")
        }
    }

    @Test
    fun `given all enum values when accessed then should have exactly five constants`() {
        val values = CraftDAlign.values()
        assertEquals(5, values.size)
    }

    @Test
    fun `given all enum values when accessed then should contain CENTER`() {
        assert(CraftDAlign.values().contains(CraftDAlign.CENTER))
    }

    @Test
    fun `given all enum values when accessed then should contain LEFT`() {
        assert(CraftDAlign.values().contains(CraftDAlign.LEFT))
    }

    @Test
    fun `given all enum values when accessed then should contain RIGHT`() {
        assert(CraftDAlign.values().contains(CraftDAlign.RIGHT))
    }

    @Test
    fun `given all enum values when accessed then should contain TOP`() {
        assert(CraftDAlign.values().contains(CraftDAlign.TOP))
    }

    @Test
    fun `given all enum values when accessed then should contain BOTTOM`() {
        assert(CraftDAlign.values().contains(CraftDAlign.BOTTOM))
    }

    @Test
    fun `given CENTER constant when compared to CENTER then should be equal`() {
        assertEquals(CraftDAlign.CENTER, CraftDAlign.CENTER)
    }

    @Test
    fun `given CENTER constant when compared to LEFT then should not be equal`() {
        assert(CraftDAlign.CENTER != CraftDAlign.LEFT)
    }

    @Test
    fun `given CENTER constant when compared to RIGHT then should not be equal`() {
        assert(CraftDAlign.CENTER != CraftDAlign.RIGHT)
    }

    @Test
    fun `given LEFT constant when compared to TOP then should not be equal`() {
        assert(CraftDAlign.LEFT != CraftDAlign.TOP)
    }

    @Test
    fun `given BOTTOM constant when converted to string then should return BOTTOM`() {
        assertEquals("BOTTOM", CraftDAlign.BOTTOM.toString())
    }

    @Test
    fun `given enum constants when compared via ordinal then CENTER should be first`() {
        assertEquals(0, CraftDAlign.CENTER.ordinal)
    }

    @Test
    fun `given enum constants when compared via ordinal then LEFT should be second`() {
        assertEquals(1, CraftDAlign.LEFT.ordinal)
    }

    @Test
    fun `given enum constants when compared via ordinal then RIGHT should be third`() {
        assertEquals(2, CraftDAlign.RIGHT.ordinal)
    }

    @Test
    fun `given enum constants when compared via ordinal then TOP should be fourth`() {
        assertEquals(3, CraftDAlign.TOP.ordinal)
    }

    @Test
    fun `given enum constants when compared via ordinal then BOTTOM should be fifth`() {
        assertEquals(4, CraftDAlign.BOTTOM.ordinal)
    }

    @Test
    fun `given CENTER constant when hashCode called then should match another CENTER hashCode`() {
        assertEquals(CraftDAlign.CENTER.hashCode(), CraftDAlign.CENTER.hashCode())
    }

    @Test
    fun `given LEFT and RIGHT constants when hashCode called then should not be equal`() {
        assert(CraftDAlign.LEFT.hashCode() != CraftDAlign.RIGHT.hashCode())
    }
}