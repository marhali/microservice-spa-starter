import { PropsWithChildren, Suspense } from 'react';
import AppHeader from '@/core/ui/presentation/components/app-header.tsx';
import LoadingSpinner from '@/core/ui/presentation/components/loading-spinner.tsx';
import ErrorBoundary from '@/core/ui/presentation/components/error-boundary.tsx';

function AppLayout({ children }: Readonly<PropsWithChildren>) {
  return (
    <div className="flex h-screen w-screen flex-col">
      <AppHeader />
      <main className="grow">
        <ErrorBoundary>
          <Suspense fallback={<LoadingSpinner />}>{children}</Suspense>
        </ErrorBoundary>
      </main>
    </div>
  );
}

export default AppLayout;
