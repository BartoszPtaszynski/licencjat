import { Component, OnInit } from '@angular/core';
import { AddFriendModalComponent } from './add-friend-modal/add-friend-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { FriendInfo } from './friends.query';
import { FriendsService } from './friends.service';
import { AuthService } from '../auth/auth.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrl: './friends.component.css',
})
export class FriendsComponent implements OnInit {
  friendsData: FriendInfo[];

  constructor(
    public dialog: MatDialog,
    private friendsService: FriendsService,
    private location: Location,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.loadFriends();
    }
  }

  displayedColumns: string[] = [
    'position',
    'username',
    'clubName',
    'results',
    'league',
    'clubRating',
  ];

  loadFriends() {
    this.friendsService.getFriendsInfo().subscribe(
      (result) => {
        this.friendsData = result;
      },
      (error) => {}
    );
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(AddFriendModalComponent, {
      panelClass: 'custom-dialog',
      data: { name: 'this.player.username', animal: 'this.animal' },
    });

    dialogRef.afterClosed().subscribe(() => {
      this.friendsService.getFriendsInfo().subscribe(
        (result) => {
          this.friendsData = result;
        },
        (error) => {}
      );
      console.log('The dialog was closed');
    });
  }

  goBack(): void {
    this.location.back();
  }
}
