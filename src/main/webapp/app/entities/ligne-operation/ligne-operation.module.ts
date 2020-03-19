import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SoftricSharedModule } from 'app/shared/shared.module';
import { LigneOperationComponent } from './ligne-operation.component';
import { LigneOperationDetailComponent } from './ligne-operation-detail.component';
import { LigneOperationUpdateComponent } from './ligne-operation-update.component';
import { LigneOperationDeleteDialogComponent } from './ligne-operation-delete-dialog.component';
import { ligneOperationRoute } from './ligne-operation.route';

@NgModule({
  imports: [SoftricSharedModule, RouterModule.forChild(ligneOperationRoute)],
  declarations: [
    LigneOperationComponent,
    LigneOperationDetailComponent,
    LigneOperationUpdateComponent,
    LigneOperationDeleteDialogComponent
  ],
  entryComponents: [LigneOperationDeleteDialogComponent]
})
export class SoftricLigneOperationModule {}
