export interface ApiClient {
  get: (url: string) => Promise<Response>;
  post: <Payload>(url: string, payload: Payload) => Promise<Response>;
  put: <Payload>(url: string, payload: Payload) => Promise<Response>;
  delete: <Payload>(url: string, payload: Payload) => Promise<Response>;
}
