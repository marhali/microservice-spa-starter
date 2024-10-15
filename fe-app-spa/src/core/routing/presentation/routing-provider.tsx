import { RouterProvider } from 'react-router-dom';
import router from '@/core/routing/presentation/router.tsx';

function RoutingProvider() {
  return <RouterProvider router={router} />;
}

export default RoutingProvider;
