import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {UsuarioDTO} from '../../../interfaces/UsuarioDTO';
import {UsuarioService} from '../../../service/usuario.service';
import {NgIf} from '@angular/common';
import {AltaDTO} from '../../../interfaces/AltaDTO';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-alta',
  imports: [
    FormsModule,
    NgIf,
    RouterLink
  ],
  templateUrl: './alta.html',
  styleUrl: './alta.css'
})
export class Alta {
  nuevoUsuario: AltaDTO = {
    identificacion: '',
    nombre_completo: '',
    contrasenia: '',
    edad: 0,
    seguro: '',
    email: '',
    telefono: '',
    genero: '',
    estado: 'Activo',
    maneja: false,
    usa_lentes: false,
    diabetico: false,
    otra_enfermedad: false,
    cual_enfermedad: ''
  };

  constructor(private usuarioService: UsuarioService) {}

  crearUsuario() {
    this.usuarioService.postUsuario(this.nuevoUsuario).subscribe({
      next: (usuarioCreado) => {
        alert(`Usuario ${usuarioCreado.nombre_completo} creado con exito!`);
        this.limpiarFormulario();
      },
      error: (error) => {
        console.error('Error creando usuario', error);
        alert('Ocurrio un error al crear el usuario.');
      }
    });
  }

  limpiarFormulario() {
    this.nuevoUsuario = {
      identificacion: '',
      nombre_completo: '',
      contrasenia: '',
      edad: 0,
      seguro: '',
      email: '',
      telefono: '',
      genero: '',
      estado: 'Activo',
      maneja: false,
      usa_lentes: false,
      diabetico: false,
      otra_enfermedad: false,
      cual_enfermedad: ''
    };
  }
}


