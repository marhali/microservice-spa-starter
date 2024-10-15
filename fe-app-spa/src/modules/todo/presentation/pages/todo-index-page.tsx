import { Link, useLoaderData } from 'react-router-dom';
import ApiRoutes from '@/core/api/domain/api-routes.ts';
import { Todo } from '@/modules/todo/domain/todo.ts';
import TodoListView from '@/modules/todo/presentation/views/todo-list-view.tsx';
import { apiClient } from '@/core/api/infrastructure/api-client-impl-fetch.ts';

async function loader(): Promise<Todo[]> {
  const response = await apiClient.get(ApiRoutes.TODO());

  if (!response.ok) {
    throw new Error(`Could not fetch todos (${JSON.stringify(response)})`);
  }

  return (await response.json()) as Todo[];
}

function Component() {
  const todos = useLoaderData() as Awaited<ReturnType<typeof loader>>;

  return (
    <div className="size-full">
      <TodoListView todos={todos} />
      <div className="p-8">
        <Link
          to="create"
          className="rounded bg-blue-500 px-4 py-2 font-bold text-white shadow hover:bg-blue-400 focus:outline-none"
        >
          New ToDo
        </Link>
      </div>
    </div>
  );
}

Component.loader = loader;

export { Component };
