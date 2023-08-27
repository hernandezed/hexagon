package com.hexagon.accounts.infrastructure.config

import com.hexagon.account.business.ports.AccountPort
import com.hexagon.account.business.ports.PersonPort
import com.hexagon.account.business.usecases.CreateAccountUseCaseImpl
import com.hexagon.account.business.usecases.CreatePersonUseCaseImpl
import com.hexagon.account.business.usecases.GetAccountByUniqueIdentifierUseCaseImpl
import com.hexagon.accounts.domain.usecases.CreateAccountUseCase
import com.hexagon.accounts.domain.usecases.CreatePersonUseCase
import com.hexagon.accounts.domain.usecases.GetAccountByUniqueIdentifierUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig {
    @Bean
    fun getAccountByUniqueIdentifierUseCase(accountPort: AccountPort): GetAccountByUniqueIdentifierUseCase {
        return GetAccountByUniqueIdentifierUseCaseImpl(accountPort)
    }

    @Bean
    fun createPersonUseCase(personPort: PersonPort): CreatePersonUseCase {
        return CreatePersonUseCaseImpl(personPort, 18)
    }

    @Bean
    fun createAccountUseCase(personPort: PersonPort, accountPort: AccountPort): CreateAccountUseCase {
        return CreateAccountUseCaseImpl(personPort, accountPort)
    }
}