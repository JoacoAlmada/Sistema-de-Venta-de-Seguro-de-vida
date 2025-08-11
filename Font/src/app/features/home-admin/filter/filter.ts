import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {UsuarioFiltro} from '../../../interfaces/UsuarioDTO';

@Component({
  selector: 'app-filter',
  imports: [
    FormsModule
  ],
  templateUrl: './filter.html',
  styleUrl: './filter.css'
})
export class Filter {
  filtro: UsuarioFiltro = {};

  @Output() filtroAplicado = new EventEmitter<UsuarioFiltro>();

  aplicarFiltro() {
    this.filtroAplicado.emit(this.filtro);
  }

  limpiarFiltro() {
    this.filtro = {};
    this.filtroAplicado.emit(this.filtro);
  }
}
