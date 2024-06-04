import { Component, Inject } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';

import { error } from 'console';
import { FootballerClub, Position } from '../../club.query';
import { ClubService } from '../../club.service';
import { FootballerChangePositionModalComponent } from '../footballer-change-position-modal/footballer-change-position-modal.component';
import { SnackbarService } from '../../../../snackbar.service';

@Component({
  selector: 'app-footballer-details-modal',
  templateUrl: './footballer-details-modal.component.html',
  styleUrl: './footballer-details-modal.component.css',
})
export class FootballerDetailsModalComponent {
  constructor(
    private dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: { footballer: FootballerClub },
    private clubService: ClubService,
    public dialogRef: MatDialogRef<FootballerDetailsModalComponent>,
    private snackbarService: SnackbarService
  ) {}

  getFootballerPositionsString() {
    return this.data.footballer.footballerPositions
      .filter((position: Position) => position.shortcut != 'R')
      .map((position: Position) => position.shortcut)
      .join(',');
  }

  sellFootballer() {
    this.clubService.sellFootballer(this.data.footballer).subscribe(
      (result) =>
        this.snackbarService.openSuccessSnackBar(
          result.name + ' ' + result.surname + 'został sprzedany!'
        ),

      (error) => this.snackbarService.openWarnSnackBar(error.error)
    );
    this.dialogRef.close();
  }

  openChangePosition(footballer: FootballerClub): void {
    const dialogRef = this.dialog.open(FootballerChangePositionModalComponent, {
      data: { footballer },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.dialog.closeAll();
    });
  }
}
