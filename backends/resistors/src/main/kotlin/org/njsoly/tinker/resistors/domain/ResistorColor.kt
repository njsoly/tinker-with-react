package org.njsoly.tinker.resistors.domain

@Suppress("unused")
enum class ResistorColor(
    val id: Int,
    /** Value given by the color when used in the significand,
     * i.e. in the first two bands, or three in a 5-band resistor. */
    val significandValue: Int,
    /** Power of ten given by the color when used in the magnitude,
     * i.e. in the third band, or fourth in a 5-band resistor. */
    val magnitudeValue: Int,
    /** Tolerance given by the color when used in the tolerance band. */
    val tolerancePercent: Double?
) {
    Black   (1, 0, 0, null),
    Brown   (2, 1, 1, 1.0),
    Red     (3, 2, 2, 2.0),
    Orange  (4, 3, 3, .05),
    Yellow  (5, 4, 4, .02),
    Green   (6, 5, 5, .5),
    Blue    (7, 6, 6, .25),
    Violet  (8, 7, 7, .1),
    Gray    (9, 8, 8, .01),
    White   (10, 9, 9, null),
    Gold    (11, 0, -1, 5.0),
    Silver  (12, 0, -2, 10.0)
}
