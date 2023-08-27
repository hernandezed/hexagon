package com.hexagon.account.business.usecases

import com.hexagon.account.business.ports.AccountPort
import com.hexagon.account.business.ports.PersonPort
import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.domain.entities.UniqueIdentifier
import com.hexagon.accounts.domain.usecases.CreateAccountUseCase

class CreateAccountUseCaseImpl(val personPort: PersonPort, val accountPort: AccountPort) : CreateAccountUseCase {
    override fun execute(personId: String, currency: String): Account {
        val person = personPort.findById(personId)
        val account = Account.createNewAccount(person, currency)
        return accountPort.create(account)
    }
}