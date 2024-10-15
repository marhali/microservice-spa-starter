import ScreenLayout from '@/core/ui/presentation/layouts/screen-layout.tsx';
import CenterLayout from '@/core/ui/presentation/layouts/center-layout.tsx';

function Component() {
  return (
    <ScreenLayout>
      <CenterLayout>
        <div className="flex flex-col items-center justify-center gap-4 rounded bg-gray-800 p-8 text-white shadow-xl">
          <h1 className="text-xl font-bold">Microservice SPA Starter</h1>
          <hr />
          <a
            href="/api/oauth2/authorization/gateway"
            className="rounded bg-blue-500 px-4 py-2 font-bold text-white shadow hover:bg-blue-400 focus:outline-none"
          >
            Login
          </a>
        </div>
      </CenterLayout>
    </ScreenLayout>
  );
}

export { Component };
