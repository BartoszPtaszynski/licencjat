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
import { FriendInfo, FriendResult } from './../friends.query';

import { AuthService } from '../../auth/auth.service';
import { Formation } from '../../club/club.query';
import { ClubService } from '../../club/club.service';
import { FriendsService } from '../friends.service';
import { SnackbarService } from '../../../snackbar.service';

@Component({
  selector: 'app-add-friend-modal',
  templateUrl: './add-friend-modal.component.html',
  styleUrl: './add-friend-modal.component.css',
})
export class AddFriendModalComponent {
  constructor(
    private _clubService: ClubService,
    private _friendService: FriendsService,
    public dialogRef: MatDialogRef<AddFriendModalComponent>,
    private snackbar: SnackbarService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  displayedColumns: string[] = ['username', 'clubName', 'email'];
  friendResults: FriendResult[];
  searchType: string = 'clubName';
  pattern: string;

  selectedRow: FriendResult = null;

  selectRow(row: FriendResult) {
    this.selectedRow = row;
    console.log(this.selectedRow);
  }

  getResults() {
    this._friendService
      .getFriendsResults(this.searchType, this.pattern)
      .subscribe((result) => {
        this.friendResults = result;
      });
  }

  addFriend() {
    this._friendService.addFriend(this.selectedRow?.id).subscribe(
      (response) => {
        this.snackbar.openSuccessSnackBar('dodano gracza do znajomych!');
      },
      (error) => {
        if (error.error == 'players already are friends')
          this.snackbar.openWarnSnackBar('gracz już jest w znajomych!');
      }
    );
    this.dialogRef.close();
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
