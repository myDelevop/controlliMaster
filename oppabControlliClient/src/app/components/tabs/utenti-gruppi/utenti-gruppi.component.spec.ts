import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtentiGruppiComponent } from './utenti-gruppi.component';

describe('UtentiGruppiComponent', () => {
  let component: UtentiGruppiComponent;
  let fixture: ComponentFixture<UtentiGruppiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtentiGruppiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtentiGruppiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
