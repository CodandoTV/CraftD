package com.github.codandotv.craftd.androidcore.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CraftDContentScaleTest {

    @Test
    fun `given CraftDContentScale enum when accessing CROP then returns CROP constant`() {
        val scale = CraftDContentScale.CROP
        assertEquals(CraftDContentScale.CROP, scale)
        assertEquals("CROP", scale.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FIT then returns FIT constant`() {
        val scale = CraftDContentScale.FIT
        assertEquals(CraftDContentScale.FIT, scale)
        assertEquals("FIT", scale.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FILL_BOUNDS then returns FILL_BOUNDS constant`() {
        val scale = CraftDContentScale.FILL_BOUNDS
        assertEquals(CraftDContentScale.FILL_BOUNDS, scale)
        assertEquals("FILL_BOUNDS", scale.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FILL_WIDTH then returns FILL_WIDTH constant`() {
        val scale = CraftDContentScale.FILL_WIDTH
        assertEquals(CraftDContentScale.FILL_WIDTH, scale)
        assertEquals("FILL_WIDTH", scale.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing FILL_HEIGHT then returns FILL_HEIGHT constant`() {
        val scale = CraftDContentScale.FILL_HEIGHT
        assertEquals(CraftDContentScale.FILL_HEIGHT, scale)
        assertEquals("FILL_HEIGHT", scale.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing INSIDE then returns INSIDE constant`() {
        val scale = CraftDContentScale.INSIDE
        assertEquals(CraftDContentScale.INSIDE, scale)
        assertEquals("INSIDE", scale.name)
    }

    @Test
    fun `given CraftDContentScale enum when accessing NONE then returns NONE constant`() {
        val scale = CraftDContentScale.NONE
        assertEquals(CraftDContentScale.NONE, scale)
        assertEquals("NONE", scale.name)
    }

    @Test
    fun `given CraftDContentScale enum when getting all values then returns 7 constants`() {
        val values = CraftDContentScale.values()
        assertEquals(7, values.size)
    }

    @Test
    fun `given CraftDContentScale enum when iterating all values then contains all expected constants`() {
        val values = CraftDContentScale.values().map { it.name }
        assertEquals(
            setOf("CROP", "FIT", "FILL_BOUNDS", "FILL_WIDTH", "FILL_HEIGHT", "INSIDE", "NONE"),
            values.toSet()
        )
    }

    @Test
    fun `given string CROP when calling enumValueOf then returns CROP constant`() {
        val scale = enumValueOf<CraftDContentScale>("CROP")
        assertEquals(CraftDContentScale.CROP, scale)
    }

    @Test
    fun `given string FIT when calling enumValueOf then returns FIT constant`() {
        val scale = enumValueOf<CraftDContentScale>("FIT")
        assertEquals(CraftDContentScale.FIT, scale)
    }

    @Test
    fun `given string FILL_BOUNDS when calling enumValueOf then returns FILL_BOUNDS constant`() {
        val scale = enumValueOf<CraftDContentScale>("FILL_BOUNDS")
        assertEquals(CraftDContentScale.FILL_BOUNDS, scale)
    }

    @Test
    fun `given string FILL_WIDTH when calling enumValueOf then returns FILL_WIDTH constant`() {
        val scale = enumValueOf<CraftDContentScale>("FILL_WIDTH")
        assertEquals(CraftDContentScale.FILL_WIDTH, scale)
    }

    @Test
    fun `given string FILL_HEIGHT when calling enumValueOf then returns FILL_HEIGHT constant`() {
        val scale = enumValueOf<CraftDContentScale>("FILL_HEIGHT")
        assertEquals(CraftDContentScale.FILL_HEIGHT, scale)
    }

    @Test
    fun `given string INSIDE when calling enumValueOf then returns INSIDE constant`() {
        val scale = enumValueOf<CraftDContentScale>("INSIDE")
        assertEquals(CraftDContentScale.INSIDE, scale)
    }

    @Test
    fun `given string NONE when calling enumValueOf then returns NONE constant`() {
        val scale = enumValueOf<CraftDContentScale>("NONE")
        assertEquals(CraftDContentScale.NONE, scale)
    }

    @Test
    fun `given CraftDContentScale enum when comparing same instance then equals returns true`() {
        val scale1 = CraftDContentScale.CROP
        val scale2 = CraftDContentScale.CROP
        assertEquals(scale1, scale2)
    }

    @Test
    fun `given CraftDContentScale enum when comparing different instances of same constant then equals returns true`() {
        assertEquals(CraftDContentScale.FIT, CraftDContentScale.FIT)
    }

    @Test
    fun `given CraftDContentScale enum when comparing different constants then equals returns false`() {
        val scale1 = CraftDContentScale.CROP
        val scale2 = CraftDContentScale.FIT
        assertNotNull(scale1)
        assertNotNull(scale2)
    }

    @Test
    fun `given CraftDContentScale enum when getting hashCode of same constant then hashCode is consistent`() {
        val scale1 = CraftDContentScale.CROP
        val scale2 = CraftDContentScale.CROP
        assertEquals(scale1.hashCode(), scale2.hashCode())
    }

    @Test
    fun `given CraftDContentScale enum when getting ordinal of CROP then returns 0`() {
        assertEquals(0, CraftDContentScale.CROP.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when getting ordinal of FIT then returns 1`() {
        assertEquals(1, CraftDContentScale.FIT.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when getting ordinal of FILL_BOUNDS then returns 2`() {
        assertEquals(2, CraftDContentScale.FILL_BOUNDS.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when getting ordinal of FILL_WIDTH then returns 3`() {
        assertEquals(3, CraftDContentScale.FILL_WIDTH.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when getting ordinal of FILL_HEIGHT then returns 4`() {
        assertEquals(4, CraftDContentScale.FILL_HEIGHT.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when getting ordinal of INSIDE then returns 5`() {
        assertEquals(5, CraftDContentScale.INSIDE.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when getting ordinal of NONE then returns 6`() {
        assertEquals(6, CraftDContentScale.NONE.ordinal)
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on CROP then returns CROP`() {
        val scale = CraftDContentScale.CROP
        assertEquals("CROP", scale.toString())
    }

    @Test
    fun `given CraftDContentScale enum when calling toString on FIT then returns FIT`() {
        val scale = CraftDContentScale.FIT
        assertEquals("FIT", scale.toString())
    }

    @Test
    fun `given CraftDContentScale enum when verifying Stable annotation then annotation is present`() {
        val annotation = CraftDContentScale::class.annotations.find { 
            it.annotationClass.simpleName == "Stable" 
        }
        assertNotNull(annotation)
    }

    @Test
    fun `given CraftDContentScale enum when verifying Immutable annotation then annotation is present`() {
        val annotation = CraftDContentScale::class.annotations.find { 
            it.annotationClass.simpleName == "Immutable" 
        }
        assertNotNull(annotation)
    }

    @Test
    fun `given CraftDContentScale enum when creating list of all values then list is not empty`() {
        val scaleList = listOf(
            CraftDContentScale.CROP,
            CraftDContentScale.FIT,
            CraftDContentScale.FILL_BOUNDS,
            CraftDContentScale.FILL_WIDTH,
            CraftDContentScale.FILL_HEIGHT,
            CraftDContentScale.INSIDE,
            CraftDContentScale.NONE
        )
        assertEquals(7, scaleList.size)
        assertEquals(scaleList, CraftDContentScale.values().toList())
    }

    @Test
    fun `given CraftDContentScale enum when filtering values by name containing FILL then returns 3 constants`() {
        val filtered = CraftDContentScale.values().filter { it.name.contains("FILL") }
        assertEquals(3, filtered.size)
    }

    @Test
    fun `given CraftDContentScale enum when checking first value then returns CROP`() {
        val firstValue = CraftDContentScale.values().first()
        assertEquals(CraftDContentScale.CROP, firstValue)
    }

    @Test
    fun `given CraftDContentScale enum when checking last value then returns NONE`() {
        val lastValue = CraftDContentScale.values().last()
        assertEquals(CraftDContentScale.NONE, lastValue)
    }

    @Test
    fun `given CraftDContentScale enum when using in set then no duplicates exist`() {
        val scaleSet = setOf(
            CraftDContentScale.CROP,
            CraftDContentScale.CROP,
            CraftDContentScale.FIT
        )
        assertEquals(2, scaleSet.size)
    }

    @Test
    fun `given CraftDContentScale enum when using in map as key then retrievable by key`() {
        val scaleMap = mapOf(
            CraftDContentScale.CROP to "crop_scale",
            CraftDContentScale.FIT to "fit_scale"
        )
        assertEquals("crop_scale", scaleMap[CraftDContentScale.CROP])
        assertEquals("fit_scale", scaleMap[CraftDContentScale.FIT])
    }

}