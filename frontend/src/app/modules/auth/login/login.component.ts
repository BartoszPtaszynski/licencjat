import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';


class LoginReq {
  username:string;
  password:string;

}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;

  loginError: boolean = false;
  loginCommand: LoginReq = {username:'',password:''};
  
  constructor(private _authService: AuthService, private formBuilder: FormBuilder, private http: HttpClient, private router: Router) {
   }

  ngOnInit(): void {
    
  }

  onSubmit() {
    console.log(this.loginCommand.username+this.loginCommand.password);
    if(! this._authService.login(this.loginCommand)){
      this.loginError=true;
    }
    
  }
}
