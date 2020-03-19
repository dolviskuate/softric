import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProduit, Produit } from 'app/shared/model/produit.model';
import { ProduitService } from './produit.service';
import { ICategorie } from 'app/shared/model/categorie.model';
import { CategorieService } from 'app/entities/categorie/categorie.service';

@Component({
  selector: 'jhi-produit-update',
  templateUrl: './produit-update.component.html'
})
export class ProduitUpdateComponent implements OnInit {
  isSaving = false;
  categories: ICategorie[] = [];

  editForm = this.fb.group({
    id: [],
    designation: [null, [Validators.required]],
    quantiteStock: [],
    quantiteMinimale: [],
    categorie: []
  });

  constructor(
    protected produitService: ProduitService,
    protected categorieService: CategorieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ produit }) => {
      this.updateForm(produit);

      this.categorieService.query().subscribe((res: HttpResponse<ICategorie[]>) => (this.categories = res.body || []));
    });
  }

  updateForm(produit: IProduit): void {
    this.editForm.patchValue({
      id: produit.id,
      designation: produit.designation,
      quantiteStock: produit.quantiteStock,
      quantiteMinimale: produit.quantiteMinimale,
      categorie: produit.categorie
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const produit = this.createFromForm();
    if (produit.id !== undefined) {
      this.subscribeToSaveResponse(this.produitService.update(produit));
    } else {
      this.subscribeToSaveResponse(this.produitService.create(produit));
    }
  }

  private createFromForm(): IProduit {
    return {
      ...new Produit(),
      id: this.editForm.get(['id'])!.value,
      designation: this.editForm.get(['designation'])!.value,
      quantiteStock: this.editForm.get(['quantiteStock'])!.value,
      quantiteMinimale: this.editForm.get(['quantiteMinimale'])!.value,
      categorie: this.editForm.get(['categorie'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduit>>): void {
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

  trackById(index: number, item: ICategorie): any {
    return item.id;
  }
}
