package com.github.codandotv.craftd.androidcore.data.model.image

import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ImagePropertiesTest {

    @Test
    fun `given all parameters when creating ImageProperties then returns correct instance`() {
        val mockActionProperties = mockk<ActionProperties>()
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockActionProperties
        )

        assertEquals("https://example.com/image.png", imageProperties.url)
        assertEquals(CraftDContentScale.Crop, imageProperties.contentScale)
        assertEquals("Test image", imageProperties.contentDescription)
        assertEquals(mockActionProperties, imageProperties.actionProperties)
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
    fun `given partial parameters when creating ImageProperties then only specified fields are set`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Fit
        )

        assertEquals("https://example.com/image.png", imageProperties.url)
        assertEquals(CraftDContentScale.Fit, imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given ImageProperties with url only when copying with new contentScale then returns updated instance`() {
        val original = ImageProperties(url = "https://example.com/image.png")
        val copied = original.copy(contentScale = CraftDContentScale.Inside)

        assertEquals("https://example.com/image.png", copied.url)
        assertEquals(CraftDContentScale.Inside, copied.contentScale)
        assertNull(copied.contentDescription)
        assertNull(copied.actionProperties)
    }

    @Test
    fun `given ImageProperties when copying all fields then returns instance with new values`() {
        val mockActionProperties = mockk<ActionProperties>()
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockActionProperties
        )
        val newMockActionProperties = mockk<ActionProperties>()
        val copied = original.copy(
            url = "https://example.com/new-image.png",
            contentScale = CraftDContentScale.Fit,
            contentDescription = "New image",
            actionProperties = newMockActionProperties
        )

        assertEquals("https://example.com/new-image.png", copied.url)
        assertEquals(CraftDContentScale.Fit, copied.contentScale)
        assertEquals("New image", copied.contentDescription)
        assertEquals(newMockActionProperties, copied.actionProperties)
    }

    @Test
    fun `given two identical ImageProperties when comparing equals then returns true`() {
        val mockActionProperties = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockActionProperties
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockActionProperties
        )

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different url when comparing equals then returns false`() {
        val imageProperties1 = ImageProperties(url = "https://example.com/image1.png")
        val imageProperties2 = ImageProperties(url = "https://example.com/image2.png")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentScale when comparing equals then returns false`() {
        val imageProperties1 = ImageProperties(contentScale = CraftDContentScale.Crop)
        val imageProperties2 = ImageProperties(contentScale = CraftDContentScale.Fit)

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentDescription when comparing equals then returns false`() {
        val imageProperties1 = ImageProperties(contentDescription = "Image 1")
        val imageProperties2 = ImageProperties(contentDescription = "Image 2")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different actionProperties when comparing equals then returns false`() {
        val mockActionProperties1 = mockk<ActionProperties>()
        val mockActionProperties2 = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(actionProperties = mockActionProperties1)
        val imageProperties2 = ImageProperties(actionProperties = mockActionProperties2)

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two identical ImageProperties when comparing hashCode then returns same hash`() {
        val mockActionProperties = mockk<ActionProperties>()
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockActionProperties
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockActionProperties
        )

        assertEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given two different ImageProperties when comparing hashCode then likely returns different hash`() {
        val imageProperties1 = ImageProperties(url = "https://example.com/image1.png")
        val imageProperties2 = ImageProperties(url = "https://example.com/image2.png")

        assertNotEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given ImageProperties with null contentScale when accessing contentScale then returns null`() {
        val imageProperties = ImageProperties(contentScale = null)

        assertNull(imageProperties.contentScale)
    }

    @Test
    fun `given ImageProperties with empty string url when creating then url is empty string`() {
        val imageProperties = ImageProperties(url = "")

        assertEquals("", imageProperties.url)
    }

    @Test
    fun `given ImageProperties with all non-null values when calling toString then string representation includes all values`() {
        val mockActionProperties = mockk<ActionProperties>()
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = mockActionProperties
        )

        val stringRepresentation = imageProperties.toString()
        assert(stringRepresentation.contains("url"))
        assert(stringRepresentation.contains("contentScale"))
        assert(stringRepresentation.contains("contentDescription"))
        assert(stringRepresentation.contains("actionProperties"))
    }

    @Test
    fun `given two ImageProperties created independently with same values when comparing then equals returns true`() {
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = null
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test image",
            actionProperties = null
        )

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given ImageProperties when copying without changing fields then returns equal instance`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop
        )
        val copied = original.copy()

        assertEquals(original, copied)
    }

    @Test
    fun `given ImageProperties with very long url when creating then url is preserved`() {
        val longUrl = "https://example.com/" + "a".repeat(1000) + ".png"
        val imageProperties = ImageProperties(url = longUrl)

        assertEquals(longUrl, imageProperties.url)
    }

    @Test
    fun `given ImageProperties with special characters in contentDescription when creating then description is preserved`() {
        val specialDescription = "Test image with special chars: @#$%^&*()"
        val imageProperties = ImageProperties(contentDescription = specialDescription)

        assertEquals(specialDescription, imageProperties.contentDescription)
    }

    @Test
    fun `given CraftDContentScale enum then all expected values exist`() {
        val values = listOf("Crop", "Fit", "Inside", "FillHeight", "FillWidth", "Fill")
        values.forEach { value ->
            val enumValue = enumValueOf<CraftDContentScale>(value)
            assertEquals(value, enumValue.name)
        }
    }

    @Test
    fun `given ImageProperties with null actionProperties when copying and setting actionProperties then new actionProperties is set`() {
        val original = ImageProperties(url = "https://example.com/image.png")
        val mockActionProperties = mockk<ActionProperties>()
        val copied = original.copy(actionProperties = mockActionProperties)

        assertEquals(mockActionProperties, copied.actionProperties)
    }

    @Test
    fun `given ImageProperties when creating multiple instances with same parameters then all are equal`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.Crop
        val description = "Test"

        val instance1 = ImageProperties(url = url, contentScale = contentScale, contentDescription = description)
        val instance2 = ImageProperties(url = url, contentScale = contentScale, contentDescription = description)
        val instance3 = ImageProperties(url = url, contentScale = contentScale, contentDescription = description)

        assertEquals(instance1, instance2)
        assertEquals(instance2, instance3)
        assertEquals(instance1, instance3)
    }
}