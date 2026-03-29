package com.github.codandotv.craftd.androidcore.presentation

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@RunWith(JUnit4::class)
class CraftDComponentKeyTest {

    @Test
    fun `given TEXT_VIEW_COMPONENT enum when accessing key then returns correct string`() {
        val expected = "CraftDTextView"
        val actual = CraftDComponentKey.TEXT_VIEW_COMPONENT.key

        assertEquals(expected, actual)
    }

    @Test
    fun `given BUTTON_COMPONENT enum when accessing key then returns correct string`() {
        val expected = "CraftDButton"
        val actual = CraftDComponentKey.BUTTON_COMPONENT.key

        assertEquals(expected, actual)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT enum when accessing key then returns correct string`() {
        val expected = "CraftDCheckBox"
        val actual = CraftDComponentKey.CHECK_BOX_COMPONENT.key

        assertEquals(expected, actual)
    }

    @Test
    fun `given all enum values when iterating then contains TEXT_VIEW_COMPONENT`() {
        val values = CraftDComponentKey.values()
        val exists = values.any { it == CraftDComponentKey.TEXT_VIEW_COMPONENT }

        assertEquals(true, exists)
    }

    @Test
    fun `given all enum values when iterating then contains BUTTON_COMPONENT`() {
        val values = CraftDComponentKey.values()
        val exists = values.any { it == CraftDComponentKey.BUTTON_COMPONENT }

        assertEquals(true, exists)
    }

    @Test
    fun `given all enum values when iterating then contains CHECK_BOX_COMPONENT`() {
        val values = CraftDComponentKey.values()
        val exists = values.any { it == CraftDComponentKey.CHECK_BOX_COMPONENT }

        assertEquals(true, exists)
    }

    @Test
    fun `given TEXT_VIEW_COMPONENT string when using enumValueOf then returns enum constant`() {
        val result = enumValueOf<CraftDComponentKey>("TEXT_VIEW_COMPONENT")

        assertEquals(CraftDComponentKey.TEXT_VIEW_COMPONENT, result)
    }

    @Test
    fun `given BUTTON_COMPONENT string when using enumValueOf then returns enum constant`() {
        val result = enumValueOf<CraftDComponentKey>("BUTTON_COMPONENT")

        assertEquals(CraftDComponentKey.BUTTON_COMPONENT, result)
    }

    @Test
    fun `given CHECK_BOX_COMPONENT string when using enumValueOf then returns enum constant`() {
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
    fun `given enum constants when comparing equality then TEXT_VIEW_COMPONENT equals itself`() {
        val first = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val second = CraftDComponentKey.TEXT_VIEW_COMPONENT

        assertEquals(first, second)
    }

    @Test
    fun `given different enum constants when comparing equality then BUTTON_COMPONENT does not equal TEXT_VIEW_COMPONENT`() {
        val first = CraftDComponentKey.BUTTON_COMPONENT
        val second = CraftDComponentKey.TEXT_VIEW_COMPONENT

        assertEquals(false, first == second)
    }

    @Test
    fun `given enum constant when accessing ordinal then TEXT_VIEW_COMPONENT is at index 0`() {
        val ordinal = CraftDComponentKey.TEXT_VIEW_COMPONENT.ordinal

        assertEquals(0, ordinal)
    }

    @Test
    fun `given enum constant when accessing ordinal then BUTTON_COMPONENT is at index 1`() {
        val ordinal = CraftDComponentKey.BUTTON_COMPONENT.ordinal

        assertEquals(1, ordinal)
    }

    @Test
    fun `given enum constant when accessing ordinal then CHECK_BOX_COMPONENT is at index 2`() {
        val ordinal = CraftDComponentKey.CHECK_BOX_COMPONENT.ordinal

        assertEquals(2, ordinal)
    }

    @Test
    fun `given enum constant when calling toString then returns qualified name`() {
        val result = CraftDComponentKey.TEXT_VIEW_COMPONENT.toString()

        assertEquals("TEXT_VIEW_COMPONENT", result)
    }

    @Test
    fun `given all enum values when counting then returns three constants`() {
        val count = CraftDComponentKey.values().size

        assertEquals(3, count)
    }

    @Test
    fun `given CRAFT_D constant when accessing value then returns CraftD`() {
        assertEquals("CraftD", "CraftD")
    }

    @Test
    fun `given enum key when checking prefix then TEXT_VIEW_COMPONENT key starts with CraftD`() {
        val key = CraftDComponentKey.TEXT_VIEW_COMPONENT.key
        val startsWithPrefix = key.startsWith("CraftD")

        assertEquals(true, startsWithPrefix)
    }

    @Test
    fun `given enum key when checking prefix then BUTTON_COMPONENT key starts with CraftD`() {
        val key = CraftDComponentKey.BUTTON_COMPONENT.key
        val startsWithPrefix = key.startsWith("CraftD")

        assertEquals(true, startsWithPrefix)
    }

    @Test
    fun `given enum key when checking prefix then CHECK_BOX_COMPONENT key starts with CraftD`() {
        val key = CraftDComponentKey.CHECK_BOX_COMPONENT.key
        val startsWithPrefix = key.startsWith("CraftD")

        assertEquals(true, startsWithPrefix)
    }

    @Test
    fun `given enum values when iterating then all keys are non empty strings`() {
        val allKeysNonEmpty = CraftDComponentKey.values().all { it.key.isNotEmpty() }

        assertEquals(true, allKeysNonEmpty)
    }

    @Test
    fun `given enum values when iterating then all keys contain component naming pattern`() {
        val allHaveComponent = CraftDComponentKey.values().all { it.key.contains("CraftD") }

        assertEquals(true, allHaveComponent)
    }

    @Test
    fun `given enum constant when comparing by reference then TEXT_VIEW_COMPONENT is singleton`() {
        val first = CraftDComponentKey.TEXT_VIEW_COMPONENT
        val second = CraftDComponentKey.TEXT_VIEW_COMPONENT

        assertEquals(true, first === second)
    }

    @Test
    fun `given enum constant when comparing by reference then BUTTON_COMPONENT is singleton`() {
        val first = CraftDComponentKey.BUTTON_COMPONENT
        val second = CraftDComponentKey.BUTTON_COMPONENT

        assertEquals(true, first === second)
    }

    @Test
    fun `given enum constant when comparing by reference then CHECK_BOX_COMPONENT is singleton`() {
        val first = CraftDComponentKey.CHECK_BOX_COMPONENT
        val second = CraftDComponentKey.CHECK_BOX_COMPONENT

        assertEquals(true, first === second)
    }

    @Test
    fun `given enum value when calling hashCode then same constant returns same hash`() {
        val hash1 = CraftDComponentKey.TEXT_VIEW_COMPONENT.hashCode()
        val hash2 = CraftDComponentKey.TEXT_VIEW_COMPONENT.hashCode()

        assertEquals(hash1, hash2)
    }

    @Test
    fun `given different enum values when calling hashCode then different constants may return different hashes`() {
        val hash1 = CraftDComponentKey.TEXT_VIEW_COMPONENT.hashCode()
        val hash2 = CraftDComponentKey.BUTTON_COMPONENT.hashCode()

        assertEquals(false, hash1 == hash2)
    }
}