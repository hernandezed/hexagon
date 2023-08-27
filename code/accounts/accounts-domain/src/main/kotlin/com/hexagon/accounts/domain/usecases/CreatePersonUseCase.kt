package com.hexagon.accounts.domain.usecases

import com.hexagon.accounts.domain.entities.Person

interface CreatePersonUseCase {
    fun execute(person: Person): Person
}