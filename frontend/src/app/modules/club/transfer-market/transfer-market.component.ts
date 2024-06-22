import { Component, OnInit } from '@angular/core';

import { error } from 'console';
import { ClubService } from '../club.service';
import { ClubInformation, Footballer, Position } from '../club.query';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackbarService } from '../../../snackbar.service';
import { AuthService } from '../../auth/auth.service';

class Filters {
  priceFrom: number;
  priceTo: number;
  ratingFrom: number;
  ratingTo: number;
  position: number;
  constructor() {
    this.priceFrom = 0;
    this.priceTo = 100000;
    this.ratingFrom = 0;
    this.ratingTo = 50;
    this.position = 0;
  }
}

@Component({
  selector: 'app-transfer-market',
  templateUrl: './transfer-market.component.html',
  styleUrl: './transfer-market.component.css',
})
export class TransferMarketComponent implements OnInit {
  constructor(
    public clubService: ClubService,
    private snackbarService: SnackbarService,
    private authService: AuthService
  ) {}

  filters: Filters;
  footballers: Footballer[];
  positions: Position[];
  clubInfo: ClubInformation;

  loadFootballers() {
    this.clubService
      .getFootballers(
        this.filters.priceFrom,
        this.filters.priceTo,
        this.filters.ratingFrom,
        this.filters.ratingTo,
        this.filters.position
      )
      .subscribe(
        (result) => {
          this.footballers = result;
          if (this.footballers?.length === 0) {
            this.snackbarService.openWarnSnackBar(
              'nie ma zawodników z wybranymi filtrami'
            );
          }
        },
        (error) => {
          this.snackbarService.openWarnSnackBar(
            'problem z załadowaniem zawodników'
          );
        }
      );
  }
  resetFilters() {
    this.filters = new Filters();
    this.loadFootballers();
  }

  //do paginacji
  currentPage = 0;
  itemsPerPage = 28;
  onPageChange(page: any) {
    this.currentPage = page?.pageIndex;
    console.log(this.currentPage);
  }

  buyFootballer(id: number) {
    this.clubService.buyFootballer(id).subscribe(
      (result) =>
        this.snackbarService.openSuccessSnackBar(
          'Zawodnik wysłany na ławkę rezerwowych!'
        ),
      (error) => {
        switch (error.error) {
          case 'footballer already in club':
            this.snackbarService.openWarnSnackBar(
              'Posiadasz tego zawodnika w klubie!'
            );
            break;
          case 'not enough money':
            this.snackbarService.openWarnSnackBar('Masz za mało pieniędzy!');
            break;
        }
      }
    );
  }
  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.clubService
        .getClubInformation()
        .subscribe((result) => (this.clubInfo = result));
    }
    this.filters = new Filters();
    this.loadFootballers();

    this.clubService.getPositions().subscribe(
      (result) => (this.positions = result),
      (error) => this.snackbarService.openWarnSnackBar(error.error)
    );
  }
}
