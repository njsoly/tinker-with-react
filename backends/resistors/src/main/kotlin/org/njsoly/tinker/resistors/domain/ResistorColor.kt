package org.njsoly.tinker.resistors.domain

import java.math.BigDecimal

@Suppress("unused")
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
    Black   (1, 0, BigDecimal(1), null),
    Brown   (2, 1, BigDecimal(10), 1.0),
    Red     (3, 2, BigDecimal(1E2), 2.0),
    Orange  (4, 3, BigDecimal(1E3), .05),
    Yellow  (5, 4, BigDecimal(1E4), .02),
    Green   (6, 5, BigDecimal(1E5), .5),
    Blue    (7, 6, BigDecimal(1E6), .25),
    Violet  (8, 7, BigDecimal(1E7), .1),
    Gray    (9, 8, BigDecimal(1E8), .01),
    White   (10, 9, BigDecimal(1E9), null),
    Gold    (11, 0, BigDecimal(1E-1), 5.0),
    Silver  (12, 0, BigDecimal(1E-2), 10.0)
}
