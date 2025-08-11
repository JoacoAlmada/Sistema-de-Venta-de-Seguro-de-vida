import { Component } from '@angular/core';
import {NgForOf, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-seguros',
  imports: [
    NgIf,
    RouterLink,
    NgForOf
  ],
  templateUrl: './seguros.html',
  styleUrl: './seguros.css'
})
export class Seguros {
  seguros = [
    { nombre: 'Vida Basico' },
    { nombre: 'Vida Premium' },
    { nombre: 'Vida Estándar' },
  ];

  verDetalles(seguro: any) {
    alert(`Detalles del seguro: ${seguro.nombre}`);
  }

}
