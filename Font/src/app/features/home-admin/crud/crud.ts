import { Component, OnInit } from '@angular/core';
import {UsuarioDTO, UsuarioFiltro} from '../../../interfaces/UsuarioDTO';
import { UsuarioService } from '../../../service/usuario.service';
import { NgForOf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Modal } from 'bootstrap';
import {Filter} from '../filter/filter';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-crud',
  imports: [
    NgForOf,
    FormsModule,
    Filter,
    RouterLink
  ],
  templateUrl: './crud.html',
  styleUrl: './crud.css'
})
export class Crud implements OnInit {
  usuarios: UsuarioDTO[] = [];
  usuarioEditando: UsuarioDTO = {} as UsuarioDTO;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit() {
    this.cargarUsuarios()
  }

  cargarUsuarios(filtro?: UsuarioFiltro) {
    if (filtro) {
      this.usuarioService.getUsuariosFiltrados(filtro).subscribe(data => {
        this.usuarios = data;
      });
    } else {
      this.usuarioService.getUsuarios().subscribe(data => {
        this.usuarios = data;
      });
    }
  }

  borrarUsuario(user: UsuarioDTO) {
    if (confirm(`¿Querés borrar al usuario ${user.nombre_completo}?`)) {
      this.usuarioService.deleteUsuario(user.id_usuario!).subscribe({
        next: () => {
          alert('Usuario borrado con exito');
          this.cargarUsuarios();
        },
        error: (err) => {
          console.error('Error al borrar usuario', err);
          alert('Error al borrar usuario');
        }
      });
    }
  }

  editarUsuario(usuario: UsuarioDTO) {
    this.usuarioEditando = { ...usuario };
    const modal = new Modal(document.getElementById('editUsuarioModal')!);
    modal.show();
  }

  guardarEdicion() {
    this.usuarioService.putUsuario(this.usuarioEditando.id_usuario!, this.usuarioEditando).subscribe(() => {
      this.cargarUsuarios();
      const modal = Modal.getInstance(document.getElementById('editUsuarioModal')!)!;
      modal.hide();
    });
  }
}
