package com.hexagon.accounts.event.api.contract

import java.time.LocalDateTime

class Event<T>(val id: String, val date: LocalDateTime, val content: T) {
}