import { Outlet } from 'react-router-dom';
import AppLayout from '@/core/ui/presentation/layouts/app-layout.tsx';

function Component() {
  return (
    <AppLayout>
      <Outlet />
    </AppLayout>
  );
}

export { Component };
