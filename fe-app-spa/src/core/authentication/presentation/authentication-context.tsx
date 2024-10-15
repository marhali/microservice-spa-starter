import { createContext } from 'react';
import { AuthenticationState } from '@/core/authentication/domain/authentication.ts';

const AuthenticationContext = createContext<AuthenticationState | undefined>(undefined);

AuthenticationContext.displayName = 'AuthenticationContext';

export default AuthenticationContext;
