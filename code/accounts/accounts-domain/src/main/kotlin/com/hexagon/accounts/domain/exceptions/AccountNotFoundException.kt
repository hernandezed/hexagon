package com.hexagon.accounts.domain.exceptions

class AccountNotFoundException(uniqueIdentifier: String): RuntimeException("""Account with unique identifier $uniqueIdentifier not exists""")
