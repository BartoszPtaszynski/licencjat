import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginCommand } from './login/login.command';
import { Observable, catchError, map } from 'rxjs';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Route, Router, RouterStateSnapshot } from '@angular/router';
import { Player } from './auth.context';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements CanActivate{

  apiUrl = "http://localhost:8080";
  
  constructor(private http: HttpClient,private router: Router) { }
  

  login(command: LoginCommand) :boolean {
      let message;
      this.http.post("http://localhost:8080/api/authenticate",command,{responseType:'text'})
     .subscribe(
      response=>{
        localStorage.setItem('loginID',response);

        this.router.navigate(['/']);
        console.log(this.isAuthenticated()+"xD")
        message=true;
        console.log("login",response)
      },( error: string) =>{
        console.log('Login failed',error);
        message=false;
      }
     );
     return message;
  }

  logout() {
    localStorage.removeItem('loginID');
    console.log(this.isAuthenticated()+"xD")
    this.router.navigate(['/']);
    window.location.reload();
  }

   getLoginId(): string| null {
    if(typeof localStorage !== 'undefined')
    {
      return localStorage.getItem("loginID");
    }
    return null;
  }
  getUser(): Observable<Player |null> {
    if( this.getLoginId() == null) {
      return null;
    }
    return this.http.get(`${this.apiUrl}/player/${this.getLoginId()}`).pipe(map((value: any)=>Object.assign(new Player(),value)));
  }
  
  isAuthenticated(): boolean {
    return !! this.getLoginId();
}


  getText():Observable<String> {
    return this.http.get(`${this.apiUrl}/xD`,{responseType:'text'});
    
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.isAuthenticated()) {
      this.router.navigate(['/']);
      return false; 
    } else {
      
      return true;
    }
  }




}