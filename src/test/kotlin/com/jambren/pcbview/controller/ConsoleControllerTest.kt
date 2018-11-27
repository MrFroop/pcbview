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

package com.jambren.pcbview.controller

import com.jambren.pcbview.event.ConsoleUpdateEvent
import com.jambren.pcbview.model.ConsoleStore
import com.jambren.pcbview.test.TestBase
import com.nhaarman.mockitokotlin2.mock
import org.amshove.kluent.Verify
import org.amshove.kluent.called
import org.amshove.kluent.on
import org.amshove.kluent.that
import org.amshove.kluent.was
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ConsoleControllerTest : TestBase() {

    private val mockStore: ConsoleStore = mock()

    private lateinit var consoleController: ConsoleController

    @BeforeEach
    fun setup() {
        consoleController = ConsoleController(mockStore)
    }

    @Test
    fun `appending a line should trigger ConsoleUpdateEvent`() {
        registerEventListener<ConsoleUpdateEvent>()
        consoleController.append("Test1")
        verifyThatEventListenerWasCalled()
    }

    @Test
    fun `appending multiple lines should trigger ConsoleUpdateEvent once`() {
        registerEventListener<ConsoleUpdateEvent>()
        consoleController.append(listOf("Test1", "Test2", "Test3"))
        verifyThatEventListenerWasCalled(times = 1)
    }

    @Test
    fun `appending one line multiple times should trigger ConsoleUpdateEvent each time`() {
        registerEventListener<ConsoleUpdateEvent>()
        consoleController.append("Test1")
        consoleController.append("Test2")
        verifyThatEventListenerWasCalled(times = 2)
    }

    @Test
    fun `calling clear should call clear on backing store`() {
        consoleController.clear()
        Verify on mockStore that mockStore.clear() was called
    }

    @Test
    fun `calling append should call append on backing store`() {
        consoleController.append("Test1")
        Verify on mockStore that mockStore.append("Test1") was called
    }
}