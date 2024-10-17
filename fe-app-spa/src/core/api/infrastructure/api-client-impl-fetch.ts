import { ApiClient } from '@/core/api/domain/api-client.ts';

const DEFAULT_HEADERS = {
  'X-Requested-With': 'XMLHttpRequest',
  'Accept': 'application/json',
};

const PAYLOAD_HEADERS = {
  'Content-Type': 'application/json',
};

class ApiClientImplFetch implements ApiClient {
  get(url: string): Promise<Response> {
    return fetch(url, {
      method: 'GET',
      headers: { ...DEFAULT_HEADERS },
      redirect: 'error',
    });
  }

  post<Payload>(url: string, payload?: Payload): Promise<Response> {
    return fetch(url, {
      method: 'POST',
      headers: { ...DEFAULT_HEADERS, ...(payload ? PAYLOAD_HEADERS : undefined) },
      body: payload ? JSON.stringify(payload) : undefined,
      redirect: 'error',
    });
  }

  put<Payload>(url: string, payload?: Payload): Promise<Response> {
    return fetch(url, {
      method: 'PUT',
      headers: { ...DEFAULT_HEADERS, ...(payload ? PAYLOAD_HEADERS : undefined) },
      body: payload ? JSON.stringify(payload) : undefined,
      redirect: 'error',
    });
  }

  delete<Payload>(url: string, payload?: Payload): Promise<Response> {
    return fetch(url, {
      method: 'DELETE',
      headers: { ...DEFAULT_HEADERS, ...(payload ? PAYLOAD_HEADERS : undefined) },
      body: payload ? JSON.stringify(payload) : undefined,
      redirect: 'error',
    });
  }
}

export const apiClient = new ApiClientImplFetch();

export default ApiClientImplFetch;
