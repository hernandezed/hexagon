package com.hexagon.accounts.domain.usecases

import com.hexagon.accounts.domain.entities.Account

interface CreateAccountUseCase {
    fun execute(personId: String, currency: String): Account
}