package com.hexagon.accounts.infrastructure.ports.inboud

import com.hexagon.accounts.domain.entities.AccountStatus
import com.hexagon.accounts.domain.entities.UniqueIdentifier
import com.hexagon.accounts.domain.usecases.UpdateAccountStatusUseCase

class UpdateStatusAccount<REQUEST>(
    val useCase: UpdateAccountStatusUseCase,
    val getStatus: (dto: REQUEST) -> AccountStatus,
) : (String, REQUEST) -> Unit {
    override fun invoke(uniqueIdentifier: String, t: REQUEST) {
        useCase.execute(UniqueIdentifier(uniqueIdentifier), getStatus.invoke(t))
    }

}