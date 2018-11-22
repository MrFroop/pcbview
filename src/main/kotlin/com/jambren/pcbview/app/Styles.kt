package com.jambren.pcbview.app

import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

@SuppressWarnings("MagicNumber")
class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}