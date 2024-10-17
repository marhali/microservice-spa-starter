package de.marhali.starter.gateway.infrastructure.springboot

import de.marhali.starter.gateway.domain.CheckSession
import de.marhali.starter.gateway.domain.CheckSessionService
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CheckSessionServiceImplSpring : CheckSessionService {

	override fun getCheckSession(): Mono<CheckSession> {
		return ReactiveSecurityContextHolder.getContext().map { context ->
			val principal = context.authentication.principal as DefaultOidcUser

			println(principal)

			return@map CheckSession(
				userId = principal.name,
				username = principal.name,
				roles = principal.authorities.map { grantedAuthority -> grantedAuthority.authority }.toSet(),
				iat = principal.issuedAt,
				exp = principal.expiresAt,
			)
		}
	}
}
