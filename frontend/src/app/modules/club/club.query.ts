export interface Formation {
  code: string;
  name: string;
  listOfPositions: string[];
}

export interface ClubInformation {
  name: string;
  league: number;
  rating: number;
  value: number;
  funds: number;
  crest: string;
}

export interface FootballerClub {
  id: number;
  name: string;
  surname: string;
  rating: number;
  value: number;
  footballerPositions: Position[];
  activePosition: Position;
}

export interface Position {
  id: number;
  nameOfPosition: string;
  shortcut: string;
}
export interface Footballer {
  id: number;
  name: string;
  surname: string;
  rating: number;
  positions: string;
  value: number;
}
