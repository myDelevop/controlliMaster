import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestioneAnagraficheComponent } from './gestione-anagrafiche.component';

describe('GestioneAnagraficheComponent', () => {
  let component: GestioneAnagraficheComponent;
  let fixture: ComponentFixture<GestioneAnagraficheComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestioneAnagraficheComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestioneAnagraficheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
