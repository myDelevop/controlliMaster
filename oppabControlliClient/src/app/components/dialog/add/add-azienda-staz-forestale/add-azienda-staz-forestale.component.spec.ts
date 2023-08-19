import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAziendaStazForestaleComponent } from './add-azienda-staz-forestale.component';

describe('AddAziendaStazForestaleComponent', () => {
  let component: AddAziendaStazForestaleComponent;
  let fixture: ComponentFixture<AddAziendaStazForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAziendaStazForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAziendaStazForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
