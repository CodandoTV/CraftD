package com.github.codandotv.craftd.androidcore.extensions

import android.content.Context
import android.content.res.AssetManager
import android.util.TypedValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.BufferedReader
import java.io.InputStream
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class ContextExtensionTest {

    private lateinit var mockContext: Context
    private lateinit var mockAssetManager: AssetManager
    private lateinit var mockInputStream: InputStream
    private lateinit var mockBufferedReader: BufferedReader

    @Before
    fun setup() {
        mockContext = mockk(relaxed = true)
        mockAssetManager = mockk(relaxed = true)
        mockInputStream = mockk(relaxed = true)
        mockBufferedReader = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `given valid json file when loadJSONFromAsset then returns file content`() {
        val fileName = "test_config"
        val expectedContent = """{"key": "value", "number": 123}"""

        every { mockContext.assets } returns mockAssetManager
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.close() } returns Unit

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
        verify { mockAssetManager.open("$fileName.json") }
        verify { mockBufferedReader.readText() }
    }

    @Test
    fun `given empty file name when loadJSONFromAsset then opens with json extension`() {
        val fileName = ""
        val expectedContent = "{}"

        every { mockContext.assets } returns mockAssetManager
        every { mockAssetManager.open(".json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.close() } returns Unit

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
        verify { mockAssetManager.open(".json") }
    }

    @Test
    fun `given file with special characters when loadJSONFromAsset then constructs correct path`() {
        val fileName = "test-config_v2"
        val expectedContent = """{"data": [1, 2, 3]}"""

        every { mockContext.assets } returns mockAssetManager
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.close() } returns Unit

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
        verify { mockAssetManager.open("test-config_v2.json") }
    }

    @Test
    fun `given multiline json file when loadJSONFromAsset then returns complete content`() {
        val fileName = "multiline"
        val expectedContent = """{
            "name": "Test",
            "items": [1, 2, 3],
            "nested": {
                "key": "value"
            }
        }"""

        every { mockContext.assets } returns mockAssetManager
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.close() } returns Unit

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
    }

    @Test
    fun `given valid attribute when getAttrColorRes then returns color data`() {
        val attributeRes = android.R.attr.textColorPrimary
        val expectedColorData = 0xFFFFFFFF.toInt()
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        val typedValue = TypedValue()
        typedValue.data = expectedColorData

        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attributeRes, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = expectedColorData
            true
        }

        val result = mockContext.getAttrColorRes(attributeRes)

        assertEquals(expectedColorData, result)
        verify { mockTheme.resolveAttribute(attributeRes, any(), false) }
    }

    @Test
    fun `given non existent attribute when getAttrColorRes then returns zero`() {
        val attributeRes = 99999
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)

        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attributeRes, any(), false) } returns false

        val result = mockContext.getAttrColorRes(attributeRes)

        assertEquals(0, result)
        verify { mockTheme.resolveAttribute(attributeRes, any(), false) }
    }

    @Test
    fun `given multiple different attributes when getAttrColorRes then returns correct color for each`() {
        val attr1 = android.R.attr.textColorPrimary
        val attr2 = android.R.attr.textColorSecondary
        val color1 = 0xFF000000.toInt()
        val color2 = 0xFF808080.toInt()
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)

        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attr1, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = color1
            true
        }
        every { mockTheme.resolveAttribute(attr2, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = color2
            true
        }

        val result1 = mockContext.getAttrColorRes(attr1)
        val result2 = mockContext.getAttrColorRes(attr2)

        assertEquals(color1, result1)
        assertEquals(color2, result2)
    }

    @Test
    fun `given attribute with zero data when getAttrColorRes then returns zero`() {
        val attributeRes = android.R.attr.textColorPrimary
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)

        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attributeRes, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = 0
            true
        }

        val result = mockContext.getAttrColorRes(attributeRes)

        assertEquals(0, result)
    }

    @Test
    fun `given negative attribute id when getAttrColorRes then resolves attribute`() {
        val attributeRes = -1
        val expectedColorData = 0xFF123456.toInt()
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)

        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attributeRes, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = expectedColorData
            true
        }

        val result = mockContext.getAttrColorRes(attributeRes)

        assertEquals(expectedColorData, result)
    }

    @Test
    fun `given theme resolution fails when getAttrColorRes then returns zero`() {
        val attributeRes = android.R.attr.textColorPrimary
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)

        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attributeRes, any(), false) } returns false

        val result = mockContext.getAttrColorRes(attributeRes)

        assertEquals(0, result)
        verify(exactly = 1) { mockTheme.resolveAttribute(attributeRes, any(), false) }
    }

    @Test
    fun `given large color value when getAttrColorRes then returns correct value`() {
        val attributeRes = android.R.attr.textColorPrimary
        val largeColorValue = 0xFFFFFFFF.toInt()
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)

        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attributeRes, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = largeColorValue
            true
        }

        val result = mockContext.getAttrColorRes(attributeRes)

        assertEquals(largeColorValue, result)
    }

    @Test
    fun `given buffered reader when loadJSONFromAsset then closes resource automatically`() {
        val fileName = "test"
        val content = "{}"

        every { mockContext.assets } returns mockAssetManager
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns content
        every { mockBufferedReader.close() } returns Unit

        mockContext.loadJSONFromAsset(fileName)

        verify { mockBufferedReader.close() }
    }
}