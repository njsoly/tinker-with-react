package org.njsoly.tinker.resistors.domain

data class ResistorBandPattern(
    val band0: ResistorColor,
    val band1: ResistorColor,
    val band2: ResistorColor,
    val band3: ResistorColor? = null,
    val band4: ResistorColor? = null,
) {
    fun getListOfColors(): List<ResistorColor> {
        val list = mutableListOf<ResistorColor>(band0, band1, band2)
        band3?.let { list.add(it) }
        band4?.let { list.add(it) }
        return list
    }
}
