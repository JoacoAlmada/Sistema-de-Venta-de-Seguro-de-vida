
export class ApiConfig {
  static readonly API_BASE = 'http://localhost:8080';

  static readonly ADMIN = `${ApiConfig.API_BASE}/api/v1/admin`;
  static readonly CUENTA = `${ApiConfig.API_BASE}/api/v1/cuenta`;
  static readonly LOGIN = `${ApiConfig.API_BASE}/api/v1/cuenta/login`;
  static readonly USUARIO = `${ApiConfig.API_BASE}/api/v1/usuario`;
  static readonly USUARIOCREATE = `${ApiConfig.API_BASE}/api/v1/create/usuario`;



}
