import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CuentaDTO} from '../interfaces/CuentaDTO';
import {Observable} from 'rxjs';
import {ApiConfig} from './configs-api';

@Injectable({providedIn: 'root'})
export class CuentaService {
  constructor(private  http: HttpClient) {}

  getCuentas():  Observable<CuentaDTO[]> {
    return this.http.get<CuentaDTO[]>(ApiConfig.CUENTA);
  }
}
