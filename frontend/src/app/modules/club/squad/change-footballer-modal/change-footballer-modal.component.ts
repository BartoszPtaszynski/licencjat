import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { FootballerClub, Formation } from '../../club.query';
import { ClubService } from '../../club.service';

@Component({
  selector: 'app-change-footballer-modal',
  templateUrl: './change-footballer-modal.component.html',
  styleUrl: './change-footballer-modal.component.css',
})
export class ChangeFootballerModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { formation: Formation },
    public dialogRef: MatDialogRef<ChangeFootballerModalComponent>,
    private clubService: ClubService
  ) {}
  selectedFormation: Formation;
  formations: Formation[];
  ngOnInit(): void {
    this.selectedFormation = this.data.formation;
    this.clubService.getFormations().subscribe(
      (result) => {
        this.formations = result;
      },
      (error) => {
        alert('Unable to load formations');
      }
    );

    console.log(this.data.formation);
  }

  changeFormation() {
    this.clubService.changeFormation(this.data.formation.code).subscribe(
      (result) => {
        this.dialogRef.close();
        alert('formation changed');
      },
      (error) => console.log(error.error)
    );
  }
}
