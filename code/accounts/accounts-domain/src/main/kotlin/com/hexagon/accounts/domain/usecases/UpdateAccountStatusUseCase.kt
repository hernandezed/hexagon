package com.hexagon.accounts.domain.usecases

import com.hexagon.accounts.domain.entities.AccountStatus
import com.hexagon.accounts.domain.entities.UniqueIdentifier
import java.time.LocalDateTime

interface UpdateAccountStatusUseCase {
    fun execute(uniqueIdentifier: UniqueIdentifier, status: AccountStatus, date: LocalDateTime = LocalDateTime.now())
}