package com.hexagon.accounts.api.rest.contract.dtos.response

data class AccountResponseDto(
    val id: String,
    val uniqueIdentifier: String,
    val person: PersonResponseDto,
    val currency: String,
    val status: String
)