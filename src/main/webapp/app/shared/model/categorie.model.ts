export interface ICategorie {
  id?: number;
  libelle?: string;
}

export class Categorie implements ICategorie {
  constructor(public id?: number, public libelle?: string) {}
}
