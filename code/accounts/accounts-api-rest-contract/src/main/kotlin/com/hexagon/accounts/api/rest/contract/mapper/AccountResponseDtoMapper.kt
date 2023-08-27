package com.hexagon.accounts.api.rest.contract.mapper

import com.hexagon.accounts.api.rest.contract.dtos.response.AccountResponseDto
import com.hexagon.accounts.api.rest.contract.dtos.response.PersonResponseDto
import com.hexagon.accounts.domain.entities.Account
import org.springframework.stereotype.Component

@Component
class AccountResponseDtoMapper : (Account) -> AccountResponseDto {
    override fun invoke(account: Account): AccountResponseDto {
        return AccountResponseDto(
            id = account.id!!,
            status = account.status.name,
            currency = account.currency,
            uniqueIdentifier = account.uniqueIdentifier.value,
            person = PersonResponseDto(
                id = account.person.id!!, nationality = account.person.nationality,
                birthDate = account.person.birthDate, name = account.person.name, surname = account.person.surname
            )
        )
    }
}