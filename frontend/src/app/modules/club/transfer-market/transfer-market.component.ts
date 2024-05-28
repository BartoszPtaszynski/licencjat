import { Component, OnInit } from '@angular/core';

import { error } from 'console';
import { ClubService } from '../club.service';
import { Footballer } from '../club.query';

@Component({
  selector: 'app-transfer-market',
  templateUrl: './transfer-market.component.html',
  styleUrl: './transfer-market.component.css',
})
export class TransferMarketComponent implements OnInit {
  constructor(public footballerService: ClubService) {}

  footballers: Footballer[];

  loadFootballers() {
    this.footballerService.getFootballers().subscribe(
      (result) => {
        this.footballers = result;
      },
      (error) => {
        alert('unable to load footballers');
      }
    );
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
    this.loadFootballers();
  }
}
