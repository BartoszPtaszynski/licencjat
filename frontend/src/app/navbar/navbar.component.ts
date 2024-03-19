import { Component } from '@angular/core';
import { AuthService } from '../modules/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor (public authService:AuthService){
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }
}
