package de.marhali.starter.gateway.presentation.rest

import de.marhali.starter.gateway.domain.CheckSession
import de.marhali.starter.gateway.domain.CheckSessionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("check-session")
class CheckSessionController(
	private val checkSessionService: CheckSessionService
) {
	@GetMapping
	fun checkSession(): Mono<CheckSession> {
		return checkSessionService.getCheckSession()
	}
}
