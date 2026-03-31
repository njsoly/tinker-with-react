package org.njsoly.tinker.resistors.core

enum class MetricPrefixes(powerOfTen: Int, writtenPrefix: String) {
    FEMTO(-15, "f"),
    PICO(-12, "p"),
    NANO(-9, "n"),
    MICRO(-6, "u"),
    MILLI(-3, "m"),
    BASE(0, ""),
    KILO(3, "k"),
    MEGA(6, "M"),
    GIGA(9, "G"),
    TERA(12, "T"),
    PETA(15, "P"),
    EXA(18, "E")
}
