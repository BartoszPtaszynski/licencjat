import { Component, OnInit } from '@angular/core';
import { AddFriendModalComponent } from './add-friend-modal/add-friend-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { FriendInfo } from './friends.query';
import { FriendsService } from './friends.service';
import { AuthService } from '../auth/auth.service';
import { Location } from '@angular/common';
import { SnackbarService } from '../../snackbar.service';
import { error } from 'console';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrl: './friends.component.css',
})
export class FriendsComponent implements OnInit {
  friendsData: FriendInfo[];

  isLoading: boolean = true;
  constructor(
    public dialog: MatDialog,
    private friendsService: FriendsService,
    private location: Location,
    private authService: AuthService,
    private snackBar: SnackbarService
  ) {}

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.loadFriends();
      this.isLoading = false;
    }
  }

  displayedColumns: string[] = [
    'position',
    'username',
    'clubName',
    'results',
    'league',
    'clubRating',
    'delete',
  ];

  loadFriends() {
    this.friendsService.getFriendsInfo().subscribe(
      (result) => {
        this.friendsData = result;
        this.isLoading = false;
      },
      (error) => {}
    );
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(AddFriendModalComponent, {
      panelClass: 'custom-dialog',
      data: { name: 'this.player.username' },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.isLoading = true;
      this.friendsService.getFriendsInfo().subscribe(
        (result) => {
          this.friendsData = result;
          this.isLoading = false;
        },
        (error) => {}
      );
      console.log('The dialog was closed');
    });
  }

  goBack(): void {
    this.location.back();
  }

  removeFriend(i: number) {
    this.friendsService.deleteFriend(this.friendsData[i].id).subscribe(
      (result) => {
        this.loadFriends();
        this.snackBar.openSuccessSnackBar(
          'Usunięto ze znajomych gracza ' + result + '!'
        );
      },
      (error) => this.snackBar.openWarnSnackBar(error.error)
    );
  }
}
