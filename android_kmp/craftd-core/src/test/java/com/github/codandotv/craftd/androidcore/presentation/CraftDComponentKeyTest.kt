package com.github.codandotv.craftd.androidcore.presentation

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDComponentKeyTest {

    @Test
    fun `given TEXT_VIEW_COMPONENT enum constant when accessing key then returns correct string value`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("CraftDTextView", component.key)
    }

    @Test
    fun `given BUTTON_COMPONENT enum constant when accessing key then returns correct string value`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("CraftDButton", component.key)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum constant when accessing key then returns correct string value`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CraftDCheckBox", component.key)
    }

    @Test
    fun `given IMAGE_COMPONENT enum constant when accessing key then returns correct string value`() {
        val component = CraftDComponentKey.IMAGE_COMPONENT
        assertEquals("CraftDImage", component.key)
    }

    @Test
    fun `given all enum constants exist when using enumValueOf then succeeds for each constant name`() {
        val textView = enumValueOf<CraftDComponentKey>("TEXT_VIEW_COMPONENT")
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, textView)

        val button = enumValueOf<CraftDComponentKey>("BUTTON_COMPONENT")
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, button)

        val checkBox = enumValueOf<CraftDComponentKey>("CHECK_BOX_COMPONENT")
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, checkBox)

        val image = enumValueOf<CraftDComponentKey>("IMAGE_COMPONENT")
        assertEquals(CraftDComponentKey.IMAGE_COMPONENT, image)
    }

    @Test
    fun `given invalid enum constant name when using enumValueOf then throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> {
            enumValueOf<CraftDComponentKey>("INVALID_COMPONENT")
        }
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when comparing with TEXT_VIEW_COMPONENT then returns equal`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1, component2)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when comparing with BUTTON_COMPONENT then returns not equal`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        assertNotEquals(component1, component2)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when getting hashCode then returns consistent value`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val hashCode1 = component.hashCode()
        val hashCode2 = component.hashCode()
        assertEquals(hashCode1, hashCode2)
    }

    @Test
    fun `given two equal enum constants when comparing hashCode then returns same hash`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1.hashCode(), component2.hashCode())
    }

    @Test
    fun `given CRAFT_D constant when accessing value then returns CraftD string`() {
        assertEquals("CraftD", CRAFT_D)
    }

    @Test
    fun `given enum when calling values then returns all four constants`() {
        val values = CraftDComponentKey.values()
        assertEquals(4, values.size)
        assertEquals(
            setOf(
                CraftDComponentKey.TEXT_VIEW_COMPONENT,
                CraftDComponentKey.BUTTON_COMPONENT,
                CraftDComponentKey.CHECK_BOX_COMPONENT,
                CraftDComponentKey.IMAGE_COMPONENT
            ),
            values.toSet()
        )
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when accessing ordinal then returns zero`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(0, component.ordinal)
    }

    @Test
    fun `given BUTTON_COMPONENT when accessing ordinal then returns one`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(1, component.ordinal)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when accessing ordinal then returns two`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals(2, component.ordinal)
    }

    @Test
    fun `given IMAGE_COMPONENT when accessing ordinal then returns three`() {
        val component = CraftDComponentKey.IMAGE_COMPONENT
        assertEquals(3, component.ordinal)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when accessing name then returns TEXT_VIEW_COMPONENT`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("TEXT_VIEW_COMPONENT", component.name)
    }

    @Test
    fun `given BUTTON_COMPONENT when accessing name then returns BUTTON_COMPONENT`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("BUTTON_COMPONENT", component.name)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when accessing name then returns CHECK_BOX_COMPONENT`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CHECK_BOX_COMPONENT", component.name)
    }

    @Test
    fun `given IMAGE_COMPONENT when accessing name then returns IMAGE_COMPONENT`() {
        val component = CraftDComponentKey.IMAGE_COMPONENT
        assertEquals("IMAGE_COMPONENT", component.name)
    }

    private fun assertNotEquals(expected: Any, actual: Any) {
        assert(expected != actual) { "Expected $expected to not equal $actual" }
    }
}