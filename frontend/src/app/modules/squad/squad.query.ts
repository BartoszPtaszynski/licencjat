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
