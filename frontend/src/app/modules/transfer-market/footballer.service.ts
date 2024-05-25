import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Footballer } from './footballer.query';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class FootballerService {
  constructor(private http: HttpClient) {}

  getFootballers(): Observable<Footballer[]> {
    return this.http.get<Footballer[]>('http://localhost:8080/footballers');
  }

  buyFootballer(footballerId: number) {
    return this.http.post(
      `http://localhost:8080/club/buyFootballer?idOfUser=${AuthService.getLoginId()}&idOfFootballer=${footballerId}`,
      null,
      {
        responseType: 'text',
      }
    );
  }
}
