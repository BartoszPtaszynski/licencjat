import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { ClubService } from './club.service';
import { error } from 'console';
import { Router } from '@angular/router';
import { SnackbarService } from '../../snackbar.service';
import { map } from 'rxjs';

class ClubInfo {
  name: string;
  league: number;
  rating: number;
  value: number;
  funds: number;
}

@Component({
  selector: 'app-club',
  templateUrl: './club.component.html',
  styleUrl: './club.component.css',
})
export class ClubComponent implements OnInit {
  constructor(
    public authService: AuthService,
    public clubService: ClubService,
    private router: Router,
    private snackbarService: SnackbarService
  ) {}
  clubInfo: ClubInfo = null;

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.clubService
        .getClubInformation()
        .subscribe((result) => (this.clubInfo = result));
    }
  }

  playMatch() {
    this.clubService.areAllFootballersInSquad().subscribe((result) => {
      if (result === true) {
        this.router.navigate(['/match']);
      } else {
        this.snackbarService.openWarnSnackBar(
          'Nie masz wszystkich zawodników w składzie! Przejdź do składu aby uzupełnić pozycje'
        );
      }
    });
  }
}
