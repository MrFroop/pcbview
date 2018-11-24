package com.jambren.pcbview.view

import com.jambren.pcbview.bootstrapTornadoFX
import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.matchers.types.shouldBeInstanceOf
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import javafx.scene.layout.HBox
import tornadofx.FX

class MainViewTest : StringSpec({

    "should have a title" {
        val view = FX.find<MainView>()
        view.title shouldBe "PCB Viewer"
    }

    "root container should be an hbox" {
        val view = FX.find<MainView>()
        view.root.shouldBeInstanceOf<HBox>()
    }
}) {
    override fun beforeSpec(description: Description, spec: Spec) {
        bootstrapTornadoFX()
    }
}
