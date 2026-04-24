package com.github.codandotv.craftd.compose.extensions

import androidx.compose.ui.layout.ContentScale
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentScaleExtensionTest {

    @Test
    fun `given CraftDContentScale CROP when toContentScale then returns ContentScale Crop`() {
        assertEquals(ContentScale.Crop, CraftDContentScale.CROP.toContentScale())
    }

    @Test
    fun `given CraftDContentScale FIT when toContentScale then returns ContentScale Fit`() {
        assertEquals(ContentScale.Fit, CraftDContentScale.FIT.toContentScale())
    }

    @Test
    fun `given CraftDContentScale FILL_BOUNDS when toContentScale then returns ContentScale FillBounds`() {
        assertEquals(ContentScale.FillBounds, CraftDContentScale.FILL_BOUNDS.toContentScale())
    }

    @Test
    fun `given CraftDContentScale FILL_WIDTH when toContentScale then returns ContentScale FillWidth`() {
        assertEquals(ContentScale.FillWidth, CraftDContentScale.FILL_WIDTH.toContentScale())
    }

    @Test
    fun `given CraftDContentScale FILL_HEIGHT when toContentScale then returns ContentScale FillHeight`() {
        assertEquals(ContentScale.FillHeight, CraftDContentScale.FILL_HEIGHT.toContentScale())
    }

    @Test
    fun `given CraftDContentScale INSIDE when toContentScale then returns ContentScale Inside`() {
        assertEquals(ContentScale.Inside, CraftDContentScale.INSIDE.toContentScale())
    }

    @Test
    fun `given CraftDContentScale NONE when toContentScale then returns ContentScale None`() {
        assertEquals(ContentScale.None, CraftDContentScale.NONE.toContentScale())
    }

    @Test
    fun `given null CraftDContentScale when toContentScale then returns ContentScale Fit as default`() {
        val nullScale: CraftDContentScale? = null
        assertEquals(ContentScale.Fit, nullScale.toContentScale())
    }
}
