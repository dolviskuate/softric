export interface IClient {
  id?: number;
  nom?: string;
  prenom?: string;
  telephone?: number;
}

export class Client implements IClient {
  constructor(public id?: number, public nom?: string, public prenom?: string, public telephone?: number) {}
}
