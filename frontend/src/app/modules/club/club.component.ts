import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-club',
  templateUrl: './club.component.html',
  styleUrl: './club.component.css',
})
export class ClubComponent {
  constructor(public authService: AuthService) {}
}
