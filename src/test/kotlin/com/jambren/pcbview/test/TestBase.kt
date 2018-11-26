/*
 *     Copyright (C) <2018>  <Fredrik Jambrén>
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
import javafx.embed.swing.JFXPanel
import tornadofx.Scope
import tornadofx.ScopedInstance
import tornadofx.setInScope
import kotlin.reflect.KClass

open class TestBase(mocks: List<ScopedInstance> = emptyList()) {

    protected val testScope = Scope()

    init {
        for (mock in mocks) {
            @Suppress("UNCHECKED_CAST")
            val mockedClass = mockingDetails(mock).mockCreationSettings.typeToMock.kotlin as KClass<ScopedInstance>
            setInScope(mock, testScope, mockedClass)
        }

        @Suppress("UNUSED_VARIABLE")
        val panel = JFXPanel()
    }
}
