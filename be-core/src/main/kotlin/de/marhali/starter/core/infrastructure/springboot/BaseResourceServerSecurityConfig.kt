package de.marhali.starter.core.infrastructure.springboot

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
abstract class BaseResourceServerSecurityConfig {
	@Bean
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
		return http
			.authorizeHttpRequests { authorize ->
				authorize.anyRequest().authenticated()
			}
			.oauth2ResourceServer { oauth2 ->
				oauth2.jwt(Customizer.withDefaults())
			}
			.build()
	}
}
