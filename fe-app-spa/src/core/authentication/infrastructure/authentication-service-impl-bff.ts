import { AuthenticationService } from '@/core/authentication/domain/authentication-service.ts';
import { AuthenticationState } from '@/core/authentication/domain/authentication.ts';
import { Listener, PubSub, TeardownFunction } from '@/core/reactive/domain/pub-sub.ts';
import PubSubImpl from '@/core/reactive/domain/pub-sub-impl.ts';

class AuthenticationServiceImplBff implements AuthenticationService {
  private readonly pubsub: PubSub<AuthenticationState>;
  private state?: AuthenticationState;

  constructor() {
    this.pubsub = new PubSubImpl();
  }

  getState(): AuthenticationState {
    return this.getOrThrowState();
  }

  initialize(): Promise<void> {
    const decodedCookies = decodeURIComponent(document.cookie).split(';');

    const isAuthenticatedCookie = decodedCookies.find((cook) => cook.trim().startsWith('isAuthenticated='));
    const isAuthenticatedCookieValue = isAuthenticatedCookie?.split('=')?.[1];
    const isAuthenticated = isAuthenticatedCookieValue ? Boolean(JSON.parse(isAuthenticatedCookieValue)) : false;

    this.state = {
      isAuthenticated,
    };

    return Promise.resolve();
  }

  subscribe(listener: Listener<AuthenticationState>): TeardownFunction {
    return this.pubsub.subscribe(listener);
  }

  isInitialized(): boolean {
    return !!this.state;
  }

  private getOrThrowState(): AuthenticationState {
    if (!this.state) {
      throw new Error('AuthenticationService is not initialized');
    }

    return this.state;
  }
}

export const authenticationService = new AuthenticationServiceImplBff();

export default AuthenticationServiceImplBff;
