package de.marhali.starter.todo.presentation.rest

import de.marhali.starter.todo.domain.Todo
import de.marhali.starter.todo.domain.TodoService
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
class TodoController(val todoService: TodoService) {
	@GetMapping
	fun listTodos(auth: Authentication): Set<Todo> {
		return todoService.getTodos(auth.name)
	}

	@GetMapping("{todoId}")
	fun getTodo(auth: Authentication, @PathVariable todoId: UUID): Todo {
		return todoService.getTodoById(auth.name, todoId)
			?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find todo with id $todoId.")
	}

	@PostMapping
	fun addTodo(auth: Authentication, @RequestBody todo: Todo) {
		todoService.addTodo(auth.name, todo)
	}

	@DeleteMapping("{todoId}")
	fun removeTodo(auth: Authentication, @PathVariable todoId: UUID) {
		todoService.removeTodoById(auth.name, todoId)
	}

	@PutMapping
	fun updateTodo(auth: Authentication, @RequestBody todo: Todo) {
		todoService.updateTodo(auth.name, todo)
	}
}
