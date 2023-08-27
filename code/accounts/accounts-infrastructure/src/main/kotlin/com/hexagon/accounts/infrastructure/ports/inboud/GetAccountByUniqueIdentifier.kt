package com.hexagon.accounts.infrastructure.ports.inboud

import com.hexagon.accounts.domain.entities.Account
import com.hexagon.accounts.domain.usecases.GetAccountByUniqueIdentifierUseCase
import org.springframework.stereotype.Component

class GetAccountByUniqueIdentifier<T>(
    val useCase: GetAccountByUniqueIdentifierUseCase,
    val mapper: (dto: Account) -> T
) : (String) -> T {

    override fun invoke(uniqueIdentifier: String): T {
        return mapper.invoke(useCase.execute(uniqueIdentifier))
    }
}