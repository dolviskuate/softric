import { Moment } from 'moment';
import { IOperation } from 'app/shared/model/operation.model';
import { IClient } from 'app/shared/model/client.model';
import { IEmploye } from 'app/shared/model/employe.model';

export interface IPaiement {
  id?: number;
  datePaiement?: Moment;
  montantPaiement?: number;
  operation?: IOperation;
  client?: IClient;
  employe?: IEmploye;
}

export class Paiement implements IPaiement {
  constructor(
    public id?: number,
    public datePaiement?: Moment,
    public montantPaiement?: number,
    public operation?: IOperation,
    public client?: IClient,
    public employe?: IEmploye
  ) {}
}
