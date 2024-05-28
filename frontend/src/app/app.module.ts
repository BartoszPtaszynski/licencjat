import { NgModule } from '@angular/core';
import {
  BrowserModule,
  provideClientHydration,
} from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './modules/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './modules/auth/login/login.component';
import { RegistrationComponent } from './modules/auth/registration/registration.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';

import {
  MAT_FORM_FIELD_DEFAULT_OPTIONS,
  MatFormFieldModule,
} from '@angular/material/form-field';
import { CreateClubModalComponent } from './modules/club/create-club-modal/create-club-modal.component';
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle,
} from '@angular/material/dialog';
import { FriendsComponent } from './modules/friends/friends.component';
import { AddFriendModalComponent } from './modules/friends/add-friend-modal/add-friend-modal.component';
import { SquadComponent } from './modules/club/squad/squad.component';
import { TransferMarketComponent } from './modules/club/transfer-market/transfer-market.component';
import { MatCardModule } from '@angular/material/card';
import { FootballerDetailsModalComponent } from './modules/club/squad/footballer-details-modal/footballer-details-modal.component';
import { ClubComponent } from './modules/club/club.component';
import { MatchComponent } from './modules/club/match/match.component';
import { ResultsComponent } from './modules/club/results/results.component';
import { ChangeFootballerModalComponent } from './modules/club/squad/change-footballer-modal/change-footballer-modal.component';
import { MatTableModule } from '@angular/material/table';
import { AboutAppComponent } from './modules/about-app/about-app.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { FootballerChangePositionModalComponent } from './modules/club/squad/footballer-change-position-modal/footballer-change-position-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    CreateClubModalComponent,
    FriendsComponent,
    AddFriendModalComponent,
    SquadComponent,
    TransferMarketComponent,
    FootballerDetailsModalComponent,
    ClubComponent,
    MatchComponent,
    ResultsComponent,
    ChangeFootballerModalComponent,
    AboutAppComponent,
    FootballerChangePositionModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatSlideToggleModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    MatCardModule,
    MatTableModule,
    MatGridListModule,
    MatPaginatorModule,
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    {
      provide: MAT_FORM_FIELD_DEFAULT_OPTIONS,
      useValue: { appearance: 'outline' },
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
