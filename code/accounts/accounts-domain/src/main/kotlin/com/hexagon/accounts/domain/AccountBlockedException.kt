package com.hexagon.accounts.domain

class AccountBlockedException(accountUniqueIdentifier: String): RuntimeException("""The account $accountUniqueIdentifier is blocked.""") {
}