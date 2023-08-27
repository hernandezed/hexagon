package com.hexagon.account.business.ports

import com.hexagon.accounts.domain.entities.Person

interface PersonPort {
    fun create(person: Person): Person
    fun findById(personId: String): Person
}