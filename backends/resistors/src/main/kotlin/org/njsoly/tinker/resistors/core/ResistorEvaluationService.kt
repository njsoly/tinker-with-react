package org.njsoly.tinker.resistors.core

import org.njsoly.tinker.resistors.domain.ResistorBandPattern
import org.njsoly.tinker.resistors.domain.ResistorColor
import org.njsoly.tinker.resistors.domain.ResistorDetails
import org.springframework.stereotype.Service
import kotlin.math.pow

@Service
class ResistorEvaluationService {

    fun evaluateResistor(resistorBandPattern: ResistorBandPattern): ResistorDetails {
        validatePattern(resistorBandPattern)

        val significand = (10 * resistorBandPattern.band0.significandValue) + resistorBandPattern.band1.significandValue

        return ResistorDetails(
            value = significand.toDouble().pow(resistorBandPattern.band2.magnitudeValue),
            engineeringNotation = "",
            bandPattern = resistorBandPattern
        )
    }

    /**
     * Validates whether the chosen pattern of resistor bands is possible.
     * Throws IllegalArgumentException if the pattern is not valid, does nothing otherwise.
     *
     * @param resistorBandPattern the color band pattern of the resistor
     */
    private fun validatePattern(resistorBandPattern: ResistorBandPattern){
        println("Validating pattern: $resistorBandPattern")

        if (resistorBandPattern.band0 in listOf(ResistorColor.Gold, ResistorColor.Silver)) {
            throw IllegalArgumentException("1st band cannot be Gold or Silver")
        }
        if (resistorBandPattern.band1 in listOf(ResistorColor.Gold, ResistorColor.Silver)) {
            throw IllegalArgumentException("2nd band cannot be Gold or Silver")
        }
    }
}
