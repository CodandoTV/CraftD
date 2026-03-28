```kotlin
package com.github.codandotv.craftd.androidcore.extensions

import android.content.Context
import android.util.TypedValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.BufferedReader
import java.io.InputStream
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ContextExtesionTest {

    private lateinit var mockContext: Context
    private lateinit var mockAssets: android.content.res.AssetManager
    private lateinit var mockInputStream: InputStream
    private lateinit var mockBufferedReader: BufferedReader
    private lateinit var mockTheme: android.content.res.Resources.Theme

    @Before
    fun setUp() {
        mockContext = mockk()
        mockAssets = mockk()
        mockInputStream = mockk()
        mockBufferedReader = mockk()
        mockTheme = mockk()

        every { mockContext.assets } returns mockAssets
        every { mockContext.theme } returns mockTheme
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `given valid json file when loadJSONFromAsset then returns json content`() {
        val fileName = "test"
        val expectedContent = """{"key":"value","number":42}"""

        every { mockAssets.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.use<String>(any()) } answers {
            val block = firstArg<(BufferedReader) -> String>()
            block(mockBufferedReader)
        }

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
    }

    @Test
    fun `given empty filename when loadJSONFromAsset then opens correct asset path`() {
        val fileName = ""
        val expectedContent = "{}"

        every { mockAssets.open(".json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.use<String>(any()) } answers {
            val block = firstArg<(BufferedReader) -> String>()
            block(mockBufferedReader)
        }

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
    }

    @Test
    fun `given special characters in filename when loadJSONFromAsset then constructs correct path`() {
        val fileName = "my-test_file"
        val expectedContent = """{"data":"test"}"""

        every { mockAssets.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.use<String>(any()) } answers {
            val block = firstArg<(BufferedReader) -> String>()
            block(mockBufferedReader)
        }

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
    }

    @Test
    fun `given multiline json when loadJSONFromAsset then preserves formatting`() {
        val fileName = "complex"
        val expectedContent = """{
            "key1": "value1",
            "key2": "value2"
        }"""

        every { mockAssets.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader() } returns mockBufferedReader
        every { mockBufferedReader.readText() } returns expectedContent
        every { mockBufferedReader.use<String>(any()) } answers {
            val block = firstArg<(BufferedReader) -> String>()
            block(mockBufferedReader)
        }

        val result = mockContext.loadJSONFromAsset(fileName)

        assertEquals(expectedContent, result)
    }

    @Test
    fun `given valid attribute when getAttrColorRes then returns color value`() {
        val attribute = android.R.attr.colorPrimary
        val expectedColorValue = 0xFF6200EE.toInt()

        val typedValue = TypedValue()
        typedValue.data = expectedColorValue

        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = expectedColorValue
            true
        }

        val result = mockContext.getAttrColorRes(attribute)

        assertEquals(expectedColorValue, result)
    }

    @Test
    fun `given unresolvable attribute when getAttrColorRes then returns zero`() {
        val attribute = 999999

        every { mockTheme.resolveAttribute(attribute, any(), false) } returns false

        val result = mockContext.getAttrColorRes(attribute)

        assertEquals(0, result)
    }

    @Test
    fun `given multiple attributes when getAttrColorRes then returns correct value for each`() {
        val attribute1 = android.R.attr.colorPrimary
        val attribute2 = android.R.attr.colorSecondary
        val colorValue1 = 0xFF6200EE.toInt()
        val colorValue2 = 0xFF03DAC6.toInt()

        every { mockTheme.resolveAttribute(attribute1, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = colorValue1
            true
        }

        every { mockTheme.resolveAttribute(attribute2, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = colorValue2
            true
        }

        val result1 = mockContext.getAttrColorRes(attribute1)
        val result2 = mockContext.getAttrColorRes(attribute2)

        assertEquals(colorValue1, result1)
        assertEquals(colorValue2, result2)
    }

    @Test
    fun `given zero attribute value when getAttrColorRes then handles correctly`() {
        val attribute = 0

        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = 0
            true
        }

        val result = mockContext.getAttrColorRes(attribute)

        assertEquals(0, result)
    }

    @Test
    fun `given negative attribute value when getAttrColorRes then returns zero on resolution failure`() {
        val attribute = -1

        every { mockTheme.resolveAttribute(attribute, any(), false) } returns false

        val result = mockContext.getAttrColorRes(attribute)

        assertEquals(0, result)
    }

    @Test
    fun `given large color value when getAttrColorRes then preserves full value`() {
        val attribute = android.R.attr.colorPrimary
        val largeColorValue = 0xFFFFFFFF.toInt()

        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = largeColorValue
            true
        }

        val result = mockContext.getAttrColorRes(attribute)

        assertEquals(largeColorValue, result)
    }

    @Test
    fun `given theme resolution succeeds when getAttrColorRes then uses resolved data`() {
        val attribute = android.R.attr.colorAccent
        val colorValue = 0xFFBB86FC.toInt()

        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val value = secondArg<TypedValue>()
            value.data = colorValue
            true
        }

        val result = mockContext.getAttrColorRes(attribute)

        assertEquals(colorValue, result)
    }

    @Test
    fun `given theme resolution fails when getAttrColorRes then returns zero instead of throwing`() {
        val attribute = android.R.attr.colorPrimary

        every { mockTheme.resolveAttribute(attribute, any(), false) } returns false

        val result = mockContext.getAttrColorRes(attribute)

        assertEquals(0, result)
    }
}
```