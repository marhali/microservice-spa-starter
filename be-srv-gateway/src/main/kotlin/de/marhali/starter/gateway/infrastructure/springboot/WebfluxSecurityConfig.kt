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

@Configuration
@EnableWebFluxSecurity
class WebfluxSecurityConfig(
	private val clientRegistrationRepository: ReactiveClientRegistrationRepository,
) {
	@Bean
	fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
		http
			.csrf { csrf -> csrf.disable() }
			.authorizeExchange { authorize ->
				authorize.anyExchange().authenticated()
			}
			.oauth2Login { oauth2 ->
				// https://github.com/spring-projects/spring-security/issues/8967
				oauth2.authenticationSuccessHandler(RedirectServerAuthenticationSuccessHandler("/.."))
			}
			.logout { logout ->
				logout.logoutSuccessHandler(oidcLogoutSuccessHandler())
			}

		return http.build()
	}

	private fun oidcLogoutSuccessHandler(): ServerLogoutSuccessHandler {
		val oidcLogoutSuccessHandler =
			OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository)

		// Sets the location that the End-User's User Agent will be redirected to
		// after the logout has been performed at the Provider
		// To support both backend and frontend logout we are using a workaround to reconstruct the correct origin
		oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseScheme}://{baseHost}{basePort}")

		return oidcLogoutSuccessHandler
	}
}
