package org.njsoly.stocks

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<StocksApplication>().with(TestcontainersConfiguration::class).run(*args)
}
