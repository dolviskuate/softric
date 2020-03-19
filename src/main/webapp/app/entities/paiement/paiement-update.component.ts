import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPaiement, Paiement } from 'app/shared/model/paiement.model';
import { PaiementService } from './paiement.service';
import { IOperation } from 'app/shared/model/operation.model';
import { OperationService } from 'app/entities/operation/operation.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe/employe.service';

type SelectableEntity = IOperation | IClient | IEmploye;

@Component({
  selector: 'jhi-paiement-update',
  templateUrl: './paiement-update.component.html'
})
export class PaiementUpdateComponent implements OnInit {
  isSaving = false;
  operations: IOperation[] = [];
  clients: IClient[] = [];
  employes: IEmploye[] = [];
  datePaiementDp: any;

  editForm = this.fb.group({
    id: [],
    datePaiement: [null, [Validators.required]],
    montantPaiement: [],
    operation: [],
    client: [],
    employe: []
  });

  constructor(
    protected paiementService: PaiementService,
    protected operationService: OperationService,
    protected clientService: ClientService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paiement }) => {
      this.updateForm(paiement);

      this.operationService.query().subscribe((res: HttpResponse<IOperation[]>) => (this.operations = res.body || []));

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));

      this.employeService.query().subscribe((res: HttpResponse<IEmploye[]>) => (this.employes = res.body || []));
    });
  }

  updateForm(paiement: IPaiement): void {
    this.editForm.patchValue({
      id: paiement.id,
      datePaiement: paiement.datePaiement,
      montantPaiement: paiement.montantPaiement,
      operation: paiement.operation,
      client: paiement.client,
      employe: paiement.employe
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paiement = this.createFromForm();
    if (paiement.id !== undefined) {
      this.subscribeToSaveResponse(this.paiementService.update(paiement));
    } else {
      this.subscribeToSaveResponse(this.paiementService.create(paiement));
    }
  }

  private createFromForm(): IPaiement {
    return {
      ...new Paiement(),
      id: this.editForm.get(['id'])!.value,
      datePaiement: this.editForm.get(['datePaiement'])!.value,
      montantPaiement: this.editForm.get(['montantPaiement'])!.value,
      operation: this.editForm.get(['operation'])!.value,
      client: this.editForm.get(['client'])!.value,
      employe: this.editForm.get(['employe'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaiement>>): void {
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
