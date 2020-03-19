import { ICategorie } from 'app/shared/model/categorie.model';

export interface IProduit {
  id?: number;
  designation?: string;
  quantiteStock?: number;
  quantiteMinimale?: number;
  categorie?: ICategorie;
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public designation?: string,
    public quantiteStock?: number,
    public quantiteMinimale?: number,
    public categorie?: ICategorie
  ) {}
}
