import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.SoftricClientModule)
      },
      {
        path: 'operation',
        loadChildren: () => import('./operation/operation.module').then(m => m.SoftricOperationModule)
      },
      {
        path: 'type-operation',
        loadChildren: () => import('./type-operation/type-operation.module').then(m => m.SoftricTypeOperationModule)
      },
      {
        path: 'ligne-operation',
        loadChildren: () => import('./ligne-operation/ligne-operation.module').then(m => m.SoftricLigneOperationModule)
      },
      {
        path: 'employe',
        loadChildren: () => import('./employe/employe.module').then(m => m.SoftricEmployeModule)
      },
      {
        path: 'poste',
        loadChildren: () => import('./poste/poste.module').then(m => m.SoftricPosteModule)
      },
      {
        path: 'categorie',
        loadChildren: () => import('./categorie/categorie.module').then(m => m.SoftricCategorieModule)
      },
      {
        path: 'paiement',
        loadChildren: () => import('./paiement/paiement.module').then(m => m.SoftricPaiementModule)
      },
      {
        path: 'produit',
        loadChildren: () => import('./produit/produit.module').then(m => m.SoftricProduitModule)
      },
      {
        path: 'agence',
        loadChildren: () => import('./agence/agence.module').then(m => m.SoftricAgenceModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class SoftricEntityModule {}
