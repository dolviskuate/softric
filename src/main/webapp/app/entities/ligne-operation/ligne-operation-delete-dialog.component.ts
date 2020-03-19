import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILigneOperation } from 'app/shared/model/ligne-operation.model';
import { LigneOperationService } from './ligne-operation.service';

@Component({
  templateUrl: './ligne-operation-delete-dialog.component.html'
})
export class LigneOperationDeleteDialogComponent {
  ligneOperation?: ILigneOperation;

  constructor(
    protected ligneOperationService: LigneOperationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ligneOperationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ligneOperationListModification');
      this.activeModal.close();
    });
  }
}
