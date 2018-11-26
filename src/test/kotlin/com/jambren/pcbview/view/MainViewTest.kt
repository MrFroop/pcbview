package com.jambren.pcbview.view

import com.jambren.pcbview.bootstrapTornadoFX
import javafx.scene.control.MenuBar
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import tornadofx.FX

class MainViewTest {

    private lateinit var view: MainView

    @BeforeEach
    fun beforeEachTest() {
        bootstrapTornadoFX()
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
