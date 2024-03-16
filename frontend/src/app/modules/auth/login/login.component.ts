import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LOGIN_FORM_VALIDATOR, LoginCommand } from './login.command';
import { error } from 'console';

class LoginReq {
  username:string;
  password:string;

}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'] // Corrected property name
})
export class LoginComponent implements OnInit {
  public loginForm!: FormGroup;

  loginCommand: LoginReq = {username:'',password:''};

  constructor(private _authService: AuthService, private formBuilder: FormBuilder, private http: HttpClient, private router: Router) {
   }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group(LOGIN_FORM_VALIDATOR);
  }

  onSubmit() {
    this._authService.authenticate(this.loginCommand).subscribe(
      result=>console.log(result)
    )
  }
}