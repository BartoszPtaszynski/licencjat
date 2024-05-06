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

import { AuthService } from '../../auth/auth.service';
import { Formation } from '../../club/club.query';
import { ClubService } from '../../club/club.service';

export interface PeriodicElement {
  username: string;
  nazwaKlubu: string;
  email: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {
    username: 'Jan',
    nazwaKlubu: 'klub1',
    email: 'jan@gmail.com',
  },
  {
    username: 'test2',
    nazwaKlubu: 'test',
    email: 'testtest@gmail.com',
  },
];

@Component({
  selector: 'app-add-friend-modal',
  templateUrl: './add-friend-modal.component.html',
  styleUrl: './add-friend-modal.component.css',
})
export class AddFriendModalComponent {
  constructor(
    private _clubService: ClubService,
    public dialogRef: MatDialogRef<AddFriendModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  displayedColumns: string[] = ['username', 'nazwaKlubu', 'email'];
  dataSource = ELEMENT_DATA;
  selectedOption: string = 'username';
  pattern: string = '';

  selectedRow: any;

  selectRow(row: any) {
    this.selectedRow = row;
  }

  ngOnInit(): void {
    this._clubService.getFormations().subscribe(
      (result) => {},
      (error) => {
        alert('Unable to load formations');
      }
    );
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
