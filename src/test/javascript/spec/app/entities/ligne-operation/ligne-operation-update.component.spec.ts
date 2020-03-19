import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SoftricTestModule } from '../../../test.module';
import { LigneOperationUpdateComponent } from 'app/entities/ligne-operation/ligne-operation-update.component';
import { LigneOperationService } from 'app/entities/ligne-operation/ligne-operation.service';
import { LigneOperation } from 'app/shared/model/ligne-operation.model';

describe('Component Tests', () => {
  describe('LigneOperation Management Update Component', () => {
    let comp: LigneOperationUpdateComponent;
    let fixture: ComponentFixture<LigneOperationUpdateComponent>;
    let service: LigneOperationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SoftricTestModule],
        declarations: [LigneOperationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LigneOperationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LigneOperationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LigneOperationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LigneOperation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new LigneOperation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
