import { Component, OnInit } from '@angular/core';
import { AuthService } from '../modules/auth/auth.service';
import { Player } from '../modules/auth/auth.context';
import { error } from 'console';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  player:Player|null;
  constructor (public authService:AuthService){
  }

  isAuthenticated():boolean {
    return this.authService.isAuthenticated();
  }
  ngOnInit(): void {

    if(this.isAuthenticated()) {
      this.authService.getUser().subscribe((user:Player)=>this.player=user);
    }
  
 }

}
