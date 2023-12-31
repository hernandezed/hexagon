package com.hexagon.accounts.business.usecases

import com.hexagon.accounts.business.ports.AccountPort
import com.hexagon.accounts.business.ports.PersonPort
import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.domain.usecases.CreateAccountUseCase

class CreateAccountUseCaseImpl(val personPort: PersonPort, val accountPort: AccountPort) : CreateAccountUseCase {
    override fun execute(personId: String, currency: String): Account {
        val person = personPort.findById(personId)
        val account = Account.createNewAccount(person, currency)
        return accountPort.save(account)
    }
}