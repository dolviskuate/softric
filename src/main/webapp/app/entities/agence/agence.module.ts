import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SoftricSharedModule } from 'app/shared/shared.module';
import { AgenceComponent } from './agence.component';
import { AgenceDetailComponent } from './agence-detail.component';
import { AgenceUpdateComponent } from './agence-update.component';
import { AgenceDeleteDialogComponent } from './agence-delete-dialog.component';
import { agenceRoute } from './agence.route';

@NgModule({
  imports: [SoftricSharedModule, RouterModule.forChild(agenceRoute)],
  declarations: [AgenceComponent, AgenceDetailComponent, AgenceUpdateComponent, AgenceDeleteDialogComponent],
  entryComponents: [AgenceDeleteDialogComponent]
})
export class SoftricAgenceModule {}
