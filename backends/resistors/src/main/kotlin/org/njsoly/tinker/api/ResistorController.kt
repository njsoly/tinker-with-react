package org.njsoly.tinker.api

import org.njsoly.tinker.resistors.core.ResistorEvaluationService
import org.njsoly.tinker.resistors.domain.ResistorBandPattern
import org.njsoly.tinker.resistors.domain.ResistorColor
import org.njsoly.tinker.resistors.domain.ResistorDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ResistorController(private val resistorEvaluationService: ResistorEvaluationService) {

    @GetMapping("/colors")
    fun getResistors(): List<ResistorColor> {
        return ResistorColor.entries
    }

    @GetMapping("/ohm-symbol")
    fun getOhmSymbol(): String {
        return "\u2126"
    }

    @PostMapping("/evaluate")
    fun getResistanceValue(@RequestBody resistorBandPattern: ResistorBandPattern): ResistorDetails {
        return resistorEvaluationService.evaluateResistor(resistorBandPattern)
    }

}
