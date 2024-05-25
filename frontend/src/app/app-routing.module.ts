import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { AuthService } from './modules/auth/auth.service';
import { RegistrationComponent } from './modules/auth/registration/registration.component';
import { ClubComponent } from './modules/club/club.component';
import { FriendsComponent } from './modules/friends/friends.component';
import { AboutAppComponent } from './modules/about-app/about-app.component';
import { MatchComponent } from './modules/match/match.component';
import { ResultsComponent } from './modules/club/results/results.component';
import { SquadComponent } from './modules/squad/squad.component';
import { TransferMarketComponent } from './modules/transfer-market/transfer-market.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent, canActivate: [AuthService] },
  {
    path: 'register',
    component: RegistrationComponent,
    canActivate: [AuthService],
  },
  { path: 'club', component: ClubComponent },
  { path: 'friends', component: FriendsComponent },
  { path: 'aboutApp', component: AboutAppComponent },
  { path: 'match', component: MatchComponent },
  { path: 'results', component: ResultsComponent },
  { path: 'squad', component: SquadComponent },
  { path: 'transferMarket', component: TransferMarketComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false })],
  exports: [RouterModule],
})
export class AppRoutingModule {}
