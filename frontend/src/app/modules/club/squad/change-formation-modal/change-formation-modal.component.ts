import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { FootballerClub, Formation } from '../../club.query';
import { ClubService } from '../../club.service';
import { SnackbarService } from '../../../../snackbar.service';

@Component({
  selector: 'app-change-formation-modal',
  templateUrl: './change-formation-modal.component.html',
  styleUrl: './change-formation-modal.component.css',
})
export class ChangeFormationModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { formation: Formation },
    public dialogRef: MatDialogRef<ChangeFormationModalComponent>,
    private clubService: ClubService,
    private snackbarService: SnackbarService
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
        this.snackbarService.openWarnSnackBar('Nie można załadować formacji');
      }
    );

    console.log(this.data.formation);
  }

  changeFormation() {
    this.clubService.changeFormation(this.data.formation.code).subscribe(
      (result) => {
        this.dialogRef.close();
        this.snackbarService.openSuccessSnackBar('formacja zmieniona');
      },
      (error) => console.log(error.error)
    );
  }
}
