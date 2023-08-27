package com.hexagon.accounts.infrastructure.db.repositories

import com.hexagon.accounts.infrastructure.db.holders.PersonDataHolder
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PersonRepository : CrudRepository<PersonDataHolder, UUID> {
}