package de.marhali.starter.auth

import de.marhali.starter.shared.runBootstrap
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = Info(
	title = "Authentication and Authorization Service API",
	description = "OAuth2.0 based authentication & authorization service"
))
class AuthApplication

fun main(args: Array<String>) {
	runBootstrap()
	runApplication<AuthApplication>(*args)
}
