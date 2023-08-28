package com.hexagon.accounts.infrastructure.ports.outbound

import com.hexagon.account.business.ports.AccountPort
import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.domain.entities.AccountStatus
import com.hexagon.accounts.domain.entities.Person
import com.hexagon.accounts.domain.entities.UniqueIdentifier
import com.hexagon.accounts.domain.exceptions.AccountNotFoundException
import com.hexagon.accounts.infrastructure.db.holders.AccountDataHolder
import com.hexagon.accounts.infrastructure.db.holders.PersonDataHolder
import com.hexagon.accounts.infrastructure.db.repositories.AccountRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.UUID

@Component
class AccountPortImpl(val accountDataRepository: AccountRepository) : AccountPort {
    override fun findByUniqueIdentifier(uniqueIdentifier: String): Account {
        val data =
            accountDataRepository.findByUniqueIdentifier(uniqueIdentifier) ?: throw AccountNotFoundException(
                uniqueIdentifier
            )
        return mapAccount(data)
    }

    override fun save(account: Account): Account {
        val accountToSave = AccountDataHolder(
            id = UUID.fromString(account.id),
            uniqueIdentifier = account.uniqueIdentifier.value,
            status = account.status.name,
            currency = account.currency,
            person = PersonDataHolder(
                id = UUID.fromString(account.person.id)
            ),
            lastModifiedStatusDate = account.lastModifiedStatusDate
        )
        val data = accountDataRepository.save(accountToSave)
        return mapAccount(data)
    }

    private fun mapAccount(data: AccountDataHolder) = Account(
        id = data.id.toString(),
        uniqueIdentifier = UniqueIdentifier(data.uniqueIdentifier!!),
        person = Person(
            id = data.person!!.id.toString(),
            name = (data.person as PersonDataHolder).name!!,
            surname = (data.person as PersonDataHolder).surname!!,
            birthDate = (data.person as PersonDataHolder).birthDate!!,
            nationality = (data.person as PersonDataHolder).nationality!!
        ),
        currency = data.currency!!,
        status = AccountStatus.valueOf(data.status!!),
        lastModifiedStatusDate = data.lastModifiedStatusDate!!
    )
}