package com.hexagon.accounts.infrastructure.ports.inboud

import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.domain.usecases.CreateAccountUseCase

class CreateAccount<REQUEST, RESPONSE>(
    val useCase: CreateAccountUseCase,
    val getPersonId: (dto: REQUEST) -> String,
    val getCurrency: (dto: REQUEST) -> String,
    val responseMapper: (person: Account) -> RESPONSE
) : (REQUEST) -> RESPONSE {
    override fun invoke(request: REQUEST): RESPONSE {
        return responseMapper.invoke(useCase.execute(getPersonId.invoke(request), getCurrency.invoke(request)))
    }
}