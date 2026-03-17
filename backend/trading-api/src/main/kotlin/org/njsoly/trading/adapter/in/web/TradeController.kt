package org.njsoly.trading.adapter.`in`.web

import org.njsoly.trading.domain.Symbol
import org.njsoly.trading.port.`in`.RecordTradeCommand
import org.njsoly.trading.port.`in`.TradeManagementUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/trading/trades")
@CrossOrigin(origins = ["http://localhost:3000"])
class TradeController(
    private val tradeManagementUseCase: TradeManagementUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun recordTrade(@RequestBody request: RecordTradeRequest): TradeResponse {
        val command = RecordTradeCommand(
            symbol = request.symbol,
            type = request.type,
            quantity = request.quantity,
            price = request.price,
            executedAt = request.executedAt,
            fees = request.fees
        )
        val trade = tradeManagementUseCase.recordTrade(command)
        return TradeResponse.fromDomain(trade)
    }

    @GetMapping
    fun getAllTrades(): List<TradeResponse> {
        return tradeManagementUseCase.getAllTrades()
            .map { TradeResponse.fromDomain(it) }
    }

    @GetMapping("/symbol/{symbol}")
    fun getTradesBySymbol(@PathVariable symbol: String): List<TradeResponse> {
        return tradeManagementUseCase.getTradesBySymbol(Symbol(symbol))
            .map { TradeResponse.fromDomain(it) }
    }
}
