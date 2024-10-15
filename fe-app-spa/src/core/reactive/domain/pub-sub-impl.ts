import { Listener, PubSub, TeardownFunction } from '@/core/reactive/domain/pub-sub.ts';

class PubSubImpl<Payload> implements PubSub<Payload> {
  private readonly listeners: Array<Listener<Payload>>;

  constructor() {
    this.listeners = [];
  }

  publish(payload: Payload): void {
    for (const listener of this.listeners) {
      listener(payload);
    }
  }

  subscribe(listener: Listener<Payload>): TeardownFunction {
    this.listeners.push(listener);
    return () => {
      const index = this.listeners.indexOf(listener);
      if (index > -1) {
        this.listeners.splice(index, 1);
      }
    };
  }
}

export default PubSubImpl;
