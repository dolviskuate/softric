export interface IPoste {
  id?: number;
  libelle?: string;
}

export class Poste implements IPoste {
  constructor(public id?: number, public libelle?: string) {}
}
