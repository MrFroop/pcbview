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

import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import org.amshove.kluent.should

fun MenuBar.shouldHaveMenu(name: String) = this.should("$this should have a menu called $name") {
    val items = this.menus.filter { name == it.text }
    !items.isEmpty()
}

fun Menu.shouldHaveMenuItem(name: String) = this.should("$this should have a menu item called $name") {
    val items = this.items.filter { name == it.text }
    !items.isEmpty()
}
