import { Component, OnInit } from '@angular/core';
import { ChangeFootballerModalComponent } from './change-formation-modal/change-formation-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { FootballerDetailsModalComponent } from './footballer-details-modal/footballer-details-modal.component';

import { error, time } from 'console';
import { timer } from 'rxjs';
import { ClubInformation, FootballerClub, Position } from '../club.query';
import { ClubService } from '../club.service';
import { AuthService } from '../../auth/auth.service';

class emptyFootballer {
  name: string;
  constructor() {
    this.name = 'NO FOOTBALLER';
  }
}

@Component({
  selector: 'app-squad',
  templateUrl: './squad.component.html',
  styleUrl: './squad.component.css',
})
export class SquadComponent implements OnInit {
  constructor(
    public dialog: MatDialog,
    private clubService: ClubService,
    private authService: AuthService
  ) {}
  loadingData: boolean = true;

  footballers: FootballerClub[];

  clubInfo: ClubInformation = null;

  openChangeFormation(): void {
    const dialogRef = this.dialog.open(ChangeFootballerModalComponent, {
      panelClass: 'custom-dialog',
      data: { formation: this.clubInfo.formation },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadFootballers();
      this.loadClubInformation();

      console.log('The dialog was closed');
    });
  }

  openFootballerDetails(footballer: FootballerClub): void {
    const dialogRef = this.dialog.open(FootballerDetailsModalComponent, {
      width: '500px', // Dostosuj szerokość dialogu według potrzeb
      height: '600px',

      data: { footballer },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.loadFootballers();
      this.loadClubInformation();
    });
  }

  loadFootballers() {
    this.loadingData = true;
    this.clubService.getClubFootballers().subscribe(
      (result) => {
        this.footballers = result;

        this.loadingData = false;
      },
      (error) => console.log(error)
    );
  }

  footballerOnReserve() {
    return this.footballers.filter(
      (footballer) => footballer.activePosition.shortcut == 'R'
    );
  }

  footballerInSquad() {
    return this.footballers.filter(
      (footballer) => footballer.activePosition.shortcut !== 'R'
    );
  }

  getFootballerPositionsString(footballer: FootballerClub) {
    return footballer.footballerPositions
      .filter((position: Position) => position.shortcut != 'R')
      .map((position: Position) => position.shortcut)
      .join(',');
  }
  loadClubInformation() {
    this.clubService.getClubInformation().subscribe(
      (result) => (this.clubInfo = result),
      (error) => console.log('error')
    );
  }

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.loadFootballers();
      this.loadClubInformation();
    }
  }
}
