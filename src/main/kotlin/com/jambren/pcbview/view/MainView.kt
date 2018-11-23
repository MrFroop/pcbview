package com.jambren.pcbview.view

import com.jambren.pcbview.app.Styles
import tornadofx.View
import tornadofx.addClass
import tornadofx.hbox
import tornadofx.label

class MainView : View("PCB Viewer") {
    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }
}
