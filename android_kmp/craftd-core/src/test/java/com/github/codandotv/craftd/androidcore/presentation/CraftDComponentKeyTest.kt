package com.github.codandotv.craftd.androidcore.presentation

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDComponentKeyTest {

    @Test
    fun `given TEXT_VIEW_COMPONENT when accessing key then returns correct value`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("CraftDTextView", component.key)
    }

    @Test
    fun `given BUTTON_COMPONENT when accessing key then returns correct value`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("CraftDButton", component.key)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when accessing key then returns correct value`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CraftDCheckBox", component.key)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when comparing names then enum name is TEXT_VIEW_COMPONENT`() {
        assertEquals("TEXT_VIEW_COMPONENT", CraftDComponentKey.TEXT_VIEW_COMPONENT.name)
    }

    @Test
    fun `given BUTTON_COMPONENT when comparing names then enum name is BUTTON_COMPONENT`() {
        assertEquals("BUTTON_COMPONENT", CraftDComponentKey.BUTTON_COMPONENT.name)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when comparing names then enum name is CHECK_BOX_COMPONENT`() {
        assertEquals("CHECK_BOX_COMPONENT", CraftDComponentKey.CHECK_BOX_COMPONENT.name)
    }

    @Test
    fun `given all enum constants when iterating then size is three`() {
        val constants = CraftDComponentKey.values()
        assertEquals(3, constants.size)
    }

    @Test
    fun `given all enum constants when iterating then contains all expected values`() {
        val constants = CraftDComponentKey.values()
        assertEquals(true, constants.contains(CraftDComponentKey.TEXT_VIEW_COMPONENT))
        assertEquals(true, constants.contains(CraftDComponentKey.BUTTON_COMPONENT))
        assertEquals(true, constants.contains(CraftDComponentKey.CHECK_BOX_COMPONENT))
    }

    @Test
    fun `given valid TEXT_VIEW_COMPONENT string when using enumValueOf then returns TEXT_VIEW_COMPONENT`() {
        val result = enumValueOf<CraftDComponentKey>("TEXT_VIEW_COMPONENT")
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, result)
    }

    @Test
    fun `given valid BUTTON_COMPONENT string when using enumValueOf then returns BUTTON_COMPONENT`() {
        val result = enumValueOf<CraftDComponentKey>("BUTTON_COMPONENT")
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, result)
    }

    @Test
    fun `given valid CHECK_BOX_COMPONENT string when using enumValueOf then returns CHECK_BOX_COMPONENT`() {
        val result = enumValueOf<CraftDComponentKey>("CHECK_BOX_COMPONENT")
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, result)
    }

    @Test
    fun `given invalid enum string when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDComponentKey>("INVALID_COMPONENT")
        }
    }

    @Test
    fun `given empty enum string when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDComponentKey>("")
        }
    }

    @Test
    fun `given CRAFT_D constant when accessing then value is CraftD`() {
        assertEquals("CraftD", CRAFT_D)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when checking prefix then key starts with CRAFT_D`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(true, component.key.startsWith(CRAFT_D))
    }

    @Test
    fun `given BUTTON_COMPONENT when checking prefix then key starts with CRAFT_D`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(true, component.key.startsWith(CRAFT_D))
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when checking prefix then key starts with CRAFT_D`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals(true, component.key.startsWith(CRAFT_D))
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when comparing ordinal then ordinal is zero`() {
        assertEquals(0, CraftDComponentKey.TEXT_VIEW_COMPONENT.ordinal)
    }

    @Test
    fun `given BUTTON_COMPONENT when comparing ordinal then ordinal is one`() {
        assertEquals(1, CraftDComponentKey.BUTTON_COMPONENT.ordinal)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when comparing ordinal then ordinal is two`() {
        assertEquals(2, CraftDComponentKey.CHECK_BOX_COMPONENT.ordinal)
    }

    @Test
    fun `given same enum instance when comparing with equals then returns true`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(true, component1 == component2)
    }

    @Test
    fun `given different enum instances when comparing with equals then returns false`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(false, component1 == component2)
    }

    @Test
    fun `given same enum instance when comparing hashCode then returns same value`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1.hashCode(), component2.hashCode())
    }

    @Test
    fun `given different enum instances when comparing hashCode then may return different values`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        val hashesAreDifferent = component1.hashCode() != component2.hashCode()
        assertEquals(true, hashesAreDifferent)
    }

    @Test
    fun `given all components when converting to string then toString returns formatted name`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val result = component.toString()
        assertEquals(true, result.contains("TEXT_VIEW_COMPONENT"))
    }

    @Test
    fun `given enum when accessing key property directly then value matches expected format`() {
        CraftDComponentKey.values().forEach { component ->
            assertEquals(true, component.key.contains(CRAFT_D))
        }
    }

    @Test
    fun `given multiple enum constants when iterating with forEach then processes all three`() {
        var count = 0
        CraftDComponentKey.values().forEach { _ ->
            count++
        }
        assertEquals(3, count)
    }

    @Test
    fun `given enum constants when checking distinct keys then all keys are unique`() {
        val keys = CraftDComponentKey.values().map { it.key }.toSet()
        assertEquals(3, keys.size)
    }

    @Test
    fun `given enum when accessing by name with TEXT_VIEW then returns TEXT_VIEW_COMPONENT`() {
        val component = CraftDComponentKey.values().find { it.name == "TEXT_VIEW_COMPONENT" }
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, component)
    }

    @Test
    fun `given enum when accessing by key value then TEXT_VIEW_COMPONENT key matches`() {
        val component = CraftDComponentKey.values().find { it.key == "CraftDTextView" }
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, component)
    }

    @Test
    fun `given enum when accessing by key value then BUTTON_COMPONENT key matches`() {
        val component = CraftDComponentKey.values().find { it.key == "CraftDButton" }
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, component)
    }

    @Test
    fun `given enum when accessing by key value then CHECK_BOX_COMPONENT key matches`() {
        val component = CraftDComponentKey.values().find { it.key == "CraftDCheckBox" }
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, component)
    }

    @Test
    fun `given non existent key when searching then find returns null`() {
        val component = CraftDComponentKey.values().find { it.key == "NonExistentKey" }
        assertEquals(null, component)
    }

    @Test
    fun `given enum constants when comparing all ordinals then are sequential`() {
        val ordinals = CraftDComponentKey.values().map { it.ordinal }
        assertEquals(listOf(0, 1, 2), ordinals)
    }
}