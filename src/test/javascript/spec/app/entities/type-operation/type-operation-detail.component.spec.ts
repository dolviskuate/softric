import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SoftricTestModule } from '../../../test.module';
import { TypeOperationDetailComponent } from 'app/entities/type-operation/type-operation-detail.component';
import { TypeOperation } from 'app/shared/model/type-operation.model';

describe('Component Tests', () => {
  describe('TypeOperation Management Detail Component', () => {
    let comp: TypeOperationDetailComponent;
    let fixture: ComponentFixture<TypeOperationDetailComponent>;
    const route = ({ data: of({ typeOperation: new TypeOperation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SoftricTestModule],
        declarations: [TypeOperationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TypeOperationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeOperationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeOperation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeOperation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
