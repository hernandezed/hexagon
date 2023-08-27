package com.hexagon.account.business.usecases

import com.hexagon.account.business.ports.PersonPort
import com.hexagon.accounts.domain.entities.Person
import com.hexagon.accounts.domain.exceptions.NotSuitablePersonException
import com.hexagon.accounts.domain.usecases.CreatePersonUseCase

class CreatePersonUseCaseImpl(val personPort: PersonPort, val ageCutPoint: Int) : CreatePersonUseCase {
    override fun execute(person: Person): Person {
        if (person.isOlder(ageCutPoint)) {
            return personPort.create(person)
        } else {
            throw NotSuitablePersonException("""The person must be older than ${person.age()} to have an account""")
        }
    }
}