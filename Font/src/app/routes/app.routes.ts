import { Routes } from '@angular/router';
import {Home} from '../features/home/home';
import {Login} from '../features/auth/login/login';
import {HomeAdmin} from '../features/home-admin/home-admin';
import {HomeUser} from '../features/home-user/home-user';
import {AdminGuard} from '../service/adminGuard';
import {AuthGuard} from '../service/authGuard';
import {Crud} from '../features/home-admin/crud/crud';
import {Alta} from '../features/home-admin/alta/alta';
import {Servicios} from '../features/home/servicios/servicios';
import {Contacto} from '../features/home/contacto/contacto';
import {HomeUserDatos} from '../features/home-user/home-user-datos/home-user-datos';
import {Reportes} from '../features/home-admin/reportes/reportes';
import {Seguros} from '../features/home-user/seguros/seguros';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'home', component: Home },
  { path: 'login', component: Login },
  { path: 'servicios', component: Servicios },
  { path: 'contacto', component: Contacto },
  { path: 'home-admin', component: HomeAdmin, canActivate: [AuthGuard, AdminGuard] },
  { path: 'home-admin/crud', component: Crud, canActivate: [AuthGuard, AdminGuard] },
  { path: 'home-admin/alta', component: Alta, canActivate: [AuthGuard, AdminGuard] },
  { path: 'home-user', component: HomeUser, canActivate: [AuthGuard] },
  { path: 'home-user/home-user-datos', component: HomeUserDatos, canActivate: [AuthGuard] },
  { path: 'reportes', component: Reportes },
  { path: 'seguros', component: Seguros },
  { path: '**', redirectTo: '' }
];
