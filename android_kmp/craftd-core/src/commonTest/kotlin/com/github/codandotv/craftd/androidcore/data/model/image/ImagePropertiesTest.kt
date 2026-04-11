package com.github.codandotv.craftd.androidcore.data.model.image

import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.domain.CraftDContentScale
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ImagePropertiesTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun `given full ImageProperties when serialized and deserialized then all fields match`() {
        val original = ImageProperties(
            url = "https://example.com/image.png",
            contentScale = CraftDContentScale.CROP,
            contentDescription = "A sample image",
        )

        val serialized = json.encodeToString(ImageProperties.serializer(), original)
        val deserialized = json.decodeFromString(ImageProperties.serializer(), serialized)

        assertEquals(original.url, deserialized.url)
        assertEquals(original.contentScale, deserialized.contentScale)
        assertEquals(original.contentDescription, deserialized.contentDescription)
        assertNull(deserialized.actionProperties)
    }

    @Test
    fun `given ImageProperties with defaults when deserialized from minimal json then nullable fields are null`() {
        val minimalJson = """{}"""

        val deserialized = json.decodeFromString(ImageProperties.serializer(), minimalJson)

        assertNull(deserialized.url)
        assertNull(deserialized.contentScale)
        assertNull(deserialized.contentDescription)
        assertNull(deserialized.actionProperties)
    }

    @Test
    fun `given json with all fields when deserialized then ImageProperties matches expected`() {
        val jsonString = """
            {
                "url": "https://example.com/photo.jpg",
                "contentScale": "FIT",
                "contentDescription": "Photo"
            }
        """.trimIndent()

        val result = json.decodeFromString(ImageProperties.serializer(), jsonString)

        assertEquals("https://example.com/photo.jpg", result.url)
        assertEquals(CraftDContentScale.FIT, result.contentScale)
        assertEquals("Photo", result.contentDescription)
    }
}
