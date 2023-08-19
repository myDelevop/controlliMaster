import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficheComponent } from './anagrafiche.component';

describe('AnagraficheComponent', () => {
  let component: AnagraficheComponent;
  let fixture: ComponentFixture<AnagraficheComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnagraficheComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
