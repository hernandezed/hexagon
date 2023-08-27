package com.hexagon.accounts.infrastructure.config.db.populators

import jakarta.annotation.PostConstruct

interface Populator<T> {
    fun populate():Iterable<T>
}