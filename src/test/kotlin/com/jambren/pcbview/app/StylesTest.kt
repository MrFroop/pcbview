package com.jambren.pcbview.app

import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import tornadofx.CssRule

class StylesTest {

    @Test
    fun `should contain a heading`() {
        Styles.heading.shouldBeInstanceOf<CssRule>()
    }

    @Suppress("UNUSED_VARIABLE")
    @Test
    fun `should not throw`() {
        val style = Styles()
    }
}
