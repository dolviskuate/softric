export interface IAgence {
  id?: number;
  nom?: string;
  ville?: string;
  telephone?: number;
}

export class Agence implements IAgence {
  constructor(public id?: number, public nom?: string, public ville?: string, public telephone?: number) {}
}
