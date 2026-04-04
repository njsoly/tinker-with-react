package org.njsoly.tinker.resistors.domain

import org.junit.jupiter.api.Test

class ResistorBandPatternTest {

    val pattern0 = ResistorBandPattern(ResistorColor.Red, ResistorColor.Black, ResistorColor.Brown)

    @Test
    fun `getListOfColors works`() {
        println("colors of pattern0: ${pattern0.getListOfColors()}")
    }
}
