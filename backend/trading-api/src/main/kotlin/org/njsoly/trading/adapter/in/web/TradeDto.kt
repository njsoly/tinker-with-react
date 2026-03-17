package org.njsoly.trading.adapter.`in`.web

import org.njsoly.trading.domain.*
import java.math.BigDecimal
import java.time.Instant

data class RecordTradeRequest(
    val symbol: String,
    val type: TradeType,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val executedAt: Instant,
    val fees: BigDecimal = BigDecimal.ZERO
)

data class TradeResponse(
    val id: String,
    val symbol: String,
    val type: TradeType,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val executedAt: Instant,
    val fees: BigDecimal,
    val totalCost: BigDecimal
) {
    companion object {
        fun fromDomain(trade: Trade): TradeResponse {
            return TradeResponse(
                id = trade.id.value.toString(),
                symbol = trade.symbol.value,
                type = trade.type,
                quantity = trade.quantity.value,
                price = trade.price.value,
                executedAt = trade.executedAt,
                fees = trade.fees.value,
                totalCost = trade.totalCost().value
            )
        }
    }
}

data class PortfolioResponse(
    val holdings: List<HoldingResponse>,
    val totalCostBasis: BigDecimal
) {
    companion object {
        fun fromDomain(portfolio: Portfolio): PortfolioResponse {
            return PortfolioResponse(
                holdings = portfolio.holdings.values.map { HoldingResponse.fromDomain(it) },
                totalCostBasis = portfolio.totalCostBasis().value
            )
        }
    }
}

data class HoldingResponse(
    val symbol: String,
    val quantity: BigDecimal,
    val averageCostBasis: BigDecimal,
    val totalFeesPaid: BigDecimal,
    val totalCostBasis: BigDecimal
) {
    companion object {
        fun fromDomain(holding: Holding): HoldingResponse {
            return HoldingResponse(
                symbol = holding.symbol.value,
                quantity = holding.quantity.value,
                averageCostBasis = holding.averageCostBasis.value,
                totalFeesPaid = holding.totalFeesPaid.value,
                totalCostBasis = holding.totalCostBasis().value
            )
        }
    }
}
