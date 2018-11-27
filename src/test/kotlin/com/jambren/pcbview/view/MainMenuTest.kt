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

import com.jambren.pcbview.controller.ApplicationController
import com.jambren.pcbview.test.TestBase
import com.jambren.pcbview.test.shouldHaveMenu
import com.jambren.pcbview.test.shouldHaveMenuItem
import com.nhaarman.mockitokotlin2.mock
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import org.amshove.kluent.Verify
import org.amshove.kluent.called
import org.amshove.kluent.on
import org.amshove.kluent.that
import org.amshove.kluent.was
import org.junit.jupiter.api.Test
import tornadofx.FX

val applicationControllerMock = mock<ApplicationController>()

class MainMenuTest : TestBase(listOf(
    applicationControllerMock
)) {
    private var menu: MainMenu = FX.find(testScope)

    @Test
    fun `should have a file menu`() {
        menu.root.shouldHaveMenu("File")
    }

    @Test
    fun `the file menu should have a Quit item`() {
        getMenu("File").shouldHaveMenuItem("Quit")
    }

    @Test
    fun `quit should call application controller quit`() {
        getMenuItem("File", "Quit").fire()
        Verify on applicationControllerMock that applicationControllerMock.quit() was called
    }

    private fun getMenu(name: String): Menu {
        return menu.root.menus.filter { name == it.text }[0]
    }

    private fun getMenuItem(name: String, item: String): MenuItem {
        return getMenu(name).items.filter { item == it.text }[0]
    }
}
