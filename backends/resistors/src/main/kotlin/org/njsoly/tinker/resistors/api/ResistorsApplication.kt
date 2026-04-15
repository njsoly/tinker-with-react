package org.njsoly.tinker.resistors.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["org.njsoly.tinker.resistors"])
@ConfigurationPropertiesScan("org.njsoly.tinker.resistors")
class ResistorsApplication

fun main(args: Array<String>) {
    runApplication<ResistorsApplication>(*args)
}
