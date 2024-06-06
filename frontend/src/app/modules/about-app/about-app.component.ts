import { Location } from '@angular/common';
import { Component } from '@angular/core';
interface LeagueData {
  liga: string;
  wygranaPunktyLigowe: number;
  wygranaZyskanePieniadze: number;
  przegranaPunktyLigowe: number;
  przegranaStraconePieniadze: number;
  remisPunktyLigowe: number;
  remisZyskanePieniadze: number;
}

interface PointsData {
  league: string;
  pointsRange: string;
}
const POINTS_DATA: PointsData[] = [
  {
    league: 'Liga 5',
    pointsRange: '[0,2000)',
  },
  {
    league: 'Liga 4',
    pointsRange: '[2000,4500)',
  },
  {
    league: 'Liga 3',
    pointsRange: '[4500,7500)',
  },
  {
    league: 'Liga 2',
    pointsRange: '[7500,11000)',
  },
  {
    league: 'Liga 1',
    pointsRange: '[11000,∞)',
  },
];
const ELEMENT_DATA: LeagueData[] = [
  {
    liga: 'Liga 5',
    wygranaPunktyLigowe: 150,
    wygranaZyskanePieniadze: 2000,
    przegranaPunktyLigowe: -100,
    przegranaStraconePieniadze: -500,
    remisPunktyLigowe: 0,
    remisZyskanePieniadze: 0,
  },
  {
    liga: 'Liga 4',
    wygranaPunktyLigowe: 250,
    wygranaZyskanePieniadze: 500,
    przegranaPunktyLigowe: -200,
    przegranaStraconePieniadze: -750,
    remisPunktyLigowe: 0,
    remisZyskanePieniadze: 0,
  },
  {
    liga: 'Liga 3',
    wygranaPunktyLigowe: 500,
    wygranaZyskanePieniadze: 7500,
    przegranaPunktyLigowe: -350,
    przegranaStraconePieniadze: -1500,
    remisPunktyLigowe: 0,
    remisZyskanePieniadze: 0,
  },
  {
    liga: 'Liga 2',
    wygranaPunktyLigowe: 800,
    wygranaZyskanePieniadze: 10000,
    przegranaPunktyLigowe: -500,
    przegranaStraconePieniadze: -2000,
    remisPunktyLigowe: 0,
    remisZyskanePieniadze: 0,
  },
  {
    liga: 'Liga 1',
    wygranaPunktyLigowe: 1000,
    wygranaZyskanePieniadze: 12500,
    przegranaPunktyLigowe: -750,
    przegranaStraconePieniadze: -4000,
    remisPunktyLigowe: 0,
    remisZyskanePieniadze: 0,
  },
];

@Component({
  selector: 'app-about-app',
  templateUrl: './about-app.component.html',
  styleUrl: './about-app.component.css',
})
export class AboutAppComponent {
  constructor(private location: Location) {}
  goBack(): void {
    this.location.back();
  }
  displayedColumns: string[] = [
    'liga',
    'wygranaPunktyLigowe',
    'wygranaZyskanePieniadze',
    'przegranaPunktyLigowe',
    'przegranaStraconePieniadze',
    'remisPunktyLigowe',
    'remisZyskanePieniadze',
  ];

  displayedColumnsPoints: string[] = ['league', 'pointsRange'];
  dataSourcePoints = POINTS_DATA;
  dataSource = ELEMENT_DATA;
}
