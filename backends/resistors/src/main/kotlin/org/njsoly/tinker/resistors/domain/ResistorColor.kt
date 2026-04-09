package org.njsoly.tinker.resistors.domain

import java.math.BigDecimal

enum class ResistorColor(
    val id: Int,

    /** Value given by the color when used in the significand,
     * i.e. in the first two bands, or three in a 5-band resistor. */
    val significandValue: Int,

    /** Multiplier given by the color when used in the magnitude band,
     * i.e. in the third band, or fourth in a 5-band resistor. */
    val magnitudeMultiplier: BigDecimal,

    /** Tolerance given by the color when used in the tolerance band. */
    val tolerancePercent: Double?
) {
    Black   (id = 1, significandValue = 0,  magnitudeMultiplier = BigDecimal(1),    tolerancePercent = null),
    Brown   (id = 2, significandValue = 1,  magnitudeMultiplier = BigDecimal(10),   tolerancePercent = 1.0),
    Red     (id = 3, significandValue = 2,  magnitudeMultiplier = BigDecimal(1E2),  tolerancePercent = 2.0),
    Orange  (id = 4, significandValue = 3,  magnitudeMultiplier = BigDecimal(1E3),  tolerancePercent = .05),
    Yellow  (id = 5, significandValue = 4,  magnitudeMultiplier = BigDecimal(1E4),  tolerancePercent = .02),
    Green   (id = 6, significandValue = 5,  magnitudeMultiplier = BigDecimal(1E5),  tolerancePercent = .5),
    Blue    (id = 7, significandValue = 6,  magnitudeMultiplier = BigDecimal(1E6),  tolerancePercent = .25),
    Violet  (id = 8, significandValue = 7,  magnitudeMultiplier = BigDecimal(1E7),  tolerancePercent = .1),
    Gray    (id = 9, significandValue = 8,  magnitudeMultiplier = BigDecimal(1E8),  tolerancePercent = .01),
    White   (id = 10, significandValue = 9, magnitudeMultiplier = BigDecimal(1E9),  tolerancePercent = null),
    Gold    (id = 11, significandValue = 0, magnitudeMultiplier = BigDecimal(1E-1), tolerancePercent = 5.0),
    Silver  (id = 12, significandValue = 0, magnitudeMultiplier = BigDecimal(1E-2), tolerancePercent = 10.0)
}
