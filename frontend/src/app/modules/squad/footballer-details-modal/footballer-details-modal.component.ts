import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FootballerClub, Position } from '../squad.query';
import { SquadService } from '../squad.service';
import { error } from 'console';

@Component({
  selector: 'app-footballer-details-modal',
  templateUrl: './footballer-details-modal.component.html',
  styleUrl: './footballer-details-modal.component.css',
})
export class FootballerDetailsModalComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { footballer: FootballerClub },
    private squadService: SquadService,
    public dialogRef: MatDialogRef<FootballerDetailsModalComponent>
  ) {}

  getFootballerPositionsString() {
    return this.data.footballer.footballerPositions
      .filter((position: Position) => position.shortcut != 'R')
      .map((position: Position) => position.shortcut)
      .join(',');
  }

  sellFootballer() {
    this.squadService.sellFootballer(this.data.footballer).subscribe(
      (result) =>
        alert(result.name + ' ' + result.surname + 'został sprzedany!'),
      (error) => alert(error.error)
    );
    this.dialogRef.close();
  }
}
