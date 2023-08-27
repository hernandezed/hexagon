package com.hexagon.accounts.domain.usecases

import com.hexagon.accounts.domain.entities.Account

interface GetAccountByUniqueIdentifierUseCase {
    fun execute(uniqueIdentifier: String): Account
}