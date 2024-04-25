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

class ClubReq {
  name: string;
  crest: string;
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
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  clubReq: ClubReq = { name: '', crest: '', formation: '' };
  formations: Formation[];
  ngOnInit(): void {
    this._clubService.getFormations().subscribe(
      (result) => {
        this.formations = result;
        this.formations.forEach((element) => {
          console.log(element.code);
        });
      },
      (error) => {
        alert('Unable to load formations');
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
