package org.njsoly.tinker.resistors.core

import org.njsoly.tinker.resistors.core.maths.MetricPrefix
import java.math.BigDecimal

object EngineeringUtils {

    fun findBestPrefix(value: BigDecimal): MetricPrefix {
        if (value >= BigDecimal(1) && value < BigDecimal(1000)) return MetricPrefix.BASE

        val prefix = MetricPrefix.entries
            .filter { it.multiplier <= value}
            .maxBy { it.multiplier }

        return prefix
    }
}
