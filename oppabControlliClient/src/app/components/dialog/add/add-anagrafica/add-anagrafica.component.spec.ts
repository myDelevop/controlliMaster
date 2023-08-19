import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAnagraficaComponent } from './add-anagrafica.component';

describe('AddAnagraficaComponent', () => {
  let component: AddAnagraficaComponent;
  let fixture: ComponentFixture<AddAnagraficaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddAnagraficaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddAnagraficaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
