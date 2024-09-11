package de.marhali.starter.gateway.presentation.rest

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("whoami")
class WhoamiController {
	@GetMapping
	fun whoami(@AuthenticationPrincipal user: DefaultOidcUser): Mono<String> {
		return Mono.just(user.toString())
	}
}
