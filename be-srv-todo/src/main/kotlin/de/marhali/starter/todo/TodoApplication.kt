package de.marhali.starter.todo

import de.marhali.starter.core.runBeforeApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoApplication

fun main(args: Array<String>) {
	runBeforeApplication()
	runApplication<TodoApplication>(*args)
}
