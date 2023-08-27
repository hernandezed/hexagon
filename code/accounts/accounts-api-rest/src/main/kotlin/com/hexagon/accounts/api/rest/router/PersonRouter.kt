package com.hexagon.accounts.api.rest.router

import com.hexagon.accounts.api.rest.contract.dtos.request.CreatePersonRequestDto
import com.hexagon.accounts.api.rest.contract.dtos.response.PersonResponseDto
import com.hexagon.accounts.api.rest.contract.mapper.CreatePersonRequestDtoToPersonMapper
import com.hexagon.accounts.api.rest.contract.mapper.PersonToPersonResponseMapper
import com.hexagon.accounts.domain.usecases.CreatePersonUseCase
import com.hexagon.accounts.infrastructure.ports.inboud.CreatePerson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.RouterFunctions
import org.springframework.web.servlet.function.ServerResponse
import java.net.URI

private const val PERSON_BASE_PATH = "/person"

@Configuration
class PersonRouter {

    @Bean
    fun createPersonRoute(
        createPersonUseCase: CreatePersonUseCase,
        createPersonRequestDtoToPersonMapper: CreatePersonRequestDtoToPersonMapper,
        personToPersonResponseMapper: PersonToPersonResponseMapper
    ): RouterFunction<ServerResponse> {
        val function =
            CreatePerson(createPersonUseCase, createPersonRequestDtoToPersonMapper, personToPersonResponseMapper)
        return RouterFunctions.route().POST(PERSON_BASE_PATH) {
            val request = it.body(CreatePersonRequestDto::class.java)
            val response = function.invoke(request)
            ServerResponse.created(URI.create("""$PERSON_BASE_PATH/${response.id}""")).body(response)
        }.build()
    }
}