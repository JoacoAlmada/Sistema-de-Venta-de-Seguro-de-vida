import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiConfig} from './configs-api';
import {UsuarioDTO, UsuarioFiltro} from '../interfaces/UsuarioDTO';
import {AltaDTO} from '../interfaces/AltaDTO';

@Injectable({providedIn: 'root'})
export class UsuarioService {
  constructor(private http: HttpClient) {}

  getUsuarios():  Observable<UsuarioDTO[]> {
    return this.http.get<UsuarioDTO[]>(ApiConfig.USUARIO);
  }
  getUsuariosFiltrados(filtro: UsuarioFiltro): Observable<UsuarioDTO[]> {
    return this.http.post<UsuarioDTO[]>(`${ApiConfig.API_BASE}/api/v1/usuario/filtro`, filtro);
  }
  getUsuariosPorIdentificacion(identificacion: string): Observable<UsuarioDTO> {
    return this.http.get<UsuarioDTO>(`${ApiConfig.API_BASE}/api/v1/usuario/${identificacion}`);
  }

  postUsuario(data: AltaDTO): Observable<UsuarioDTO> {
    return this.http.post<UsuarioDTO>(ApiConfig.USUARIOCREATE, data);
  }

  putUsuario(id: number, data: UsuarioDTO): Observable<UsuarioDTO> {
    return this.http.put<UsuarioDTO>(`${ApiConfig.API_BASE}/api/v1/update/${id}`, data);
  }

  deleteUsuario(id: number): Observable<void> {
    return this.http.delete<void>(`${ApiConfig.API_BASE}/api/v1/delete/${id}`);
  }
  putUsuarioEmail(id: number, data: UsuarioDTO): Observable<UsuarioDTO> {
    return this.http.put<UsuarioDTO>(`${ApiConfig.API_BASE}/api/v1/update/${id}/userE`, data);
  }
  putUsuarioContra(id: number, data: UsuarioDTO): Observable<UsuarioDTO> {
    return this.http.put<UsuarioDTO>(`${ApiConfig.API_BASE}/api/v1/update/${id}/userC`, data);
  }
  getUsuariosCantidad():  Observable<number> {
    return this.http.get<number>(`${ApiConfig.API_BASE}/api/v1/usuario/cantidad`);
  }

}

