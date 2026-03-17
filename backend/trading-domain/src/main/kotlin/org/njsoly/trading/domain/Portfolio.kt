package org.njsoly.trading.domain

import java.math.BigDecimal
import java.math.RoundingMode

data class Portfolio(
    val holdings: Map<Symbol, Holding> = emptyMap()
) {
    fun applyTrade(trade: Trade): Portfolio {
        val currentHolding = holdings[trade.symbol] ?: Holding(trade.symbol)
        val updatedHolding = when (trade.type) {
            TradeType.BUY -> currentHolding.buy(trade.quantity, trade.price, trade.fees)
            TradeType.SELL -> currentHolding.sell(trade.quantity, trade.price, trade.fees)
        }

        val updatedHoldings = if (updatedHolding.quantity.value == BigDecimal.ZERO) {
            holdings - trade.symbol
        } else {
            holdings + (trade.symbol to updatedHolding)
        }

        return copy(holdings = updatedHoldings)
    }

    fun totalValue(currentPrices: Map<Symbol, Price>): Money {
        return holdings.values.fold(Money.ZERO) { acc, holding ->
            val currentPrice = currentPrices[holding.symbol]
            if (currentPrice != null) {
                acc + holding.currentValue(currentPrice)
            } else {
                acc
            }
        }
    }

    fun totalCostBasis(): Money {
        return holdings.values.fold(Money.ZERO) { acc, holding ->
            acc + holding.totalCostBasis()
        }
    }
}

data class Holding(
    val symbol: Symbol,
    val quantity: Quantity = Quantity(BigDecimal.ZERO),
    val averageCostBasis: Price = Price(BigDecimal.ZERO),
    val totalFeesPaid: Money = Money.ZERO
) {
    fun buy(qty: Quantity, price: Price, fees: Money): Holding {
        val newQuantity = quantity.value + qty.value
        val totalCost = (quantity.value * averageCostBasis.value) + (qty.value * price.value)
        val newAverageCost = if (newQuantity > BigDecimal.ZERO) {
            totalCost.divide(newQuantity, 8, RoundingMode.HALF_UP)
        } else {
            BigDecimal.ZERO
        }

        return copy(
            quantity = Quantity(newQuantity),
            averageCostBasis = Price(newAverageCost),
            totalFeesPaid = totalFeesPaid + fees
        )
    }

    fun sell(qty: Quantity, price: Price, fees: Money): Holding {
        require(qty.value <= quantity.value) { "Cannot sell more than held quantity" }
        
        val newQuantity = quantity.value - qty.value
        return copy(
            quantity = Quantity(newQuantity),
            totalFeesPaid = totalFeesPaid + fees
        )
    }

    fun currentValue(currentPrice: Price): Money {
        return Money(quantity.value * currentPrice.value)
    }

    fun totalCostBasis(): Money {
        return Money(quantity.value * averageCostBasis.value) + totalFeesPaid
    }

    fun unrealizedGainLoss(currentPrice: Price): Money {
        return currentValue(currentPrice) - totalCostBasis()
    }
}
