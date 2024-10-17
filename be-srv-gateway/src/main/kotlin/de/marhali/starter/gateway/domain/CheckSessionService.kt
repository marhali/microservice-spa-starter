package de.marhali.starter.gateway.domain

import reactor.core.publisher.Mono

interface CheckSessionService {
	fun getCheckSession(): Mono<CheckSession>
}
