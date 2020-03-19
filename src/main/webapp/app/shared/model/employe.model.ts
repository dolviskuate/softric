import { IUser } from 'app/core/user/user.model';
import { IAgence } from 'app/shared/model/agence.model';
import { IPoste } from 'app/shared/model/poste.model';

export interface IEmploye {
  id?: number;
  nom?: string;
  prenom?: string;
  telephone?: number;
  numCni?: number;
  user?: IUser;
  agence?: IAgence;
  poste?: IPoste;
}

export class Employe implements IEmploye {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public telephone?: number,
    public numCni?: number,
    public user?: IUser,
    public agence?: IAgence,
    public poste?: IPoste
  ) {}
}
