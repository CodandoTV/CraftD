package com.github.codandotv.craftd.androidcore.data.model.image

import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale

@RunWith(JUnit4::class)
class ImagePropertiesTest {

    @Test
    fun `given all parameters when constructing ImageProperties then creates instance with all fields`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.Crop
        val contentDescription = "Test image"
        val actionProperties = mockk<ActionProperties>()

        val imageProperties = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = contentDescription,
            actionProperties = actionProperties
        )

        assertEquals(url, imageProperties.url)
        assertEquals(contentScale, imageProperties.contentScale)
        assertEquals(contentDescription, imageProperties.contentDescription)
        assertEquals(actionProperties, imageProperties.actionProperties)
    }

    @Test
    fun `given no parameters when constructing ImageProperties then creates instance with default null values`() {
        val imageProperties = ImageProperties()

        assertNull(imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given partial parameters when constructing ImageProperties then creates instance with provided and default values`() {
        val url = "https://example.com/image.png"
        val contentDescription = "Test image"

        val imageProperties = ImageProperties(
            url = url,
            contentDescription = contentDescription
        )

        assertEquals(url, imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertEquals(contentDescription, imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties when calling copy with no arguments then returns identical instance`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockk()
        )

        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given ImageProperties when calling copy with single field then returns new instance with only that field changed`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image"
        )
        val newUrl = "https://example.com/newimage.png"

        val copied = original.copy(url = newUrl)

        assertEquals(newUrl, copied.url)
        assertEquals(original.contentScale, copied.contentScale)
        assertEquals(original.contentDescription, copied.contentDescription)
    }

    @Test
    fun `given ImageProperties when calling copy with multiple fields then returns new instance with all specified fields changed`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop
        )
        val newUrl = "https://example.com/newimage.png"
        val newContentScale = CraftDContentScale.Fit

        val copied = original.copy(url = newUrl, contentScale = newContentScale)

        assertEquals(newUrl, copied.url)
        assertEquals(newContentScale, copied.contentScale)
        assertEquals(original.contentDescription, copied.contentDescription)
    }

    @Test
    fun `given two identical ImageProperties when comparing with equals then returns true`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.Crop
        val contentDescription = "Test image"

        val imageProperties1 = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = contentDescription
        )
        val imageProperties2 = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = contentDescription
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
        val imageProperties1 = ImageProperties(contentDescription = "Image 1")
        val imageProperties2 = ImageProperties(contentDescription = "Image 2")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different actionProperties when comparing with equals then returns false`() {
        val imageProperties1 = ImageProperties(actionProperties = mockk(name = "action1"))
        val imageProperties2 = ImageProperties(actionProperties = mockk(name = "action2"))

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two identical ImageProperties when comparing hashCode then returns same hash`() {
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image"
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image"
        )

        assertEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given ImageProperties with all null values when checking fields then all are null`() {
        val imageProperties = ImageProperties()

        assertNull(imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties with only url when checking fields then only url is set`() {
        val url = "https://example.com/image.png"
        val imageProperties = ImageProperties(url = url)

        assertEquals(url, imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties with empty string url when constructing then creates instance with empty url`() {
        val imageProperties = ImageProperties(url = "")

        assertEquals("", imageProperties.url)
    }

    @Test
    fun `given ImageProperties with empty string contentDescription when constructing then creates instance with empty description`() {
        val imageProperties = ImageProperties(contentDescription = "")

        assertEquals("", imageProperties.contentDescription)
    }

    @Test
    fun `given ImageProperties with all CraftDContentScale values then each can be assigned`() {
        CraftDContentScale.entries.forEach { contentScale ->
            val imageProperties = ImageProperties(contentScale = contentScale)
            assertEquals(contentScale, imageProperties.contentScale)
        }
    }
}