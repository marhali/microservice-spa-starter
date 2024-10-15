import { createBrowserRouter, Outlet } from 'react-router-dom';
import ErrorRoute from '@/core/ui/presentation/components/error-route.tsx';
import todoRoutes from '@/modules/todo/presentation/todo-routes.tsx';

const router = createBrowserRouter([
  {
    lazy: () => import('@/core/ui/presentation/pages/app-page.tsx'),
    errorElement: <ErrorRoute />,
    children: [
      {
        index: true,
        element: <Outlet />,
      },
      {
        path: 'todo',
        children: todoRoutes,
      },
    ],
  },
]);

export default router;
