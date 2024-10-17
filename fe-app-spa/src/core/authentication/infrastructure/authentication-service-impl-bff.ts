import { AuthenticationService } from '@/core/authentication/domain/authentication-service.ts';
import { AuthenticationState } from '@/core/authentication/domain/authentication.ts';
import { Listener, PubSub, TeardownFunction } from '@/core/reactive/domain/pub-sub.ts';
import PubSubImpl from '@/core/reactive/domain/pub-sub-impl.ts';
import { apiClient } from '@/core/api/infrastructure/api-client-impl-fetch.ts';
import ApiRoutes from '@/core/api/domain/api-routes.ts';

class AuthenticationServiceImplBff implements AuthenticationService {
  private readonly pubsub: PubSub<AuthenticationState>;
  private initialized?: boolean;
  private state?: AuthenticationState;

  constructor() {
    this.pubsub = new PubSubImpl();
  }

  getState(): AuthenticationState | undefined {
    return this.state;
  }

  async initialize(): Promise<void> {
    try {
      const response = await apiClient.get(ApiRoutes.CHECK_SESSION());
      this.state = response.ok ? ((await response.json()) as AuthenticationState) : undefined;
    } catch {
      this.state = undefined;
    } finally {
      this.initialized = true;
    }
  }

  subscribe(listener: Listener<AuthenticationState>): TeardownFunction {
    return this.pubsub.subscribe(listener);
  }

  isInitialized(): boolean {
    return !!this.initialized;
  }
}

export const authenticationService = new AuthenticationServiceImplBff();

export default AuthenticationServiceImplBff;
