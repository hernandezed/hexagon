package com.hexagon.accounts.infrastructure.db.repositories

import com.hexagon.accounts.infrastructure.db.holders.AccountDataHolder
import com.hexagon.accounts.infrastructure.db.holders.PersonDataHolder
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

interface AccountRepository : CrudRepository<AccountDataHolder, UUID> {
    fun findByUniqueIdentifier(uniqueIdentifier: String): AccountDataHolder?
}