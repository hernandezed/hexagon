package com.hexagon.account.event.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class AccountEventApiApplication

fun main(args: Array<String>) {
    runApplication<AccountEventApiApplication>(*args)
}
