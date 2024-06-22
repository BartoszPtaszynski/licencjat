import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { error } from 'console';
import { HttpErrorResponse } from '@angular/common/http';
import { Player } from '../auth/auth.query';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Inject } from '@angular/core';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
  MatDialogTitle,
  MatDialogContent,
  MatDialogActions,
  MatDialogClose,
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CreateClubModalComponent } from '../club/create-club-modal/create-club-modal.component';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  player: Player | null;

  constructor(public authService: AuthService, public dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(CreateClubModalComponent, {
      data: { name: this.player.username },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
  ngOnInit(): void {
    if (this.isAuthenticated()) {
      this.authService.getUser().subscribe(
        (user: Player) => (this.player = user),
        (error) => console.log(error.error)
      );
    }
  }
}
