import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILigneOperation } from 'app/shared/model/ligne-operation.model';

@Component({
  selector: 'jhi-ligne-operation-detail',
  templateUrl: './ligne-operation-detail.component.html'
})
export class LigneOperationDetailComponent implements OnInit {
  ligneOperation: ILigneOperation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ligneOperation }) => (this.ligneOperation = ligneOperation));
  }

  previousState(): void {
    window.history.back();
  }
}
