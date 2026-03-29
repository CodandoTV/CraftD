```kotlin
package com.github.codandotv.craftd.androidcore.presentation

import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CraftDViewListenerTest {

    @Test
    fun `given valid ActionProperties when invoking CraftDViewListener then executes lambda`() {
        val mockActionProperties = mockk<ActionProperties>()
        var invoked = false
        var capturedProperties: ActionProperties? = null

        val listener: CraftDViewListener = { properties ->
            invoked = true
            capturedProperties = properties
        }

        listener.invoke(mockActionProperties)

        assert(invoked)
        assert(capturedProperties == mockActionProperties)
    }

    @Test
    fun `given CraftDViewListener when assigning lambda then lambda is callable`() {
        val listener: CraftDViewListener = { _ ->
            // no-op
        }

        assert(listener != null)
    }

    @Test
    fun `given multiple ActionProperties when invoking CraftDViewListener sequentially then all invocations execute`() {
        val mockActionProperties1 = mockk<ActionProperties>()
        val mockActionProperties2 = mockk<ActionProperties>()
        val invokedProperties = mutableListOf<ActionProperties>()

        val listener: CraftDViewListener = { properties ->
            invokedProperties.add(properties)
        }

        listener.invoke(mockActionProperties1)
        listener.invoke(mockActionProperties2)

        assert(invokedProperties.size == 2)
        assert(invokedProperties[0] == mockActionProperties1)
        assert(invokedProperties[1] == mockActionProperties2)
    }

    @Test
    fun `given CraftDViewListener with side effects when invoked then side effects are applied`() {
        var sideEffectCounter = 0

        val listener: CraftDViewListener = { _ ->
            sideEffectCounter++
        }

        listener.invoke(mockk())
        listener.invoke(mockk())
        listener.invoke(mockk())

        assert(sideEffectCounter == 3)
    }

    @Test
    fun `given CraftDViewListener type alias when creating instance then type is function with ActionProperties parameter`() {
        val mockActionProperties = mockk<ActionProperties>()
        val listener: CraftDViewListener = { capturedProps ->
            assert(capturedProps is ActionProperties)
        }

        listener.invoke(mockActionProperties)
    }

    @Test
    fun `given CraftDViewListener when listener returns Unit then no return value expected`() {
        val listener: CraftDViewListener = { _ ->
            Unit
        }

        val result = listener.invoke(mockk<ActionProperties>())

        assert(result == Unit)
    }

    @Test
    fun `given CraftDViewListener created from lambda when invoking with ActionProperties then properties are accessible in lambda scope`() {
        val mockActionProperties = mockk<ActionProperties>()
        var accessibleInScope = false

        val listener: CraftDViewListener = { properties ->
            accessibleInScope = properties != null
        }

        listener.invoke(mockActionProperties)

        assert(accessibleInScope)
    }

    @Test
    fun `given CraftDViewListener type alias then it is function type with single ActionProperties parameter`() {
        val listener: CraftDViewListener = { _ -> }
        val isFunction = listener is Function1<*, *>

        assert(isFunction)
    }

    @Test
    fun `given CraftDViewListener with exception in lambda when invoked then exception propagates`() {
        val testException = IllegalStateException("Test exception")
        val listener: CraftDViewListener = { _ ->
            throw testException
        }

        try {
            listener.invoke(mockk())
            assert(false) { "Expected exception to be thrown" }
        } catch (e: IllegalStateException) {
            assert(e == testException)
        }
    }

    @Test
    fun `given CraftDViewListener assigned to variable when calling through variable then listener executes`() {
        val mockActionProperties = mockk<ActionProperties>()
        var executed = false

        val listener: CraftDViewListener = { _ ->
            executed = true
        }

        val listenerVariable = listener
        listenerVariable.invoke(mockActionProperties)

        assert(executed)
    }

    @Test
    fun `given two CraftDViewListener instances with same implementation when comparing then they are different instances`() {
        val listener1: CraftDViewListener = { _ -> }
        val listener2: CraftDViewListener = { _ -> }

        assert(listener1 !== listener2)
    }

    @Test
    fun `given CraftDViewListener when invoked multiple times with same ActionProperties instance then all invocations use same instance`() {
        val mockActionProperties = mockk<ActionProperties>()
        val capturedInstances = mutableListOf<ActionProperties>()

        val listener: CraftDViewListener = { properties ->
            capturedInstances.add(properties)
        }

        listener.invoke(mockActionProperties)
        listener.invoke(mockActionProperties)

        assert(capturedInstances[0] === capturedInstances[1])
    }
}
```