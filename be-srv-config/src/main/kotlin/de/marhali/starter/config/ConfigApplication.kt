package de.marhali.starter.config

import de.marhali.starter.core.runBeforeApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigApplication

fun main(args: Array<String>) {
	runBeforeApplication()
	runApplication<ConfigApplication>(*args)
}
