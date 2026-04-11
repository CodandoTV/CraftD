package com.github.codandotv.craftd.androidcore.data.model.image

import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties

@RunWith(JUnit4::class)
class ImagePropertiesTest {

    private lateinit var actionPropertiesMock: ActionProperties

    @Before
    fun setUp() {
        actionPropertiesMock = mockk<ActionProperties>()
    }

    @Test
    fun `given all parameters when constructing ImageProperties then all fields are set correctly`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.Crop
        val contentDescription = "Test image"
        val actionProperties = actionPropertiesMock

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
    fun `given no parameters when constructing ImageProperties then all fields are null`() {
        val imageProperties = ImageProperties()

        assertNull(imageProperties.url)
        assertNull(imageProperties.contentScale)
        assertNull(imageProperties.contentDescription)
        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given partial parameters when constructing ImageProperties then only specified fields are set`() {
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
    fun `given ImageProperties when copying with new url then new instance has updated url`() {
        val original = ImageProperties(
            url = "https://example.com/image1.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Original image",
            actionProperties = actionPropertiesMock
        )
        val newUrl = "https://example.com/image2.png"

        val copied = original.copy(url = newUrl)

        assertEquals(newUrl, copied.url)
        assertEquals(original.contentScale, copied.contentScale)
        assertEquals(original.contentDescription, copied.contentDescription)
        assertEquals(original.actionProperties, copied.actionProperties)
    }

    @Test
    fun `given ImageProperties when copying with new contentScale then new instance has updated contentScale`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop
        )
        val newContentScale = CraftDContentScale.Fit

        val copied = original.copy(contentScale = newContentScale)

        assertEquals(original.url, copied.url)
        assertEquals(newContentScale, copied.contentScale)
    }

    @Test
    fun `given ImageProperties when copying with new contentDescription then new instance has updated contentDescription`() {
        val original = ImageProperties(
            contentDescription = "Original description"
        )
        val newDescription = "New description"

        val copied = original.copy(contentDescription = newDescription)

        assertEquals(newDescription, copied.contentDescription)
    }

    @Test
    fun `given ImageProperties when copying with new actionProperties then new instance has updated actionProperties`() {
        val original = ImageProperties(
            actionProperties = actionPropertiesMock
        )
        val newActionProperties = mockk<ActionProperties>()

        val copied = original.copy(actionProperties = newActionProperties)

        assertEquals(newActionProperties, copied.actionProperties)
    }

    @Test
    fun `given ImageProperties when copying all fields then new instance has all updated values`() {
        val original = ImageProperties(
            url = "https://example.com/image1.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Original",
            actionProperties = actionPropertiesMock
        )
        val newUrl = "https://example.com/image2.png"
        val newContentScale = CraftDContentScale.Fit
        val newDescription = "New"
        val newActionProperties = mockk<ActionProperties>()

        val copied = original.copy(
            url = newUrl,
            contentScale = newContentScale,
            contentDescription = newDescription,
            actionProperties = newActionProperties
        )

        assertEquals(newUrl, copied.url)
        assertEquals(newContentScale, copied.contentScale)
        assertEquals(newDescription, copied.contentDescription)
        assertEquals(newActionProperties, copied.actionProperties)
    }

    @Test
    fun `given two identical ImageProperties when comparing then equals returns true`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.Crop
        val description = "Test image"

        val imageProperties1 = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = description,
            actionProperties = actionPropertiesMock
        )
        val imageProperties2 = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = description,
            actionProperties = actionPropertiesMock
        )

        assertEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different url when comparing then equals returns false`() {
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image1.png"
        )
        val imageProperties2 = ImageProperties(
            url = "https://example.com/image2.png"
        )

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentScale when comparing then equals returns false`() {
        val imageProperties1 = ImageProperties(contentScale = CraftDContentScale.Crop)
        val imageProperties2 = ImageProperties(contentScale = CraftDContentScale.Fit)

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different contentDescription when comparing then equals returns false`() {
        val imageProperties1 = ImageProperties(contentDescription = "Description 1")
        val imageProperties2 = ImageProperties(contentDescription = "Description 2")

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two ImageProperties with different actionProperties when comparing then equals returns false`() {
        val actionProperties1 = mockk<ActionProperties>()
        val actionProperties2 = mockk<ActionProperties>()

        val imageProperties1 = ImageProperties(actionProperties = actionProperties1)
        val imageProperties2 = ImageProperties(actionProperties = actionProperties2)

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given two identical ImageProperties when calculating hashCode then hashCodes are equal`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.Crop
        val description = "Test image"

        val imageProperties1 = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = description,
            actionProperties = actionPropertiesMock
        )
        val imageProperties2 = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = description,
            actionProperties = actionPropertiesMock
        )

        assertEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given two different ImageProperties when calculating hashCode then hashCodes are different`() {
        val imageProperties1 = ImageProperties(url = "https://example.com/image1.png")
        val imageProperties2 = ImageProperties(url = "https://example.com/image2.png")

        assertNotEquals(imageProperties1.hashCode(), imageProperties2.hashCode())
    }

    @Test
    fun `given ImageProperties with all null fields when converting to string then toString returns valid representation`() {
        val imageProperties = ImageProperties()
        val stringRepresentation = imageProperties.toString()

        assertTrue(stringRepresentation.contains("ImageProperties"))
        assertTrue(stringRepresentation.contains("url=null"))
    }

    @Test
    fun `given ImageProperties with all fields set when converting to string then toString includes all field values`() {
        val url = "https://example.com/image.png"
        val contentScale = CraftDContentScale.Crop
        val description = "Test image"

        val imageProperties = ImageProperties(
            url = url,
            contentScale = contentScale,
            contentDescription = description,
            actionProperties = actionPropertiesMock
        )
        val stringRepresentation = imageProperties.toString()

        assertTrue(stringRepresentation.contains("ImageProperties"))
        assertTrue(stringRepresentation.contains(url))
        assertTrue(stringRepresentation.contains(contentScale.toString()))
        assertTrue(stringRepresentation.contains(description))
    }

    @Test
    fun `given ImageProperties when verifying data class properties then all properties are accessible`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = actionPropertiesMock
        )

        assertTrue(imageProperties.url != null)
        assertTrue(imageProperties.contentScale != null)
        assertTrue(imageProperties.contentDescription != null)
        assertTrue(imageProperties.actionProperties != null)
    }

    @Test
    fun `given ImageProperties with null actionProperties when checking actionProperties then null is returned`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            actionProperties = null
        )

        assertNull(imageProperties.actionProperties)
    }

    @Test
    fun `given multiple ImageProperties copies when comparing then each copy is distinct but equal`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test"
        )

        val copy1 = original.copy()
        val copy2 = original.copy()

        assertEquals(copy1, copy2)
        assertEquals(original, copy1)
        assertEquals(original, copy2)
    }

    @Test
    fun `given ImageProperties when creating with empty url string then url is set to empty string`() {
        val imageProperties = ImageProperties(url = "")

        assertEquals("", imageProperties.url)
    }

    @Test
    fun `given ImageProperties when creating with empty contentDescription string then contentDescription is set to empty string`() {
        val imageProperties = ImageProperties(contentDescription = "")

        assertEquals("", imageProperties.contentDescription)
    }

    @Test
    fun `given ImageProperties with url when copying without url parameter then url remains unchanged`() {
        val original = ImageProperties(url = "https://example.com/image.png")
        val copied = original.copy(contentScale = CraftDContentScale.Crop)

        assertEquals(original.url, copied.url)
    }

    @Test
    fun `given two ImageProperties where one has all fields and other has all nulls when comparing then equals returns false`() {
        val imageProperties1 = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = actionPropertiesMock
        )
        val imageProperties2 = ImageProperties()

        assertNotEquals(imageProperties1, imageProperties2)
    }

    @Test
    fun `given ImageProperties when comparing with itself then equals returns true`() {
        val imageProperties = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop
        )

        assertEquals(imageProperties, imageProperties)
    }

    @Test
    fun `given ImageProperties when copying with same values then new instance is equal but distinct`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.Crop,
            contentDescription = "Test",
            actionProperties = actionPropertiesMock
        )

        val copied = original.copy(
            url = original.url,
            contentScale = original.contentScale,
            contentDescription = original.contentDescription,
            actionProperties = original.actionProperties
        )

        assertEquals(original, copied)
    }
}