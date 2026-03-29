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
    `given valid ActionProperties when invoking listener then executes callback`() {
        val actionProperties = mockk<ActionProperties>()
        var callbackExecuted = false
        var receivedProperties: ActionProperties? = null

        val listener: CraftDViewListener = { properties ->
            callbackExecuted = true
            receivedProperties = properties
        }

        listener.invoke(actionProperties)

        assert(callbackExecuted)
        assert(receivedProperties === actionProperties)
    }

    @Test
    `given listener when invoking with ActionProperties then callback receives correct argument`() {
        val actionProperties = mockk<ActionProperties>()
        var capturedProperties: ActionProperties? = null

        val listener: CraftDViewListener = { properties ->
            capturedProperties = properties
        }

        listener(actionProperties)

        assert(capturedProperties === actionProperties)
    }

    @Test
    `given multiple listeners when each invoked then all execute independently`() {
        val actionProperties = mockk<ActionProperties>()
        var listener1Called = false
        var listener2Called = false

        val listener1: CraftDViewListener = { listener1Called = true }
        val listener2: CraftDViewListener = { listener2Called = true }

        listener1(actionProperties)
        listener2(actionProperties)

        assert(listener1Called)
        assert(listener2Called)
    }

    @Test
    `given listener with side effects when invoked then executes all side effects`() {
        val actionProperties = mockk<ActionProperties>()
        val sideEffects = mutableListOf<String>()

        val listener: CraftDViewListener = { _ ->
            sideEffects.add("effect1")
            sideEffects.add("effect2")
            sideEffects.add("effect3")
        }

        listener(actionProperties)

        assert(sideEffects.size == 3)
        assert(sideEffects == listOf("effect1", "effect2", "effect3"))
    }

    @Test
    `given listener type alias when cast to function then behaves as function`() {
        val actionProperties = mockk<ActionProperties>()
        var executed = false

        val listener: CraftDViewListener = { _ -> executed = true }
        val functionRef: (ActionProperties) -> Unit = listener

        functionRef(actionProperties)

        assert(executed)
    }

    @Test
    `given listener when assigned multiple times then last assignment takes effect`() {
        val actionProperties = mockk<ActionProperties>()
        var firstListenerCalled = false
        var secondListenerCalled = false

        var listener: CraftDViewListener = { firstListenerCalled = true }
        listener = { secondListenerCalled = true }

        listener(actionProperties)

        assert(!firstListenerCalled)
        assert(secondListenerCalled)
    }

    @Test
    `given null properties in listener when handling then processes gracefully`() {
        var receivedNull = false
        val listener: CraftDViewListener = { properties ->
            receivedNull = properties == null
        }

        val actionProperties = mockk<ActionProperties>()
        listener(actionProperties)

        assert(!receivedNull)
    }

    @Test
    `given listener with exception when invoked then propagates exception`() {
        val actionProperties = mockk<ActionProperties>()
        val listener: CraftDViewListener = { _ -> throw IllegalStateException("Test exception") }

        try {
            listener(actionProperties)
            assert(false) { "Expected exception to be thrown" }
        } catch (e: IllegalStateException) {
            assert(e.message == "Test exception")
        }
    }

    @Test
    `given complex ActionProperties when listener invoked then correctly references object`() {
        val actionProperties = mockk<ActionProperties>()
        val listeners = mutableListOf<ActionProperties>()

        val listener: CraftDViewListener = { properties ->
            listeners.add(properties)
        }

        listener(actionProperties)
        listener(actionProperties)

        assert(listeners.size == 2)
        assert(listeners[0] === listeners[1])
    }

    @Test
    `given listener assignment when using SAM conversion then compiles and works`() {
        val actionProperties = mockk<ActionProperties>()
        var called = false

        val listener: CraftDViewListener = CraftDViewListener { _ -> called = true }

        listener(actionProperties)

        assert(called)
    }

    @Test
    `given listener in collection when iterating then all can be invoked`() {
        val actionProperties = mockk<ActionProperties>()
        val callCounts = mutableListOf<Int>()

        val listeners = listOf<CraftDViewListener>(
            { callCounts.add(1) },
            { callCounts.add(2) },
            { callCounts.add(3) }
        )

        listeners.forEach { it(actionProperties) }

        assert(callCounts == listOf(1, 2, 3))
    }

    @Test
    `given listener when used as higher order function param then executes correctly`() {
        val actionProperties = mockk<ActionProperties>()
        var executed = false

        fun executeListener(listener: CraftDViewListener, props: ActionProperties) {
            listener(props)
        }

        executeListener({ _ -> executed = true }, actionProperties)

        assert(executed)
    }
}
```