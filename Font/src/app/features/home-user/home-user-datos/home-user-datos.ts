import {Component, OnInit} from '@angular/core';
import {NgIf} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UsuarioDTO} from '../../../interfaces/UsuarioDTO';
import {AuthService} from '../../../service/jwt.service';
import {UsuarioService} from '../../../service/usuario.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-home-user-datos',
  imports: [
    NgIf,
    ReactiveFormsModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './home-user-datos.html',
  styleUrl: './home-user-datos.css'
})
export class HomeUserDatos implements OnInit {
  nombreUsuario: string | null = null;
  usuario: UsuarioDTO | null = null;
  editandoEmail: boolean = false;
  editandoContrasenia: boolean = false;
  emailEditable: string = '';
  contraseniaEditable: string = '';

  constructor(private authService: AuthService, private usuarioService: UsuarioService) {}

  ngOnInit() {
    this.nombreUsuario = this.authService.getNombreCompleto();
    this.cargarUsuario();
  }

  cargarUsuario() {
    const id = this.authService.getIdentificacion();

    if (!id) return;
    this.usuarioService.getUsuariosPorIdentificacion(id).subscribe({
      next: (data) => {
        this.usuario = data;
        this.emailEditable = this.usuario.email;
      },
      error: (err) => {
        console.error('Error al cargar el usuario:', err);
      }
    });
  }

  activarEdicionEmail() {
    this.editandoEmail = true;
  }

  guardarEmail() {
    if (!this.usuario) return;
    const id = this.usuario.id_usuario;
    this.usuario.email = this.emailEditable;

    this.usuarioService.putUsuarioEmail(id, { ...this.usuario }).subscribe(() => {
      this.editandoEmail = false;
      this.cargarUsuario();
    });
  }

  activarEdicionContrasenia() {
    this.editandoContrasenia = true;
  }

  guardarContrasenia() {
    if (!this.usuario) return;
    const id = this.usuario.id_usuario;
    this.usuario.contrasenia = this.contraseniaEditable;

    this.usuarioService.putUsuarioContra(id, { ...this.usuario }).subscribe(() => {
      this.editandoContrasenia = false;
      this.contraseniaEditable = '';
    });
  }
}
