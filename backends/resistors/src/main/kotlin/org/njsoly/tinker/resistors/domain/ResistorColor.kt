package org.njsoly.tinker.resistors.domain

import java.math.BigDecimal

enum class ResistorColor(
    /** Value given by the color when used in the significand,
     * i.e. in the first two bands, or three in a 5-band resistor. */
    val significandValue: Int,

    /** Multiplier given by the color when used in the magnitude band,
     * i.e. in the third band, or fourth in a 5-band resistor. */
    val magnitudeMultiplier: BigDecimal,

    /** Tolerance given by the color when used in the tolerance band. */
    val tolerancePercent: Double?
) {
    Black   (significandValue = 0, magnitudeMultiplier = BigDecimal(1), tolerancePercent = null),
    Brown   (significandValue = 1, magnitudeMultiplier = BigDecimal(10), tolerancePercent = 1.0),
    Red     (significandValue = 2, magnitudeMultiplier = BigDecimal(1E2), tolerancePercent = 2.0),
    Orange  (significandValue = 3, magnitudeMultiplier = BigDecimal(1E3), tolerancePercent = .05),
    Yellow  (significandValue = 4, magnitudeMultiplier = BigDecimal(1E4), tolerancePercent = .02),
    Green   (significandValue = 5, magnitudeMultiplier = BigDecimal(1E5), tolerancePercent = .5),
    Blue    (significandValue = 6, magnitudeMultiplier = BigDecimal(1E6), tolerancePercent = .25),
    Violet  (significandValue = 7, magnitudeMultiplier = BigDecimal(1E7), tolerancePercent = .1),
    Gray    (significandValue = 8, magnitudeMultiplier = BigDecimal(1E8), tolerancePercent = .01),
    White   (significandValue = 9, magnitudeMultiplier = BigDecimal(1E9), tolerancePercent = null),
    Gold    (significandValue = 0, magnitudeMultiplier = BigDecimal(1E-1), tolerancePercent = 5.0),
    Silver  (significandValue = 0, magnitudeMultiplier = BigDecimal(1E-2), tolerancePercent = 10.0)
}
