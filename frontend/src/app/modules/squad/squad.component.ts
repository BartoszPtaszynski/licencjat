import { Component } from '@angular/core';
import { ChangeFootballerModalComponent } from './change-footballer-modal/change-footballer-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { FootballerDetailsModalComponent } from './footballer-details-modal/footballer-details-modal.component';

@Component({
  selector: 'app-squad',
  templateUrl: './squad.component.html',
  styleUrl: './squad.component.css',
})
export class SquadComponent {
  constructor(public dialog: MatDialog) {}

  footballer() {
    console.log('cristiano');
  }

  openChangeFormation(): void {
    const dialogRef = this.dialog.open(ChangeFootballerModalComponent, {
      panelClass: 'custom-dialog',
      data: { name: 'this.player.username', animal: 'this.animal' },
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });
  }
  openFootballerDetails(): void {
    const dialogRef = this.dialog.open(FootballerDetailsModalComponent, {
      width: '500px', // Dostosuj szerokość dialogu według potrzeb
      height: '600px',

      data: { name: 'this.player.username', animal: 'this.animal' },
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });
  }
}
