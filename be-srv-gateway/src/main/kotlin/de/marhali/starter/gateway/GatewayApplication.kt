package de.marhali.starter.gateway

import de.marhali.starter.core.runBeforeApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatewayApplication

fun main(args: Array<String>) {
	runBeforeApplication()
	runApplication<GatewayApplication>(*args)
}
