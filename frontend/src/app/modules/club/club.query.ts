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

  formation: Formation;
}

export class FootballerClub {
  id: number;
  name: string;
  surname: string;
  rating: number;
  value: number;
  footballerPositions: Position[];
  activePosition: Position;
  constructor(
    id: number,
    name: string,
    surname: string,
    rating: number,
    value: number,
    footballerPositions: Position[],
    activePosition: string
  ) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.rating = rating;
    this.value = value;
    this.footballerPositions = footballerPositions;
    this.activePosition = new Position(activePosition);
  }
}

export class Position {
  id: number;
  nameOfPosition: string;
  shortcut: string;
  constructor(shortcut: string) {
    this.id = null;
    this.nameOfPosition = null;
    this.shortcut = shortcut;
  }
}
export interface Footballer {
  id: number;
  name: string;
  surname: string;
  rating: number;
  positions: string;
  value: number;
}

export interface MatchInformation {
  hostTeamScore: number;
  guestTeamScore: number;
  hostClubName: string;
  guestClubName: string;
  hostSquadRating: number;
  guestSquadRating: number;
  clubPoints: number;
  league: number;
  collectedMoney: number;
  collectedPoints: number;
  newLeague: number;
}
