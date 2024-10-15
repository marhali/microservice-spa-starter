import { ApiClient } from '@/core/api/domain/api-client.ts';

class ApiClientImplFetch implements ApiClient {
  private static readonly DEFAULT_HEADERS = {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
  };

  get(url: string): Promise<Response> {
    return fetch(url, {
      method: 'GET',
      headers: { ...ApiClientImplFetch.DEFAULT_HEADERS },
      redirect: 'error',
    });
  }

  post<Payload>(url: string, payload?: Payload): Promise<Response> {
    return fetch(url, {
      method: 'POST',
      headers: { ...ApiClientImplFetch.DEFAULT_HEADERS, ...this.getCsrfHeaders() },
      body: payload ? JSON.stringify(payload) : undefined,
      redirect: 'error',
    });
  }

  put<Payload>(url: string, payload?: Payload): Promise<Response> {
    return fetch(url, {
      method: 'PUT',
      headers: { ...ApiClientImplFetch.DEFAULT_HEADERS, ...this.getCsrfHeaders() },
      body: payload ? JSON.stringify(payload) : undefined,
      redirect: 'error',
    });
  }

  delete<Payload>(url: string, payload?: Payload): Promise<Response> {
    return fetch(url, {
      method: 'DELETE',
      headers: { ...ApiClientImplFetch.DEFAULT_HEADERS, ...this.getCsrfHeaders() },
      body: payload ? JSON.stringify(payload) : undefined,
      redirect: 'error',
    });
  }

  private getCsrfHeaders() {
    return {
      'X-Xsrf-Token': document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'))?.[2] ?? '',
    };
  }
}

export const apiClient = new ApiClientImplFetch();

export default ApiClientImplFetch;
