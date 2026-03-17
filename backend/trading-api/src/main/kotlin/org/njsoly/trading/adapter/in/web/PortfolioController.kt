package org.njsoly.trading.adapter.`in`.web

import org.njsoly.trading.port.`in`.TradeManagementUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/trading/portfolio")
@CrossOrigin(origins = ["http://localhost:3000"])
class PortfolioController(
    private val tradeManagementUseCase: TradeManagementUseCase
) {

    @GetMapping
    fun getPortfolio(): PortfolioResponse {
        val portfolio = tradeManagementUseCase.getPortfolio()
        return PortfolioResponse.fromDomain(portfolio)
    }
}
