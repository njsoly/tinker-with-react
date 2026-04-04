package org.njsoly.tinker.resistors.core

import java.math.BigDecimal

enum class MetricPrefixes(val multiplier: BigDecimal, val writtenPrefix: String) {
    FEMTO(BigDecimal(1E-15), "f"),
    PICO(BigDecimal(1E-12), "p"),
    NANO(BigDecimal(1E-9), "n"),
    MICRO(BigDecimal(1E-6), "u"),
    MILLI(BigDecimal(1E-3), "m"),
    BASE(BigDecimal(1), ""),
    KILO(BigDecimal(1E3), "k"),
    MEGA(BigDecimal(1E6), "M"),
    GIGA(BigDecimal(1E9), "G"),
    TERA(BigDecimal(1E12), "T"),
}
