package org.njsoly.tinker.resistors.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.njsoly.tinker.resistors.core.ResistorEvaluationService

class ResistorControllerTest {

    private val resistorEvaluationService = ResistorEvaluationService()

    @Test
    fun `getOhmSymbol returns correct symbol`() {
        val controller = ResistorController(resistorEvaluationService)
        println("Ohm: ${controller.getOhmSymbol()}")
        assertEquals("\u2126", controller.getOhmSymbol())
    }
}
