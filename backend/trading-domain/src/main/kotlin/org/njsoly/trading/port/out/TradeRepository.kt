package org.njsoly.trading.port.out

import org.njsoly.trading.domain.Symbol
import org.njsoly.trading.domain.Trade
import org.njsoly.trading.domain.TradeId

interface TradeRepository {
    fun save(trade: Trade): Trade
    fun findById(id: TradeId): Trade?
    fun findAll(): List<Trade>
    fun findBySymbol(symbol: Symbol): List<Trade>
}
