package org.njsoly.tinker.resistors.core.maths

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.njsoly.tinker.resistors.core.maths.EngineeringUtils
import org.njsoly.tinker.resistors.core.maths.MetricPrefix
import java.math.BigDecimal

class EngineeringUtilsTest {

    @Test
    fun `findBestPrefix returns base prefix for 11`() {
        assertEquals(MetricPrefix.BASE, EngineeringUtils.findBestPrefix(BigDecimal(11.0)))
    }

    @Test
    fun `findBestPrefix returns kilo prefix for 1000`() {
        assertEquals(MetricPrefix.KILO, EngineeringUtils.findBestPrefix(BigDecimal(1000.0)))
    }

    @Test
    fun `findBestPrefix returns milli prefix for point-999`() {
        assertEquals(MetricPrefix.MILLI, EngineeringUtils.findBestPrefix(BigDecimal(0.999)))
    }
}
