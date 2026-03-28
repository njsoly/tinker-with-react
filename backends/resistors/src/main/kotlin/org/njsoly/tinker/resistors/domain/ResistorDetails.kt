package org.njsoly.tinker.resistors.domain

class ResistorDetails(
    val value: Double,
    val engineeringNotation: String,
    val ohmNotation: String,
    val tolerance: Double?,
    val bandPattern: ResistorBandPattern
)
