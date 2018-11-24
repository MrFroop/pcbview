package com.jambren.pcbview.view

import tornadofx.View
import tornadofx.borderpane

class MainView : View("PCB Viewer") {
    override val root = borderpane {
        top(MainMenu::class)
    }
}
