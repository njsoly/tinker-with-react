package org.njsoly.tinker.resistors.domain

import org.junit.jupiter.api.Test

class ResistorBandPatternTest {

    val pattern0 = ResistorBandPattern(ResistorColor.RED, ResistorColor.BLACK, ResistorColor.BROWN)

    @Test
    fun `getListOfColors works`() {
        println("colors of pattern0: ${pattern0.getListOfColors()}")
    }
}
