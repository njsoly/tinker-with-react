package org.njsoly.tinker.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ResistorControllerTest {

    @Test
    fun `getOhmSymbol returns correct symbol`() {
        val controller = ResistorController(resistorEvaluationService)
        println("Ohm: ${controller.getOhmSymbol()}")
        assertEquals("\u2126", controller.getOhmSymbol())
    }
}
