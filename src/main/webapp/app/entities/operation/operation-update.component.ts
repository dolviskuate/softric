import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOperation, Operation } from 'app/shared/model/operation.model';
import { OperationService } from './operation.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';
import { ITypeOperation } from 'app/shared/model/type-operation.model';
import { TypeOperationService } from 'app/entities/type-operation/type-operation.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe/employe.service';

type SelectableEntity = IClient | ITypeOperation | IEmploye;

@Component({
  selector: 'jhi-operation-update',
  templateUrl: './operation-update.component.html'
})
export class OperationUpdateComponent implements OnInit {
  isSaving = false;
  clients: IClient[] = [];
  typeoperations: ITypeOperation[] = [];
  employes: IEmploye[] = [];
  dateOperationDp: any;

  editForm = this.fb.group({
    id: [],
    dateOperation: [null, [Validators.required]],
    montantTotal: [],
    client: [],
    typeOperation: [],
    employe: []
  });

  constructor(
    protected operationService: OperationService,
    protected clientService: ClientService,
    protected typeOperationService: TypeOperationService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operation }) => {
      this.updateForm(operation);

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));

      this.typeOperationService.query().subscribe((res: HttpResponse<ITypeOperation[]>) => (this.typeoperations = res.body || []));

      this.employeService.query().subscribe((res: HttpResponse<IEmploye[]>) => (this.employes = res.body || []));
    });
  }

  updateForm(operation: IOperation): void {
    this.editForm.patchValue({
      id: operation.id,
      dateOperation: operation.dateOperation,
      montantTotal: operation.montantTotal,
      client: operation.client,
      typeOperation: operation.typeOperation,
      employe: operation.employe
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operation = this.createFromForm();
    if (operation.id !== undefined) {
      this.subscribeToSaveResponse(this.operationService.update(operation));
    } else {
      this.subscribeToSaveResponse(this.operationService.create(operation));
    }
  }

  private createFromForm(): IOperation {
    return {
      ...new Operation(),
      id: this.editForm.get(['id'])!.value,
      dateOperation: this.editForm.get(['dateOperation'])!.value,
      montantTotal: this.editForm.get(['montantTotal'])!.value,
      client: this.editForm.get(['client'])!.value,
      typeOperation: this.editForm.get(['typeOperation'])!.value,
      employe: this.editForm.get(['employe'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperation>>): void {
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
