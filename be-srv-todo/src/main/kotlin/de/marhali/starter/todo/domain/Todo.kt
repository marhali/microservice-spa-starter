package de.marhali.starter.todo.domain

import java.util.UUID

data class Todo(
	var id: UUID,
	var title: String,
	var completed: Boolean,
)
