import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteAnagraficaComponent } from './delete-anagrafica.component';

describe('DeleteAnagraficaComponent', () => {
  let component: DeleteAnagraficaComponent;
  let fixture: ComponentFixture<DeleteAnagraficaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteAnagraficaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteAnagraficaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
