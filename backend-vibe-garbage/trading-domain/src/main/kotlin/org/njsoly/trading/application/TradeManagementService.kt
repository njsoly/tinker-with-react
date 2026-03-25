package org.njsoly.trading.application

import org.njsoly.trading.domain.*
import org.njsoly.trading.port.`in`.RecordTradeCommand
import org.njsoly.trading.port.`in`.TradeManagementUseCase
import org.njsoly.trading.port.out.TradeRepository

class TradeManagementService(
    private val tradeRepository: TradeRepository
) : TradeManagementUseCase {

    override fun recordTrade(command: RecordTradeCommand): Trade {
        val trade = Trade(
            id = TradeId(),
            symbol = Symbol(command.symbol),
            type = command.type,
            quantity = Quantity(command.quantity),
            price = Price(command.price),
            executedAt = command.executedAt,
            fees = Money(command.fees)
        )

        require(trade.isValid()) { "Invalid trade data" }

        return tradeRepository.save(trade)
    }

    override fun getAllTrades(): List<Trade> {
        return tradeRepository.findAll()
    }

    override fun getTradesBySymbol(symbol: Symbol): List<Trade> {
        return tradeRepository.findBySymbol(symbol)
    }

    override fun getPortfolio(): Portfolio {
        val trades = tradeRepository.findAll()
            .sortedBy { it.executedAt }
        
        return trades.fold(Portfolio()) { portfolio, trade ->
            portfolio.applyTrade(trade)
        }
    }
}
