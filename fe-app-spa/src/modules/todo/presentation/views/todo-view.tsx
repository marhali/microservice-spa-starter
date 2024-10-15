import { Todo } from '@/modules/todo/domain/todo.ts';

type TodoProperties = Todo & {
  onTitleChange: (newTitle: string) => void;
  onCompletedChange: (newCompleted: boolean) => void;
};

function TodoView(properties: TodoProperties) {
  const { id, title, onTitleChange, completed, onCompletedChange } = properties;

  return (
    <form className="w-full max-w-xl p-2">
      <div className="mb-6 md:flex md:items-center">
        <div className="md:w-1/3">
          <label className="mb-1 block pr-4 font-bold text-gray-500 md:mb-0 md:text-right" htmlFor="id">
            ID
          </label>
        </div>
        <div className="md:w-2/3">
          <input
            className="w-full appearance-none rounded border-2 border-gray-200 bg-gray-200 px-4 py-2 leading-tight text-gray-700 focus:border-blue-500 focus:bg-white focus:outline-none"
            id="id"
            type="text"
            disabled
            value={id}
          />
        </div>
      </div>
      <div className="mb-6 md:flex md:items-center">
        <div className="md:w-1/3">
          <label className="mb-1 block pr-4 font-bold text-gray-500 md:mb-0 md:text-right" htmlFor="title">
            Title
          </label>
        </div>
        <div className="md:w-2/3">
          <input
            className="w-full appearance-none rounded border-2 border-gray-200 bg-gray-200 px-4 py-2 leading-tight text-gray-700 focus:border-blue-500 focus:bg-white focus:outline-none"
            id="title"
            type="text"
            value={title}
            onChange={(error) => onTitleChange(error.target.value)}
          />
        </div>
      </div>
      <div className="mb-6 md:flex md:items-center">
        <div className="md:w-1/3"></div>
        <label className="block font-bold text-gray-500 md:w-2/3">
          <input
            className="mr-2 leading-tight"
            type="checkbox"
            checked={completed}
            onChange={(error) => onCompletedChange(error.target.checked)}
          />
          <span className="text-sm">Completed</span>
        </label>
      </div>
    </form>
  );
}

export default TodoView;
