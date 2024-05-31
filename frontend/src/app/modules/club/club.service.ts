import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ClubCommand } from './club.command';
import { AuthService } from '../auth/auth.service';
import { Observable, map } from 'rxjs';
import {
  ClubInformation,
  Footballer,
  FootballerClub,
  Formation,
} from './club.query';

@Injectable({
  providedIn: 'root',
})
export class ClubService {
  apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient, private authservice: AuthService) {
    this.initializeLoginId();
  }
  loginId: string;

  async initializeLoginId() {
    this.loginId = await this.authservice.getLoginId();
  }
  addClub(command: ClubCommand): void {
    this.http
      .post(
        `http://localhost:8080/club/add/${this.authservice.getLoginId()}`,
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

  getFormations(): Observable<Formation[]> {
    return this.http.get<Formation[]>(this.apiUrl + '/formations');
  }

  getClubInformation(): Observable<ClubInformation> {
    return this.http.get<ClubInformation>(
      this.apiUrl + `/club/info?idOfUser=${this.authservice.getLoginId()}`
    );
  }

  getClubFootballers(): Observable<FootballerClub[]> {
    return this.http.get<FootballerClub[]>(
      `http://localhost:8080/club/allFootballers?id=${this.loginId}`
    );
  }

  sellFootballer(footballer: FootballerClub) {
    return this.http.delete<FootballerClub>(
      `http://localhost:8080/club/sellFootballer?idOfUser=${this.authservice.getLoginId()}&idOfFootballer=${
        footballer.id
      }`
    );
  }

  getFootballers(): Observable<Footballer[]> {
    return this.http.get<Footballer[]>('http://localhost:8080/footballers');
  }

  buyFootballer(footballerId: number) {
    return this.http.post(
      `http://localhost:8080/club/buyFootballer?idOfUser=${this.authservice.getLoginId()}&idOfFootballer=${footballerId}`,
      null,
      {
        responseType: 'text',
      }
    );
  }

  getFootballersForChange(footballerId: number): Observable<FootballerClub[]> {
    return this.http.get<FootballerClub[]>(
      `http://localhost:8080/club/footballersToChange?footballerId=${footballerId}&userId=${this.loginId}`
    );
  }

  changeFootballer(
    footballer1Id: number,
    footballer2Id: number
  ): Observable<any> {
    return this.http.put(
      `http://localhost:8080/club/changeFootballers?footballer1Id=${footballer1Id}&footballer2Id=${footballer2Id}&userId=${this.loginId}`,
      null,
      {
        responseType: 'text',
      }
    );
  }
  changeFootballerWithEmptyPosition(
    footballerId: number,
    position: string
  ): Observable<any> {
    return this.http.put(
      `http://localhost:8080/club/changeEmptyFootballer?footballerId=${footballerId}&positionShortcut=${position}&userId=${this.loginId}`,
      null,
      {
        responseType: 'text',
      }
    );
  }

  changeFormation(formation: string) {
    return this.http.put(
      `http://localhost:8080/club/changeFormation?formation=${formation}&userId=${this.loginId}`,
      null,
      {
        responseType: 'text',
      }
    );
  }
}
