package org.njsoly.tinker.resistors.domain

import java.math.BigDecimal

class ResistanceDetail(
    /** Basic, numeric value of the resistance. */
    val value: BigDecimal,
    /** [value] when represented in engineering notation, meaning the leading number is between 1 and 1000,
     * with metric prefixes (k, M, u, etc.) as necessary. */
    val engineeringNotation: String,
    val tolerance: Double? = null
)
