import { Component } from '@angular/core';

export interface PeriodicElement {
  team1: string;
  score: string;
  team2: string;
  points: number;
  league: number;
  collectedMoney: number;
  collectedPoints: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {
    team1: 'testTeam',
    score: '1:0',
    team2: 'real Madrid',
    points: 5500,
    league: 3,
    collectedMoney: 7500,
    collectedPoints: 500,
  },
  {
    team1: 'testTeam',
    score: '0:5',
    team2: 'FC Barcelona',
    points: 6000,
    league: 3,
    collectedMoney: -1500,
    collectedPoints: -350,
  },
];

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrl: './results.component.css',
})
export class ResultsComponent {
  displayedColumns: string[] = [
    'team1',
    'score',
    'team2',
    'points',
    'league',
    'collectedMoney',
    'collectedPoints',
  ];
  dataSource = ELEMENT_DATA;
}
