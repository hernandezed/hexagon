package com.hexagon.accounts.configs

import com.hexagon.accounts.domain.entities.AccountStatus
import com.hexagon.accounts.domain.usecases.UpdateAccountStatusUseCase
import com.hexagon.accounts.event.api.contract.Event
import com.hexagon.accounts.event.api.contract.UpdateStatusAccountContent
import com.hexagon.accounts.infrastructure.ports.inboud.UpdateStatusAccount
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InboundPortConfig {
    @Bean
    fun a(useCase: UpdateAccountStatusUseCase): UpdateStatusAccount<Event<UpdateStatusAccountContent>> {
        return UpdateStatusAccount(
            useCase
        ) { AccountStatus.valueOf(it.content.status) }
    }
}