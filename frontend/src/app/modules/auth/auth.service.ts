import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginCommand } from './login/login.command';
import { response } from 'express';
import { Observable, catchError } from 'rxjs';
import { error } from 'console';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = "http://localhost:8080";
  
  constructor(private http: HttpClient) { }

  authenticate(command: LoginCommand){
    return this.http.post<any>("http://localhost:8080/api/authenticate",command)
  }
  getText():Observable<String> {
    return this.http.get(`${this.apiUrl}/xD`,{responseType:'text'});
    
  }
}