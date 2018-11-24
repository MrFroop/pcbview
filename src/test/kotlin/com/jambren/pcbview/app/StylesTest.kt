package com.jambren.pcbview.app

import io.kotlintest.matchers.types.shouldBeTypeOf
import io.kotlintest.specs.StringSpec
import tornadofx.CssRule

class StylesTest : StringSpec({

    "should contain companion styles heading" {
        Styles.heading.shouldBeTypeOf<CssRule>()
    }
})
