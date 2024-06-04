import { Component, OnInit } from '@angular/core';
import { ClubService } from '../club.service';
import { MatchInformation } from '../club.query';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrl: './results.component.css',
})
export class ResultsComponent implements OnInit {
  constructor(
    private clubService: ClubService,
    private authService: AuthService
  ) {}
  loadingData: boolean = true;
  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.clubService.getResults().subscribe((result) => {
        this.teamResults = result;
        this.updateDisplayedResults();
        this.loadingData = false;
      });
    }
  }

  teamResults: MatchInformation[];
  displayedResults: MatchInformation[];

  displayedColumns: string[] = [
    'team1',
    'score',
    'team2',
    'hostRating',
    'guestRating',
    'league',
    'collectedMoney',
    'collectedPoints',
  ];

  currentPage = 0;
  itemsPerPage = 7;
  onPageChange(page: any) {
    this.currentPage = page?.pageIndex;
    console.log(this.currentPage);
    this.updateDisplayedResults();
  }
  updateDisplayedResults() {
    this.displayedResults = this.teamResults.slice(
      this.currentPage * this.itemsPerPage,
      (this.currentPage + 1) * this.itemsPerPage
    );
  }
}
