import { Initializable } from '@/core/reactive/domain/initializable.ts';
import { Subscribable } from '@/core/reactive/domain/pub-sub.ts';
import { AuthenticationState } from '@/core/authentication/domain/authentication.ts';

export interface AuthenticationService extends Initializable, Subscribable<AuthenticationState> {
  getState: () => AuthenticationState | undefined;
}
