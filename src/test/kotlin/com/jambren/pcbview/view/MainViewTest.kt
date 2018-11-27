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

import com.jambren.pcbview.test.TestBase
import javafx.scene.control.MenuBar
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tornadofx.FX

class MainViewTest : TestBase() {

    private lateinit var view: MainView

    @BeforeEach
    fun beforeEachTest() {
        view = FX.find()
    }

    @Test
    fun `should have a title`() {
        view.title shouldBe "PCB Viewer"
    }

    @Test
    fun `root container should be an BorderPane`() {
        view.root shouldBeInstanceOf BorderPane::class
    }

    @Test
    fun `menu should be on top`() {
        view.root.top shouldBeInstanceOf MenuBar::class
    }

    @Test
    fun `tool view should be to the left`() {
        view.root.left shouldBeInstanceOf VBox::class
    }

    @Test
    fun `right should be a vbox`() {
        view.root.right shouldBeInstanceOf VBox::class
    }

    @Test
    fun `graphic viewport should be top right`() {
        val vbox = view.root.right as VBox
        vbox.children[0] shouldBeInstanceOf VBox::class
    }

    @Test
    fun `console should be bottom right`() {
        val vbox = view.root.right as VBox
        vbox.children[1] shouldBeInstanceOf TextArea::class
    }
}
