package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDAlignTest {

    @Test
    fun `given CraftDAlign enum when accessing CENTER then constant exists`() {
        val align = CraftDAlign.CENTER
        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given CraftDAlign enum when accessing LEFT then constant exists`() {
        val align = CraftDAlign.LEFT
        assertEquals(CraftDAlign.LEFT, align)
    }

    @Test
    fun `given CraftDAlign enum when accessing RIGHT then constant exists`() {
        val align = CraftDAlign.RIGHT
        assertEquals(CraftDAlign.RIGHT, align)
    }

    @Test
    fun `given CraftDAlign enum when accessing TOP then constant exists`() {
        val align = CraftDAlign.TOP
        assertEquals(CraftDAlign.TOP, align)
    }

    @Test
    fun `given CraftDAlign enum when accessing BOTTOM then constant exists`() {
        val align = CraftDAlign.BOTTOM
        assertEquals(CraftDAlign.BOTTOM, align)
    }

    @Test
    fun `given CraftDAlign enum when converting CENTER via enumValueOf then returns CENTER`() {
        val align = enumValueOf<CraftDAlign>("CENTER")
        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given CraftDAlign enum when converting LEFT via enumValueOf then returns LEFT`() {
        val align = enumValueOf<CraftDAlign>("LEFT")
        assertEquals(CraftDAlign.LEFT, align)
    }

    @Test
    fun `given CraftDAlign enum when converting RIGHT via enumValueOf then returns RIGHT`() {
        val align = enumValueOf<CraftDAlign>("RIGHT")
        assertEquals(CraftDAlign.RIGHT, align)
    }

    @Test
    fun `given CraftDAlign enum when converting TOP via enumValueOf then returns TOP`() {
        val align = enumValueOf<CraftDAlign>("TOP")
        assertEquals(CraftDAlign.TOP, align)
    }

    @Test
    fun `given CraftDAlign enum when converting BOTTOM via enumValueOf then returns BOTTOM`() {
        val align = enumValueOf<CraftDAlign>("BOTTOM")
        assertEquals(CraftDAlign.BOTTOM, align)
    }

    @Test
    fun `given CraftDAlign enum when converting invalid string via enumValueOf then throws exception`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDAlign>("INVALID")
        }
    }

    @Test
    fun `given CraftDAlign enum when comparing CENTER instances then they are equal`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.CENTER
        assertEquals(align1, align2)
    }

    @Test
    fun `given CraftDAlign enum when comparing different constants then they are not equal`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.LEFT
        val areEqual = align1 == align2
        assertEquals(false, areEqual)
    }

    @Test
    fun `given CraftDAlign enum when getting values then all five constants are present`() {
        val values = CraftDAlign.values()
        assertEquals(5, values.size)
    }

    @Test
    fun `given CraftDAlign enum when getting values then CENTER is in values`() {
        val values = CraftDAlign.values()
        assert(values.contains(CraftDAlign.CENTER))
    }

    @Test
    fun `given CraftDAlign enum when getting values then LEFT is in values`() {
        val values = CraftDAlign.values()
        assert(values.contains(CraftDAlign.LEFT))
    }

    @Test
    fun `given CraftDAlign enum when getting values then RIGHT is in values`() {
        val values = CraftDAlign.values()
        assert(values.contains(CraftDAlign.RIGHT))
    }

    @Test
    fun `given CraftDAlign enum when getting values then TOP is in values`() {
        val values = CraftDAlign.values()
        assert(values.contains(CraftDAlign.TOP))
    }

    @Test
    fun `given CraftDAlign enum when getting values then BOTTOM is in values`() {
        val values = CraftDAlign.values()
        assert(values.contains(CraftDAlign.BOTTOM))
    }

    @Test
    fun `given CraftDAlign enum when calling valueOf with CENTER then returns CENTER`() {
        val align = CraftDAlign.valueOf("CENTER")
        assertEquals(CraftDAlign.CENTER, align)
    }

    @Test
    fun `given CraftDAlign enum when calling valueOf with LEFT then returns LEFT`() {
        val align = CraftDAlign.valueOf("LEFT")
        assertEquals(CraftDAlign.LEFT, align)
    }

    @Test
    fun `given CraftDAlign enum when getting name of CENTER then returns CENTER string`() {
        val align = CraftDAlign.CENTER
        assertEquals("CENTER", align.name)
    }

    @Test
    fun `given CraftDAlign enum when getting name of LEFT then returns LEFT string`() {
        val align = CraftDAlign.LEFT
        assertEquals("LEFT", align.name)
    }

    @Test
    fun `given CraftDAlign enum when getting name of RIGHT then returns RIGHT string`() {
        val align = CraftDAlign.RIGHT
        assertEquals("RIGHT", align.name)
    }

    @Test
    fun `given CraftDAlign enum when getting name of TOP then returns TOP string`() {
        val align = CraftDAlign.TOP
        assertEquals("TOP", align.name)
    }

    @Test
    fun `given CraftDAlign enum when getting name of BOTTOM then returns BOTTOM string`() {
        val align = CraftDAlign.BOTTOM
        assertEquals("BOTTOM", align.name)
    }

    @Test
    fun `given CraftDAlign enum when getting ordinal of CENTER then returns 0`() {
        val align = CraftDAlign.CENTER
        assertEquals(0, align.ordinal)
    }

    @Test
    fun `given CraftDAlign enum when getting ordinal of LEFT then returns 1`() {
        val align = CraftDAlign.LEFT
        assertEquals(1, align.ordinal)
    }

    @Test
    fun `given CraftDAlign enum when getting ordinal of RIGHT then returns 2`() {
        val align = CraftDAlign.RIGHT
        assertEquals(2, align.ordinal)
    }

    @Test
    fun `given CraftDAlign enum when getting ordinal of TOP then returns 3`() {
        val align = CraftDAlign.TOP
        assertEquals(3, align.ordinal)
    }

    @Test
    fun `given CraftDAlign enum when getting ordinal of BOTTOM then returns 4`() {
        val align = CraftDAlign.BOTTOM
        assertEquals(4, align.ordinal)
    }

    @Test
    fun `given CraftDAlign enum when checking hash code of same constant then hash codes are equal`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.CENTER
        assertEquals(align1.hashCode(), align2.hashCode())
    }

    @Test
    fun `given CraftDAlign enum when converting to string then returns enum name`() {
        val align = CraftDAlign.CENTER
        assertEquals("CENTER", align.toString())
    }
}