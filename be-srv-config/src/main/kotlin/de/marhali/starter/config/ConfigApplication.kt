package de.marhali.starter.config

import co.elastic.apm.attach.ElasticApmAttacher
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigApplication

fun main(args: Array<String>) {
	ElasticApmAttacher.attach()
	runApplication<ConfigApplication>(*args)
}
