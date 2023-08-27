package com.hexagon.accounts.api.rest.contract.mapper

import com.hexagon.accounts.api.rest.contract.dtos.response.PersonResponseDto
import com.hexagon.accounts.domain.entities.Person
import org.springframework.stereotype.Component

@Component
class PersonToPersonResponseMapper : (Person) -> PersonResponseDto {
    override fun invoke(p1: Person): PersonResponseDto {
        return PersonResponseDto(
            id = p1.id!!,
            name = p1.name,
            surname = p1.surname,
            birthDate = p1.birthDate,
            nationality = p1.nationality
        )
    }
}