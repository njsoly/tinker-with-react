package org.njsoly.tinker.resistors.domain

import java.math.BigDecimal

class ResistanceDetail(
    val value: BigDecimal,
    val engineeringNotation: String,
    val tolerance: Double? = null
)

