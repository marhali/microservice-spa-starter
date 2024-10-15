import { useCallback, useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import TodoView from '@/modules/todo/presentation/views/todo-view.tsx';
import ApiRoutes from '@/core/api/domain/api-routes.ts';
import { apiClient } from '@/core/api/infrastructure/api-client-impl-fetch.ts';

function Component() {
  const navigate = useNavigate();

  const [id] = useState(crypto.randomUUID());
  const [title, setTitle] = useState('');
  const [completed, setCompleted] = useState(false);

  const onCreate = useCallback(() => {
    apiClient
      .post(ApiRoutes.TODO(), { id, title, completed })
      .then(() => navigate('..'))
      .catch(console.log);
  }, [id, title, completed, navigate]);

  return (
    <div className="size-full">
      <TodoView id={id} title={title} onTitleChange={setTitle} completed={completed} onCompletedChange={setCompleted} />
      <div className="flex justify-between p-8">
        <div className="flex gap-2">
          <button
            className="rounded bg-blue-500 px-4 py-2 font-bold text-white shadow hover:bg-blue-400 focus:outline-none"
            type="button"
            onClick={onCreate}
          >
            Create
          </button>
          <NavLink
            to=".."
            className="rounded bg-gray-500 px-4 py-2 font-bold text-white shadow hover:bg-gray-400 focus:outline-none"
          >
            Abort
          </NavLink>
        </div>
      </div>
    </div>
  );
}

export { Component };
