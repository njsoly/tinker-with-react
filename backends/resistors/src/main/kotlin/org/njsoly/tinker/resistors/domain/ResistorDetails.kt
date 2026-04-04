package org.njsoly.tinker.resistors.domain

import java.math.BigDecimal

class ResistorDetails(
    val value: BigDecimal,
    val engineeringNotation: String,
    val tolerance: Double? = null,
    val bandPattern: ResistorBandPattern
)
