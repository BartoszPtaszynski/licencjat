import { Component, OnInit } from '@angular/core';

import { error } from 'console';
import { ClubService } from '../club.service';
import { Footballer, Position } from '../club.query';

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
  constructor(public footballerService: ClubService) {}

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
      (result) => alert(result),
      (error) => alert(error.error)
    );
  }
  ngOnInit(): void {
    this.filters = new Filters();
    this.loadFootballers();

    this.footballerService.getPositions().subscribe(
      (result) => (this.positions = result),
      (error) => alert(error.error)
    );
  }
}
