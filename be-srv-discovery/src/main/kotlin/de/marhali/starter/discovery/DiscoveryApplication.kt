package de.marhali.starter.discovery

import de.marhali.starter.shared.runBootstrap
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class DiscoveryApplication

fun main(args: Array<String>) {
	runBootstrap()
	runApplication<DiscoveryApplication>(*args)
}
