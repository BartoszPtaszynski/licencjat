import { Component, OnInit } from '@angular/core';

import { error } from 'console';
import { ClubService } from '../club.service';
import { Footballer, Position } from '../club.query';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SnackbarService } from '../../../snackbar.service';

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
    public footballerService: ClubService,
    private snackbarService: SnackbarService
  ) {}

  filters: Filters;
  footballers: Footballer[];
  positions: Position[];

  loadFootballers() {
    this.footballerService
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
        },
        (error) => {
          alert('unable to load footballers');
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
    this.footballerService.buyFootballer(id).subscribe(
      (result) => this.snackbarService.openSuccessSnackBar(result),
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
    this.filters = new Filters();
    this.loadFootballers();

    this.footballerService.getPositions().subscribe(
      (result) => (this.positions = result),
      (error) => this.snackbarService.openWarnSnackBar(error.error)
    );
  }
}
