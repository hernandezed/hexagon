package com.hexagon.accounts.infrastructure.ports.outbound

import com.hexagon.accounts.business.ports.PersonPort
import com.hexagon.accounts.domain.entities.Person
import com.hexagon.accounts.infrastructure.db.holders.PersonDataHolder
import com.hexagon.accounts.infrastructure.db.repositories.PersonRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonPortImpl(val personRepository: PersonRepository) : PersonPort {
    override fun create(person: Person): Person {
        val personData = PersonDataHolder(
            name = person.name,
            surname = person.surname,
            birthDate = person.birthDate,
            nationality = person.nationality
        )
        val saved = personRepository.save(personData)
        return Person(
            id = saved.id.toString(),
            name = saved.name!!,
            surname = saved.surname!!,
            birthDate = saved.birthDate!!,
            nationality = saved.nationality!!
        )
    }

    override fun findById(personId: String): Person {
        val saved = personRepository.findById(UUID.fromString(personId)).orElseThrow()
        return Person(
            id = saved.id.toString(),
            name = saved.name!!,
            surname = saved.surname!!,
            birthDate = saved.birthDate!!,
            nationality = saved.nationality!!
        )
    }


}