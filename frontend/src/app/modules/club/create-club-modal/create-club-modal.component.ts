import { Component, Inject } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import {
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { DialogData } from '../../home/home.component';
import { ClubService } from '../club.service';
import { AuthService } from '../../auth/auth.service';
import { Formation } from '../club.query';
import { SnackbarService } from '../../../snackbar.service';

class ClubReq {
  name: string;
  formation: string;
}

@Component({
  selector: 'app-create-club-modal',
  templateUrl: './create-club-modal.component.html',
  styleUrl: './create-club-modal.component.css',
})
export class CreateClubModalComponent {
  constructor(
    private _clubService: ClubService,
    public dialogRef: MatDialogRef<CreateClubModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private snackbarService: SnackbarService
  ) {}

  clubReq: ClubReq = { name: '', formation: '' };
  formations: Formation[];
  ngOnInit(): void {
    this._clubService.getFormations().subscribe(
      (result) => {
        this.formations = result;
      },
      (error) => {
        this.snackbarService.openWarnSnackBar('Nie można aktualnie pobrać formacji');
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  createClub(): void {
    this._clubService.addClub(this.clubReq);
    this.dialogRef.close();
  }
}
