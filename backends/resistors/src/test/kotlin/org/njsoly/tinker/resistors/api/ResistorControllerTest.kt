package org.njsoly.tinker.resistors.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.njsoly.tinker.resistors.core.ResistorEvaluationService
import org.njsoly.tinker.resistors.domain.ResistorConstants
import org.njsoly.tinker.resistors.domain.ResistorConstantsTest

class ResistorControllerTest {

    private val resistorEvaluationService = ResistorEvaluationService()

    @Test
    fun `getOhmSymbol returns correct symbol`() {
        val controller = ResistorController(resistorEvaluationService)
        println("Ohm: ${controller.getOhmSymbol()}")
        assertEquals(ResistorConstants.OHM_SYMBOLS[0], controller.getOhmSymbol())
    }
}
