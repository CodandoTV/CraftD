package com.github.codandotv.craftd.androidcore.presentation

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class CraftDComponentKeyTest {

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when accessing key then returns correct value`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("CraftDTextView", component.key)
    }

    @Test
    fun `given BUTTON_COMPONENT enum when accessing key then returns correct value`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("CraftDButton", component.key)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum when accessing key then returns correct value`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CraftDCheckBox", component.key)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when accessing name then returns TEXT_VIEW_COMPONENT`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("TEXT_VIEW_COMPONENT", component.name)
    }

    @Test
    fun `given BUTTON_COMPONENT enum when accessing name then returns BUTTON_COMPONENT`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("BUTTON_COMPONENT", component.name)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum when accessing name then returns CHECK_BOX_COMPONENT`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CHECK_BOX_COMPONENT", component.name)
    }

    @Test
    fun `given valid enum name when using enumValueOf then returns correct enum`() {
        val component = enumValueOf<CraftDComponentKey>("TEXT_VIEW_COMPONENT")
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, component)
    }

    @Test
    fun `given valid enum name BUTTON_COMPONENT when using enumValueOf then returns correct enum`() {
        val component = enumValueOf<CraftDComponentKey>("BUTTON_COMPONENT")
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, component)
    }

    @Test
    fun `given valid enum name CHECK_BOX_COMPONENT when using enumValueOf then returns correct enum`() {
        val component = enumValueOf<CraftDComponentKey>("CHECK_BOX_COMPONENT")
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, component)
    }

    @Test
    fun `given all enum constants when iterating then all constants are present`() {
        val constants = CraftDComponentKey.values()
        assertEquals(4, constants.size)
        assertEquals(true, constants.contains(CraftDComponentKey.TEXT_VIEW_COMPONENT))
        assertEquals(true, constants.contains(CraftDComponentKey.BUTTON_COMPONENT))
        assertEquals(true, constants.contains(CraftDComponentKey.CHECK_BOX_COMPONENT))
        assertEquals(true, constants.contains(CraftDComponentKey.IMAGE_COMPONENT))
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when comparing equality then same instance returns true`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1, component2)
    }

    @Test
    fun `given different enum constants when comparing equality then returns false`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(false, component1 == component2)
    }

    @Test
    fun `given enum constant when calling hashCode then returns consistent value`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1.hashCode(), component2.hashCode())
    }

    @Test
    fun `given different enum constants when comparing hashCode then returns different values`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(false, component1.hashCode() == component2.hashCode())
    }

    @Test
    fun `given CRAFT_D constant when accessed then returns CraftD`() {
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
    fun `given enum when calling toString then returns proper format`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertNotNull(component.toString())
        assertEquals(true, component.toString().contains("TEXT_VIEW_COMPONENT"))
    }

    @Test
    fun `given all components when checking keys are unique then no duplicates exist`() {
        val keys = CraftDComponentKey.values().map { it.key }.toSet()
        assertEquals(4, keys.size)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when comparing ordinal then returns zero`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(0, component.ordinal)
    }

    @Test
    fun `given BUTTON_COMPONENT when comparing ordinal then returns one`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(1, component.ordinal)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when comparing ordinal then returns two`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals(2, component.ordinal)
    }

}