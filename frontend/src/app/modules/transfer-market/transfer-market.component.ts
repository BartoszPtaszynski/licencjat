import { Component, OnInit } from '@angular/core';
import { Footballer } from './footballer.query';
import { FootballerService } from './footballer.service';
import { error } from 'console';

@Component({
  selector: 'app-transfer-market',
  templateUrl: './transfer-market.component.html',
  styleUrl: './transfer-market.component.css',
})
export class TransferMarketComponent implements OnInit {
  constructor(public footballerService: FootballerService) {}

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
