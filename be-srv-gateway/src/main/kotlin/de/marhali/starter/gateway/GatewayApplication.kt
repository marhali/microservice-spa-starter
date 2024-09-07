package de.marhali.starter.gateway

import de.marhali.starter.shared.runBootstrap
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatewayApplication

fun main(args: Array<String>) {
	runBootstrap()
	runApplication<GatewayApplication>(*args)
}
