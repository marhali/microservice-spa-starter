import { PropsWithChildren } from 'react';

function CenterLayout({ children }: PropsWithChildren) {
  return <div className="flex size-full items-center justify-center">{children}</div>;
}

export default CenterLayout;
