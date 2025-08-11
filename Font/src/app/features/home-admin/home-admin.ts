import {Component, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {UsuarioService} from '../../service/usuario.service';
import {DecimalPipe} from '@angular/common';

@Component({
  selector: 'app-home-admin',
  imports: [
    RouterLink,
    DecimalPipe
  ],
  templateUrl: './home-admin.html',
  styleUrl: './home-admin.css'
})
export class HomeAdmin implements OnInit {
  usuariosCantidad : number = 0;

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit() {
    this.usuarioService.getUsuariosCantidad().subscribe({
      next: data => {
        this.usuariosCantidad = data;
      }
    })
  }

}
