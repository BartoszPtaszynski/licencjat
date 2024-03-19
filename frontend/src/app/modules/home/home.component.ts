import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { error } from 'console';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {



  text !:String;
  textv2:String;

  constructor (public authService:AuthService){


  }
  isAuthenticated():boolean{
    return this.authService.isAuthenticated();
  }
  ngOnInit(){
    this.authService.getText().subscribe((data: String)=>{
      
      this.text=data;
      console.log(this.text)
    },
    (error:HttpErrorResponse)=>{
      console.log("error",error);
      })
this.textv2=this.authService.getLoginId();

  }
}
