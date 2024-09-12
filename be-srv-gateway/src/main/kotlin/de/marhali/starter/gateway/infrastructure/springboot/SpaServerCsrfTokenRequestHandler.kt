package de.marhali.starter.gateway.infrastructure.springboot

import org.springframework.security.web.server.csrf.CsrfToken
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler
import org.springframework.security.web.server.csrf.XorServerCsrfTokenRequestAttributeHandler
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * CSRF token request handler for single page applications.
 * Adapted from https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-integration-javascript-spa
 * @see "https://stackoverflow.com/questions/74447118/csrf-protection-not-working-with-spring-security-6"
 */
class SpaServerCsrfTokenRequestHandler: ServerCsrfTokenRequestAttributeHandler() {
	private val delegate = XorServerCsrfTokenRequestAttributeHandler()

	override fun handle(exchange: ServerWebExchange?, csrfToken: Mono<CsrfToken>?) {
		/*
         * Always use XorCsrfTokenRequestAttributeHandler to provide BREACH protection of the CsrfToken when it is rendered in the response body.
         */
		this.delegate.handle(exchange, csrfToken)
	}

	override fun resolveCsrfTokenValue(exchange: ServerWebExchange?, csrfToken: CsrfToken?): Mono<String> {
		return if (exchange!!.request.headers.containsKey(csrfToken!!.headerName)) {
			super.resolveCsrfTokenValue(exchange, csrfToken)
		} else {
			this.delegate.resolveCsrfTokenValue(exchange, csrfToken)
		}
	}
}
