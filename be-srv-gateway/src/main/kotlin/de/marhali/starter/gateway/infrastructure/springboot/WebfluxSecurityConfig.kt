package de.marhali.starter.gateway.infrastructure.springboot

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository
import org.springframework.security.web.server.csrf.CsrfToken
import org.springframework.web.server.WebFilter
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class WebfluxSecurityConfig(
	private val clientRegistrationRepository: ReactiveClientRegistrationRepository
) {
	@Bean
	fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
		val cookieServerCsrfTokenRepository = CookieServerCsrfTokenRepository.withHttpOnlyFalse()
		cookieServerCsrfTokenRepository.setCookiePath("/")

		http
			.authorizeExchange { authorize ->
				authorize.anyExchange().authenticated()
			}
			.csrf { csrf ->
				csrf.csrfTokenRepository(cookieServerCsrfTokenRepository)
				csrf.csrfTokenRequestHandler(SpaServerCsrfTokenRequestHandler())
			}
			.oauth2Login { oauth2 ->
				// https://github.com/spring-projects/spring-security/issues/8967
				oauth2.authenticationSuccessHandler(RedirectServerAuthenticationSuccessHandler("/"))
			}
			.logout { logout ->
				logout.logoutSuccessHandler(oidcLogoutSuccessHandler())
			}

		return http.build()
	}

	@Bean
	fun csrfCookieWebFilter(): WebFilter {
		return WebFilter { exchange, chain ->
			exchange.getAttributeOrDefault<Mono<Any>>(CsrfToken::class.java.getName(), Mono.empty()).subscribe()
			chain.filter(exchange)
		}
	}

	private fun oidcLogoutSuccessHandler(): ServerLogoutSuccessHandler {
		val oidcLogoutSuccessHandler =
			OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository)

		// Sets the location that the End-User's User Agent will be redirected to
		// after the logout has been performed at the Provider
		oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/..")

		return oidcLogoutSuccessHandler
	}
}
