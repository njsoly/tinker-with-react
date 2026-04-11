package org.njsoly.tinker.resistors.core.maths

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnitBuilderTest {

    @Test
    fun `addTerm given empty when single valid parameter then does not throw exception`() {
        val builder = UnitBuilder()
        assertDoesNotThrow { builder.addTerm(BaseUnit.METER) }
    }

    @Test
    fun `addTerm given not empty when valid parameter then does not throw exception`() {
        val builder = UnitBuilder(BaseUnit.METER, MetricPrefix.MILLI)
        assertDoesNotThrow { builder.addTerm(BaseUnit.SECOND, MetricPrefix.BASE, -1) }
    }

    @Test
    fun `UnitBuilder when two like terms then combines them` () {
        val builder = UnitBuilder()
        builder.addTerm(BaseUnit.METER, MetricPrefix.BASE, 1)
        builder.addTerm(BaseUnit.METER, MetricPrefix.BASE, 1)

        val expected = Unit()
    }
}
