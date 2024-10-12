package de.marhali.starter.gateway.presentation.rest

import io.opentelemetry.instrumentation.annotations.SpanAttribute
import io.opentelemetry.instrumentation.annotations.WithSpan
import org.slf4j.LoggerFactory
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("whoami")
class WhoamiController {
	val log = LoggerFactory.getLogger(WhoamiController::class.java)

	@GetMapping
	fun whoami(@AuthenticationPrincipal user: DefaultOidcUser): Mono<String> {
		var strinigiied = "(" + user.toString() + ")"
		log.info(strinigiied)
		log.info(doSth("helloWorld"))
		return Mono.just(extractUser(user))
	}

	@WithSpan
	fun doSth(@SpanAttribute input: String): String {
		return input.capitalize()
	}

	@WithSpan
	fun extractUser(user: DefaultOidcUser): String {
		log.info("extractUser method log")
		return user.toString()
	}
}
