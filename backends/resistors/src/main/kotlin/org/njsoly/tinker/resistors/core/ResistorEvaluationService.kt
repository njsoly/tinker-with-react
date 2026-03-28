package org.njsoly.tinker.resistors.core

import org.njsoly.tinker.resistors.domain.ResistorBandPattern
import org.njsoly.tinker.resistors.domain.ResistorDetails
import org.springframework.stereotype.Service

@Service
class ResistorEvaluationService {

    fun evaluateResistor(resistorBandPattern: ResistorBandPattern): ResistorDetails {
        validatePattern(resistorBandPattern)
    }

    /**
     * Validates whether the chosen pattern of resistor bands is possible.
     * @param resistorBandPattern the color band pattern of the resistor
     */
    private fun validatePattern(resistorBandPattern: ResistorBandPattern){
        println("Validating pattern: $resistorBandPattern")
        TODO("Not yet implemented")
    }
}
