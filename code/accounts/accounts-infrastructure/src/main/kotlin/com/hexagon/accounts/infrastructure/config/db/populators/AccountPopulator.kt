package com.hexagon.accounts.infrastructure.config.db.populators

import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.infrastructure.db.holders.AccountDataHolder
import com.hexagon.accounts.infrastructure.db.repositories.AccountRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.streams.asSequence

@Component
class AccountPopulator(
    private val accountDataRepository: AccountRepository,
    private val personPopulator: PersonPopulator
) :
    Populator<AccountDataHolder> {
    override fun populate(): Iterable<AccountDataHolder> {
        val charPool: List<Char> = listOf<Char>() + ('0'..'9')
        val accounts = ArrayList<AccountDataHolder>()
        val persons = personPopulator.populate()
        persons.forEach {
            accounts.add(
                AccountDataHolder(
                    uniqueIdentifier = (1..23)
                        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
                        .joinToString(""),
                    person = it,
                    currency = "USD",
                    status = "OPEN",
                    lastModifiedStatusDate = LocalDateTime.now()
                )
            )
            if (it.nationality != "US") {
                accounts.add(
                    AccountDataHolder(
                        uniqueIdentifier = (1..23)
                            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
                            .joinToString(""),
                        person = it,
                        currency = Currency.getInstance(Locale.of("en", it.nationality)).currencyCode,
                        status = "OPEN",
                        lastModifiedStatusDate = LocalDateTime.now()
                    )
                )
            }
        }
        return accountDataRepository.saveAll(accounts)
    }
}