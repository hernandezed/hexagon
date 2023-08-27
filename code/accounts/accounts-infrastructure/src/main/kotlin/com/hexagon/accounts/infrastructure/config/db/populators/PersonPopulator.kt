package com.hexagon.accounts.infrastructure.config.db.populators

import com.hexagon.accounts.infrastructure.db.holders.PersonDataHolder
import com.hexagon.accounts.infrastructure.db.repositories.PersonRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@Component
class PersonPopulator(private val personRepository: PersonRepository) : Populator<PersonDataHolder> {
    override fun populate(): Iterable<PersonDataHolder> {
        val nationalityList = listOf("US", "CA", "GB", "AU", "AL", "FR", "JP", "CH")
        val random = Random(System.currentTimeMillis())

        val personList = mutableListOf<PersonDataHolder>()

        for (i in 1..200) {
            val randomName = generateRandomName(random)
            val randomSurname = generateRandomSurname(random)
            val randomBirthDate = generateRandomBirthDate(random)
            val randomNationality = nationalityList[random.nextInt(nationalityList.size)]

            val person = PersonDataHolder(
                name = randomName,
                surname = randomSurname,
                birthDate = randomBirthDate,
                nationality = randomNationality
            )

            personList.add(person)
        }

        return personRepository.saveAll(personList)
    }

    private fun generateRandomName(random: Random): String {
        val names = listOf("John", "Alice", "Michael", "Emma", "Daniel", "Olivia", "David", "Sophia")
        return names.random(random)
    }

    private fun generateRandomSurname(random: Random): String {
        val surnames = listOf("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis")
        return surnames.random(random)
    }

    private fun generateRandomBirthDate(random: Random): String {
        val startDate = LocalDate.of(1970, 1, 1)
        val endDate = LocalDate.of(2000, 12, 31)
        val randomDate = startDate.plusDays(random.nextLong((startDate.until(endDate).days.toLong())))
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return randomDate.format(formatter)
    }
}