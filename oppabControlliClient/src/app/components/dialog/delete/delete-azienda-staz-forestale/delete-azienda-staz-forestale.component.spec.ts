import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteAziendaStazForestaleComponent } from './delete-azienda-staz-forestale.component';

describe('DeleteAziendaStazForestaleComponent', () => {
  let component: DeleteAziendaStazForestaleComponent;
  let fixture: ComponentFixture<DeleteAziendaStazForestaleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteAziendaStazForestaleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteAziendaStazForestaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
