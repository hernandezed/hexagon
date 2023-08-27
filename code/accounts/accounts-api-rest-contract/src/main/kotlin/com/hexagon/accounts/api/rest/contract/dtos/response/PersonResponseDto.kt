package com.hexagon.accounts.api.rest.contract.dtos.response

data class PersonResponseDto(
    val id: String,
    val name: String,
    val surname: String,
    val birthDate: String,
    val nationality: String,
)