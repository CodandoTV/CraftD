package com.github.codandotv.craftd.androidcore.extensions

import android.content.Context
import android.content.res.AssetManager
import android.util.TypedValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.InputStream

@RunWith(JUnit4::class)
class ContextExtesionTest {

    private lateinit var mockContext: Context
    private lateinit var mockAssetManager: AssetManager

    @Before
    fun setUp() {
        mockContext = mockk(relaxed = true)
        mockAssetManager = mockk(relaxed = true)
        every { mockContext.assets } returns mockAssetManager
    }

    @Test
    fun `given valid fileName when loadJSONFromAsset then returns json string`() {
        val fileName = "test_file"
        val expectedJson = """{"key": "value"}"""
        
        val mockInputStream = mockk<InputStream>(relaxed = true)
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader().use(any()) } answers {
            val block = it.invocation.args[0] as (java.io.BufferedReader) -> String
            val mockBufferedReader = mockk<java.io.BufferedReader>(relaxed = true)
            every { mockBufferedReader.readText() } returns expectedJson
            block(mockBufferedReader)
        }
        
        val result = mockContext.loadJSONFromAsset(fileName)
        assertEquals(expectedJson, result)
    }

    @Test
    fun `given fileName with special characters when loadJSONFromAsset then appends json extension`() {
        val fileName = "config_v2"
        val mockInputStream = mockk<InputStream>(relaxed = true)
        
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader().use(any()) } answers {
            val block = it.invocation.args[0] as (java.io.BufferedReader) -> String
            val mockBufferedReader = mockk<java.io.BufferedReader>(relaxed = true)
            every { mockBufferedReader.readText() } returns "{}"
            block(mockBufferedReader)
        }
        
        mockContext.loadJSONFromAsset(fileName)
        
        verify { mockAssetManager.open("$fileName.json") }
    }

    @Test
    fun `given empty file when loadJSONFromAsset then returns empty string`() {
        val fileName = "empty"
        val mockInputStream = mockk<InputStream>(relaxed = true)
        
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader().use(any()) } answers {
            val block = it.invocation.args[0] as (java.io.BufferedReader) -> String
            val mockBufferedReader = mockk<java.io.BufferedReader>(relaxed = true)
            every { mockBufferedReader.readText() } returns ""
            block(mockBufferedReader)
        }
        
        val result = mockContext.loadJSONFromAsset(fileName)
        assertEquals("", result)
    }

    @Test
    fun `given multiline JSON when loadJSONFromAsset then returns complete content`() {
        val fileName = "multiline"
        val multilineJson = """{
            "key1": "value1",
            "key2": "value2"
        }"""
        val mockInputStream = mockk<InputStream>(relaxed = true)
        
        every { mockAssetManager.open("$fileName.json") } returns mockInputStream
        every { mockInputStream.bufferedReader().use(any()) } answers {
            val block = it.invocation.args[0] as (java.io.BufferedReader) -> String
            val mockBufferedReader = mockk<java.io.BufferedReader>(relaxed = true)
            every { mockBufferedReader.readText() } returns multilineJson
            block(mockBufferedReader)
        }
        
        val result = mockContext.loadJSONFromAsset(fileName)
        assertEquals(multilineJson, result)
    }

    @Test
    fun `given valid attribute when getAttrColorRes then returns resolved color value`() {
        val attribute = android.R.attr.colorPrimary
        val expectedColor = 0xFF6200EE.toInt()
        
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val typedValue = it.invocation.args[1] as TypedValue
            typedValue.data = expectedColor
            true
        }
        
        val result = mockContext.getAttrColorRes(attribute)
        assertEquals(expectedColor, result)
    }

    @Test
    fun `given invalid attribute when getAttrColorRes then returns zero`() {
        val attribute = 999
        
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attribute, any(), false) } returns false
        
        val result = mockContext.getAttrColorRes(attribute)
        assertEquals(0, result)
    }

    @Test
    fun `given multiple attributes when getAttrColorRes then resolves each independently`() {
        val attribute1 = android.R.attr.colorPrimary
        val attribute2 = android.R.attr.colorAccent
        val expectedColor1 = 0xFF6200EE.toInt()
        val expectedColor2 = 0xFFBB86FC.toInt()
        
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        every { mockTheme.resolveAttribute(attribute1, any(), false) } answers {
            val typedValue = it.invocation.args[1] as TypedValue
            typedValue.data = expectedColor1
            true
        }
        every { mockTheme.resolveAttribute(attribute2, any(), false) } answers {
            val typedValue = it.invocation.args[1] as TypedValue
            typedValue.data = expectedColor2
            true
        }
        every { mockContext.theme } returns mockTheme
        
        val result1 = mockContext.getAttrColorRes(attribute1)
        val result2 = mockContext.getAttrColorRes(attribute2)
        
        assertEquals(expectedColor1, result1)
        assertEquals(expectedColor2, result2)
    }

    @Test
    fun `given color attribute with zero value when getAttrColorRes then returns zero`() {
        val attribute = android.R.attr.colorPrimary
        
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val typedValue = it.invocation.args[1] as TypedValue
            typedValue.data = 0
            true
        }
        
        val result = mockContext.getAttrColorRes(attribute)
        assertEquals(0, result)
    }

    @Test
    fun `given negative attribute ID when getAttrColorRes then returns zero`() {
        val attribute = -1
        
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attribute, any(), false) } returns false
        every { mockContext.theme } returns mockTheme
        
        val result = mockContext.getAttrColorRes(attribute)
        assertEquals(0, result)
    }

    @Test
    fun `given theme resolution succeeds when getAttrColorRes then verifies resolveAttribute called with correct params`() {
        val attribute = android.R.attr.colorPrimary
        
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        every { mockContext.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val typedValue = it.invocation.args[1] as TypedValue
            typedValue.data = 0xFF6200EE.toInt()
            true
        }
        
        mockContext.getAttrColorRes(attribute)
        
        verify { mockTheme.resolveAttribute(attribute, any(), false) }
    }

    @Test
    fun `given context with theme when getAttrColorRes then accesses theme property`() {
        val attribute = android.R.attr.colorPrimary
        
        val mockTheme = mockk<android.content.res.Resources.Theme>(relaxed = true)
        val contextWithTheme = spyk(mockContext)
        every { contextWithTheme.theme } returns mockTheme
        every { mockTheme.resolveAttribute(attribute, any(), false) } answers {
            val typedValue = it.invocation.args[1] as TypedValue
            typedValue.data = 0xFF6200EE.toInt()
            true
        }
        
        contextWithTheme.getAttrColorRes(attribute)
        
        verify(atLeast = 1) { contextWithTheme.theme }
    }
}