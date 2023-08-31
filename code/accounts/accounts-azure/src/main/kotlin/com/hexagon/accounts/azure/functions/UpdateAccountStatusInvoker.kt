package com.hexagon.accounts.azure.functions

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.hexagon.accounts.event.api.contract.Event
import com.hexagon.accounts.event.api.contract.UpdateStatusAccountContent
import com.hexagon.accounts.infrastructure.ports.inboud.UpdateStatusAccount
import com.microsoft.azure.functions.ExecutionContext
import com.microsoft.azure.functions.HttpMethod
import com.microsoft.azure.functions.HttpRequestMessage
import com.microsoft.azure.functions.annotation.AuthorizationLevel
import com.microsoft.azure.functions.annotation.FunctionName
import com.microsoft.azure.functions.annotation.HttpTrigger
import com.microsoft.azure.functions.annotation.ServiceBusTopicTrigger
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.lang.reflect.TypeVariable

import java.util.*

@Component
class UpdateAccountStatusInvoker(
    val port: UpdateStatusAccount<Event<UpdateStatusAccountContent>>,
    val objectMapper: ObjectMapper
) {

    @FunctionName("changeStatusAccount")
    fun invoke(
        @ServiceBusTopicTrigger(
            connection = "",
            name = "req",
            subscriptionName = "",
            topicName = ""
        ) message: String,
        context: ExecutionContext
    ) {
        val typeRef: JavaType =
            objectMapper.constructType(object : TypeReference<Event<UpdateStatusAccountContent>>() {})

        val event: Event<UpdateStatusAccountContent> = objectMapper.readValue(message, typeRef)

        this.port.invoke(event.content.uniqueReference, event)
    }
}