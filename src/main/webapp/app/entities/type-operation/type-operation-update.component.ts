import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeOperation, TypeOperation } from 'app/shared/model/type-operation.model';
import { TypeOperationService } from './type-operation.service';

@Component({
  selector: 'jhi-type-operation-update',
  templateUrl: './type-operation-update.component.html'
})
export class TypeOperationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: []
  });

  constructor(protected typeOperationService: TypeOperationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeOperation }) => {
      this.updateForm(typeOperation);
    });
  }

  updateForm(typeOperation: ITypeOperation): void {
    this.editForm.patchValue({
      id: typeOperation.id,
      libelle: typeOperation.libelle
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeOperation = this.createFromForm();
    if (typeOperation.id !== undefined) {
      this.subscribeToSaveResponse(this.typeOperationService.update(typeOperation));
    } else {
      this.subscribeToSaveResponse(this.typeOperationService.create(typeOperation));
    }
  }

  private createFromForm(): ITypeOperation {
    return {
      ...new TypeOperation(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeOperation>>): void {
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
}
