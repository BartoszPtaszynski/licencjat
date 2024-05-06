import { Component } from '@angular/core';
import { AddFriendModalComponent } from './add-friend-modal/add-friend-modal.component';
import { MatDialog } from '@angular/material/dialog';

export interface PeriodicElement {
  username: string;
  nazwaKlubu: string;
  ostatnieWyniki: string;
  position: number;
  liga: number;
  ratingSkladu: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {
    position: 1,
    username: 'Jan',
    nazwaKlubu: 'klub1',
    ostatnieWyniki: 'W W P P P',
    liga: 5,
    ratingSkladu: 44,
  },
  {
    position: 2,
    username: 'testUser',
    nazwaKlubu: 'klub 2',
    ostatnieWyniki: 'W W W W W',
    liga: 1,
    ratingSkladu: 50,
  },
];

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrl: './friends.component.css',
})
export class FriendsComponent {
  constructor(public dialog: MatDialog) {}

  displayedColumns: string[] = [
    'position',
    'username',
    'nazwaKlubu',
    'ostatnieWyniki',
    'liga',
    'ratingSkladu',
  ];
  dataSource = ELEMENT_DATA;

  openDialog(): void {
    const dialogRef = this.dialog.open(AddFriendModalComponent, {
      panelClass: 'custom-dialog',
      data: { name: 'this.player.username', animal: 'this.animal' },
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });
  }
}
