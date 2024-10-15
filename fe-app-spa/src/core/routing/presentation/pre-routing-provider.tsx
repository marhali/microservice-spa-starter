import { RouterProvider } from 'react-router-dom';
import preRouter from '@/core/routing/presentation/pre-router.ts';

function PreRoutingProvider() {
  return <RouterProvider router={preRouter} />;
}

export default PreRoutingProvider;
