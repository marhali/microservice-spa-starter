package de.marhali.starter.todo

import de.marhali.starter.shared.runBootstrap
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoApplication

fun main(args: Array<String>) {
	runBootstrap()
	runApplication<TodoApplication>(*args)
}
