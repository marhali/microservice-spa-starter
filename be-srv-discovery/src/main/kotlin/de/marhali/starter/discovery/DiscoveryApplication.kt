package de.marhali.starter.discovery

import de.marhali.starter.core.runBeforeApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryApplication

fun main(args: Array<String>) {
	runBeforeApplication()
	runApplication<DiscoveryApplication>(*args)
}
