package com.github.codandotv.craftd.androidcore.domain

import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDContentScaleTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given CROP enum constant when accessing then returns valid constant`() {
        val crop = CraftDContentScale.CROP
        assertEquals(CraftDContentScale.CROP, crop)
    }

    @Test
    fun `given FIT enum constant when accessing then returns valid constant`() {
        val fit = CraftDContentScale.FIT
        assertEquals(CraftDContentScale.FIT, fit)
    }

    @Test
    fun `given FILL_BOUNDS enum constant when accessing then returns valid constant`() {
        val fillBounds = CraftDContentScale.FILL_BOUNDS
        assertEquals(CraftDContentScale.FILL_BOUNDS, fillBounds)
    }

    @Test
    fun `given FILL_WIDTH enum constant when accessing then returns valid constant`() {
        val fillWidth = CraftDContentScale.FILL_WIDTH
        assertEquals(CraftDContentScale.FILL_WIDTH, fillWidth)
    }

    @Test
    fun `given FILL_HEIGHT enum constant when accessing then returns valid constant`() {
        val fillHeight = CraftDContentScale.FILL_HEIGHT
        assertEquals(CraftDContentScale.FILL_HEIGHT, fillHeight)
    }

    @Test
    fun `given INSIDE enum constant when accessing then returns valid constant`() {
        val inside = CraftDContentScale.INSIDE
        assertEquals(CraftDContentScale.INSIDE, inside)
    }

    @Test
    fun `given NONE enum constant when accessing then returns valid constant`() {
        val none = CraftDContentScale.NONE
        assertEquals(CraftDContentScale.NONE, none)
    }

    @Test
    fun `given valid enum name CROP when using enumValueOf then returns CROP constant`() {
        val result = enumValueOf<CraftDContentScale>("CROP")
        assertEquals(CraftDContentScale.CROP, result)
    }

    @Test
    fun `given valid enum name FIT when using enumValueOf then returns FIT constant`() {
        val result = enumValueOf<CraftDContentScale>("FIT")
        assertEquals(CraftDContentScale.FIT, result)
    }

    @Test
    fun `given valid enum name FILL_BOUNDS when using enumValueOf then returns FILL_BOUNDS constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_BOUNDS")
        assertEquals(CraftDContentScale.FILL_BOUNDS, result)
    }

    @Test
    fun `given valid enum name FILL_WIDTH when using enumValueOf then returns FILL_WIDTH constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_WIDTH")
        assertEquals(CraftDContentScale.FILL_WIDTH, result)
    }

    @Test
    fun `given valid enum name FILL_HEIGHT when using enumValueOf then returns FILL_HEIGHT constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_HEIGHT")
        assertEquals(CraftDContentScale.FILL_HEIGHT, result)
    }

    @Test
    fun `given valid enum name INSIDE when using enumValueOf then returns INSIDE constant`() {
        val result = enumValueOf<CraftDContentScale>("INSIDE")
        assertEquals(CraftDContentScale.INSIDE, result)
    }

    @Test
    fun `given valid enum name NONE when using enumValueOf then returns NONE constant`() {
        val result = enumValueOf<CraftDContentScale>("NONE")
        assertEquals(CraftDContentScale.NONE, result)
    }

    @Test
    fun `given invalid enum name when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("INVALID")
        }
    }

    @Test
    fun `given invalid enum name lowercase when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("crop")
        }
    }

    @Test
    fun `given empty string when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("")
        }
    }

    @Test
    fun `given all enum constants when iterating then returns all seven values`() {
        val values = CraftDContentScale.values()
        assertEquals(7, values.size)
    }

    @Test
    fun `given all enum constants when iterating then contains CROP`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.CROP))
    }

    @Test
    fun `given all enum constants when iterating then contains FIT`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FIT))
    }

    @Test
    fun `given all enum constants when iterating then contains FILL_BOUNDS`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FILL_BOUNDS))
    }

    @Test
    fun `given all enum constants when iterating then contains FILL_WIDTH`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FILL_WIDTH))
    }

    @Test
    fun `given all enum constants when iterating then contains FILL_HEIGHT`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.FILL_HEIGHT))
    }

    @Test
    fun `given all enum constants when iterating then contains INSIDE`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.INSIDE))
    }

    @Test
    fun `given all enum constants when iterating then contains NONE`() {
        val values = CraftDContentScale.values()
        assert(values.contains(CraftDContentScale.NONE))
    }

    @Test
    fun `given CROP constant when getting name then returns CROP string`() {
        assertEquals("CROP", CraftDContentScale.CROP.name)
    }

    @Test
    fun `given FIT constant when getting name then returns FIT string`() {
        assertEquals("FIT", CraftDContentScale.FIT.name)
    }

    @Test
    fun `given FILL_BOUNDS constant when getting name then returns FILL_BOUNDS string`() {
        assertEquals("FILL_BOUNDS", CraftDContentScale.FILL_BOUNDS.name)
    }

    @Test
    fun `given FILL_WIDTH constant when getting name then returns FILL_WIDTH string`() {
        assertEquals("FILL_WIDTH", CraftDContentScale.FILL_WIDTH.name)
    }

    @Test
    fun `given FILL_HEIGHT constant when getting name then returns FILL_HEIGHT string`() {
        assertEquals("FILL_HEIGHT", CraftDContentScale.FILL_HEIGHT.name)
    }

    @Test
    fun `given INSIDE constant when getting name then returns INSIDE string`() {
        assertEquals("INSIDE", CraftDContentScale.INSIDE.name)
    }

    @Test
    fun `given NONE constant when getting name then returns NONE string`() {
        assertEquals("NONE", CraftDContentScale.NONE.name)
    }

    @Test
    fun `given CROP constant when getting ordinal then returns zero`() {
        assertEquals(0, CraftDContentScale.CROP.ordinal)
    }

    @Test
    fun `given FIT constant when getting ordinal then returns one`() {
        assertEquals(1, CraftDContentScale.FIT.ordinal)
    }

    @Test
    fun `given FILL_BOUNDS constant when getting ordinal then returns two`() {
        assertEquals(2, CraftDContentScale.FILL_BOUNDS.ordinal)
    }

    @Test
    fun `given FILL_WIDTH constant when getting ordinal then returns three`() {
        assertEquals(3, CraftDContentScale.FILL_WIDTH.ordinal)
    }

    @Test
    fun `given FILL_HEIGHT constant when getting ordinal then returns four`() {
        assertEquals(4, CraftDContentScale.FILL_HEIGHT.ordinal)
    }

    @Test
    fun `given INSIDE constant when getting ordinal then returns five`() {
        assertEquals(5, CraftDContentScale.INSIDE.ordinal)
    }

    @Test
    fun `given NONE constant when getting ordinal then returns six`() {
        assertEquals(6, CraftDContentScale.NONE.ordinal)
    }

    @Test
    fun `given two same enum constants when comparing with equals then returns true`() {
        val crop1 = CraftDContentScale.CROP
        val crop2 = CraftDContentScale.CROP
        assertEquals(crop1, crop2)
    }

    @Test
    fun `given two different enum constants when comparing with equals then returns false`() {
        val crop = CraftDContentScale.CROP
        val fit = CraftDContentScale.FIT
        assert(crop != fit)
    }

    @Test
    fun `given same enum constant when comparing hashCode then returns same hash`() {
        val crop1 = CraftDContentScale.CROP
        val crop2 = CraftDContentScale.CROP
        assertEquals(crop1.hashCode(), crop2.hashCode())
    }

    @Test
    fun `given enum constant when converting to string then returns name representation`() {
        val toString = CraftDContentScale.CROP.toString()
        assert(toString.contains("CROP"))
    }
}