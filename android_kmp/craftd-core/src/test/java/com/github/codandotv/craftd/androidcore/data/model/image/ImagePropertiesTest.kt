package com.github.codandotv.craftd.androidcore.data.model.image

import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImagePropertiesTest {

    @Test
    fun `given all parameters when constructing ImageProperties then all fields are set correctly`() {
        val actionProperties = mockk<ActionProperties>()
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Sample Image",
            actionProperties = actionProperties
        )

        assertEquals("https://example.com/image.png", imageProperties.url)
        assertEquals(CraftDContentScale.Crop, imageProperties.contentScale)
        assertEquals("Sample Image", imageProperties.contentDescription)
        assertEquals(actionProperties, imageProperties.actionProperties)
    }

    @Test
    fun `given no parameters when constructing ImageProperties then all fields are null`() {
        val imageProperties = ImageProperties()

        assertNull(imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given partial parameters when constructing ImageProperties then only specified fields are set`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentDescription = "Test Description"
        )

        assertEquals("https://example.com/image.png", imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertEquals("Test Description", imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties when copying with new url then returns new instance with updated url`() {
        val actionProperties = mockk<ActionProperties>()
        val original = ImageProperties(
            url = "https://example.com/old.png",
            contentScale = CraftDContentScale.Fit,
            contentDescription = "Original",
            actionProperties = actionProperties
        )

        val copied = original.copy(url = "https://example.com/new.png")

        assertEquals("https://example.com/new.png", copied.url)
        assertEquals(CraftDContentScale.Fit, copied.contentScale)
        assertEquals("Original", copied.contentDescription)
        assertEquals(actionProperties, copied.actionProperties)
    }

    @Test
    fun `given ImageProperties when copying with new contentScale then returns new instance with updated contentScale`() {
        val original = ImageProperties(contentScale = CraftDContentScale.Crop)
        val copied = original.copy(contentScale = CraftDContentScale.Fit)

        assertEquals(CraftDContentScale.Fit, copied.contentScale)
    }

    @Test
    fun `given ImageProperties when copying with new contentDescription then returns new instance with updated contentDescription`() {
        val original = ImageProperties(contentDescription = "Original")
        val copied = original.copy(contentDescription = "Updated")

        assertEquals("Updated", copied.contentDescription)
    }

    @Test
    fun `given ImageProperties when copying with null actionProperties then returns new instance with null actionProperties`() {
        val actionProperties = mockk<ActionProperties>()
        val original = ImageProperties(actionProperties = actionProperties)
        val copied = original.copy(actionProperties = null)

        assertNull(copied.actionProperties)
    }

    @Test
    fun `given two identical ImageProperties when comparing with equals then returns true`() {
        val actionProperties = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Image",
            actionProperties = actionProperties
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Image",
            actionProperties = actionProperties
        )

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different url when comparing with equals then returns false`() {
        val imageProperties1 = ImageProperties(url = "https://example.com/image1.png")
        val imageProperties2 = ImageProperties(url = "https://example.com/image2.png")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentScale when comparing with equals then returns false`() {
        val imageProperties1 = ImageProperties(contentScale = CraftDContentScale.Crop)
        val imageProperties2 = ImageProperties(contentScale = CraftDContentScale.Fit)

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentDescription when comparing with equals then returns false`() {
        val imageProperties1 = ImageProperties(contentDescription = "Description 1")
        val imageProperties2 = ImageProperties(contentDescription = "Description 2")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different actionProperties when comparing with equals then returns false`() {
        val actionProperties1 = mockk<ActionProperties>()
        val actionProperties2 = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(actionProperties = actionProperties1)
        val imageProperties2 = ImageProperties(actionProperties = actionProperties2)

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given identical ImageProperties when computing hashCode then returns same hashCode`() {
        val actionProperties = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Image",
            actionProperties = actionProperties
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Image",
            actionProperties = actionProperties
        )

        assertEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given two different ImageProperties when computing hashCode then may return different hashCode`() {
        val imageProperties1 = ImageProperties(url = "https://example.com/image1.png")
        val imageProperties2 = ImageProperties(url = "https://example.com/image2.png")

        assertNotEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given ImageProperties when calling toString then returns valid string representation`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Image"
        )

        val stringRepresentation = imageProperties.toString()
        assertTrue(stringRepresentation.contains("url"))
        assertTrue(stringRepresentation.contains("contentScale"))
        assertTrue(stringRepresentation.contains("contentDescription"))
        assertTrue(stringRepresentation.contains("actionProperties"))
    }

    @Test
    fun `given null and ImageProperties when comparing with equals then returns false`() {
        val imageProperties = ImageProperties(url = "https://example.com/image.png")

        assertNotEquals(imageProperties, null)
    }

    @Test
    fun `given ImageProperties and different type when comparing with equals then returns false`() {
        val imageProperties = ImageProperties(url = "https://example.com/image.png")

        assertNotEquals(imageProperties, "not an ImageProperties")
    }

    @Test
    fun `given all null fields when constructing ImageProperties then instance is valid`() {
        val imageProperties = ImageProperties(
            url = null,
            contentScale = null,
            contentDescription = null,
            actionProperties = null
        )

        assertNull(imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given empty string url when constructing ImageProperties then url is set to empty string`() {
        val imageProperties = ImageProperties(url = "")

        assertEquals("", imageProperties.url)
    }

    @Test
    fun `given empty string contentDescription when constructing ImageProperties then contentDescription is set to empty string`() {
        val imageProperties = ImageProperties(contentDescription = "")

        assertEquals("", imageProperties.contentDescription)
    }

    @Test
    fun `given CraftDContentScale values then all enum constants exist`() {
        val enumValues = enumValues<CraftDContentScale>()
        assertTrue(enumValues.isNotEmpty())
        assertTrue(enumValues.any { it.name.isNotEmpty() })
    }

    @Test
    fun `given ImageProperties when copying all fields then returns new instance with all fields copied`() {
        val actionProperties = mockk<ActionProperties>()
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Image",
            actionProperties = actionProperties
        )

        val copied = original.copy(
            url = "https://example.com/new.png",
            contentScale = CraftDContentScale.Fit,
            contentDescription = "New Image",
            actionProperties = null
        )

        assertEquals("https://example.com/new.png", copied.url)
        assertEquals(CraftDContentScale.Fit, copied.contentScale)
        assertEquals("New Image", copied.contentDescription)
        assertNull(copied.actionProperties)
    }

    @Test
    fun `given ImageProperties with same reference when copying without parameters then returns equal instance`() {
        val actionProperties = mockk<ActionProperties>()
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Image",
            actionProperties = actionProperties
        )

        val copied = original.copy()

        assertEquals(original, copied)
    }
}