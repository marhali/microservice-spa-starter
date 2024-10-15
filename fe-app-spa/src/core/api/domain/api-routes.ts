const apiRoutes = {
  TODO: () => '/api/todo',
  TODO_ITEM: (todoId: string) => `/api/todo/${todoId}`,
};

export default apiRoutes;
