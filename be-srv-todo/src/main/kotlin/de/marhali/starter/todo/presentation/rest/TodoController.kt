package de.marhali.starter.todo.presentation.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController {
	@GetMapping
	fun listTodos(): String {
		return "helloWorld"
	}
}
