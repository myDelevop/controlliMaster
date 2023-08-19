import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtenteGruppoComponent } from './utente-gruppo.component';

describe('UtenteGruppoComponent', () => {
  let component: UtenteGruppoComponent;
  let fixture: ComponentFixture<UtenteGruppoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtenteGruppoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtenteGruppoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
