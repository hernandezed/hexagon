package com.hexagon.accounts.api.rest.contract.mapper

import com.hexagon.accounts.api.rest.contract.dtos.request.CreatePersonRequestDto
import com.hexagon.accounts.domain.entities.Person
import org.springframework.stereotype.Component

@Component
class CreatePersonRequestDtoToPersonMapper : (CreatePersonRequestDto) -> Person {
    override fun invoke(p1: CreatePersonRequestDto): Person {
        return Person(
            name = p1.name,
            surname = p1.surname,
            birthDate = p1.birthDate,
            nationality = p1.nationality
        )
    }
}