import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder } from '@angular/forms';
import { register } from 'module';

class RegistrationReq {
  username: string;
  email:string;
  password:string;
  passwordRepeat:string;
}

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent implements OnInit{
  constructor(private _authService: AuthService) {
    this.errorSignUp={status:false,message:''}
  }
  registrationReq : RegistrationReq=new RegistrationReq();
  errorSignUp: {status:boolean,message:string};
  
passwordIdentical():boolean {
  return this.registrationReq.password === this.registrationReq.passwordRepeat;
}


onSubmit() {
// if(this.registrationReq.password !== this.registrationReq.passwordRepeat) {
//   this.errorSignUp.status=true;
//   this.errorSignUp.message='hasła nie są identyczne!';
//   return;
// }

this._authService.register(this.registrationReq)}
  

  ngOnInit(): void {
    
  }

}
