package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDContentScaleTest {

    @Test
    fun `given CROP enum constant when accessing then returns valid constant`() {
        val constant = CraftDContentScale.CROP
        assertEquals(CraftDContentScale.CROP, constant)
    }

    @Test
    fun `given FIT enum constant when accessing then returns valid constant`() {
        val constant = CraftDContentScale.FIT
        assertEquals(CraftDContentScale.FIT, constant)
    }

    @Test
    fun `given FILL_BOUNDS enum constant when accessing then returns valid constant`() {
        val constant = CraftDContentScale.FILL_BOUNDS
        assertEquals(CraftDContentScale.FILL_BOUNDS, constant)
    }

    @Test
    fun `given FILL_WIDTH enum constant when accessing then returns valid constant`() {
        val constant = CraftDContentScale.FILL_WIDTH
        assertEquals(CraftDContentScale.FILL_WIDTH, constant)
    }

    @Test
    fun `given FILL_HEIGHT enum constant when accessing then returns valid constant`() {
        val constant = CraftDContentScale.FILL_HEIGHT
        assertEquals(CraftDContentScale.FILL_HEIGHT, constant)
    }

    @Test
    fun `given INSIDE enum constant when accessing then returns valid constant`() {
        val constant = CraftDContentScale.INSIDE
        assertEquals(CraftDContentScale.INSIDE, constant)
    }

    @Test
    fun `given NONE enum constant when accessing then returns valid constant`() {
        val constant = CraftDContentScale.NONE
        assertEquals(CraftDContentScale.NONE, constant)
    }

    @Test
    fun `given valid enum name CROP when converting via enumValueOf then returns correct constant`() {
        val result = enumValueOf<CraftDContentScale>("CROP")
        assertEquals(CraftDContentScale.CROP, result)
    }

    @Test
    fun `given valid enum name FIT when converting via enumValueOf then returns correct constant`() {
        val result = enumValueOf<CraftDContentScale>("FIT")
        assertEquals(CraftDContentScale.FIT, result)
    }

    @Test
    fun `given valid enum name FILL_BOUNDS when converting via enumValueOf then returns correct constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_BOUNDS")
        assertEquals(CraftDContentScale.FILL_BOUNDS, result)
    }

    @Test
    fun `given valid enum name FILL_WIDTH when converting via enumValueOf then returns correct constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_WIDTH")
        assertEquals(CraftDContentScale.FILL_WIDTH, result)
    }

    @Test
    fun `given valid enum name FILL_HEIGHT when converting via enumValueOf then returns correct constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_HEIGHT")
        assertEquals(CraftDContentScale.FILL_HEIGHT, result)
    }

    @Test
    fun `given valid enum name INSIDE when converting via enumValueOf then returns correct constant`() {
        val result = enumValueOf<CraftDContentScale>("INSIDE")
        assertEquals(CraftDContentScale.INSIDE, result)
    }

    @Test
    fun `given valid enum name NONE when converting via enumValueOf then returns correct constant`() {
        val result = enumValueOf<CraftDContentScale>("NONE")
        assertEquals(CraftDContentScale.NONE, result)
    }

    @Test
    fun `given invalid enum name when converting via enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("INVALID_SCALE")
        }
    }

    @Test
    fun `given empty string enum name when converting via enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("")
        }
    }

    @Test
    fun `given lowercase enum name when converting via enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("crop")
        }
    }

    @Test
    fun `given all enum constants when iterating values then returns seven constants`() {
        val values = CraftDContentScale.values()
        assertEquals(7, values.size)
    }

    @Test
    fun `given all enum constants when iterating values then contains CROP`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.CROP))
    }

    @Test
    fun `given all enum constants when iterating values then contains FIT`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FIT))
    }

    @Test
    fun `given all enum constants when iterating values then contains FILL_BOUNDS`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FILL_BOUNDS))
    }

    @Test
    fun `given all enum constants when iterating values then contains FILL_WIDTH`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FILL_WIDTH))
    }

    @Test
    fun `given all enum constants when iterating values then contains FILL_HEIGHT`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FILL_HEIGHT))
    }

    @Test
    fun `given all enum constants when iterating values then contains INSIDE`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.INSIDE))
    }

    @Test
    fun `given all enum constants when iterating values then contains NONE`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.NONE))
    }

    @Test
    fun `given enum constant CROP when getting name then returns CROP string`() {
        assertEquals("CROP", CraftDContentScale.CROP.name)
    }

    @Test
    fun `given enum constant FIT when getting name then returns FIT string`() {
        assertEquals("FIT", CraftDContentScale.FIT.name)
    }

    @Test
    fun `given enum constant FILL_BOUNDS when getting name then returns FILL_BOUNDS string`() {
        assertEquals("FILL_BOUNDS", CraftDContentScale.FILL_BOUNDS.name)
    }

    @Test
    fun `given enum constant FILL_WIDTH when getting name then returns FILL_WIDTH string`() {
        assertEquals("FILL_WIDTH", CraftDContentScale.FILL_WIDTH.name)
    }

    @Test
    fun `given enum constant FILL_HEIGHT when getting name then returns FILL_HEIGHT string`() {
        assertEquals("FILL_HEIGHT", CraftDContentScale.FILL_HEIGHT.name)
    }

    @Test
    fun `given enum constant INSIDE when getting name then returns INSIDE string`() {
        assertEquals("INSIDE", CraftDContentScale.INSIDE.name)
    }

    @Test
    fun `given enum constant NONE when getting name then returns NONE string`() {
        assertEquals("NONE", CraftDContentScale.NONE.name)
    }

    @Test
    fun `given enum constant CROP when getting ordinal then returns zero`() {
        assertEquals(0, CraftDContentScale.CROP.ordinal)
    }

    @Test
    fun `given enum constant FIT when getting ordinal then returns one`() {
        assertEquals(1, CraftDContentScale.FIT.ordinal)
    }

    @Test
    fun `given enum constant FILL_BOUNDS when getting ordinal then returns two`() {
        assertEquals(2, CraftDContentScale.FILL_BOUNDS.ordinal)
    }

    @Test
    fun `given enum constant FILL_WIDTH when getting ordinal then returns three`() {
        assertEquals(3, CraftDContentScale.FILL_WIDTH.ordinal)
    }

    @Test
    fun `given enum constant FILL_HEIGHT when getting ordinal then returns four`() {
        assertEquals(4, CraftDContentScale.FILL_HEIGHT.ordinal)
    }

    @Test
    fun `given enum constant INSIDE when getting ordinal then returns five`() {
        assertEquals(5, CraftDContentScale.INSIDE.ordinal)
    }

    @Test
    fun `given enum constant NONE when getting ordinal then returns six`() {
        assertEquals(6, CraftDContentScale.NONE.ordinal)
    }

    @Test
    fun `given two same enum constants when comparing equality then returns true`() {
        val constant1 = CraftDContentScale.CROP
        val constant2 = CraftDContentScale.CROP
        assertEquals(constant1, constant2)
    }

    @Test
    fun `given two different enum constants when comparing equality then returns false`() {
        val constant1 = CraftDContentScale.CROP
        val constant2 = CraftDContentScale.FIT
        assertNotEquals(constant1, constant2)
    }

    @Test
    fun `given enum constant when converting to string then returns name representation`() {
        val result = CraftDContentScale.CROP.toString()
        assert(result.isNotEmpty())
    }

    @Test
    fun `given enum constant CROP when getting hash code then returns consistent value`() {
        val hashCode1 = CraftDContentScale.CROP.hashCode()
        val hashCode2 = CraftDContentScale.CROP.hashCode()
        assertEquals(hashCode1, hashCode2)
    }

    @Test
    fun `given different enum constants when getting hash codes then may differ`() {
        val hashCode1 = CraftDContentScale.CROP.hashCode()
        val hashCode2 = CraftDContentScale.FIT.hashCode()
        assertNotEquals(hashCode1, hashCode2)
    }
}

fun assertNotEquals(expected: Any?, actual: Any?) {
    assert(expected != actual)
}