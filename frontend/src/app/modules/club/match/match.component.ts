import { Component, OnInit } from '@angular/core';
import { MatchInformation } from '../club.query';
import { ClubService } from '../club.service';
import { SnackbarService } from '../../../snackbar.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrl: './match.component.css',
})
export class MatchComponent implements OnInit {
  constructor(
    private clubService: ClubService,
    private snackbar: SnackbarService,
    private loacation: Location
  ) {}
  isFullSquad: boolean = true;
  matchInfomation: MatchInformation;
  isLoading: boolean = true;

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

  changeLeagueToast() {
    if (this.matchInfomation?.newLeague < this.matchInfomation?.league) {
      this.snackbar.openSuccessSnackBar(
        'Gratulacje, Awansujesz do wyższej ligi:' +
          this.matchInfomation?.newLeague
      );
    } else if (this.matchInfomation?.newLeague > this.matchInfomation?.league) {
      this.snackbar.openWarnSnackBar(
        'Niestety, spadasz do niższej ligi:' + this.matchInfomation?.newLeague
      );
    }
  }

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
        this.isLoading = false;
      },
      (error) => {
        this.loacation.back();
        if (error.error === 'club with this league not found') {
          this.snackbar.openWarnSnackBar(
            'nie znaleziono aktualnie przeciwnika z pełnym składem w tej lidze. Musisz chwilę poczekać'
          );
        }
      }
    );
  }

  generateRandomNumber(): number {
    return Math.floor(Math.random() * 89);
  }

  showAnimation: boolean = false;

  showGoalAnimation() {
    this.showAnimation = true;
    setTimeout(() => (this.showAnimation = false), 1400);
  }

  minCounter: number = 0;
  startTimer() {
    const interval = setInterval(() => {
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
        clearInterval(interval);
        this.changeLeagueToast();
      }
    }, 200);
  }
}
