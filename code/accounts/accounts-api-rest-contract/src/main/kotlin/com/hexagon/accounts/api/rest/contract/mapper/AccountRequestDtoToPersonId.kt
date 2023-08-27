package com.hexagon.accounts.api.rest.contract.mapper

import com.hexagon.accounts.api.rest.contract.dtos.request.CreateAccountRequestDto
import org.springframework.stereotype.Component

@Component
class AccountRequestDtoToPersonId : (CreateAccountRequestDto) -> String {
    override fun invoke(dto: CreateAccountRequestDto): String {
        return dto.personId
    }
}