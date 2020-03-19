import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeOperation } from 'app/shared/model/type-operation.model';

@Component({
  selector: 'jhi-type-operation-detail',
  templateUrl: './type-operation-detail.component.html'
})
export class TypeOperationDetailComponent implements OnInit {
  typeOperation: ITypeOperation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeOperation }) => (this.typeOperation = typeOperation));
  }

  previousState(): void {
    window.history.back();
  }
}
