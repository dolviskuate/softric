import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeOperation } from 'app/shared/model/type-operation.model';
import { TypeOperationService } from './type-operation.service';

@Component({
  templateUrl: './type-operation-delete-dialog.component.html'
})
export class TypeOperationDeleteDialogComponent {
  typeOperation?: ITypeOperation;

  constructor(
    protected typeOperationService: TypeOperationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeOperationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeOperationListModification');
      this.activeModal.close();
    });
  }
}
