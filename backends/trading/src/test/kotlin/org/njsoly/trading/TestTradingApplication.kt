package org.njsoly.trading

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<TradingApplication>().with(TestcontainersConfiguration::class).run(*args)
}
