import { Injectable } from '@angular/core';
import { jwtDecode }from 'jwt-decode';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private TOKEN_KEY = 'authToken';

  saveToken(token: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  getRole(): string | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const payload: any = jwtDecode(token);
      return payload.rol || payload.role || null;
    } catch {
      return null;
    }
  }

  getIdentificacion(): string | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const payload: any = jwtDecode(token);
      return payload.sub || null;
    } catch {
      return null;
    }
  }

  getNombreCompleto(): string | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      const payload: any = jwtDecode(token);
      return payload.nombre_completo || null;
    } catch {
      return null;
    }
  }
}
