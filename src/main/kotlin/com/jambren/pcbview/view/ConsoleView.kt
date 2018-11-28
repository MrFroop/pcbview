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

import com.jambren.pcbview.app.Styles
import com.jambren.pcbview.controller.ConsoleController
import com.jambren.pcbview.event.ConsoleUpdateEvent
import tornadofx.View
import tornadofx.addClass
import tornadofx.textarea

class ConsoleView : View() {

    private val consoleController: ConsoleController by inject()

    override val root = textarea {
        isEditable = false
        addClass(Styles.console)

        subscribe<ConsoleUpdateEvent> {
            clear()
            for (line in consoleController.getLast(5)) {
                text += "$line\n"
            }
        }
    }
}
