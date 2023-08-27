package com.hexagon.accounts.domain.entities

import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import kotlin.random.Random

data class Account(
    val id: String? = null,
    val uniqueIdentifier: UniqueIdentifier,
    val person: Person,
    val currency: String,
    val status: AccountStatus
) {

    companion object {
        fun createNewAccount(person: Person, currency: String): Account {
            return Account(
                uniqueIdentifier = UniqueIdentifier.generate(),
                person = person,
                currency = currency,
                status = AccountStatus.OPEN
            )
        }
    }
}


class UniqueIdentifier(val value: String) {
    companion object {
        fun generate(): UniqueIdentifier {
            val charPool: List<Char> = listOf<Char>() + ('0'..'9')
            return UniqueIdentifier((1..23)
                .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
                .joinToString(""))
        }
    }
}


enum class AccountStatus {
    OPEN,
    CLOSED,
    BLOCKED
}

data class Person(
    val id: String? = null,
    val name: String,
    val surname: String,
    val birthDate: String,
    val nationality: String,
) {
    fun isOlder(cutPoint: Int): Boolean {
        return age() > cutPoint
    }

    fun age(): Int {
        val actualDate = LocalDate.now()
        val birthDate = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return Period.between(birthDate, actualDate).years
    }
}