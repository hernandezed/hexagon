package com.hexagon.accounts.infrastructure.ports.inboud

import com.hexagon.accounts.domain.entities.Person
import com.hexagon.accounts.domain.usecases.CreatePersonUseCase

class CreatePerson<REQUEST, RESPONSE>(
    val useCase: CreatePersonUseCase,
    val requestMapper: (dto: REQUEST) -> Person,
    val responseMapper: (person: Person) -> RESPONSE
) : (REQUEST) -> RESPONSE {
    override fun invoke(request: REQUEST): RESPONSE {
        return responseMapper.invoke(useCase.execute(requestMapper.invoke(request)))
    }
}