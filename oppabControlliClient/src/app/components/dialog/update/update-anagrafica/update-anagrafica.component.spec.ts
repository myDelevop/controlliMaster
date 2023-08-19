import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAnagraficaComponent } from './update-anagrafica.component';

describe('UpdateAnagraficaComponent', () => {
  let component: UpdateAnagraficaComponent;
  let fixture: ComponentFixture<UpdateAnagraficaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateAnagraficaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAnagraficaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
