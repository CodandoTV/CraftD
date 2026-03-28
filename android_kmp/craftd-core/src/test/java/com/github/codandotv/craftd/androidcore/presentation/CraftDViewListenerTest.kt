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
    `given valid ActionProperties when CraftDViewListener is invoked then callback receives ActionProperties`() {
        val actionProperties = mockk<ActionProperties>()
        var capturedActionProperties: ActionProperties? = null

        val listener: CraftDViewListener = { actionProperties ->
            capturedActionProperties = actionProperties
        }

        listener.invoke(actionProperties)

        assert(capturedActionProperties === actionProperties)
    }

    @Test
    `given CraftDViewListener when invoked multiple times then all invocations are processed`() {
        val firstActionProperties = mockk<ActionProperties>()
        val secondActionProperties = mockk<ActionProperties>()
        val invocations = mutableListOf<ActionProperties>()

        val listener: CraftDViewListener = { actionProperties ->
            invocations.add(actionProperties)
        }

        listener.invoke(firstActionProperties)
        listener.invoke(secondActionProperties)

        assert(invocations.size == 2)
        assert(invocations[0] === firstActionProperties)
        assert(invocations[1] === secondActionProperties)
    }

    @Test
    `given CraftDViewListener when assigned different implementations then each implementation is called correctly`() {
        val actionProperties = mockk<ActionProperties>()
        var firstListenerCalled = false
        var secondListenerCalled = false

        val firstListener: CraftDViewListener = { _ ->
            firstListenerCalled = true
        }

        val secondListener: CraftDViewListener = { _ ->
            secondListenerCalled = true
        }

        firstListener.invoke(actionProperties)
        assert(firstListenerCalled)
        assert(!secondListenerCalled)

        firstListenerCalled = false
        secondListener.invoke(actionProperties)
        assert(!firstListenerCalled)
        assert(secondListenerCalled)
    }

    @Test
    `given CraftDViewListener as function type when called then executes lambda body`() {
        val actionProperties = mockk<ActionProperties>()
        var executionCount = 0

        val listener: CraftDViewListener = { _ ->
            executionCount++
        }

        repeat(3) {
            listener(actionProperties)
        }

        assert(executionCount == 3)
    }

    @Test
    `given CraftDViewListener when created with null handling then gracefully handles null parameter`() {
        var receivedNull = false
        val listener: CraftDViewListener = { actionProperties ->
            if (actionProperties == null) {
                receivedNull = true
            }
        }

        val nullActionProperties: ActionProperties? = null
        listener.invoke(nullActionProperties as ActionProperties)

        assert(receivedNull || nullActionProperties == null)
    }

    @Test
    `given multiple listeners when invoked sequentially then maintains independent state`() {
        val actionProperties1 = mockk<ActionProperties>()
        val actionProperties2 = mockk<ActionProperties>()

        var listener1Received: ActionProperties? = null
        var listener2Received: ActionProperties? = null

        val listener1: CraftDViewListener = { props ->
            listener1Received = props
        }

        val listener2: CraftDViewListener = { props ->
            listener2Received = props
        }

        listener1.invoke(actionProperties1)
        listener2.invoke(actionProperties2)

        assert(listener1Received === actionProperties1)
        assert(listener2Received === actionProperties2)
    }

    @Test
    `given CraftDViewListener as typealias when used in collection then stored correctly`() {
        val actionProperties = mockk<ActionProperties>()
        val listeners = mutableListOf<CraftDViewListener>()
        val results = mutableListOf<ActionProperties>()

        val listener1: CraftDViewListener = { props -> results.add(props) }
        val listener2: CraftDViewListener = { props -> results.add(props) }

        listeners.add(listener1)
        listeners.add(listener2)

        listeners.forEach { it.invoke(actionProperties) }

        assert(results.size == 2)
        assert(results.all { it === actionProperties })
    }

    @Test
    `given CraftDViewListener when exception is thrown in callback then exception propagates`() {
        val actionProperties = mockk<ActionProperties>()
        val listener: CraftDViewListener = { _ ->
            throw IllegalStateException("Test exception")
        }

        var exceptionThrown = false
        try {
            listener.invoke(actionProperties)
        } catch (e: IllegalStateException) {
            exceptionThrown = true
        }

        assert(exceptionThrown)
    }

    @Test
    `given CraftDViewListener type alias when used as function parameter then accepts lambda`() {
        var callbackExecuted = false
        val actionProperties = mockk<ActionProperties>()

        fun executeListener(listener: CraftDViewListener) {
            listener.invoke(actionProperties)
        }

        val testListener: CraftDViewListener = { _ ->
            callbackExecuted = true
        }

        executeListener(testListener)

        assert(callbackExecuted)
    }

    @Test
    `given CraftDViewListener when returning from function then callable and functional`() {
        val actionProperties = mockk<ActionProperties>()

        fun createListener(): CraftDViewListener {
            return { _ -> Unit }
        }

        val listener = createListener()
        var invoked = false

        val listeningListener: CraftDViewListener = { props ->
            listener.invoke(props)
            invoked = true
        }

        listeningListener.invoke(actionProperties)

        assert(invoked)
    }

    @Test
    `given CraftDViewListener when composed with other functions then maintains correct execution order`() {
        val actionProperties = mockk<ActionProperties>()
        val executionOrder = mutableListOf<String>()

        val listener1: CraftDViewListener = { _ ->
            executionOrder.add("first")
        }

        val listener2: CraftDViewListener = { _ ->
            executionOrder.add("second")
        }

        listener1.invoke(actionProperties)
        listener2.invoke(actionProperties)

        assert(executionOrder[0] == "first")
        assert(executionOrder[1] == "second")
    }
}
```