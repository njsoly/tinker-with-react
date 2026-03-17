package org.njsoly.trading.adapter.out.persistence

import jakarta.persistence.*
import org.njsoly.trading.domain.*
import java.math.BigDecimal
import java.time.Instant
import java.util.*

@Entity
@Table(name = "trades")
class TradeEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    
    @Column(nullable = false)
    val symbol: String,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: TradeType,
    
    @Column(nullable = false, precision = 19, scale = 8)
    val quantity: BigDecimal,
    
    @Column(nullable = false, precision = 19, scale = 8)
    val price: BigDecimal,
    
    @Column(nullable = false)
    val executedAt: Instant,
    
    @Column(nullable = false, precision = 19, scale = 8)
    val fees: BigDecimal = BigDecimal.ZERO
) {
    fun toDomain(): Trade {
        return Trade(
            id = TradeId(id),
            symbol = Symbol(symbol),
            type = type,
            quantity = Quantity(quantity),
            price = Price(price),
            executedAt = executedAt,
            fees = Money(fees)
        )
    }

    companion object {
        fun fromDomain(trade: Trade): TradeEntity {
            return TradeEntity(
                id = trade.id.value,
                symbol = trade.symbol.value,
                type = trade.type,
                quantity = trade.quantity.value,
                price = trade.price.value,
                executedAt = trade.executedAt,
                fees = trade.fees.value
            )
        }
    }
}
