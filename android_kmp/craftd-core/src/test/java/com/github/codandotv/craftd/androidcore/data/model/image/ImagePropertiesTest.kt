package com.github.codandotv.craftd.androidcore.data.model.image

import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImagePropertiesTest {

    @Test
    fun `given all parameters when creating ImageProperties then all fields are set`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.CROP
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
    fun `given default values when creating ImageProperties then all fields are null`() {
        val imageProperties = ImageProperties()

        assertEquals(null, imageProperties.url)
        assertEquals(null, imageProperties.contentScale)
        assertEquals(null, imageProperties.contentDescription)
        assertEquals(null, imageProperties.actionProperties)
    }

    @Test
    fun `given partial parameters when creating ImageProperties then only specified fields are set`() {
        val url = "https://example.com/image.png"
        val contentDescription = "Test image"

        val imageProperties = ImageProperties(
            url = url,
            contentDescription = contentDescription
        )

        assertEquals(url, imageProperties.url)
        assertEquals(null, imageProperties.contentScale)
        assertEquals(contentDescription, imageProperties.contentDescription)
        assertEquals(null, imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties when calling copy with new url then returns new instance with updated url`() {
        val original = ImageProperties(
            url = "https://example.com/image1.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "Original"
        )
        val newUrl = "https://example.com/image2.png"

        val copied = original.copy(url = newUrl)

        assertEquals(newUrl, copied.url)
        assertEquals(original.contentScale, copied.contentScale)
        assertEquals(original.contentDescription, copied.contentDescription)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given ImageProperties when calling copy with new contentScale then returns new instance with updated contentScale`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP
        )
        val newContentScale = CraftDContentScale.FIT

        val copied = original.copy(contentScale = newContentScale)

        assertEquals(original.url, copied.url)
        assertEquals(newContentScale, copied.contentScale)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given ImageProperties when calling copy with new contentDescription then returns new instance with updated contentDescription`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentDescription = "Original"
        )
        val newDescription = "Updated"

        val copied = original.copy(contentDescription = newDescription)

        assertEquals(original.url, copied.url)
        assertEquals(newDescription, copied.contentDescription)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given ImageProperties when calling copy with new actionProperties then returns new instance with updated actionProperties`() {
        val actionProperties1 = mockk<ActionProperties>()
        val actionProperties2 = mockk<ActionProperties>()
        val original = ImageProperties(actionProperties = actionProperties1)

        val copied = original.copy(actionProperties = actionProperties2)

        assertEquals(actionProperties2, copied.actionProperties)
        assertNotEquals(original, copied)
    }

    @Test
    fun `given two ImageProperties with same values when comparing then equals returns true`() {
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "Test",
            actionProperties = null
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "Test",
            actionProperties = null
        )

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different urls when comparing then equals returns false`() {
        val imageProperties1 = ImageProperties(url = "https://example.com/image1.png")
        val imageProperties2 = ImageProperties(url = "https://example.com/image2.png")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentScale when comparing then equals returns false`() {
        val imageProperties1 = ImageProperties(contentScale = CraftDContentScale.CROP)
        val imageProperties2 = ImageProperties(contentScale = CraftDContentScale.FIT)

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentDescription when comparing then equals returns false`() {
        val imageProperties1 = ImageProperties(contentDescription = "Description 1")
        val imageProperties2 = ImageProperties(contentDescription = "Description 2")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with same values when calling hashCode then returns same hash`() {
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "Test"
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "Test"
        )

        assertEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given two ImageProperties with different values when calling hashCode then likely returns different hash`() {
        val imageProperties1 = ImageProperties(url = "https://example.com/image1.png")
        val imageProperties2 = ImageProperties(url = "https://example.com/image2.png")

        assertNotEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given ImageProperties when calling toString then contains all field values`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "Test"
        )

        val stringRepresentation = imageProperties.toString()

        assert(stringRepresentation.contains("url"))
        assert(stringRepresentation.contains("https://example.com/image.png"))
    }

    @Test
    fun `given ImageProperties with null url when accessing url then returns null`() {
        val imageProperties = ImageProperties(url = null)

        assertEquals(null, imageProperties.url)
    }

    @Test
    fun `given ImageProperties with null contentScale when accessing contentScale then returns null`() {
        val imageProperties = ImageProperties(contentScale = null)

        assertEquals(null, imageProperties.contentScale)
    }

    @Test
    fun `given ImageProperties with null contentDescription when accessing contentDescription then returns null`() {
        val imageProperties = ImageProperties(contentDescription = null)

        assertEquals(null, imageProperties.contentDescription)
    }

    @Test
    fun `given ImageProperties with null actionProperties when accessing actionProperties then returns null`() {
        val imageProperties = ImageProperties(actionProperties = null)

        assertEquals(null, imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties with empty string url when creating then url is empty string`() {
        val imageProperties = ImageProperties(url = "")

        assertEquals("", imageProperties.url)
    }

    @Test
    fun `given ImageProperties with empty string contentDescription when creating then contentDescription is empty string`() {
        val imageProperties = ImageProperties(contentDescription = "")

        assertEquals("", imageProperties.contentDescription)
    }

    @Test
    fun `given two identical ImageProperties when calling copy on one then original remains unchanged`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP
        )
        val originalUrl = original.url
        val originalContentScale = original.contentScale

        original.copy(url = "https://example.com/other.png")

        assertEquals(originalUrl, original.url)
        assertEquals(originalContentScale, original.contentScale)
    }

    @Test
    fun `given ImageProperties with actionProperties when comparing two instances then equals considers actionProperties`() {
        val actionProperties = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(actionProperties = actionProperties)
        val imageProperties2 = ImageProperties(actionProperties = actionProperties)

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given multiple field changes when calling copy with all parameters then returns new instance with all updates`() {
        val original = ImageProperties(
            url = "https://example.com/image1.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "Original",
            actionProperties = null
        )
        val newActionProperties = mockk<ActionProperties>()

        val copied = original.copy(
            url = "https://example.com/image2.png",
            contentScale = CraftDContentScale.FIT,
            contentDescription = "Updated",
            actionProperties = newActionProperties
        )

        assertEquals("https://example.com/image2.png", copied.url)
        assertEquals(CraftDContentScale.FIT, copied.contentScale)
        assertEquals("Updated", copied.contentDescription)
        assertEquals(newActionProperties, copied.actionProperties)
        assertNotEquals(original, copied)
    }
}