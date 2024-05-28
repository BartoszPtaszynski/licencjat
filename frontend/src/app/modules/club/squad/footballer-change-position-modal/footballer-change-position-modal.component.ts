import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FootballerClub, Position } from '../../club.query';
import { ClubService } from '../../club.service';
import { error } from 'node:console';

@Component({
  selector: 'app-footballer-change-position-modal',
  templateUrl: './footballer-change-position-modal.component.html',
  styleUrl: './footballer-change-position-modal.component.css',
})
export class FootballerChangePositionModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { footballer: FootballerClub },
    private clubService: ClubService,
    public dialogRef: MatDialogRef<FootballerChangePositionModalComponent>
  ) {}

  footballers: FootballerClub[];

  selectedFootballer: FootballerClub = null;

  ngOnInit(): void {
    this.getFootballersToChange();
  }

  getFootballersToChange() {
    this.clubService.getFootballersForChange(this.data.footballer.id).subscribe(
      (result) => (this.footballers = result),
      (error) => console.log(error.error)
    );
  }

  selectFootballer(footballer: FootballerClub) {
    this.selectedFootballer = footballer;
  }
  isSelectedFootballer() {
    return this.selectedFootballer !== null;
  }
  getFootballerPositionsString(footballer: FootballerClub) {
    return footballer.footballerPositions
      .filter((position: Position) => position.shortcut != 'R')
      .map((position: Position) => position.shortcut)
      .join(',');
  }

  changeFootballer() {
    return this.clubService
      .changeFootballer(this.data.footballer.id, this.selectedFootballer.id)
      .subscribe(
        (result) => {
          this.dialogRef.close();
        },
        (error) => console.log(error)
      );
  }
}
