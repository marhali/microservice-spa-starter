import { Link } from 'react-router-dom';
import { Todo } from '@/modules/todo/domain/todo.ts';

type TodoListViewProperties = {
  todos: Array<Todo>;
};

function TodoListView(properties: TodoListViewProperties) {
  const { todos } = properties;

  return (
    <div className="relative overflow-x-auto">
      <table className="w-full text-left text-sm text-gray-500 rtl:text-right dark:text-gray-400">
        <thead className="bg-gray-50 text-xs uppercase text-gray-700 dark:bg-gray-700 dark:text-gray-400">
          <tr>
            <th scope="col" className="px-6 py-3">
              ID
            </th>
            <th scope="col" className="px-6 py-3">
              Title
            </th>
            <th scope="col" className="px-6 py-3">
              Completed
            </th>
          </tr>
        </thead>
        <tbody>
          {todos.map((todo) => (
            <tr key={todo.id} className="border-b bg-white dark:border-gray-700 dark:bg-gray-800">
              <th scope="row" className="whitespace-nowrap px-6 py-4 font-medium text-gray-900 dark:text-white">
                <Link to={todo.id} className="italic">
                  {todo.id}
                </Link>
              </th>
              <td className="px-6 py-4">{todo.title}</td>
              <td className="px-6 py-4">{String(todo.completed)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TodoListView;
