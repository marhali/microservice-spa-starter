import useOrThrowContext from '@/core/ui/hooks/use-or-throw-context.ts';
import AuthenticationContext from '@/core/authentication/presentation/authentication-context.tsx';

function useAuthenticationContext() {
  return useOrThrowContext(AuthenticationContext);
}

export default useAuthenticationContext;
