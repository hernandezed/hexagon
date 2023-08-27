package com.hexagon.accounts

import com.hexagon.accounts.infrastructure.config.db.populators.AccountPopulator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AccountsApiRestApplication

fun main(args: Array<String>) {
    val context = runApplication<AccountsApiRestApplication>(*args)
    val ap = context.getBean(AccountPopulator::class.java)
    ap.populate()
}
