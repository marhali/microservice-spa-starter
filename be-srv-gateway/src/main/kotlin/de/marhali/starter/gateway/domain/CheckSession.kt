package de.marhali.starter.gateway.domain

import java.time.Instant

data class CheckSession(
	val userId: String,
	val username: String,
	val roles: Set<String>,
	val iat: Instant,
	val exp: Instant,
)
