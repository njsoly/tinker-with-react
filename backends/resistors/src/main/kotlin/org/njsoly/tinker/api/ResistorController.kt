package org.njsoly.tinker.api

import org.njsoly.tinker.resistors.domain.ResistorColor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ResistorController {

    @GetMapping("/colors")
    fun getResistors(): List<ResistorColor> {
        return ResistorColor.entries
    }
}
