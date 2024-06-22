import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';

import { AuthService } from '../auth/auth.service';
import { Observable, map } from 'rxjs';
import {
  ClubCommand,
  ClubInformation,
  Footballer,
  FootballerClub,
  Formation,
  MatchInformation,
  Position,
} from './club.query';

@Injectable({
  providedIn: 'root',
})
export class ClubService {
  apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient, public authservice: AuthService) {}

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
      `http://localhost:8080/club/allFootballers?id=${this.authservice.getLoginId()}`
    );
  }

  sellFootballer(footballer: FootballerClub) {
    return this.http.delete<FootballerClub>(
      `http://localhost:8080/club/sellFootballer?idOfUser=${this.authservice.getLoginId()}&idOfFootballer=${
        footballer.id
      }`
    );
  }

  getFootballers(
    priceFrom: number,
    priceTo: number,
    ratingFrom: number,
    ratingTo: number,
    positions: number
  ): Observable<Footballer[]> {
    return this.http.get<Footballer[]>(
      `http://localhost:8080/footballers?priceFrom=${priceFrom}&priceTo=${priceTo}&ratingForm=${ratingFrom}&ratingTo=${ratingTo}&positionId=${positions}`
    );
  }
  getPositions(): Observable<Position[]> {
    return this.http.get<Position[]>(
      `http://localhost:8080/footballers/positions`
    );
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
      `http://localhost:8080/club/footballersToChange?footballerId=${footballerId}&userId=${this.authservice.getLoginId()}`
    );
  }

  changeFootballer(
    footballer1Id: number,
    footballer2Id: number
  ): Observable<any> {
    return this.http.put(
      `http://localhost:8080/club/changeFootballers?footballer1Id=${footballer1Id}&footballer2Id=${footballer2Id}&userId=${this.authservice.getLoginId()}`,
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
      `http://localhost:8080/club/changeEmptyFootballer?footballerId=${footballerId}&positionShortcut=${position}&userId=${this.authservice.getLoginId()}`,
      null,
      {
        responseType: 'text',
      }
    );
  }

  changeFormation(formation: string) {
    return this.http.put(
      `http://localhost:8080/club/changeFormation?formation=${formation}&userId=${this.authservice.getLoginId()}`,
      null,
      {
        responseType: 'text',
      }
    );
  }

  playMatch(): Observable<MatchInformation> {
    return this.http.post<MatchInformation>(
      `http://localhost:8080/club/playMatch?userId=${this.authservice.getLoginId()}`,
      null
    );
  }

  getResults(): Observable<MatchInformation[]> {
    return this.http.get<MatchInformation[]>(
      `http://localhost:8080/club/results?userId=${this.authservice.getLoginId()}`
    );
  }

  areAllFootballersInSquad(): Observable<boolean> {
    return this.http.get<boolean>(
      `http://localhost:8080/club/allFootballersInSquad?userId=${this.authservice.getLoginId()}`
    );
  }
}
