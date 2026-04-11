package com.github.codandotv.craftd.compose.ui.image

import com.github.codandotv.craftd.androidcore.presentation.CraftDComponentKey
import kotlin.test.Test
import kotlin.test.assertEquals

class CraftDImageBuilderTest {

    @Test
    fun `given CraftDImageBuilder when created then key matches IMAGE_COMPONENT`() {
        val builder = CraftDImageBuilder(imageLoader = { _, _, _ -> })

        assertEquals(CraftDComponentKey.IMAGE_COMPONENT.key, builder.key)
    }

    @Test
    fun `given CraftDImageBuilder when created with custom key then key is overridden`() {
        val customKey = "custom_image_key"
        val builder = CraftDImageBuilder(imageLoader = { _, _, _ -> }, key = customKey)

        assertEquals(customKey, builder.key)
    }
}
