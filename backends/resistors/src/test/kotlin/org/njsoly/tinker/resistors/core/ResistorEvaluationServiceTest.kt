package org.njsoly.tinker.resistors.core

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.njsoly.tinker.resistors.domain.ResistorBandPattern
import org.njsoly.tinker.resistors.domain.ResistorColor

class ResistorEvaluationServiceTest {

    private val service = ResistorEvaluationService()

    @Test
    fun `validatePattern given invalid pattern throws IllegalArgumentException`() {
        val pattern = ResistorBandPattern(
            ResistorColor.Silver,
            ResistorColor.Silver,
            ResistorColor.Silver
        )

        val exception = assertThrows<IllegalArgumentException> {
            service.validatePattern(pattern)
        }
        assertEquals("1st band cannot be Gold or Silver", exception.message)
    }

    @Test
    fun `validatePattern given valid pattern does not throw exception`() {
        val pattern = ResistorBandPattern(
            ResistorColor.Red,
            ResistorColor.Brown,
            ResistorColor.Black
        )
        assertDoesNotThrow { service.validatePattern(pattern) }
    }
}
