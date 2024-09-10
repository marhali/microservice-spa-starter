package de.marhali.starter.auth.infrastructure.springboot

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher


@Configuration
@EnableWebSecurity
class WebSecurityConfig {
	@Bean
	@Order(1)
	fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)

		http.getConfigurer(OAuth2AuthorizationServerConfigurer::class.java)
			.oidc(Customizer.withDefaults()) // Enable OpenID Connect 1.0

		http
			// Redirect to the OAuth 2.0 Login endpoint when not authenticated
			// from the authorization endpoint
			.exceptionHandling { exceptions ->
				exceptions
					.defaultAuthenticationEntryPointFor(
						LoginUrlAuthenticationEntryPoint("/login"),
						MediaTypeRequestMatcher(MediaType.TEXT_HTML)
					)
			}
			// Accept access tokens for User Info and/or Client Registration
			.oauth2ResourceServer { oauth2 -> oauth2.jwt(Customizer.withDefaults()) }

		return http.build()
	}

	@Bean
	@Order(2)
	fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
		http
			.authorizeHttpRequests { authorize ->
				authorize.anyRequest().authenticated()
			}
			// OAuth2 Login handles the redirect to the OAuth 2.0 Login endpoint
			// from the authorization server filter chain
			.oauth2Login(Customizer.withDefaults())
			// Local login (classic username + password)
			.formLogin(Customizer.withDefaults())

		return http.build()
	}
}
