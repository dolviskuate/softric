import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILigneOperation, LigneOperation } from 'app/shared/model/ligne-operation.model';
import { LigneOperationService } from './ligne-operation.service';
import { IOperation } from 'app/shared/model/operation.model';
import { OperationService } from 'app/entities/operation/operation.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit/produit.service';

type SelectableEntity = IOperation | IProduit;

@Component({
  selector: 'jhi-ligne-operation-update',
  templateUrl: './ligne-operation-update.component.html'
})
export class LigneOperationUpdateComponent implements OnInit {
  isSaving = false;
  operations: IOperation[] = [];
  produits: IProduit[] = [];

  editForm = this.fb.group({
    id: [],
    quantite: [],
    operation: [],
    produit: []
  });

  constructor(
    protected ligneOperationService: LigneOperationService,
    protected operationService: OperationService,
    protected produitService: ProduitService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ligneOperation }) => {
      this.updateForm(ligneOperation);

      this.operationService.query().subscribe((res: HttpResponse<IOperation[]>) => (this.operations = res.body || []));

      this.produitService.query().subscribe((res: HttpResponse<IProduit[]>) => (this.produits = res.body || []));
    });
  }

  updateForm(ligneOperation: ILigneOperation): void {
    this.editForm.patchValue({
      id: ligneOperation.id,
      quantite: ligneOperation.quantite,
      operation: ligneOperation.operation,
      produit: ligneOperation.produit
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ligneOperation = this.createFromForm();
    if (ligneOperation.id !== undefined) {
      this.subscribeToSaveResponse(this.ligneOperationService.update(ligneOperation));
    } else {
      this.subscribeToSaveResponse(this.ligneOperationService.create(ligneOperation));
    }
  }

  private createFromForm(): ILigneOperation {
    return {
      ...new LigneOperation(),
      id: this.editForm.get(['id'])!.value,
      quantite: this.editForm.get(['quantite'])!.value,
      operation: this.editForm.get(['operation'])!.value,
      produit: this.editForm.get(['produit'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILigneOperation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
