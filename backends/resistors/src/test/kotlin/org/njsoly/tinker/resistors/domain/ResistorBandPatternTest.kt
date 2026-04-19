package org.njsoly.tinker.resistors.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class ResistorBandPatternTest {

    @Test
    fun `constructor constructs`(){
        assertDoesNotThrow {
            ResistorBandPattern(ResistorColor.Red, ResistorColor.Black, ResistorColor.Brown)
        }
    }
}
