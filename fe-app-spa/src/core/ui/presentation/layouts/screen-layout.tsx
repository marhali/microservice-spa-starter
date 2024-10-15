import { PropsWithChildren } from 'react';

function ScreenLayout({ children }: Readonly<PropsWithChildren>) {
  return <div className="h-screen w-screen">{children}</div>;
}

export default ScreenLayout;
