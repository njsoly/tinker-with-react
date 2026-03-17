package org.njsoly.trading.domain

import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class Trade(
    val id: TradeId,
    val symbol: Symbol,
    val type: TradeType,
    val quantity: Quantity,
    val price: Price,
    val executedAt: Instant,
    val fees: Money = Money.ZERO
) {
    fun totalCost(): Money {
        return when (type) {
            TradeType.BUY -> Money(price.value.multiply(quantity.value)).add(fees)
            TradeType.SELL -> Money(price.value.multiply(quantity.value)).subtract(fees)
        }
    }

    fun isValid(): Boolean {
        return quantity.value > BigDecimal.ZERO && 
               price.value > BigDecimal.ZERO &&
               fees.value >= BigDecimal.ZERO
    }
}

@JvmInline
value class TradeId(val value: UUID = UUID.randomUUID())

@JvmInline
value class Symbol(val value: String) {
    init {
        require(value.isNotBlank()) { "Symbol cannot be blank" }
    }
}

enum class TradeType {
    BUY, SELL
}

@JvmInline
value class Quantity(val value: BigDecimal) {
    init {
        require(value >= BigDecimal.ZERO) { "Quantity must be non-negative" }
    }
}

@JvmInline
value class Price(val value: BigDecimal) {
    init {
        require(value >= BigDecimal.ZERO) { "Price must be non-negative" }
    }
}

@JvmInline
value class Money(val value: BigDecimal) {
    companion object {
        val ZERO = Money(BigDecimal.ZERO)
    }

    fun add(other: Money): Money = Money(this.value.add(other.value))
    fun subtract(other: Money): Money = Money(this.value.subtract(other.value))
    
    operator fun plus(other: Money): Money = add(other)
    operator fun minus(other: Money): Money = subtract(other)
}
