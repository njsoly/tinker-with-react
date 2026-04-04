package org.njsoly.tinker.resistors.core

import java.math.BigDecimal

object EngineeringUtils {

    fun findBestPrefix(value: BigDecimal): MetricPrefixes {
        if (value >= BigDecimal(1) && value < BigDecimal(1000)) return MetricPrefixes.BASE

        val prefix = MetricPrefixes.entries
            .filter { it.multiplier <= value}
            .maxBy { it.multiplier }

        return prefix
    }
}
