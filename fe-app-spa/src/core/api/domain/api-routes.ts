const apiRoutes = {
  CHECK_SESSION: () => '/api/check-session',
  TODO: () => '/api/todo',
  TODO_ITEM: (todoId: string) => `/api/todo/${todoId}`,
};

export default apiRoutes;
