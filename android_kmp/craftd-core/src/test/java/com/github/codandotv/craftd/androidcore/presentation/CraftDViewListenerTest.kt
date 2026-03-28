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
    `given valid ActionProperties when CraftDViewListener is invoked then callback is executed`() {
        val mockActionProperties = mockk<ActionProperties>()
        var callbackExecuted = false
        var receivedActionProperties: ActionProperties? = null

        val listener: CraftDViewListener = { actionProperties ->
            callbackExecuted = true
            receivedActionProperties = actionProperties
        }

        listener.invoke(mockActionProperties)

        assert(callbackExecuted)
        assert(receivedActionProperties === mockActionProperties)
    }

    @Test
    `given CraftDViewListener when called multiple times then each invocation executes callback`() {
        val mockActionProperties1 = mockk<ActionProperties>()
        val mockActionProperties2 = mockk<ActionProperties>()
        var invocationCount = 0

        val listener: CraftDViewListener = { _ ->
            invocationCount++
        }

        listener.invoke(mockActionProperties1)
        listener.invoke(mockActionProperties2)

        assert(invocationCount == 2)
    }

    @Test
    `given two different listeners when invoked separately then each executes independently`() {
        val mockActionProperties = mockk<ActionProperties>()
        var listener1Executed = false
        var listener2Executed = false

        val listener1: CraftDViewListener = { _ ->
            listener1Executed = true
        }

        val listener2: CraftDViewListener = { _ ->
            listener2Executed = true
        }

        listener1.invoke(mockActionProperties)
        listener2.invoke(mockActionProperties)

        assert(listener1Executed)
        assert(listener2Executed)
    }

    @Test
    `given CraftDViewListener with lambda when ActionProperties is passed then lambda receives correct parameter`() {
        val expectedActionProperties = mockk<ActionProperties>()
        var capturedActionProperties: ActionProperties? = null

        val listener: CraftDViewListener = { actionProperties ->
            capturedActionProperties = actionProperties
        }

        listener(expectedActionProperties)

        assert(capturedActionProperties === expectedActionProperties)
    }

    @Test
    `given CraftDViewListener alias when used as function parameter then accepts lambda correctly`() {
        val mockActionProperties = mockk<ActionProperties>()
        var wasListenerCalled = false

        fun executeListener(listener: CraftDViewListener) {
            listener(mockActionProperties)
        }

        val testListener: CraftDViewListener = { _ ->
            wasListenerCalled = true
        }

        executeListener(testListener)

        assert(wasListenerCalled)
    }

    @Test
    `given CraftDViewListener when used with unit return type then callback executes without error`() {
        val mockActionProperties = mockk<ActionProperties>()
        var executionCount = 0

        val listener: CraftDViewListener = { _ ->
            executionCount++
        }

        listener(mockActionProperties)

        assert(executionCount == 1)
    }

    @Test
    `given CraftDViewListener with exception throwing code when invoked then exception propagates`() {
        val mockActionProperties = mockk<ActionProperties>()
        val testException = RuntimeException("Test exception")

        val listener: CraftDViewListener = { _ ->
            throw testException
        }

        var exceptionThrown: Exception? = null
        try {
            listener(mockActionProperties)
        } catch (e: Exception) {
            exceptionThrown = e
        }

        assert(exceptionThrown === testException)
    }

    @Test
    `given CraftDViewListener when stored in collection then can be iterated and invoked`() {
        val mockActionProperties = mockk<ActionProperties>()
        val listeners = mutableListOf<CraftDViewListener>()
        var totalInvocations = 0

        repeat(3) {
            listeners.add { _ ->
                totalInvocations++
            }
        }

        listeners.forEach { it(mockActionProperties) }

        assert(totalInvocations == 3)
    }

    @Test
    `given CraftDViewListener as variable when reassigned then latest assignment is used`() {
        val mockActionProperties = mockk<ActionProperties>()
        var finalResult = ""

        var listener: CraftDViewListener = { _ ->
            finalResult = "first"
        }

        listener(mockActionProperties)
        assert(finalResult == "first")

        listener = { _ ->
            finalResult = "second"
        }

        listener(mockActionProperties)
        assert(finalResult == "second")
    }

    @Test
    `given CraftDViewListener with captured variable when invoked then closure captures current value`() {
        val mockActionProperties = mockk<ActionProperties>()
        var capturedValue = "initial"

        val listener: CraftDViewListener = { _ ->
            assert(capturedValue == "initial")
        }

        listener(mockActionProperties)
    }

    @Test
    `given CraftDViewListener when passed as higher order function parameter then maintains type safety`() {
        val mockActionProperties = mockk<ActionProperties>()
        var executed = false

        fun applyListener(listener: CraftDViewListener, props: ActionProperties) {
            listener(props)
        }

        val testListener: CraftDViewListener = { _ ->
            executed = true
        }

        applyListener(testListener, mockActionProperties)

        assert(executed)
    }

    @Test
    `given CraftDViewListener when composed with another function then chain executes correctly`() {
        val mockActionProperties = mockk<ActionProperties>()
        var executionOrder = emptyList<String>()

        val listener1: CraftDViewListener = { _ ->
            executionOrder = executionOrder + "first"
        }

        val listener2: CraftDViewListener = { props ->
            executionOrder = executionOrder + "second"
            listener1(props)
        }

        listener2(mockActionProperties)

        assert(executionOrder == listOf("second", "first"))
    }
}
```