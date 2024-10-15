import { PropsWithChildren, ReactNode, useSyncExternalStore } from 'react';
import { authenticationService } from '@/core/authentication/infrastructure/authentication-service-impl-bff.ts';
import AuthenticationContext from '@/core/authentication/presentation/authentication-context.tsx';

type AuthenticationProviderProperties = PropsWithChildren & {
  fallback: ReactNode;
};

function AuthenticationProvider(properties: Readonly<AuthenticationProviderProperties>) {
  const { children, fallback } = properties;

  if (!authenticationService.isInitialized()) {
    // eslint-disable-next-line @typescript-eslint/only-throw-error
    throw authenticationService.initialize();
  }

  const state = useSyncExternalStore(
    (listener) => authenticationService.subscribe(listener),
    () => authenticationService.getState(),
  );

  if (state.isAuthenticated) {
    return <AuthenticationContext.Provider value={state}>{children}</AuthenticationContext.Provider>;
  }

  return fallback;
}

export default AuthenticationProvider;
