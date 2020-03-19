import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEmploye, Employe } from 'app/shared/model/employe.model';
import { EmployeService } from './employe.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IAgence } from 'app/shared/model/agence.model';
import { AgenceService } from 'app/entities/agence/agence.service';
import { IPoste } from 'app/shared/model/poste.model';
import { PosteService } from 'app/entities/poste/poste.service';

type SelectableEntity = IUser | IAgence | IPoste;

@Component({
  selector: 'jhi-employe-update',
  templateUrl: './employe-update.component.html'
})
export class EmployeUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  agences: IAgence[] = [];
  postes: IPoste[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    prenom: [],
    telephone: [],
    numCni: [],
    user: [],
    agence: [],
    poste: []
  });

  constructor(
    protected employeService: EmployeService,
    protected userService: UserService,
    protected agenceService: AgenceService,
    protected posteService: PosteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ employe }) => {
      this.updateForm(employe);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.agenceService.query().subscribe((res: HttpResponse<IAgence[]>) => (this.agences = res.body || []));

      this.posteService.query().subscribe((res: HttpResponse<IPoste[]>) => (this.postes = res.body || []));
    });
  }

  updateForm(employe: IEmploye): void {
    this.editForm.patchValue({
      id: employe.id,
      nom: employe.nom,
      prenom: employe.prenom,
      telephone: employe.telephone,
      numCni: employe.numCni,
      user: employe.user,
      agence: employe.agence,
      poste: employe.poste
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const employe = this.createFromForm();
    if (employe.id !== undefined) {
      this.subscribeToSaveResponse(this.employeService.update(employe));
    } else {
      this.subscribeToSaveResponse(this.employeService.create(employe));
    }
  }

  private createFromForm(): IEmploye {
    return {
      ...new Employe(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      numCni: this.editForm.get(['numCni'])!.value,
      user: this.editForm.get(['user'])!.value,
      agence: this.editForm.get(['agence'])!.value,
      poste: this.editForm.get(['poste'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmploye>>): void {
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
