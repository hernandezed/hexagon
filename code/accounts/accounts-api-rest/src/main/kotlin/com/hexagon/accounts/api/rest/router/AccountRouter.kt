package com.hexagon.accounts.api.rest.router

import com.hexagon.accounts.api.rest.contract.dtos.request.CreateAccountRequestDto
import com.hexagon.accounts.api.rest.contract.dtos.request.CreatePersonRequestDto
import com.hexagon.accounts.api.rest.contract.dtos.response.AccountResponseDto
import com.hexagon.accounts.api.rest.contract.dtos.response.PersonResponseDto
import com.hexagon.accounts.api.rest.contract.mapper.AccountRequestDtoToCurrency
import com.hexagon.accounts.api.rest.contract.mapper.AccountRequestDtoToPersonId
import com.hexagon.accounts.api.rest.contract.mapper.AccountResponseDtoMapper
import com.hexagon.accounts.domain.usecases.CreateAccountUseCase
import com.hexagon.accounts.domain.usecases.GetAccountByUniqueIdentifierUseCase
import com.hexagon.accounts.infrastructure.ports.inboud.CreateAccount
import com.hexagon.accounts.infrastructure.ports.inboud.CreatePerson
import com.hexagon.accounts.infrastructure.ports.inboud.GetAccountByUniqueIdentifier
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
            println(function)
            val response = function.invoke(uniqueIdentifier)
            ServerResponse.ok().body(response)
        }.build()
    }
}