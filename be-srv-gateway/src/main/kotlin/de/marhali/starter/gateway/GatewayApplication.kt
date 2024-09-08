package de.marhali.starter.gateway

import de.marhali.starter.core.runBeforeApplication
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = Info(
	title = "Central API gateway service",
	description = "Central API endpoint and authentication handler for frontend facing applications"
))
class GatewayApplication

fun main(args: Array<String>) {
	runBeforeApplication()
	runApplication<GatewayApplication>(*args)
}
