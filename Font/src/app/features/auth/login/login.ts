import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {LoginService} from '../../../service/login.service';
import {AuthService} from '../../../service/jwt.service';
import {Router, RouterLink} from '@angular/router';
import {jwtDecode} from 'jwt-decode';



@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {

  loginData = {
    identificacion: '',
    contrasenia: '',
    rol: ''
  };

  constructor(
    private loginService: LoginService,
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    const form = document.querySelector('.needs-validation') as HTMLFormElement;

    if (!form.checkValidity()) {
      form.classList.add('was-validated');
      return;
    }

    if (this.loginData.identificacion && this.loginData.contrasenia) {
      this.loginService.login(this.loginData).subscribe({
        next: (response) => {
          this.authService.saveToken(response.token);

          const decodedToken: any = jwtDecode(response.token);
          const role = decodedToken.role || decodedToken.rol; // según como venga el claim en tu token

          localStorage.setItem('rol', role);

          if (role === 'Administrador') {
            this.router.navigate(['/home-admin']);
          } else if (role === 'Usuario') {
            this.router.navigate(['/home-user']);
          } else {
            console.warn('Rol desconocido:', role);
          }
        },
        error: (err) => {
          console.error('Login fallido', err);
          alert('Credenciales incorrectas');
        }
      });
    } else {
      alert('Por favor completa todos los campos');
    }
  }

}
