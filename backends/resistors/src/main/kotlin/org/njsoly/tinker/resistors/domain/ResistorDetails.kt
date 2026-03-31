package org.njsoly.tinker.resistors.domain

class ResistorDetails(
    val value: Double,
    val engineeringNotation: String,
    val tolerance: Double? = null,
    val bandPattern: ResistorBandPattern
)
