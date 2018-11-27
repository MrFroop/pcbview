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
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.BeforeEach
import tornadofx.FX
import tornadofx.FXEvent
import tornadofx.FXEventRegistration
import tornadofx.Scope
import tornadofx.ScopedInstance
import tornadofx.setInScope
import java.util.concurrent.Semaphore
import kotlin.reflect.KClass

open class TestBase(mocks: List<ScopedInstance> = emptyList()) {

    protected val testScope = Scope()
    protected var listenerCalled = 0L
    protected lateinit var registration: FXEventRegistration

    init {
        for (mock in mocks) {
            @Suppress("UNCHECKED_CAST")
            val mockedClass = mockingDetails(mock).mockCreationSettings.typeToMock.kotlin as KClass<ScopedInstance>
            setInScope(mock, testScope, mockedClass)
        }

        @Suppress("UNUSED_VARIABLE")
        val panel = JFXPanel()
    }

    @BeforeEach
    fun resetEventListener() {
        listenerCalled = 0
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
    private fun waitForFxEvents() = waitForFxEvents(5)

    private fun waitForFxEvents(attempts: Int) {
        for (i in 0..attempts) {
            blockFxThreadWithSemaphore()
            Thread.sleep(10)
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
}
