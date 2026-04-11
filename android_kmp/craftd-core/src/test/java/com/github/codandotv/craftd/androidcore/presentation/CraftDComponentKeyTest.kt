```kotlin
package com.github.codandotv.craftd.androidcore.presentation

import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CraftDComponentKeyTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @Test
    fun `given TEXT_VIEW_COMPONENT when accessed then key is CraftDTextView`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("CraftDTextView", component.key)
    }

    @Test
    fun `given BUTTON_COMPONENT when accessed then key is CraftDButton`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals("CraftDButton", component.key)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when accessed then key is CraftDCheckBox`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals("CraftDCheckBox", component.key)
    }

    @Test
    fun `given IMAGE_COMPONENT when accessed then key is CraftDImage`() {
        val component = CraftDComponentKey.IMAGE_COMPONENT
        assertEquals("CraftDImage", component.key)
    }

    @Test
    fun `given all enum constants when enumValueOf called then returns correct constant`() {
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, enumValueOf<CraftDComponentKey>("TEXT_VIEW_COMPONENT"))
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, enumValueOf<CraftDComponentKey>("BUTTON_COMPONENT"))
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, enumValueOf<CraftDComponentKey>("CHECK_BOX_COMPONENT"))
        assertEquals(CraftDComponentKey.IMAGE_COMPONENT, enumValueOf<CraftDComponentKey>("IMAGE_COMPONENT"))
    }

    @Test
    fun `given CraftDComponentKey when values called then returns all four constants`() {
        val values = CraftDComponentKey.values()
        assertEquals(4, values.size)
        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, values[0])
        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, values[1])
        assertEquals(CraftDComponentKey.CHECK_BOX_COMPONENT, values[2])
        assertEquals(CraftDComponentKey.IMAGE_COMPONENT, values[3])
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when name called then returns TEXT_VIEW_COMPONENT`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals("TEXT_VIEW_COMPONENT", component.name)
    }

    @Test
    fun `given BUTTON_COMPONENT when ordinal called then returns 1`() {
        val component = CraftDComponentKey.BUTTON_COMPONENT
        assertEquals(1, component.ordinal)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT when ordinal called then returns 2`() {
        val component = CraftDComponentKey.CHECK_BOX_COMPONENT
        assertEquals(2, component.ordinal)
    }

    @Test
    fun `given IMAGE_COMPONENT when ordinal called then returns 3`() {
        val component = CraftDComponentKey.IMAGE_COMPONENT
        assertEquals(3, component.ordinal)
    }

    @Test
    fun `given same CraftDComponentKey when compared then equals returns true`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1, component2)
    }

    @Test
    fun `given different CraftDComponentKey when compared then equals returns false`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.BUTTON_COMPONENT
        assertNotNull(component1)
        assertNotNull(component2)
    }

    @Test
    fun `given same CraftDComponentKey when hashCode called then returns same hash`() {
        val component1 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val component2 = CraftDComponentKey.TEXT_VIEW_COMPONENT
        assertEquals(component1.hashCode(), component2.hashCode())
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when toString called then returns enum representation`() {
        val component = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val stringRepresentation = component.toString()
        assertNotNull(stringRepresentation)
    }

    @Test
    fun `given CRAFT_D constant when accessed then value is CraftD`() {
        assertEquals("CraftD", CRAFT_D)
    }

    @Test
    fun `given all components when keys accessed then all keys contain CraftD prefix`() {
        CraftDComponentKey.values().forEach { component ->
            assert(component.key.startsWith(CRAFT_D))
        }
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT when compared by key then TEXT_VIEW_COMPONENT has correct key`() {
        val expectedKey = "CraftDTextView"
        val actualKey = CraftDComponentKey.TEXT_VIEW_COMPONENT.key
        assertEquals(expectedKey, actualKey)
    }

    @Test
    fun `given all enum constants when checked then none have empty key`() {
        CraftDComponentKey.values().forEach { component ->
            assert(component.key.isNotEmpty())
        }
    }
}
```