package org.njsoly.trading.adapter.out.persistence

import org.njsoly.trading.domain.Symbol
import org.njsoly.trading.domain.Trade
import org.njsoly.trading.domain.TradeId
import org.njsoly.trading.port.out.TradeRepository
import org.springframework.stereotype.Component

@Component
class TradeRepositoryAdapter(
    private val jpaRepository: TradeJpaRepository
) : TradeRepository {

    override fun save(trade: Trade): Trade {
        val entity = TradeEntity.fromDomain(trade)
        return jpaRepository.save(entity).toDomain()
    }

    override fun findById(id: TradeId): Trade? {
        return jpaRepository.findById(id.value)
            .map { it.toDomain() }
            .orElse(null)
    }

    override fun findAll(): List<Trade> {
        return jpaRepository.findAll()
            .map { it.toDomain() }
    }

    override fun findBySymbol(symbol: Symbol): List<Trade> {
        return jpaRepository.findBySymbol(symbol.value)
            .map { it.toDomain() }
    }
}
