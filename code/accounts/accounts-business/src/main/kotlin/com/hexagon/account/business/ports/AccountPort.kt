package com.hexagon.account.business.ports

import com.hexagon.accounts.domain.entities.Account

interface AccountPort {
    fun findByUniqueIdentifier(uniqueIdentifier: String): Account
    fun save(account: Account): Account
}