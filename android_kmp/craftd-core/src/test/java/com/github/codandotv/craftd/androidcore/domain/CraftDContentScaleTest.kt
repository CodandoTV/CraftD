package com.github.codandotv.craftd.androidcore.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDContentScaleTest {

    @Test
    fun `given CROP enum constant when accessing then value exists`() {
        val result = enumValueOf<CraftDContentScale>("CROP")
        assertEquals(CraftDContentScale.CROP, result)
    }

    @Test
    fun `given FIT enum constant when accessing then value exists`() {
        val result = enumValueOf<CraftDContentScale>("FIT")
        assertEquals(CraftDContentScale.FIT, result)
    }

    @Test
    fun `given FILL_BOUNDS enum constant when accessing then value exists`() {
        val result = enumValueOf<CraftDContentScale>("FILL_BOUNDS")
        assertEquals(CraftDContentScale.FILL_BOUNDS, result)
    }

    @Test
    fun `given FILL_WIDTH enum constant when accessing then value exists`() {
        val result = enumValueOf<CraftDContentScale>("FILL_WIDTH")
        assertEquals(CraftDContentScale.FILL_WIDTH, result)
    }

    @Test
    fun `given FILL_HEIGHT enum constant when accessing then value exists`() {
        val result = enumValueOf<CraftDContentScale>("FILL_HEIGHT")
        assertEquals(CraftDContentScale.FILL_HEIGHT, result)
    }

    @Test
    fun `given INSIDE enum constant when accessing then value exists`() {
        val result = enumValueOf<CraftDContentScale>("INSIDE")
        assertEquals(CraftDContentScale.INSIDE, result)
    }

    @Test
    fun `given NONE enum constant when accessing then value exists`() {
        val result = enumValueOf<CraftDContentScale>("NONE")
        assertEquals(CraftDContentScale.NONE, result)
    }

    @Test
    fun `given all enum constants when iterating then size is seven`() {
        val allValues = CraftDContentScale.values()
        assertEquals(7, allValues.size)
    }

    @Test
    fun `given all enum constants when iterating then contains CROP`() {
        val allValues = CraftDContentScale.values()
        assert(allValues.contains(CraftDContentScale.CROP))
    }

    @Test
    fun `given all enum constants when iterating then contains FIT`() {
        val allValues = CraftDContentScale.values()
        assert(allValues.contains(CraftDContentScale.FIT))
    }

    @Test
    fun `given all enum constants when iterating then contains FILL_BOUNDS`() {
        val allValues = CraftDContentScale.values()
        assert(allValues.contains(CraftDContentScale.FILL_BOUNDS))
    }

    @Test
    fun `given all enum constants when iterating then contains FILL_WIDTH`() {
        val allValues = CraftDContentScale.values()
        assert(allValues.contains(CraftDContentScale.FILL_WIDTH))
    }

    @Test
    fun `given all enum constants when iterating then contains FILL_HEIGHT`() {
        val allValues = CraftDContentScale.values()
        assert(allValues.contains(CraftDContentScale.FILL_HEIGHT))
    }

    @Test
    fun `given all enum constants when iterating then contains INSIDE`() {
        val allValues = CraftDContentScale.values()
        assert(allValues.contains(CraftDContentScale.INSIDE))
    }

    @Test
    fun `given all enum constants when iterating then contains NONE`() {
        val allValues = CraftDContentScale.values()
        assert(allValues.contains(CraftDContentScale.NONE))
    }

    @Test
    fun `given CROP constant when comparing with same instance then returns true`() {
        val first = CraftDContentScale.CROP
        val second = CraftDContentScale.CROP
        assertEquals(first, second)
    }

    @Test
    fun `given CROP constant when comparing with different constant then returns false`() {
        val first = CraftDContentScale.CROP
        val second = CraftDContentScale.FIT
        assert(first != second)
    }

    @Test
    fun `given FIT constant when comparing with different constant then returns false`() {
        val first = CraftDContentScale.FIT
        val second = CraftDContentScale.FILL_BOUNDS
        assert(first != second)
    }

    @Test
    fun `given CROP constant when getting ordinal then returns zero`() {
        val ordinal = CraftDContentScale.CROP.ordinal
        assertEquals(0, ordinal)
    }

    @Test
    fun `given FIT constant when getting ordinal then returns one`() {
        val ordinal = CraftDContentScale.FIT.ordinal
        assertEquals(1, ordinal)
    }

    @Test
    fun `given FILL_BOUNDS constant when getting ordinal then returns two`() {
        val ordinal = CraftDContentScale.FILL_BOUNDS.ordinal
        assertEquals(2, ordinal)
    }

    @Test
    fun `given FILL_WIDTH constant when getting ordinal then returns three`() {
        val ordinal = CraftDContentScale.FILL_WIDTH.ordinal
        assertEquals(3, ordinal)
    }

    @Test
    fun `given FILL_HEIGHT constant when getting ordinal then returns four`() {
        val ordinal = CraftDContentScale.FILL_HEIGHT.ordinal
        assertEquals(4, ordinal)
    }

    @Test
    fun `given INSIDE constant when getting ordinal then returns five`() {
        val ordinal = CraftDContentScale.INSIDE.ordinal
        assertEquals(5, ordinal)
    }

    @Test
    fun `given NONE constant when getting ordinal then returns six`() {
        val ordinal = CraftDContentScale.NONE.ordinal
        assertEquals(6, ordinal)
    }

    @Test
    fun `given CROP constant when getting name then returns CROP string`() {
        val name = CraftDContentScale.CROP.name
        assertEquals("CROP", name)
    }

    @Test
    fun `given FIT constant when getting name then returns FIT string`() {
        val name = CraftDContentScale.FIT.name
        assertEquals("FIT", name)
    }

    @Test
    fun `given FILL_BOUNDS constant when getting name then returns FILL_BOUNDS string`() {
        val name = CraftDContentScale.FILL_BOUNDS.name
        assertEquals("FILL_BOUNDS", name)
    }

    @Test
    fun `given FILL_WIDTH constant when getting name then returns FILL_WIDTH string`() {
        val name = CraftDContentScale.FILL_WIDTH.name
        assertEquals("FILL_WIDTH", name)
    }

    @Test
    fun `given FILL_HEIGHT constant when getting name then returns FILL_HEIGHT string`() {
        val name = CraftDContentScale.FILL_HEIGHT.name
        assertEquals("FILL_HEIGHT", name)
    }

    @Test
    fun `given INSIDE constant when getting name then returns INSIDE string`() {
        val name = CraftDContentScale.INSIDE.name
        assertEquals("INSIDE", name)
    }

    @Test
    fun `given NONE constant when getting name then returns NONE string`() {
        val name = CraftDContentScale.NONE.name
        assertEquals("NONE", name)
    }

    @Test
    fun `given invalid enum constant name when converting then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("INVALID")
        }
    }

    @Test
    fun `given empty string enum constant name when converting then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("")
        }
    }

    @Test
    fun `given lowercase enum constant name when converting then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDContentScale>("crop")
        }
    }

    @Test
    fun `given CROP constant when converting to string then returns proper representation`() {
        val result = CraftDContentScale.CROP.toString()
        assert(result.contains("CROP"))
    }

    @Test
    fun `given FIT constant when converting to string then returns proper representation`() {
        val result = CraftDContentScale.FIT.toString()
        assert(result.contains("FIT"))
    }

    @Test
    fun `given two CROP instances when comparing hash codes then returns same hash`() {
        val first = CraftDContentScale.CROP
        val second = CraftDContentScale.CROP
        assertEquals(first.hashCode(), second.hashCode())
    }

    @Test
    fun `given CROP and FIT instances when comparing hash codes then may differ`() {
        val crop = CraftDContentScale.CROP
        val fit = CraftDContentScale.FIT
        assert(crop.hashCode() != fit.hashCode())
    }

    @Test
    fun `given all constants when using valueOf then returns correct instances`() {
        val valueOfCrop = enumValueOf<CraftDContentScale>("CROP")
        val directCrop = CraftDContentScale.CROP
        assertEquals(valueOfCrop, directCrop)
    }

    @Test
    fun `given all constants when using valueOf then FIT is accessible`() {
        val valueOfFit = enumValueOf<CraftDContentScale>("FIT")
        val directFit = CraftDContentScale.FIT
        assertEquals(valueOfFit, directFit)
    }
}