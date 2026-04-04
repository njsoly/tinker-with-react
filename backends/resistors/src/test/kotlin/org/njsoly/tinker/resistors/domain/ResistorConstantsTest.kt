package org.njsoly.tinker.resistors.domain

import org.junit.jupiter.api.Test

class ResistorConstantsTest {

    @Test
    fun `print ohms constants`() {
        ResistorConstants.OHM_SYMBOLS.forEachIndexed { i, s ->
            println("i: U+${s[0].code.toString(16).uppercase()} == $s")
        }
    }
}
