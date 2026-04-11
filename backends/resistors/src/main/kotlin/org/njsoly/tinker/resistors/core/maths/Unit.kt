package org.njsoly.tinker.resistors.core.maths

/**
 * A full expression of the measurable units, which can be as easy as [Time], as complex as [Power],
 *
 */
class Unit(
    val terms: List<UnitTerm>
) {
    constructor() : this(emptyList())
    constructor(
        baseUnit: BaseUnit,
        prefix: MetricPrefix = MetricPrefix.BASE,
        power: Int = 1
    ) : this(listOf(UnitTerm(baseUnit, prefix, power)))
}

/**
 * Builder for a [Unit], for cases such as:
 * * like terms that should be combined, nullified, etc.
 * * conflicting prefixes for the same [BaseUnit]
 * * conflicting [BaseUnit]s for the same [Quantifiable]
 */
class UnitBuilder() {

    constructor(
        baseUnit: BaseUnit,
        prefix: MetricPrefix = MetricPrefix.BASE,
        power: Int = 1
    ) : this()

    val terms = mutableListOf<UnitTerm>()

    fun addTerm(baseUnit: BaseUnit, prefix: MetricPrefix = MetricPrefix.BASE, power: Int = 1): UnitBuilder {
        terms.add(UnitTerm(baseUnit, prefix, power))
        return this
    }

    fun build(): Unit {
        return Unit(terms)
    }
}
