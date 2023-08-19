import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssegnamentoControlliComponent } from './assegnamento-controlli.component';

describe('AssegnamentoControlliComponent', () => {
  let component: AssegnamentoControlliComponent;
  let fixture: ComponentFixture<AssegnamentoControlliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssegnamentoControlliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssegnamentoControlliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
