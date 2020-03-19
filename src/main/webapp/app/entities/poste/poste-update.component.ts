import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPoste, Poste } from 'app/shared/model/poste.model';
import { PosteService } from './poste.service';

@Component({
  selector: 'jhi-poste-update',
  templateUrl: './poste-update.component.html'
})
export class PosteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: []
  });

  constructor(protected posteService: PosteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ poste }) => {
      this.updateForm(poste);
    });
  }

  updateForm(poste: IPoste): void {
    this.editForm.patchValue({
      id: poste.id,
      libelle: poste.libelle
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const poste = this.createFromForm();
    if (poste.id !== undefined) {
      this.subscribeToSaveResponse(this.posteService.update(poste));
    } else {
      this.subscribeToSaveResponse(this.posteService.create(poste));
    }
  }

  private createFromForm(): IPoste {
    return {
      ...new Poste(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPoste>>): void {
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
