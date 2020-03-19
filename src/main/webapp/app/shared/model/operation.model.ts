import { Moment } from 'moment';
import { IClient } from 'app/shared/model/client.model';
import { ITypeOperation } from 'app/shared/model/type-operation.model';
import { IEmploye } from 'app/shared/model/employe.model';

export interface IOperation {
  id?: number;
  dateOperation?: Moment;
  montantTotal?: number;
  client?: IClient;
  typeOperation?: ITypeOperation;
  employe?: IEmploye;
}

export class Operation implements IOperation {
  constructor(
    public id?: number,
    public dateOperation?: Moment,
    public montantTotal?: number,
    public client?: IClient,
    public typeOperation?: ITypeOperation,
    public employe?: IEmploye
  ) {}
}
