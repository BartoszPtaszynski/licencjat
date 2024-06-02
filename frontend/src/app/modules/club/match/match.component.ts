import { Component, OnInit } from '@angular/core';
import { MatchInformation } from '../club.query';
import { ClubService } from '../club.service';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrl: './match.component.css',
})
export class MatchComponent implements OnInit {
  constructor(private clubService: ClubService) {}
  isFullSquad: boolean = true;

  ngOnInit(): void {
    if (this.isFullSquad) {
      this.playMatch();
    }
  }

  playMatch() {
    this.clubService.playMatch().subscribe(
      (result) => (this.matchInfomation = result),
      (error) => console.log(error.error)
    );
  }
  matchInfomation: MatchInformation;
}
