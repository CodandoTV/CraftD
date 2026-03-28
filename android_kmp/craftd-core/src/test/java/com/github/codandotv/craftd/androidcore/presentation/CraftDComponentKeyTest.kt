package com.github.codandotv.craftd.androidcore.presentation

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDComponentKeyTest {

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when accessing key property then returns correct string value`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("CraftDTextView", component.key)
    }

    @Test
    fun `given BUTTON_COMPONENT enum when accessing key property then returns correct string value`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("CraftDButton", component.key)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum when accessing key property then returns correct string value`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CraftDCheckBox", component.key)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when accessing name property then returns correct enum name`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("TEXT_VIEW_COMPONENT", component.name)
    }

    @Test
    fun `given BUTTON_COMPONENT enum when accessing name property then returns correct enum name`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("BUTTON_COMPONENT", component.name)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum when accessing name property then returns correct enum name`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CHECK_BOX_COMPONENT", component.name)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT string when using enumValueOf then returns TEXT_VIEW_COMPONENT enum`() {
        val result = enumValueOf<CraftDComponentKey>("TEXT_VIEW_COMPONENT")
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, result)
    }

    @Test
    fun `given BUTTON_COMPONENT string when using enumValueOf then returns BUTTON_COMPONENT enum`() {
        val result = enumValueOf<CraftDComponentKey>("BUTTON_COMPONENT")
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, result)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT string when using enumValueOf then returns CHECK_BOX_COMPONENT enum`() {
        val result = enumValueOf<CraftDComponentKey>("CHECK_BOX_COMPONENT")
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, result)
    }

    @Test
    fun `given invalid enum name when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDComponentKey>("INVALID_COMPONENT")
        }
    }

    @Test
    fun `given two different enum constants when comparing equality then returns false`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(false, component1 == component2)
    }

    @Test
    fun `given two same enum constants when comparing equality then returns true`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(true, component1 == component2)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when accessing ordinal then returns zero`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(0, component.ordinal)
    }

    @Test
    fun `given BUTTON_COMPONENT enum when accessing ordinal then returns one`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(1, component.ordinal)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum when accessing ordinal then returns two`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals(2, component.ordinal)
    }

    @Test
    fun `given CraftDComponentKey enum when calling values then returns all three constants`() {
        val values = CraftDComponentKey.values()
        assertEquals(3, values.size)
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, values[0])
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, values[1])
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, values[2])
    }

    @Test
    fun `given CRAFT_D constant when accessing value then returns CraftD string`() {
        assertEquals("CraftD", CRAFT_D)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when accessing key property then key starts with CRAFT_D prefix`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(true, component.key.startsWith(CRAFT_D))
    }

    @Test
    fun `given BUTTON_COMPONENT enum when accessing key property then key starts with CRAFT_D prefix`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(true, component.key.startsWith(CRAFT_D))
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum when accessing key property then key starts with CRAFT_D prefix`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals(true, component.key.startsWith(CRAFT_D))
    }

    @Test
    fun `given all enum constants when verifying uniqueness of keys then all keys are distinct`() {
        val keys = CraftDComponentKey.values().map { it.key }
        assertEquals(keys.size, keys.toSet().size)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when calling toString then returns enum name representation`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(true, component.toString().contains("TEXT_VIEW_COMPONENT"))
    }

    @Test
    fun `given two TEXT_VIEW_COMPONENT enums when calling hashCode then returns same hash`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1.hashCode(), component2.hashCode())
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT and BUTTON_COMPONENT enums when calling hashCode then returns different hashes`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(false, component1.hashCode() == component2.hashCode())
    }

    @Test
    fun `given null enum name when using enumValueOfOrNull then returns null`() {
        val result = runCatching {
            enumValueOf<CraftDComponentKey>("null")
        }.getOrNull()
        assertEquals(null, result)
    }

    @Test
    fun `given empty string when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDComponentKey>("")
        }
    }

    @Test
    fun `given case sensitive different string when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDComponentKey>("text_view_component")
        }
    }
}