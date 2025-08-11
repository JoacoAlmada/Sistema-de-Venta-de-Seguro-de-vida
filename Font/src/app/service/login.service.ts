import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiConfig} from './configs-api';
import {LoginDTO} from '../interfaces/loginDTO';
import {TokenDTO} from '../interfaces/TokenDTO';

@Injectable({providedIn: 'root'})
export class LoginService {
  constructor(private http: HttpClient) {}

  login(data: LoginDTO): Observable<TokenDTO> {
    return this.http.post<TokenDTO>(ApiConfig.LOGIN, data);
  }
}
