import { LoaderFunctionArgs, NavLink, useLoaderData, useNavigate } from 'react-router-dom';
import { useCallback, useState } from 'react';
import { Todo } from '@/modules/todo/domain/todo.ts';
import TodoView from '@/modules/todo/presentation/views/todo-view.tsx';
import { apiClient } from '@/core/api/infrastructure/api-client-impl-fetch.ts';
import ApiRoutes from '@/core/api/domain/api-routes.ts';

async function loader({ params }: LoaderFunctionArgs) {
  const response = await apiClient.get(ApiRoutes.TODO_ITEM(params.todoId!));

  if (!response.ok) {
    throw new Error(`Could not fetch todo (${JSON.stringify(response)})`);
  }

  return (await response.json()) as Todo;
}

function Component() {
  const todo = useLoaderData() as Awaited<ReturnType<typeof loader>>;

  const navigate = useNavigate();

  const [title, setTitle] = useState(todo.title);
  const [completed, setCompleted] = useState(todo.completed);

  const onSave = useCallback(() => {
    apiClient
      .put(ApiRoutes.TODO(), { ...todo, title, completed })
      .then(() => navigate('..'))
      .catch(console.log);
  }, [todo.id, title, completed]);

  const onDelete = useCallback(() => {
    apiClient
      .delete(ApiRoutes.TODO_ITEM(todo.id))
      .then(() => navigate('..'))
      .catch(console.log);
  }, [todo.id]);

  return (
    <div className="size-full">
      <TodoView
        id={todo.id}
        title={title}
        onTitleChange={setTitle}
        completed={completed}
        onCompletedChange={setCompleted}
      />
      <div className="flex justify-between p-8">
        <div className="flex gap-2">
          <button
            className="rounded bg-blue-500 px-4 py-2 font-bold text-white shadow hover:bg-blue-400 focus:outline-none"
            type="button"
            onClick={onSave}
          >
            Save
          </button>
          <NavLink
            to=".."
            className="rounded bg-gray-500 px-4 py-2 font-bold text-white shadow hover:bg-gray-400 focus:outline-none"
          >
            Abort
          </NavLink>
        </div>
        <button
          className="rounded bg-red-500 px-4 py-2 font-bold text-white shadow hover:bg-red-400 focus:outline-none"
          type="button"
          onClick={onDelete}
        >
          Delete
        </button>
      </div>
    </div>
  );
}

Component.loader = loader;

export { Component };
