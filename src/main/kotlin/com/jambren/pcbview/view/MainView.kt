package com.jambren.pcbview.view

import tornadofx.View
import tornadofx.borderpane
import tornadofx.left
import tornadofx.right
import tornadofx.top
import tornadofx.vbox

class MainView : View("PCB Viewer") {

    override val root = borderpane {
        top {
            add<MainMenu>()
        }
        left {
            add<ToolView>()
        }
        right {
            vbox {
                add<PCBView>()
                add<ConsoleView>()
            }
        }
    }
}


