package com.hexagon.accounts.infrastructure.db.holders

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity(name = "Account")
class AccountDataHolder(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var uniqueIdentifier: String? = null,
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    var person: PersonDataHolder? = null,
    var currency: String? = null,
    var status: String? = null
)

@Entity(name = "Person")
data class PersonDataHolder(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    var name: String? = null,
    var surname: String? = null,
    var birthDate: String? = null,
    var nationality: String? = null,
)
