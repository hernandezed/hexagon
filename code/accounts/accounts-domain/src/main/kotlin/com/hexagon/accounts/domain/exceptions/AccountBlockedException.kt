package com.hexagon.accounts.domain.exceptions

class AccountBlockedException(accountUniqueIdentifier: String): RuntimeException("""The account $accountUniqueIdentifier is blocked.""") {
}