package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDAlignTest {

    @Test
    fun `given CENTER enum constant when accessed then returns CENTER`() {
        val result = CraftDAlign.CENTER
        assertEquals(CraftDAlign.CENTER, result)
    }

    @Test
    fun `given LEFT enum constant when accessed then returns LEFT`() {
        val result = CraftDAlign.LEFT
        assertEquals(CraftDAlign.LEFT, result)
    }

    @Test
    fun `given RIGHT enum constant when accessed then returns RIGHT`() {
        val result = CraftDAlign.RIGHT
        assertEquals(CraftDAlign.RIGHT, result)
    }

    @Test
    fun `given TOP enum constant when accessed then returns TOP`() {
        val result = CraftDAlign.TOP
        assertEquals(CraftDAlign.TOP, result)
    }

    @Test
    fun `given BOTTOM enum constant when accessed then returns BOTTOM`() {
        val result = CraftDAlign.BOTTOM
        assertEquals(CraftDAlign.BOTTOM, result)
    }

    @Test
    fun `given CENTER string when enumValueOf called then returns CENTER`() {
        val result = enumValueOf<CraftDAlign>("CENTER")
        assertEquals(CraftDAlign.CENTER, result)
    }

    @Test
    fun `given LEFT string when enumValueOf called then returns LEFT`() {
        val result = enumValueOf<CraftDAlign>("LEFT")
        assertEquals(CraftDAlign.LEFT, result)
    }

    @Test
    fun `given RIGHT string when enumValueOf called then returns RIGHT`() {
        val result = enumValueOf<CraftDAlign>("RIGHT")
        assertEquals(CraftDAlign.RIGHT, result)
    }

    @Test
    fun `given TOP string when enumValueOf called then returns TOP`() {
        val result = enumValueOf<CraftDAlign>("TOP")
        assertEquals(CraftDAlign.TOP, result)
    }

    @Test
    fun `given BOTTOM string when enumValueOf called then returns BOTTOM`() {
        val result = enumValueOf<CraftDAlign>("BOTTOM")
        assertEquals(CraftDAlign.BOTTOM, result)
    }

    @Test
    fun `given invalid enum string when enumValueOf called then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDAlign>("INVALID")
        }
    }

    @Test
    fun `given invalid enum string with lowercase when enumValueOf called then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDAlign>("center")
        }
    }

    @Test
    fun `given all enum values when values called then returns all five constants`() {
        val values = CraftDAlign.values()
        assertEquals(5, values.size)
        assertEquals(setOf(CraftDAlign.CENTER, CraftDAlign.LEFT, CraftDAlign.RIGHT, CraftDAlign.TOP, CraftDAlign.BOTTOM), values.toSet())
    }

    @Test
    fun `given CENTER and CENTER when compared then returns true`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.CENTER
        assertEquals(align1, align2)
    }

    @Test
    fun `given CENTER and LEFT when compared then returns false`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.LEFT
        assert(align1 != align2)
    }

    @Test
    fun `given CENTER and RIGHT when compared then returns false`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.RIGHT
        assert(align1 != align2)
    }

    @Test
    fun `given TOP and BOTTOM when compared then returns false`() {
        val align1 = CraftDAlign.TOP
        val align2 = CraftDAlign.BOTTOM
        assert(align1 != align2)
    }

    @Test
    fun `given CENTER when hashCode called then returns consistent value`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.CENTER
        assertEquals(align1.hashCode(), align2.hashCode())
    }

    @Test
    fun `given different enum constants when hashCode called then may differ`() {
        val align1 = CraftDAlign.CENTER
        val align2 = CraftDAlign.LEFT
        assert(align1.hashCode() != align2.hashCode())
    }

    @Test
    fun `given CENTER when name called then returns CENTER`() {
        val align = CraftDAlign.CENTER
        assertEquals("CENTER", align.name)
    }

    @Test
    fun `given LEFT when name called then returns LEFT`() {
        val align = CraftDAlign.LEFT
        assertEquals("LEFT", align.name)
    }

    @Test
    fun `given RIGHT when name called then returns RIGHT`() {
        val align = CraftDAlign.RIGHT
        assertEquals("RIGHT", align.name)
    }

    @Test
    fun `given TOP when name called then returns TOP`() {
        val align = CraftDAlign.TOP
        assertEquals("TOP", align.name)
    }

    @Test
    fun `given BOTTOM when name called then returns BOTTOM`() {
        val align = CraftDAlign.BOTTOM
        assertEquals("BOTTOM", align.name)
    }

    @Test
    fun `given CENTER when ordinal called then returns 0`() {
        val align = CraftDAlign.CENTER
        assertEquals(0, align.ordinal)
    }

    @Test
    fun `given LEFT when ordinal called then returns 1`() {
        val align = CraftDAlign.LEFT
        assertEquals(1, align.ordinal)
    }

    @Test
    fun `given RIGHT when ordinal called then returns 2`() {
        val align = CraftDAlign.RIGHT
        assertEquals(2, align.ordinal)
    }

    @Test
    fun `given TOP when ordinal called then returns 3`() {
        val align = CraftDAlign.TOP
        assertEquals(3, align.ordinal)
    }

    @Test
    fun `given BOTTOM when ordinal called then returns 4`() {
        val align = CraftDAlign.BOTTOM
        assertEquals(4, align.ordinal)
    }

    @Test
    fun `given CENTER when toString called then returns CENTER`() {
        val align = CraftDAlign.CENTER
        assertEquals("CENTER", align.toString())
    }

    @Test
    fun `given LEFT when toString called then returns LEFT`() {
        val align = CraftDAlign.LEFT
        assertEquals("LEFT", align.toString())
    }

    @Test
    fun `given enum values when all distinct then returns true`() {
        val values = CraftDAlign.values()
        val distinctCount = values.distinct().size
        assertEquals(values.size, distinctCount)
    }

    @Test
    fun `given enum when compared to self then returns true`() {
        val align = CraftDAlign.CENTER
        assertEquals(align, align)
    }

    @Test
    fun `given different aligns when stored in set then all are stored`() {
        val alignSet = setOf(CraftDAlign.CENTER, CraftDAlign.LEFT, CraftDAlign.RIGHT, CraftDAlign.TOP, CraftDAlign.BOTTOM)
        assertEquals(5, alignSet.size)
    }

    @Test
    fun `given enum values when sorted by ordinal then maintains correct order`() {
        val sorted = CraftDAlign.values().sortedBy { it.ordinal }
        assertEquals(listOf(CraftDAlign.CENTER, CraftDAlign.LEFT, CraftDAlign.RIGHT, CraftDAlign.TOP, CraftDAlign.BOTTOM), sorted)
    }
}