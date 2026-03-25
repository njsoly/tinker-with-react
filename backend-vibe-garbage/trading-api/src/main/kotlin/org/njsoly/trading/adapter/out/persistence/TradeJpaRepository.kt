package org.njsoly.trading.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TradeJpaRepository : JpaRepository<TradeEntity, UUID> {
    fun findBySymbol(symbol: String): List<TradeEntity>
}
