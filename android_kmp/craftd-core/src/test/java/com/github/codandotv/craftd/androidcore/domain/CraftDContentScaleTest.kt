package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDContentScaleTest {

    @Test
    fun `given CraftDContentScale enum when accessing CROP then returns CROP constant`() {
        val result = CraftDContentScale.CROP
        assertNotNull(result)
        assertEquals(CraftDContentScale.CROP, result)
        assertEquals("CROP", result.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FIT then returns FIT constant`() {
        val result = CraftDContentScale.FIT
        assertNotNull(result)
        assertEquals(CraftDContentScale.FIT, result)
        assertEquals("FIT", result.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FILL_BOUNDS then returns FILL_BOUNDS constant`() {
        val result = CraftDContentScale.FILL_BOUNDS
        assertNotNull(result)
        assertEquals(CraftDContentScale.FILL_BOUNDS, result)
        assertEquals("FILL_BOUNDS", result.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FILL_WIDTH then returns FILL_WIDTH constant`() {
        val result = CraftDContentScale.FILL_WIDTH
        assertNotNull(result)
        assertEquals(CraftDContentScale.FILL_WIDTH, result)
        assertEquals("FILL_WIDTH", result.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FILL_HEIGHT then returns FILL_HEIGHT constant`() {
        val result = CraftDContentScale.FILL_HEIGHT
        assertNotNull(result)
        assertEquals(CraftDContentScale.FILL_HEIGHT, result)
        assertEquals("FILL_HEIGHT", result.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing INSIDE then returns INSIDE constant`() {
        val result = CraftDContentScale.INSIDE
        assertNotNull(result)
        assertEquals(CraftDContentScale.INSIDE, result)
        assertEquals("INSIDE", result.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing NONE then returns NONE constant`() {
        val result = CraftDContentScale.NONE
        assertNotNull(result)
        assertEquals(CraftDContentScale.NONE, result)
        assertEquals("NONE", result.name)
    }

    @Test
    fun `given CraftDContentScale enum when calling values then returns all constants`() {
        val values = CraftDContentScale.values()
        assertEquals(7, values.size)
        assertEquals(
            setOf(
                CraftDContentScale.CROP,
                CraftDContentScale.FIT,
                CraftDContentScale.FILL_BOUNDS,
                CraftDContentScale.FILL_WIDTH,
                CraftDContentScale.FILL_HEIGHT,
                CraftDContentScale.INSIDE,
                CraftDContentScale.NONE
            ),
            values.toSet()
        )
    }

    @Test
    fun `given CraftDContentScale enum when calling valueOf with CROP string then returns CROP constant`() {
        val result = enumValueOf<CraftDContentScale>("CROP")
        assertEquals(CraftDContentScale.CROP, result)
    }

    @Test
    fun `given CraftDContentScale enum when calling valueOf with FIT string then returns FIT constant`() {
        val result = enumValueOf<CraftDContentScale>("FIT")
        assertEquals(CraftDContentScale.FIT, result)
    }

    @Test
    fun `given CraftDContentScale enum when calling valueOf with FILL_BOUNDS string then returns FILL_BOUNDS constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_BOUNDS")
        assertEquals(CraftDContentScale.FILL_BOUNDS, result)
    }

    @Test
    fun `given CraftDContentScale enum when calling valueOf with FILL_WIDTH string then returns FILL_WIDTH constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_WIDTH")
        assertEquals(CraftDContentScale.FILL_WIDTH, result)
    }

    @Test
    fun `given CraftDContentScale enum when calling valueOf with FILL_HEIGHT string then returns FILL_HEIGHT constant`() {
        val result = enumValueOf<CraftDContentScale>("FILL_HEIGHT")
        assertEquals(CraftDContentScale.FILL_HEIGHT, result)
    }

    @Test
    fun `given CraftDContentScale enum when calling valueOf with INSIDE string then returns INSIDE constant`() {
        val result = enumValueOf<CraftDContentScale>("INSIDE")
        assertEquals(CraftDContentScale.INSIDE, result)
    }

    @Test
    fun `given CraftDContentScale enum when calling valueOf with NONE string then returns NONE constant`() {
        val result = enumValueOf<CraftDContentScale>("NONE")
        assertEquals(CraftDContentScale.NONE, result)
    }

    @Test
    fun `given CraftDContentScale enum when comparing two CROP constants then they are equal`() {
        val first = CraftDContentScale.CROP
        val second = CraftDContentScale.CROP
        assertEquals(first, second)
        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given CraftDContentScale enum when comparing CROP and FIT then they are not equal`() {
        val crop = CraftDContentScale.CROP
        val fit = CraftDContentScale.FIT
        assertNotNull(crop)
        assertNotNull(fit)
    }

    @Test
    fun `given CraftDContentScale enum when checking ordinal of CROP then returns 0`() {
        assertEquals(0, CraftDContentScale.CROP.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when checking ordinal of FIT then returns 1`() {
        assertEquals(1, CraftDContentScale.FIT.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when checking ordinal of FILL_BOUNDS then returns 2`() {
        assertEquals(2, CraftDContentScale.FILL_BOUNDS.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when checking ordinal of FILL_WIDTH then returns 3`() {
        assertEquals(3, CraftDContentScale.FILL_WIDTH.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when checking ordinal of FILL_HEIGHT then returns 4`() {
        assertEquals(4, CraftDContentScale.FILL_HEIGHT.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when checking ordinal of INSIDE then returns 5`() {
        assertEquals(5, CraftDContentScale.INSIDE.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when checking ordinal of NONE then returns 6`() {
        assertEquals(6, CraftDContentScale.NONE.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on CROP then returns CROP string`() {
        val result = CraftDContentScale.CROP.toString()
        assertEquals("CROP", result)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on FIT then returns FIT string`() {
        val result = CraftDContentScale.FIT.toString()
        assertEquals("FIT", result)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on FILL_BOUNDS then returns FILL_BOUNDS string`() {
        val result = CraftDContentScale.FILL_BOUNDS.toString()
        assertEquals("FILL_BOUNDS", result)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on FILL_WIDTH then returns FILL_WIDTH string`() {
        val result = CraftDContentScale.FILL_WIDTH.toString()
        assertEquals("FILL_WIDTH", result)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on FILL_HEIGHT then returns FILL_HEIGHT string`() {
        val result = CraftDContentScale.FILL_HEIGHT.toString()
        assertEquals("FILL_HEIGHT", result)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on INSIDE then returns INSIDE string`() {
        val result = CraftDContentScale.INSIDE.toString()
        assertEquals("INSIDE", result)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on NONE then returns NONE string`() {
        val result = CraftDContentScale.NONE.toString()
        assertEquals("NONE", result)
    }
}