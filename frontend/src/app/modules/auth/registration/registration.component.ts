import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder } from '@angular/forms';
import { register } from 'module';
import { MatSnackBar } from '@angular/material/snack-bar';

class RegistrationReq {
  username: string;
  email: string;
  password: string;
  passwordRepeat: string;
}

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css',
})
export class RegistrationComponent implements OnInit {
  constructor(
    private _authService: AuthService,
    private _snackBar: MatSnackBar
  ) {
    this.errorSignUp = { status: false, message: '' };
  }
  registrationReq: RegistrationReq = new RegistrationReq();
  errorSignUp: { status: boolean; message: string };

  passwordIdentical(): boolean {
    return (
      this.registrationReq.password === this.registrationReq.passwordRepeat
    );
  }

  isDisabled(): boolean {
    return (
      this.passwordIdentical() &&
      this.registrationReq.email?.length > 0 &&
      this.registrationReq.username?.length > 0 &&
      this.registrationReq.password?.length > 0
    );
  }
  onSubmit() {
    this._authService.register(this.registrationReq);
  }

  ngOnInit(): void {}
}
