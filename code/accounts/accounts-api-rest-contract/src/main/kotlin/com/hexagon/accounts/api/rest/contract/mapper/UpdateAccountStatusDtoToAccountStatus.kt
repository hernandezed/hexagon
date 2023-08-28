package com.hexagon.accounts.api.rest.contract.mapper

import com.hexagon.accounts.api.rest.contract.dtos.request.UpdateStatusAccountRequestDto
import com.hexagon.accounts.domain.entities.AccountStatus
import org.springframework.stereotype.Component

@Component
class UpdateAccountStatusDtoToAccountStatus : (UpdateStatusAccountRequestDto) -> AccountStatus {
    override fun invoke(p1: UpdateStatusAccountRequestDto): AccountStatus {
        return AccountStatus.valueOf(p1.newStatus)
    }
}