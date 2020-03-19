import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SoftricSharedModule } from 'app/shared/shared.module';
import { TypeOperationComponent } from './type-operation.component';
import { TypeOperationDetailComponent } from './type-operation-detail.component';
import { TypeOperationUpdateComponent } from './type-operation-update.component';
import { TypeOperationDeleteDialogComponent } from './type-operation-delete-dialog.component';
import { typeOperationRoute } from './type-operation.route';

@NgModule({
  imports: [SoftricSharedModule, RouterModule.forChild(typeOperationRoute)],
  declarations: [TypeOperationComponent, TypeOperationDetailComponent, TypeOperationUpdateComponent, TypeOperationDeleteDialogComponent],
  entryComponents: [TypeOperationDeleteDialogComponent]
})
export class SoftricTypeOperationModule {}
