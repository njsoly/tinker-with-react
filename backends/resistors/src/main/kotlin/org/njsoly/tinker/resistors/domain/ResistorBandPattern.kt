package org.njsoly.tinker.resistors.domain

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResistorBandPattern(
    val band0: ResistorColor,
    val band1: ResistorColor,
    val band2: ResistorColor,
    val band3: ResistorColor? = null,
    val band4: ResistorColor? = null,
)
