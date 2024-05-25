import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FootballerClub } from './squad.query';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class SquadService {
  constructor(private http: HttpClient, private authService: AuthService) {}

  getClubFootballers(): Observable<FootballerClub[]> {
    return this.http.get<FootballerClub[]>(
      `http://localhost:8080/club/allFootballers?id=${AuthService.getLoginId()}`
    );
  }

  sellFootballer(footballer: FootballerClub) {
    return this.http.delete<FootballerClub>(
      `http://localhost:8080/club/sellFootballer?idOfUser=${AuthService.getLoginId()}&idOfFootballer=${
        footballer.id
      }`
    );
  }
}
