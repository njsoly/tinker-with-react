package org.njsoly.trading.port.`in`

import org.njsoly.trading.domain.*
import java.math.BigDecimal
import java.time.Instant

interface TradeManagementUseCase {
    fun recordTrade(command: RecordTradeCommand): Trade
    fun getAllTrades(): List<Trade>
    fun getTradesBySymbol(symbol: Symbol): List<Trade>
    fun getPortfolio(): Portfolio
}

data class RecordTradeCommand(
    val symbol: String,
    val type: TradeType,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val executedAt: Instant,
    val fees: BigDecimal = BigDecimal.ZERO
)
