package com.jambren.pcbview.view

import com.jambren.pcbview.bootstrapTornadoFX
import javafx.scene.control.MenuBar
import javafx.scene.layout.BorderPane
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
        view.root.shouldBeInstanceOf(BorderPane::class)
    }

    @Test
    fun `menu should be on top`() {
        view.root.top.shouldBeInstanceOf(MenuBar::class)
    }
}
