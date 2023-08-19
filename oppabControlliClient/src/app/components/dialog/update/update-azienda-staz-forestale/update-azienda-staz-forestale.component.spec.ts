import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAziendaStazForestaleComponent } from './update-azienda-staz-forestale.component';

describe('UpdateAziendaStazForestaleComponent', () => {
  let component: UpdateAziendaStazForestaleComponent;
  let fixture: ComponentFixture<UpdateAziendaStazForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateAziendaStazForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAziendaStazForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
