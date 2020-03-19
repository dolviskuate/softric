import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILigneOperation } from 'app/shared/model/ligne-operation.model';

type EntityResponseType = HttpResponse<ILigneOperation>;
type EntityArrayResponseType = HttpResponse<ILigneOperation[]>;

@Injectable({ providedIn: 'root' })
export class LigneOperationService {
  public resourceUrl = SERVER_API_URL + 'api/ligne-operations';

  constructor(protected http: HttpClient) {}

  create(ligneOperation: ILigneOperation): Observable<EntityResponseType> {
    return this.http.post<ILigneOperation>(this.resourceUrl, ligneOperation, { observe: 'response' });
  }

  update(ligneOperation: ILigneOperation): Observable<EntityResponseType> {
    return this.http.put<ILigneOperation>(this.resourceUrl, ligneOperation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILigneOperation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILigneOperation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
