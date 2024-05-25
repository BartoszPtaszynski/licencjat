import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { ClubService } from './club.service';
import { error } from 'console';

class ClubInfo {
  name: string;
  league: number;
  rating: number;
  value: number;
  funds: number;
  crest: string;
}

@Component({
  selector: 'app-club',
  templateUrl: './club.component.html',
  styleUrl: './club.component.css',
})
export class ClubComponent implements OnInit {
  constructor(
    public authService: AuthService,
    public clubService: ClubService
  ) {}
  clubInfo: ClubInfo = null;

  ngOnInit(): void {
    this.clubService.getClubInformation().subscribe(
      (result) => (this.clubInfo = result),
      (error) => console.log('error')
    );
  }
}
