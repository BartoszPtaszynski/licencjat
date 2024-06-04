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
  matchInfomation: MatchInformation;

  ngOnInit(): void {
    if (this.isFullSquad) {
      this.playMatch();
    }
    this.startTimer();
  }
  randomMinutesOfHostGoals: number[] = [];
  randomMinutesOfGuestGoals: number[] = [];

  hostScore: number = 0;
  guestScore: number = 0;

  isMatchFinished: boolean = false;

  playMatch() {
    this.clubService.playMatch().subscribe(
      (result) => {
        this.matchInfomation = result;
        for (let i = 0; i < this.matchInfomation.hostTeamScore; i++) {
          this.randomMinutesOfHostGoals.push(this.generateRandomNumber());
        }
        for (let i = 0; i < this.matchInfomation.guestTeamScore; i++) {
          this.randomMinutesOfGuestGoals.push(this.generateRandomNumber());
        }
      },
      (error) => console.log(error.error)
    );
  }

  generateRandomNumber(): number {
    return Math.floor(Math.random() * 90);
  }

  showAnimation: boolean = false;

  showGoalAnimation() {
    this.showAnimation = true;
    setTimeout(() => (this.showAnimation = false), 1400);
  }

  minCounter: number = 0;
  startTimer() {
    setInterval(() => {
      if (this.minCounter < 90) {
        if (this.randomMinutesOfHostGoals.includes(this.minCounter)) {
          this.hostScore++;
          this.showGoalAnimation();
        }
        if (this.randomMinutesOfGuestGoals.includes(this.minCounter)) {
          this.guestScore++;
          this.showGoalAnimation();
        }
        this.minCounter++;
      } else {
        this.isMatchFinished = true;
      }
    }, 100);
  }
}
