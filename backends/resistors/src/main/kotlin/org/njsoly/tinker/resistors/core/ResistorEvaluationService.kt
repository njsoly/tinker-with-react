package org.njsoly.tinker.resistors.core

import org.njsoly.tinker.resistors.core.maths.EngineeringUtils
import org.njsoly.tinker.resistors.core.maths.MetricPrefix
import org.njsoly.tinker.resistors.domain.ResistorBandPattern
import org.njsoly.tinker.resistors.domain.ResistorColor
import org.njsoly.tinker.resistors.domain.ResistanceDetail
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.MathContext

@Service
class ResistorEvaluationService {

    /**
     * Base service call for /evaluate endpoint
     *
     * Currently only set up to handle four-band resistors.
     */
    fun evaluateResistance(resistorBandPattern: ResistorBandPattern): ResistanceDetail {
        validatePattern(resistorBandPattern)

        if (resistorBandPattern.band4 != null) {
            throw IllegalArgumentException("5-band resistors are not supported.")
        }

        val significand = (10 * resistorBandPattern.band0.significandValue) + resistorBandPattern.band1.significandValue
        val resistanceValue = BigDecimal(significand) * (resistorBandPattern.band2.magnitudeMultiplier)

        return ResistanceDetail(
            value = resistanceValue,
            engineeringNotation = getEngineeringNotation(resistanceValue),
            tolerance = ResistorColor.entries.firstOrNull{ k -> resistorBandPattern.band3?.name == k.name }?.tolerancePercent
        )
    }

    fun getEngineeringNotation(resistanceValue: BigDecimal): String {
        val prefix = EngineeringUtils.findBestPrefix(resistanceValue)
        val value = resistanceValue.divide(prefix.multiplier, MathContext.DECIMAL64).stripTrailingZeros()
        return if (prefix == MetricPrefix.BASE) {
            value.toPlainString()
        } else {
            "${value.toPlainString()} ${prefix.shorthand}"
        }
    }

    /**
     * Validates whether the chosen pattern of resistor bands is possible.
     * Throws IllegalArgumentException if the pattern is not valid, does nothing otherwise.
     *
     * @param resistorBandPattern the color band pattern of the resistor
     */
    internal fun validatePattern(resistorBandPattern: ResistorBandPattern){
        println("Validating pattern: $resistorBandPattern")

        if (resistorBandPattern.band0 in listOf(ResistorColor.Gold, ResistorColor.Silver)) {
            throw IllegalArgumentException("1st band cannot be Gold or Silver")
        }
        if (resistorBandPattern.band1 in listOf(ResistorColor.Gold, ResistorColor.Silver)) {
            throw IllegalArgumentException("2nd band cannot be Gold or Silver")
        }

        // TODO there's some other colors that don't go in certain places
    }
}
