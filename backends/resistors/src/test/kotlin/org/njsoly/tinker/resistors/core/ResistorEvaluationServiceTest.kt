package org.njsoly.tinker.resistors.core

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.njsoly.tinker.resistors.domain.ResistorBandPattern
import org.njsoly.tinker.resistors.domain.ResistorColor
import java.math.BigDecimal

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

    @Test
    fun `getEngineeringNotation given 10 returns no change`() {
        val value = BigDecimal("10")
        val expected = "10"
        assertEquals(expected, service.getEngineeringNotation(value))
    }

    @Test
    fun `getEngineeringNotation given 1E5 returns 100 k`() {
        val value = BigDecimal("1E5")
        val expected = "100 k"
        assertEquals(expected, service.getEngineeringNotation(value))
    }

    @Test
    fun `getEngineeringNotation given 100000 returns 100 k`() {
        val value = BigDecimal("100000")
        val expected = "100 k"
        assertEquals(expected, service.getEngineeringNotation(value))
    }

    @Test
    fun `getEngineeringNotation given 330 returns 330`() {
        val value = BigDecimal("330")
        val expected = "330"
        assertEquals(expected, service.getEngineeringNotation(value))
    }

    @Test
    fun `getEngineeringNotation given 1500000 returns 1pt5 M`() {
        val value = BigDecimal("1.5E6")
        val expected = "1.5 M"
        assertEquals(expected, service.getEngineeringNotation(value))
    }

    @Test
    fun `getEngineeringNotation given 0pt05 returns 50 m`() {
        val value = BigDecimal("0.05")
        val expected = "50 m"
        assertEquals(expected, service.getEngineeringNotation(value))
    }
}
