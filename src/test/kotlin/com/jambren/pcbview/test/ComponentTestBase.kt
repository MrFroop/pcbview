/*
 *     Copyright (C) 2018  Fredrik Jambrén
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.jambren.pcbview.test

import com.nhaarman.mockitokotlin2.mockingDetails
import com.nhaarman.mockitokotlin2.reset
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import tornadofx.FX
import tornadofx.FXEvent
import tornadofx.FXEventRegistration
import tornadofx.Scope
import tornadofx.ScopedInstance
import tornadofx.UIComponent
import tornadofx.View
import tornadofx.removeFromParent
import tornadofx.setInScope
import tornadofx.vbox
import java.util.concurrent.Semaphore
import kotlin.reflect.KClass

open class ComponentTestBase {

    protected val testScope = Scope()
    protected var listenerCalled = 0L
    protected lateinit var registration: FXEventRegistration
    protected lateinit var testView: TestView
    protected var mocks: List<Any> = emptyList()
    private var panel: JFXPanel

    init {
        @Suppress("UNUSED_VARIABLE")
        panel = JFXPanel()
    }

    protected inline fun <reified T : UIComponent> prepareUIComponentForTest(): T {
        val component: T = FX.find(testScope)
        testView = TestView(component)
        return component
    }

    @BeforeEach
    fun setupMocks() {
        for (mock in mocks) {
            if (mock is ScopedInstance) {
                @Suppress("UNCHECKED_CAST")
                val mockedClass = mockingDetails(mock).mockCreationSettings.typeToMock.kotlin as KClass<ScopedInstance>
                setInScope(mock, testScope, mockedClass)
            }
        }
    }

    @BeforeEach
    fun resetEventListener() {
        listenerCalled = 0
        for (mock in mocks) {
            reset(mock)
        }
    }

    @AfterEach
    fun removeComponent() {
        if (this::testView.isInitialized) {
            testView.component.removeFromParent()
        }
    }

    protected fun verifyThatEventListenerWasCalled(times: Long = 1) {
        waitForFxEvents()
        FX.eventbus.unsubscribe(registration)
        listenerCalled shouldEqualTo times
    }

    protected inline fun <reified T : FXEvent> registerEventListener() =
        registerEventListener(T::class)

    protected inline fun <reified T : FXEvent> registerEventListener(eventType: KClass<T>) {
        registration = FXEventRegistration(eventType, null, null) {
            listenerCalled++
        }
        FX.eventbus.subscribe<T>(testScope, registration)
    }

    /*
     * Attempt to wait for "JavaFX Application Thread"
     * Code taken from https://github.com/TestFX and converted to kotlin.
     */
    protected fun waitForFxEvents() = waitForFxEvents(10)

    private fun waitForFxEvents(attempts: Int) {
        for (i in 0..attempts) {
            blockFxThreadWithSemaphore()
            Thread.sleep(15)
        }
    }

    private fun blockFxThreadWithSemaphore() {
        val semaphore = Semaphore(0)
        runOnFxThread {
            semaphore.release()
        }
        semaphore.acquire()
    }

    private fun runOnFxThread(action: () -> Unit) {
        if (Platform.isFxApplicationThread()) {
            return action()
        } else {
            Platform.runLater {
                action()
            }
        }
    }

    protected class TestView(val component: UIComponent) : View() {
        override val root = vbox {
            add(component)
        }
    }
}
