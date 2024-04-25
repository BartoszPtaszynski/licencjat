import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ClubCommand } from './club.command';
import { AuthService } from '../auth/auth.service';
import { Observable, map } from 'rxjs';
import { Formation } from './club.query';

@Injectable({
  providedIn: 'root',
})
export class ClubService {
  apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) {}

  addClub(command: ClubCommand): void {
    this.http
      .post(
        `http://localhost:8080/club/add/${AuthService.getLoginId()}`,
        command,
        {
          responseType: 'text',
        }
      )
      .subscribe({
        next: (response) => {
          console.log(response);
          window.location.reload();
        },
        error: (error) => {
          console.error('error with creating new club:', error);
        },
      });
  }

  //   getFormations(): Observable<Formation[]> {
  //     return this.http.get<Formation[]>(this.apiUrl + '/formations').pipe(map(response => response.data));
  // }
  getFormations(): Observable<Formation[]> {
    return this.http.get<Formation[]>(this.apiUrl + '/formations');
  }
}
