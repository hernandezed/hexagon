package com.hexagon.accounts.api.rest.router

import com.hexagon.accounts.api.rest.contract.dtos.request.CreateAccountRequestDto
import com.hexagon.accounts.api.rest.contract.dtos.request.CreatePersonRequestDto
import com.hexagon.accounts.api.rest.contract.dtos.request.UpdateStatusAccountRequestDto
import com.hexagon.accounts.api.rest.contract.dtos.response.AccountResponseDto
import com.hexagon.accounts.api.rest.contract.dtos.response.PersonResponseDto
import com.hexagon.accounts.api.rest.contract.mapper.*
import com.hexagon.accounts.domain.usecases.CreateAccountUseCase
import com.hexagon.accounts.domain.usecases.GetAccountByUniqueIdentifierUseCase
import com.hexagon.accounts.domain.usecases.UpdateAccountStatusUseCase
import com.hexagon.accounts.infrastructure.ports.inboud.CreateAccount
import com.hexagon.accounts.infrastructure.ports.inboud.CreatePerson
import com.hexagon.accounts.infrastructure.ports.inboud.GetAccountByUniqueIdentifier
import com.hexagon.accounts.infrastructure.ports.inboud.UpdateStatusAccount
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.*
import java.net.URI

private const val ACCOUNT_BASE_PATH = "/account"

@Configuration
class AccountRouter {

    @Bean
    fun createAccountRoute(
        createAccountUseCase: CreateAccountUseCase,
        getPersonId: AccountRequestDtoToPersonId,
        getCurrency: AccountRequestDtoToCurrency,
        accountResponseDtoMapper: AccountResponseDtoMapper
    ): RouterFunction<ServerResponse> {
        val function = CreateAccount(createAccountUseCase, getPersonId, getCurrency, accountResponseDtoMapper)
        return RouterFunctions.route().POST(ACCOUNT_BASE_PATH) {
            val request = it.body(CreateAccountRequestDto::class.java)
            val response = function.invoke(request)
            ServerResponse.created(URI.create("""$ACCOUNT_BASE_PATH/${response.id}""")).body(response)
        }.build()
    }

    @Bean
    fun getByUniqueIdentifierRoute(
        getAccountByUniqueIdentifierUseCase: GetAccountByUniqueIdentifierUseCase,
        accountResponseDtoMapper: AccountResponseDtoMapper
    ): RouterFunction<ServerResponse> {
        val function = GetAccountByUniqueIdentifier(getAccountByUniqueIdentifierUseCase, accountResponseDtoMapper)
        return RouterFunctions.route().GET("$ACCOUNT_BASE_PATH/{uniqueIdentifier}") {
            val uniqueIdentifier = it.pathVariable("uniqueIdentifier")
            val response = function.invoke(uniqueIdentifier)
            ServerResponse.ok().body(response)
        }.build()
    }

    @Bean
    fun updateAccountStatus(
        updateAccountStatusUseCase: UpdateAccountStatusUseCase,
        updateAccountStatusDtoToAccountStatus: UpdateAccountStatusDtoToAccountStatus
    ): RouterFunction<ServerResponse> {
        val function = UpdateStatusAccount(
            updateAccountStatusUseCase,
            updateAccountStatusDtoToAccountStatus
        )
        return RouterFunctions.route().PATCH("""$ACCOUNT_BASE_PATH/{uniqueIdentifier}""") {
            val uniqueIdentifier = it.pathVariable("uniqueIdentifier")
            function.invoke(uniqueIdentifier, it.body(UpdateStatusAccountRequestDto::class.java))
            ServerResponse.noContent().build()
        }.build()
    }
}