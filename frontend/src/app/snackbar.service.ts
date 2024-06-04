import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class SnackbarService {
  constructor(private _snackBar: MatSnackBar) {}

  openSuccessSnackBar(message: string) {
    this._snackBar.open(message, 'X', {
      duration: 3000,
      verticalPosition: 'top',
      panelClass: 'success-snackbar',
    });
  }

  openWarnSnackBar(message: string) {
    this._snackBar.open(message, 'X', {
      duration: 3000,
      verticalPosition: 'top',
      panelClass: 'warn-snackbar',
    });
  }
}
