package org.njsoly.tinker.resistors.core.maths

import org.njsoly.tinker.resistors.core.maths.Quantifiable.*

/**
 * "Base" in the sense that they needn't be broken down any further.
 *
 * Representations of ways a [Quantifiable] can be measured.
 */
enum class BaseUnit (
    val symbol: String,
    val written: String,
    val quantifiable: Quantifiable,
    val metric: Boolean = true
){
    /**
     * A unitless quantity, such as a dimensionless ratio.
     */
    UNITLESS("", "", Dimensionless),
    /** Marked as non-metric because of minutes, years, etc.*/
    SECOND("s", "second", Time, metric = false),
    GRAM("g", "gram", Mass),
    METER("m", "meter", Length),
    OHM("Ω", "ohm", Resistance),
    AMPERE("A", "ampere", ElectricCurrent),
    VOLT("V", "volt", PotentialDifference),
}
