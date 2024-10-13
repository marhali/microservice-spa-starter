package de.marhali.starter.todo.domain

import java.util.UUID

interface TodoService {
	fun getTodos(userId: String): Set<Todo>
	fun getTodoById(userId: String, todoId: UUID): Todo?
	fun addTodo(userId: String, todo: Todo)
	fun removeTodoById(userId: String, todoId: UUID)
	fun updateTodo(userId: String, todo: Todo)
}
