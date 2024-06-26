import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginCommand } from './login/login.query';
import { Observable, catchError, map } from 'rxjs';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Route,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { Player } from './auth.query';
import { RegistrationCommand } from './registration/registration.query';
import { LoginComponent } from './login/login.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackbarService } from '../../snackbar.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements CanActivate {
  apiUrl = 'http://localhost:8080';

  constructor(
    private http: HttpClient,
    private router: Router,
    private _snackBar: SnackbarService
  ) {}

  login(command: LoginCommand): boolean {
    let message;
    this.http
      .post('http://localhost:8080/api/authenticate', command, {
        responseType: 'text',
      })
      .subscribe({
        next: (response) => {
          localStorage.setItem('loginID', response);

          this.router.navigate(['/']);
          message = true;
          console.log('login', response);
        },
        error: (error: string) => {
          console.log('Login failed', error);
          message = false;
        },
      });
    return message;
  }

  register(command: RegistrationCommand) {
    this.http
      .post('http://localhost:8080/api/authenticate/register', command, {
        responseType: 'text',
      })
      .subscribe({
        next: (response) => {
          localStorage.setItem('loginID', response);
          this.router.navigate(['/']);
          setTimeout(() => {
            this._snackBar.openSuccessSnackBar('zarejestrowano!');
          }, 500);
        },
        error: (error) => {
          switch (error.error) {
            case 'username exists':
              this._snackBar.openWarnSnackBar(
                'użytkownik o wybranej nazwie już istnieje!'
              );
              break;
            case 'email exists':
              this._snackBar.openWarnSnackBar(
                'użytkownik o wybranym emailu już istnieje!'
              );
          }
        },
      });
  }

  logout() {
    localStorage.removeItem('loginID');
    this.router.navigate(['/']);
  }

  getLoginId(): string | null {
    if (typeof localStorage != 'undefined') {
      return localStorage.getItem('loginID');
    }
    return null;
  }

  isAuthenticated(): boolean {
    return !!this.getLoginId();
  }

  getUser(): Observable<Player | null> {
    if (this.getLoginId() == null) {
      return null;
    }
    return this.http
      .get(`${this.apiUrl}/player/${this.getLoginId()}`)
      .pipe(map((value: any) => Object.assign(new Player(), value)));
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.isAuthenticated()) {
      this.router.navigate(['/']);
      return false;
    } else {
      return true;
    }
  }
}
