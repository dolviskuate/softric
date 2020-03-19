import { IOperation } from 'app/shared/model/operation.model';
import { IProduit } from 'app/shared/model/produit.model';

export interface ILigneOperation {
  id?: number;
  quantite?: string;
  operation?: IOperation;
  produit?: IProduit;
}

export class LigneOperation implements ILigneOperation {
  constructor(public id?: number, public quantite?: string, public operation?: IOperation, public produit?: IProduit) {}
}
