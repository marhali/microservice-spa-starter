import { createBrowserRouter } from 'react-router-dom';

const preRouter = createBrowserRouter([
  {
    index: true,
    lazy: () => import('@/modules/pre/presentation/pages/pre-index-page.tsx'),
  },
]);

export default preRouter;
