package com.jambren.pcbview.view

import tornadofx.View
import tornadofx.item
import tornadofx.menu
import tornadofx.menubar

class MainMenu : View() {

    override val root = menubar {
        menu("File") {
            item("Quit")
        }
    }
}
