import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { AuthService } from './modules/auth/auth.service';
import { RegistrationComponent } from './modules/auth/registration/registration.component';

const routes: Routes = [
  {path:"", component : HomeComponent},
  {path:"login",component:LoginComponent,canActivate:[AuthService]},
  {path:"register",component:RegistrationComponent,canActivate:[AuthService]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
