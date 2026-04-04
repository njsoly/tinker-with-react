package org.njsoly.tinker.resistors.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class EngineeringUtilsTest {

    @Test
    fun `findBestPrefix returns base prefix for 11`() {
        assertEquals(MetricPrefixes.BASE, EngineeringUtils.findBestPrefix(BigDecimal(11.0)))
    }

    @Test
    fun `findBestPrefix returns kilo prefix for 1000`() {
        assertEquals(MetricPrefixes.KILO, EngineeringUtils.findBestPrefix(BigDecimal(1000.0)))
    }

    @Test
    fun `findBestPrefix returns milli prefix for point-999`() {
        assertEquals(MetricPrefixes.MILLI, EngineeringUtils.findBestPrefix(BigDecimal(0.999)))
    }


}
