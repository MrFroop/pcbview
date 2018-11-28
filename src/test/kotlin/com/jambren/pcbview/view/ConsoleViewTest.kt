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

package com.jambren.pcbview.view

import com.jambren.pcbview.controller.ConsoleController
import com.jambren.pcbview.event.ConsoleUpdateEvent
import com.jambren.pcbview.test.ComponentTestBase
import org.amshove.kluent.Verify
import org.amshove.kluent.called
import org.amshove.kluent.mock
import org.amshove.kluent.on
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldContain
import org.amshove.kluent.that
import org.amshove.kluent.was
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import tornadofx.FX

class ConsoleViewTest : ComponentTestBase() {

    private val consoleView = prepareUIComponentForTest<ConsoleView>()
    private val consoleControllerMock: ConsoleController = mock()

    init {
        mocks = listOf(
            consoleControllerMock
        )
    }

    @Test
    fun `should have style console`() {
        consoleView.root.styleClass shouldContain "console"
    }

    @Test
    fun `should not be editable`() {
        consoleView.root.isEditable shouldBe false
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "APPVEYOR", matches = "true")
    fun `when triggering ConsoleUpdateEvent view should call controller`() {
        FX.eventbus.fire(ConsoleUpdateEvent())
        waitForFxEvents()
        Verify on consoleControllerMock that consoleControllerMock.getLast(10) was called
    }
}
