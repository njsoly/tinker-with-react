package org.njsoly.trading.config

import org.njsoly.trading.adapter.out.persistence.TradeRepositoryAdapter
import org.njsoly.trading.application.TradeManagementService
import org.njsoly.trading.port.`in`.TradeManagementUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TradingConfiguration {

    @Bean
    fun tradeManagementUseCase(
        tradeRepositoryAdapter: TradeRepositoryAdapter
    ): TradeManagementUseCase {
        return TradeManagementService(tradeRepositoryAdapter)
    }
}
