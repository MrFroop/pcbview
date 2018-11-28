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

import com.jambren.pcbview.app.appConfig
import com.jambren.pcbview.event.ConsoleUpdateEvent
import com.jambren.pcbview.model.ConsoleStore
import tornadofx.Controller

class ConsoleController(
    private val consoleStore: ConsoleStore = appConfig().consoleStore
) : Controller() {

    fun clear() {
        consoleStore.clear()
    }

    fun append(lines: List<String>) {
        for (line in lines) {
            append(line, false)
        }
        fire(ConsoleUpdateEvent())
    }

    fun append(line: String) = append(line, true)

    private fun append(line: String, triggerEvent: Boolean) {
        consoleStore.append(line)
        if (triggerEvent) {
            fire(ConsoleUpdateEvent())
        }
    }

    fun getLast(numLines: Int): List<String> {
        return consoleStore.getLast(numLines)
    }
}
