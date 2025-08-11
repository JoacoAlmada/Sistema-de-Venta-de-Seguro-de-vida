import {Component, OnInit} from '@angular/core';

import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-home-user',
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './home-user.html',
  styleUrl: './home-user.css'
})
export class HomeUser  {

  constructor(private router: Router) {}

  cerrarSesion() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    this.router.navigate(['/login']);
  }
}


