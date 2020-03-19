export interface ITypeOperation {
  id?: number;
  libelle?: number;
}

export class TypeOperation implements ITypeOperation {
  constructor(public id?: number, public libelle?: number) {}
}
