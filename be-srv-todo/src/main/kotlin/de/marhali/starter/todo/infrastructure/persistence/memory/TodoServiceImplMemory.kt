package de.marhali.starter.todo.infrastructure.persistence.memory

import de.marhali.starter.todo.domain.Todo
import de.marhali.starter.todo.domain.TodoService
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoServiceImplMemory : TodoService {
	private val todos = mutableMapOf<String, Set<Todo>>()

	override fun getTodos(userId: String): Set<Todo> {
		return todos.getOrDefault(userId, setOf());
	}

	override fun getTodoById(userId: String, todoId: UUID): Todo? {
		return todos[userId]?.find { todo -> todo.id == todoId }
	}

	override fun addTodo(userId: String, todo: Todo) {
		todos[userId] = getTodos(userId).plus(todo);
	}

	override fun removeTodoById(userId: String, todoId: UUID) {
		todos[userId] = getTodos(userId).filter { todo -> todo.id != todoId }.toSet()
	}

	override fun updateTodo(userId: String, todo: Todo) {
		val targetTodo = getTodoById(userId, todo.id)

		if(targetTodo != null) {
			targetTodo.title = todo.title
			targetTodo.completed = todo.completed
		} else {
			addTodo(userId, todo)
		}
	}
}
