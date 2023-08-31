package com.hexagon.accounts.business.usecases

import com.hexagon.accounts.business.ports.AccountPort
import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.domain.entities.AccountStatus
import com.hexagon.accounts.domain.entities.UniqueIdentifier
import com.hexagon.accounts.domain.usecases.UpdateAccountStatusUseCase
import java.lang.RuntimeException
import java.time.LocalDateTime

class UpdateAccountStatusUseCaseImpl(val accountPort: AccountPort) : UpdateAccountStatusUseCase {
    override fun execute(uniqueIdentifier: UniqueIdentifier, status: AccountStatus, date: LocalDateTime) {
        val account: Account = accountPort.findByUniqueIdentifier(uniqueIdentifier.value)
        if (account.lastModifiedStatusDate > date) {
            throw RuntimeException()
        }
        val newAccountValue = Account(
            id = account.id,
            uniqueIdentifier = account.uniqueIdentifier,
            person = account.person,
            currency = account.currency,
            status = status,
            lastModifiedStatusDate = date
        )
        accountPort.save(newAccountValue)
    }
}