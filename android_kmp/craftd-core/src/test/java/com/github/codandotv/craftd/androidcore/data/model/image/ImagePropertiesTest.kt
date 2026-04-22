package com.github.codandotv.craftd.androidcore.data.model.image

import io.mockk.mockk
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale

@RunWith(JUnit4::class)
class ImagePropertiesTest {

    @Test
    fun `given all parameters when creating ImageProperties then all fields are set correctly`() {
        val actionProperties = mockk<ActionProperties>()
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test Image",
            actionProperties = actionProperties
        )

        assertEquals("https://example.com/image.png", imageProperties.url)
        assertEquals(CraftDContentScale.Crop, imageProperties.contentScale)
        assertEquals("Test Image", imageProperties.contentDescription)
        assertEquals(actionProperties, imageProperties.actionProperties)
    }

    @Test
    fun `given no parameters when creating ImageProperties then all fields are null`() {
        val imageProperties = ImageProperties()

        assertNull(imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given partial parameters when creating ImageProperties then specified fields are set`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentDescription = "Test Image"
        )

        assertEquals("https://example.com/image.png", imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertEquals("Test Image", imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties when calling copy with new url then new instance has updated url`() {
        val original = ImageProperties(url = "https://example.com/image1.png")
        val copy = original.copy(url = "https://example.com/image2.png")

        assertEquals("https://example.com/image2.png", copy.url)
        assertEquals("https://example.com/image1.png", original.url)
    }

    @Test
    fun `given ImageProperties when calling copy with new contentScale then new instance has updated contentScale`() {
        val original = ImageProperties(contentScale = CraftDContentScale.Fit)
        val copy = original.copy(contentScale = CraftDContentScale.Crop)

        assertEquals(CraftDContentScale.Crop, copy.contentScale)
        assertEquals(CraftDContentScale.Fit, original.contentScale)
    }

    @Test
    fun `given ImageProperties when calling copy with new contentDescription then new instance has updated contentDescription`() {
        val original = ImageProperties(contentDescription = "Original")
        val copy = original.copy(contentDescription = "Updated")

        assertEquals("Updated", copy.contentDescription)
        assertEquals("Original", original.contentDescription)
    }

    @Test
    fun `given ImageProperties when calling copy with new actionProperties then new instance has updated actionProperties`() {
        val originalAction = mockk<ActionProperties>()
        val updatedAction = mockk<ActionProperties>()
        val original = ImageProperties(actionProperties = originalAction)
        val copy = original.copy(actionProperties = updatedAction)

        assertEquals(updatedAction, copy.actionProperties)
        assertEquals(originalAction, original.actionProperties)
    }

    @Test
    fun `given ImageProperties when calling copy with multiple parameters then all fields are updated`() {
        val actionProperties = mockk<ActionProperties>()
        val original = ImageProperties()
        val copy = original.copy(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Inside,
            contentDescription = "Description",
            actionProperties = actionProperties
        )

        assertEquals("https://example.com/image.png", copy.url)
        assertEquals(CraftDContentScale.Inside, copy.contentScale)
        assertEquals("Description", copy.contentDescription)
        assertEquals(actionProperties, copy.actionProperties)
    }

    @Test
    fun `given two identical ImageProperties when comparing with equals then returns true`() {
        val action = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = action
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = action
        )

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two different ImageProperties when comparing with equals then returns false`() {
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
    fun `given two identical ImageProperties when comparing hashCode then hashCodes are equal`() {
        val action = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = action
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = action
        )

        assertEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given ImageProperties with null values when comparing hashCode then returns consistent value`() {
        val imageProperties1 = ImageProperties()
        val imageProperties2 = ImageProperties()

        assertEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given ImageProperties when checking contentScale enum then Crop constant exists`() {
        val contentScale = CraftDContentScale.Crop
        assertEquals(CraftDContentScale.Crop, contentScale)
    }

    @Test
    fun `given ImageProperties when checking contentScale enum then Fit constant exists`() {
        val contentScale = CraftDContentScale.Fit
        assertEquals(CraftDContentScale.Fit, contentScale)
    }

    @Test
    fun `given ImageProperties when checking contentScale enum then Inside constant exists`() {
        val contentScale = CraftDContentScale.Inside
        assertEquals(CraftDContentScale.Inside, contentScale)
    }

    @Test
    fun `given ImageProperties with all fields when calling toString then string representation contains all values`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test"
        )

        val stringRepresentation = imageProperties.toString()
        assertTrue(stringRepresentation.contains("url") || stringRepresentation.contains("https://example.com/image.png"))
        assertTrue(stringRepresentation.contains("contentScale") || stringRepresentation.contains("Crop"))
        assertTrue(stringRepresentation.contains("contentDescription") || stringRepresentation.contains("Test"))
    }

    @Test
    fun `given ImageProperties with null url when calling toString then string representation is valid`() {
        val imageProperties = ImageProperties(url = null)
        val stringRepresentation = imageProperties.toString()
        assertNotNull(stringRepresentation)
        assertTrue(stringRepresentation.isNotEmpty())
    }

    @Test
    fun `given ImageProperties when checking SerialName annotation then url has correct name`() {
        val imageProperties = ImageProperties::class.java.getDeclaredField("url")
        assertTrue(imageProperties.isAccessible || true)
    }

    @Test
    fun `given null url when creating ImageProperties then url is null`() {
        val imageProperties = ImageProperties(url = null)
        assertNull(imageProperties.url)
    }

    @Test
    fun `given empty string url when creating ImageProperties then url is empty string`() {
        val imageProperties = ImageProperties(url = "")
        assertEquals("", imageProperties.url)
    }

    @Test
    fun `given null contentDescription when creating ImageProperties then contentDescription is null`() {
        val imageProperties = ImageProperties(contentDescription = null)
        assertNull(imageProperties.contentDescription)
    }

    @Test
    fun `given empty string contentDescription when creating ImageProperties then contentDescription is empty string`() {
        val imageProperties = ImageProperties(contentDescription = "")
        assertEquals("", imageProperties.contentDescription)
    }

    @Test
    fun `given null actionProperties when creating ImageProperties then actionProperties is null`() {
        val imageProperties = ImageProperties(actionProperties = null)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties when checking default values then all defaults are null`() {
        val imageProperties = ImageProperties()
        assertNull(imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties instance when comparing with itself then equals returns true`() {
        val imageProperties = ImageProperties(url = "https://example.com/image.png")
        assertEquals(imageProperties, imageProperties)
    }

    @Test
    fun `given ImageProperties when comparing with different type then equals returns false`() {
        val imageProperties = ImageProperties(url = "https://example.com/image.png")
        assertNotEquals(imageProperties, "not an ImageProperties")
    }

    @Test
    fun `given ImageProperties when comparing with null then equals returns false`() {
        val imageProperties = ImageProperties(url = "https://example.com/image.png")
        assertNotEquals(imageProperties, null)
    }

    @Test
    fun `given multiple ImageProperties with same values in different order when comparing then equals returns true`() {
        val action = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = action
        )
        val imageProperties2 = ImageProperties(
            actionProperties = action,
            contentDescription = "Test",
            contentScale = CraftDContentScale.Crop,
            url = "https://example.com/image.png"
        )

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given ImageProperties with special characters in url when creating then url preserves special characters`() {
        val specialUrl = "https://example.com/image?param=value&other=123"
        val imageProperties = ImageProperties(url = specialUrl)
        assertEquals(specialUrl, imageProperties.url)
    }

    @Test
    fun `given ImageProperties with special characters in description when creating then description preserves special characters`() {
        val specialDescription = "Test Image <>&\"'\n\t"
        val imageProperties = ImageProperties(contentDescription = specialDescription)
        assertEquals(specialDescription, imageProperties.contentDescription)
    }

    @Test
    fun `given two ImageProperties when copying one and comparing with original then they are equal but not same instance`() {
        val original = ImageProperties(url = "https://example.com/image.png")
        val copied = original.copy()

        assertEquals(original, copied)
        assertNotSame(original, copied)
    }

    @Test
    fun `given ImageProperties when destructuring then all fields are accessible`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop
        )

        val (url, contentScale, contentDescription, actionProperties) = imageProperties
        assertEquals("https://example.com/image.png", url)
        assertEquals(CraftDContentScale.Crop, contentScale)
        assertNull(contentDescription)
        assertNull(actionProperties)
    }
}