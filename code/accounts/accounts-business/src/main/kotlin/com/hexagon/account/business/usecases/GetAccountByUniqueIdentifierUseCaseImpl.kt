package com.hexagon.account.business.usecases

import com.hexagon.account.business.ports.AccountPort
import com.hexagon.accounts.domain.exceptions.AccountBlockedException
import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.domain.entities.AccountStatus
import com.hexagon.accounts.domain.usecases.GetAccountByUniqueIdentifierUseCase

class GetAccountByUniqueIdentifierUseCaseImpl(val accountPort: AccountPort) : GetAccountByUniqueIdentifierUseCase {
    override fun execute(uniqueIdentifier: String): Account {
        val account = this.accountPort.findByUniqueIdentifier(uniqueIdentifier)
        if (account.status == AccountStatus.BLOCKED) {
            throw AccountBlockedException(uniqueIdentifier)
        }
        return account
    }
}