import { Component, OnInit } from '@angular/core';
import { ChangeFootballerModalComponent } from './change-footballer-modal/change-footballer-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { FootballerDetailsModalComponent } from './footballer-details-modal/footballer-details-modal.component';
import { FootballerClub, Position } from './squad.query';
import { SquadService } from './squad.service';
import { error, time } from 'console';
import { timer } from 'rxjs';

@Component({
  selector: 'app-squad',
  templateUrl: './squad.component.html',
  styleUrl: './squad.component.css',
})
export class SquadComponent implements OnInit {
  constructor(public dialog: MatDialog, private service: SquadService) {}
  loadingData: boolean = true;

  footballers: FootballerClub[];

  openChangeFormation(): void {
    const dialogRef = this.dialog.open(ChangeFootballerModalComponent, {
      panelClass: 'custom-dialog',
      data: { name: 'this.player.username', animal: 'this.animal' },
    });

    dialogRef.afterClosed().subscribe(() => {
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
      console.log('The dialog was closed');
    });
  }

  loadFootballers() {
    this.service.getClubFootballers().subscribe(
      (result) => {
        // timer(3000).subscribe(() => {
        //   // Tutaj możesz umieścić kod, który ma zostać wykonany po opóźnieniu

        this.footballers = result;
        this.loadingData = false;
        // });
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

  ngOnInit(): void {
    this.loadFootballers();
  }
}
