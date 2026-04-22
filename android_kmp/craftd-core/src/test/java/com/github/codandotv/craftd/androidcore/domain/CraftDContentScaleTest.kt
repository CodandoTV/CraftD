package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDContentScaleTest {

    @Test
    fun `given CROP enum when accessed then returns valid constant`() {
        val constant = CraftDContentScale.CROP
        assertNotNull(constant)
        assertEquals("CROP", constant.name)
    }

    @Test
    fun `given FIT enum when accessed then returns valid constant`() {
        val constant = CraftDContentScale.FIT
        assertNotNull(constant)
        assertEquals("FIT", constant.name)
    }

    @Test
    fun `given FILL_BOUNDS enum when accessed then returns valid constant`() {
        val constant = CraftDContentScale.FILL_BOUNDS
        assertNotNull(constant)
        assertEquals("FILL_BOUNDS", constant.name)
    }

    @Test
    fun `given FILL_WIDTH enum when accessed then returns valid constant`() {
        val constant = CraftDContentScale.FILL_WIDTH
        assertNotNull(constant)
        assertEquals("FILL_WIDTH", constant.name)
    }

    @Test
    fun `given FILL_HEIGHT enum when accessed then returns valid constant`() {
        val constant = CraftDContentScale.FILL_HEIGHT
        assertNotNull(constant)
        assertEquals("FILL_HEIGHT", constant.name)
    }

    @Test
    fun `given INSIDE enum when accessed then returns valid constant`() {
        val constant = CraftDContentScale.INSIDE
        assertNotNull(constant)
        assertEquals("INSIDE", constant.name)
    }

    @Test
    fun `given NONE enum when accessed then returns valid constant`() {
        val constant = CraftDContentScale.NONE
        assertNotNull(constant)
        assertEquals("NONE", constant.name)
    }

    @Test
    fun `given CROP string when enumValueOf then returns CROP constant`() {
        val constant = enumValueOf<CraftDContentScale>("CROP")
        assertEquals(CraftDContentScale.CROP, constant)
    }

    @Test
    fun `given FIT string when enumValueOf then returns FIT constant`() {
        val constant = enumValueOf<CraftDContentScale>("FIT")
        assertEquals(CraftDContentScale.FIT, constant)
    }

    @Test
    fun `given FILL_BOUNDS string when enumValueOf then returns FILL_BOUNDS constant`() {
        val constant = enumValueOf<CraftDContentScale>("FILL_BOUNDS")
        assertEquals(CraftDContentScale.FILL_BOUNDS, constant)
    }

    @Test
    fun `given FILL_WIDTH string when enumValueOf then returns FILL_WIDTH constant`() {
        val constant = enumValueOf<CraftDContentScale>("FILL_WIDTH")
        assertEquals(CraftDContentScale.FILL_WIDTH, constant)
    }

    @Test
    fun `given FILL_HEIGHT string when enumValueOf then returns FILL_HEIGHT constant`() {
        val constant = enumValueOf<CraftDContentScale>("FILL_HEIGHT")
        assertEquals(CraftDContentScale.FILL_HEIGHT, constant)
    }

    @Test
    fun `given INSIDE string when enumValueOf then returns INSIDE constant`() {
        val constant = enumValueOf<CraftDContentScale>("INSIDE")
        assertEquals(CraftDContentScale.INSIDE, constant)
    }

    @Test
    fun `given NONE string when enumValueOf then returns NONE constant`() {
        val constant = enumValueOf<CraftDContentScale>("NONE")
        assertEquals(CraftDContentScale.NONE, constant)
    }

    @Test
    fun `given all enum values when accessed then count equals seven`() {
        val values = CraftDContentScale.values()
        assertEquals(7, values.size)
    }

    @Test
    fun `given enum values when iterated then all constants present`() {
        val values = CraftDContentScale.values().map { it.name }
        val expected = listOf("CROP", "FIT", "FILL_BOUNDS", "FILL_WIDTH", "FILL_HEIGHT", "INSIDE", "NONE")
        assertEquals(expected, values)
    }

    @Test
    fun `given CROP constant when compared to itself then returns true`() {
        val constant1 = CraftDContentScale.CROP
        val constant2 = CraftDContentScale.CROP
        assertEquals(constant1, constant2)
    }

    @Test
    fun `given different constants when compared then returns false`() {
        val constant1 = CraftDContentScale.CROP
        val constant2 = CraftDContentScale.FIT
        kotlin.test.assertNotEquals(constant1, constant2)
    }

    @Test
    fun `given CROP constant when hashCode called then returns consistent value`() {
        val constant1 = CraftDContentScale.CROP
        val constant2 = CraftDContentScale.CROP
        assertEquals(constant1.hashCode(), constant2.hashCode())
    }

    @Test
    fun `given CROP and FIT when hashCode called then may differ`() {
        val constant1 = CraftDContentScale.CROP
        val constant2 = CraftDContentScale.FIT
        kotlin.test.assertTrue(constant1.hashCode() != constant2.hashCode())
    }

    @Test
    fun `given CROP constant when toString called then returns string representation`() {
        val constant = CraftDContentScale.CROP
        val stringValue = constant.toString()
        kotlin.test.assertTrue(stringValue.contains("CROP"))
    }

    @Test
    fun `given two CROP constants when ordinal compared then equals zero`() {
        val crop = CraftDContentScale.CROP
        assertEquals(0, crop.ordinal)
    }

    @Test
    fun `given FIT constant when ordinal compared then equals one`() {
        val fit = CraftDContentScale.FIT
        assertEquals(1, fit.ordinal)
    }

    @Test
    fun `given FILL_BOUNDS constant when ordinal compared then equals two`() {
        val fillBounds = CraftDContentScale.FILL_BOUNDS
        assertEquals(2, fillBounds.ordinal)
    }

    @Test
    fun `given FILL_WIDTH constant when ordinal compared then equals three`() {
        val fillWidth = CraftDContentScale.FILL_WIDTH
        assertEquals(3, fillWidth.ordinal)
    }

    @Test
    fun `given FILL_HEIGHT constant when ordinal compared then equals four`() {
        val fillHeight = CraftDContentScale.FILL_HEIGHT
        assertEquals(4, fillHeight.ordinal)
    }

    @Test
    fun `given INSIDE constant when ordinal compared then equals five`() {
        val inside = CraftDContentScale.INSIDE
        assertEquals(5, inside.ordinal)
    }

    @Test
    fun `given NONE constant when ordinal compared then equals six`() {
        val none = CraftDContentScale.NONE
        assertEquals(6, none.ordinal)
    }

    @Test
    fun `given CROP constant when compared with valueOf then returns same instance`() {
        val constant1 = CraftDContentScale.CROP
        val constant2 = CraftDContentScale.valueOf("CROP")
        assertEquals(constant1, constant2)
    }

    @Test
    fun `given valid enum name when valueOf then returns correct constant`() {
        val constant = CraftDContentScale.valueOf("FIT")
        assertEquals(CraftDContentScale.FIT, constant)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `given invalid enum name when valueOf then throws IllegalArgumentException`() {
        CraftDContentScale.valueOf("INVALID_SCALE")
    }

    @Test
    fun `given all enum constants when checking type then all are CraftDContentScale instance`() {
        CraftDContentScale.values().forEach { constant ->
            kotlin.test.assertTrue(constant is CraftDContentScale)
        }
    }

    @Test
    fun `given enum constant when serialized to string then can be deserialized back`() {
        val original = CraftDContentScale.FILL_BOUNDS
        val serialized = original.name
        val deserialized = enumValueOf<CraftDContentScale>(serialized)
        assertEquals(original, deserialized)
    }
}