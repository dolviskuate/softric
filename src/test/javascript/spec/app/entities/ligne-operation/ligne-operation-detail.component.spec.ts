import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SoftricTestModule } from '../../../test.module';
import { LigneOperationDetailComponent } from 'app/entities/ligne-operation/ligne-operation-detail.component';
import { LigneOperation } from 'app/shared/model/ligne-operation.model';

describe('Component Tests', () => {
  describe('LigneOperation Management Detail Component', () => {
    let comp: LigneOperationDetailComponent;
    let fixture: ComponentFixture<LigneOperationDetailComponent>;
    const route = ({ data: of({ ligneOperation: new LigneOperation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SoftricTestModule],
        declarations: [LigneOperationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LigneOperationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LigneOperationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ligneOperation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ligneOperation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
